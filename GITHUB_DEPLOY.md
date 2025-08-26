# GitHub Actions 自动构建说明

## 🚀 如何使用 GitHub Actions 自动构建

### 步骤1: 推送代码到GitHub
```bash
# 如果还未初始化Git仓库
git init
git add .
git commit -m "Initial commit: GogSkybox mod"

# 添加远程仓库（替换为您的GitHub仓库地址）
git remote add origin https://github.com/yourusername/gogskybox.git
git push -u origin main
```

### 步骤2: GitHub Actions 自动构建
一旦代码被推送到GitHub，Actions会自动：

1. **设置环境**: 配置JDK 8和Gradle
2. **缓存依赖**: 缓存Gradle文件以加速构建
3. **设置工作空间**: 运行`setupDecompWorkspace`
4. **构建模组**: 运行`gradle build`
5. **上传产物**: 将构建好的jar文件上传为artifact

### 步骤3: 下载构建好的模组
1. 进入您的GitHub仓库
2. 点击"Actions"标签页
3. 选择最新的构建任务
4. 在"Artifacts"部分下载`GogSkybox-<build-number>`
5. 解压获得jar文件

### 步骤4: 安装模组
1. 将jar文件放入`.minecraft/mods`文件夹
2. 启动Minecraft 1.7.10 + Forge
3. 享受美丽的天空盒！

## 🏷️ 创建发布版本
如果您想创建正式发布：

```bash
# 创建标签
git tag v1.0.0
git push origin v1.0.0
```

这将触发发布工作流，自动创建GitHub Release并附加jar文件。

## ⚙️ 配置选项
构建完成后，模组会在游戏配置目录生成：
- `config/GogSkybox.cfg`: 配置文件
- `skybox.enabled=true`: 启用/禁用天空盒
- `skybox.allDimensions=false`: 是否在所有维度启用

## 🔧 自定义构建
如果需要修改构建配置，编辑以下文件：
- `.github/workflows/build.yml`: GitHub Actions工作流
- `build.gradle`: Gradle构建配置
- `gradle.properties`: Gradle属性（如果需要）

## 📋 构建状态
您可以在README中添加构建状态徽章：

```markdown
![Build Status](https://github.com/yourusername/gogskybox/workflows/Build%20GogSkybox%20Mod/badge.svg)
```

## 🐛 常见问题
1. **构建失败**: 检查Java版本和Gradle配置
2. **找不到jar文件**: 确保构建成功完成
3. **模组不工作**: 检查Minecraft版本和Forge版本兼容性

## 🎯 下一步
- 推送代码到GitHub
- 等待自动构建完成
- 下载并测试模组
- 享受美丽的水晶花园天空！