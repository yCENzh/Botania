# GogSkybox 模组构建修复方案

## 问题诊断

从最新的构建日志分析，主要问题是：

1. **downloadClient 任务失败**: 无法从 `http://s3.amazonaws.com/Minecraft.Download/versions/1.7.10/1.7.10.jar` 下载客户端JAR
2. **downloadServer 任务失败**: 无法从 `http://s3.amazonaws.com/Minecraft.Download/versions/1.7.10/minecraft_server.1.7.10.jar` 下载服务端JAR
3. **网络资源失效**: 原始的Amazon S3链接已经失效，返回404错误

## 解决方案

### 1. 创建自定义JAR下载任务 (`fixMinecraftJars`)

这个任务会：
- 从Mojang官方launcher APIs下载正确的1.7.10 JAR文件
- 客户端JAR: `https://launcher.mojang.com/v1/objects/30bfe37a8db404db11c7edf02cb5165817afb4d9/client.jar`
- 服务端JAR: `https://launcher.mojang.com/v1/objects/48820c84cb1b98ba8e4622cb9ac7509d4b5c01bd/server.jar`
- 将文件放置在ForgeGradle期望的缓存目录中

### 2. 重写downloadClient和downloadServer任务

使用 `gradle.taskGraph.whenReady` 来：
- 清除原始任务的动作
- 添加对我们自定义下载任务的依赖
- 验证JAR文件是否成功下载

### 3. 改进的GitHub Actions工作流

- 分步骤执行构建过程
- 添加详细的调试信息
- 使用 `continue-on-error` 来处理部分失败
- 自动上传构建工件
- 支持GitHub Release创建

## 文件修改摘要

### `build.gradle`
- 添加了 `fixMinecraftJars` 任务
- 重写了 `downloadClient` 和 `downloadServer` 任务
- 改进了错误处理和日志记录

### `.github/workflows/build.yml`
- 优化了构建步骤顺序
- 添加了构建结果验证
- 配置了工件上传

## 使用方法

### 本地构建
```bash
# 清理之前的构建
./gradlew clean

# 下载Minecraft JAR文件
./gradlew fixMinecraftJars

# 设置开发环境（可选）
./gradlew setupDecompWorkspace

# 构建模组
./gradlew build
```

### GitHub Actions
只需推送到main或master分支，GitHub Actions会自动：
1. 设置Java 8环境
2. 缓存Gradle依赖
3. 下载必需的Minecraft JAR文件
4. 构建模组
5. 上传JAR文件作为工件

## 预期结果

成功构建后，您将得到：
- `build/libs/GogSkybox-1.0.1.jar` - 主模组文件
- 自动上传到GitHub Actions工件
- 如果推送标签，还会创建GitHub Release

## 技术细节

### JAR文件位置
```
~/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/
├── minecraft-1.7.10.jar          # 客户端JAR
└── minecraft_server-1.7.10.jar   # 服务端JAR
```

### 官方JAR文件哈希
- 客户端: `30bfe37a8db404db11c7edf02cb5165817afb4d9`
- 服务端: `48820c84cb1b98ba8e4622cb9ac7509d4b5c01bd`

这些哈希值来自Mojang官方launcher manifest，确保下载的文件是正确和安全的。

## 故障排除

如果构建仍然失败：

1. **检查网络连接**: 确保能够访问 `launcher.mojang.com`
2. **清理缓存**: 运行 `./gradlew clean` 和删除 `~/.gradle/caches/minecraft`
3. **手动下载**: 可以手动下载JAR文件并放置在正确位置
4. **查看日志**: GitHub Actions提供详细的构建日志

## 作者信息

- 模组作者: yCENzh
- 版本: 1.0.1
- 基于: Botania的水晶花园天空盒功能