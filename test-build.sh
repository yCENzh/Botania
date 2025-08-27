#!/bin/bash
# 本地构建测试脚本
# 这个脚本可以在本地测试构建过程，确保GitHub Actions能够正常工作

set -e  # 遇到错误时退出

echo "🚀 开始Garden of Glass Skybox模组构建测试..."

# 检查Java版本
echo "📋 检查Java版本..."
java -version

# 检查Gradle wrapper
echo "📋 检查Gradle wrapper..."
if [ ! -f "./gradlew" ]; then
    echo "❌ gradlew 文件不存在"
    exit 1
fi

# 设置Gradle权限（Linux/Mac）
if [[ "$OSTYPE" == "linux-gnu"* ]] || [[ "$OSTYPE" == "darwin"* ]]; then
    chmod +x ./gradlew
fi

# 清理之前的构建
echo "🧹 清理之前的构建..."
./gradlew clean --no-daemon

# 设置工作空间
echo "⚙️  设置反混淆工作空间..."
export GRADLE_OPTS="-Xmx4G -Dorg.gradle.daemon=false"
./gradlew setupDecompWorkspace --no-daemon --stacktrace --info

# 构建模组
echo "🔨 构建模组..."
./gradlew build --no-daemon --stacktrace --info

# 检查构建结果
echo "📦 检查构建结果..."
if [ -d "build/libs" ]; then
    echo "✅ build/libs 目录存在"
    
    # 列出所有JAR文件
    echo "📋 构建的JAR文件列表:"
    find build/libs -name "*.jar" -type f | while read jar; do
        echo "  - $(basename "$jar") ($(du -h "$jar" | cut -f1))"
    done
    
    # 检查主JAR文件
    MAIN_JAR=$(find build/libs -name "*.jar" -not -name "*sources*" -not -name "*javadoc*" | head -1)
    if [ -f "$MAIN_JAR" ]; then
        echo "✅ 主JAR文件: $(basename "$MAIN_JAR")"
        
        # 验证JAR内容
        echo "📋 验证JAR内容..."
        if jar -tf "$MAIN_JAR" | grep -q "mcmod.info"; then
            echo "  ✅ mcmod.info 存在"
        else
            echo "  ❌ mcmod.info 不存在"
        fi
        
        if jar -tf "$MAIN_JAR" | grep -q "vazkii/skybox"; then
            echo "  ✅ 模组类文件存在"
        else
            echo "  ❌ 模组类文件不存在"
        fi
        
        if jar -tf "$MAIN_JAR" | grep -q "assets/gogskybox"; then
            echo "  ✅ 资源文件存在"
        else
            echo "  ❌ 资源文件不存在"
        fi
        
    else
        echo "❌ 没有找到主JAR文件"
        exit 1
    fi
else
    echo "❌ build/libs 目录不存在"
    exit 1
fi

echo ""
echo "🎉 构建测试完成！"
echo "📦 主要产物: $MAIN_JAR"
echo ""
echo "💡 提示："
echo "   - 将JAR文件复制到Minecraft的mods文件夹中使用"
echo "   - 确保已安装Minecraft Forge 1.7.10"
echo "   - 如果在GitHub Actions中运行，产物会被自动上传"