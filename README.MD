# Garden of Glass Skybox Mod

一个为Minecraft 1.7.10制作的天空盒模组，提供美丽的星空、行星和彩虹效果。

## 功能特性

- ✨ 美丽的星空渲染效果
- 🌍 多种行星纹理显示
- 🌈 动态彩虹效果
- 🌙 太阳和月亮渲染
- 🎨 可自定义的天空盒纹理

## 构建说明

### 自动构建 (推荐)

本项目配置了GitHub Actions自动构建，每次推送代码都会自动构建模组。

### 手动构建

#### 环境要求
- JDK 8
- Gradle (包含在项目中)

#### 构建步骤

1. **设置开发环境**
   ```bash
   # Linux/Mac
   ./gradlew setupDecompWorkspace
   
   # Windows
   ./gradlew.bat setupDecompWorkspace
   ```

2. **编译模组**
   ```bash
   # Linux/Mac
   ./gradlew build
   
   # Windows
   ./gradlew.bat build
   ```

3. **构建产物**
   编译完成后，JAR文件将位于 `build/libs/` 目录中。

### 运行测试

```bash
# 运行客户端
./gradlew runClient

# 运行服务端
./gradlew runServer
```

## 模组信息

- **模组ID**: gogskybox
- **模组名称**: Garden of Glass Skybox
- **版本**: 1.1-3
- **支持版本**: Minecraft 1.7.10
- **作者**: yCENzh
- **依赖**: Minecraft Forge 1.7.10

## 安装说明

1. 确保已安装Minecraft Forge 1.7.10
2. 将构建的JAR文件放入Minecraft的 `mods` 文件夹
3. 启动游戏即可享受美丽的天空效果

## 开发说明

本模组基于RetroFuturaGradle构建系统，适用于1.7.10版本的模组开发。

### 项目结构

```
src/main/
├── java/vazkii/skybox/          # 主要代码
│   ├── GoGSkybox.java          # 主模组类
│   ├── ModEventHandler.java    # 事件处理器
│   ├── SkyblockSkyRenderer.java # 天空渲染器
│   └── ModMethodHandles.java   # 反射工具类
└── resources/
    ├── mcmod.info              # 模组信息文件
    └── assets/gogskybox/textures/ # 纹理资源
```

### 技术特点

- 使用现代的Gradle构建系统
- 支持GitHub Actions自动化构建
- 包含完整的纹理资源
- 使用反射技术访问Minecraft内部类

## 问题排查

如果构建遇到问题，请尝试：

1. 清理构建缓存：`./gradlew clean`
2. 重新设置工作空间：`./gradlew setupDecompWorkspace`
3. 检查JDK版本是否为8
4. 确保网络连接正常（下载依赖需要）

## 许可证

查看 [LICENSE](LICENSE) 文件了解详情。

## 贡献

欢迎提交Issue和Pull Request来改进这个模组！
