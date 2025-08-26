# GogSkybox - Garden of Glass Skybox Mod

一个为Minecraft 1.7.10版本制作的轻量级天空盒模组，从Botania模组中提取了水晶花园(Garden of Glass)的美丽天空效果。

## ✨ 功能特色

- **美丽的天空盒**: 包含6个彩色行星、动态光线效果和彩虹
- **轻量级设计**: 只保留天空盒功能，不影响游戏其他机制
- **高度可配置**: 可以自定义启用/禁用及应用范围
- **独立运行**: 不依赖Botania或其他任何模组

## 🎮 效果预览

安装后您将看到：
- 天空中漂浮的彩色行星（6个不同颜色和大小的行星）
- 随时间变化的动态光线效果
- 美丽的彩虹效果
- 动态旋转的星空
- 保留原版的太阳、月亮和日出日落效果

## 📦 安装方法

### 自动构建（推荐）
1. 在GitHub上Fork或Clone本项目
2. 推送到您的GitHub仓库
3. GitHub Actions会自动构建jar文件
4. 在Actions页面下载构建好的模组文件

### 手动构建
```bash
# 克隆项目
git clone <your-repo-url>
cd <project-directory>

# 构建模组
./gradlew setupDecompWorkspace
./gradlew build

# jar文件将生成在build/libs/目录中
```

### 安装到游戏
1. 确保安装了Minecraft 1.7.10和对应版本的Forge
2. 将生成的jar文件放入`.minecraft/mods`文件夹
3. 启动游戏即可

## ⚙️ 配置选项

首次运行后会在`.minecraft/config`文件夹生成`GogSkybox.cfg`配置文件：

- `skybox.enabled`: 是否启用天空盒（默认：true）
- `skybox.allDimensions`: 是否在所有维度启用（默认：false，仅主世界）

## 🔧 GitHub Actions 自动构建

本项目已配置GitHub Actions，每次推送代码时会自动：
1. 设置JDK 8环境
2. 配置Gradle缓存
3. 运行`setupDecompWorkspace`
4. 构建模组jar文件
5. 上传构建产物

构建完成后，您可以在Actions页面下载构建好的jar文件

## 📋 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── gogsky/
│   │       ├── core/           # 模组主类
│   │       ├── config/         # 配置处理
│   │       ├── handler/        # 事件处理器
│   │       ├── proxy/          # 客户端/服务端代理
│   │       └── render/         # 天空盒渲染器
│   └── resources/
│       ├── assets/gogskybox/textures/misc/  # 纹理资源
│       └── mcmod.info          # 模组信息
├── .github/workflows/          # GitHub Actions配置
├── build.gradle               # 构建配置
└── README.md                 # 项目说明
```

## 🎯 技术说明

- **基于Botania原始代码**: 保持了原版天空盒的所有视觉效果
- **1.7.10兼容**: 使用正确的混淆名称进行反射调用
- **客户端专用**: 只需在客户端安装，服务器无需安装
- **高性能**: 轻量级设计，对游戏性能影响极小

## ⚠️ 注意事项

- 如果同时安装了Botania模组，建议在配置中禁用其Garden of Glass天空盒功能
- 某些光影包可能会覆盖天空效果
- 在虚空中（Y坐标-2以下）天空效果会逐渐减弱

## 🙏 致谢

- 感谢 [Vazkii](https://github.com/Vazkii) 创作了美丽的原始天空盒设计
- 原始代码来自 [Botania模组](https://github.com/Vazkii/Botania)
- 所有纹理和视觉效果版权归原作者所有

## 📜 许可证

本模组基于Botania的开源代码制作，遵循Botania License条款。