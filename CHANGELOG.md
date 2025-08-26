# 变更日志

## [1.0.1] - 2025-08-26

### 修复
- 修复GitHub Actions构建失败问题
- 升级GitHub Actions到v4版本（解决deprecated警告）
- 更新模组作者信息为yCENzh

### 技术变更
- `actions/checkout@v3` → `actions/checkout@v4`
- `actions/setup-java@v3` → `actions/setup-java@v4` 
- `actions/cache@v3` → `actions/cache@v4`
- `actions/upload-artifact@v3` → `actions/upload-artifact@v4`

## [1.0.0] - 2025-08-26

### 新增
- 初始发布
- 完整的Garden of Glass天空盒功能
- 支持6个彩色行星渲染
- 动态光线效果
- 彩虹效果
- 动态星空效果
- 可配置的启用/禁用选项
- 可配置的维度应用范围
- GitHub Actions自动构建支持

### 功能
- 在主世界默认启用美丽的天空盒
- 配置文件支持自定义设置
- 轻量级设计，不依赖其他模组
- 完全兼容Minecraft 1.7.10 + Forge