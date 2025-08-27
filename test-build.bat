@echo off
REM 本地构建测试脚本 (Windows版本)
REM 这个脚本可以在本地测试构建过程，确保GitHub Actions能够正常工作

echo 🚀 开始Garden of Glass Skybox模组构建测试...

REM 检查Java版本
echo 📋 检查Java版本...
java -version
if %ERRORLEVEL% neq 0 (
    echo ❌ Java未正确安装或未添加到PATH
    pause
    exit /b 1
)

REM 检查Gradle wrapper
echo 📋 检查Gradle wrapper...
if not exist "gradlew.bat" (
    echo ❌ gradlew.bat 文件不存在
    pause
    exit /b 1
)

REM 清理之前的构建
echo 🧹 清理之前的构建...
call gradlew.bat clean --no-daemon
if %ERRORLEVEL% neq 0 (
    echo ❌ 清理构建失败
    pause
    exit /b 1
)

REM 设置工作空间
echo ⚙️ 设置反混淆工作空间...
set GRADLE_OPTS=-Xmx4G -Dorg.gradle.daemon=false
call gradlew.bat setupDecompWorkspace --no-daemon --stacktrace --info
if %ERRORLEVEL% neq 0 (
    echo ❌ 工作空间设置失败
    pause
    exit /b 1
)

REM 构建模组
echo 🔨 构建模组...
call gradlew.bat build --no-daemon --stacktrace --info
if %ERRORLEVEL% neq 0 (
    echo ❌ 模组构建失败
    pause
    exit /b 1
)

REM 检查构建结果
echo 📦 检查构建结果...
if exist "build\libs" (
    echo ✅ build\libs 目录存在
    
    echo 📋 构建的JAR文件列表:
    for %%f in (build\libs\*.jar) do (
        echo   - %%~nxf
    )
    
    REM 检查主JAR文件（简化版，不包含复杂的文件过滤）
    for %%f in (build\libs\*.jar) do (
        set MAIN_JAR=%%f
        goto :check_jar
    )
    
    :check_jar
    if exist "%MAIN_JAR%" (
        echo ✅ 主JAR文件: %MAIN_JAR%
        
        REM 简单验证JAR内容（Windows下jar命令验证）
        echo 📋 验证JAR内容...
        jar -tf "%MAIN_JAR%" | findstr "mcmod.info" >nul
        if %ERRORLEVEL% equ 0 (
            echo   ✅ mcmod.info 存在
        ) else (
            echo   ❌ mcmod.info 不存在
        )
        
        jar -tf "%MAIN_JAR%" | findstr "vazkii/skybox" >nul
        if %ERRORLEVEL% equ 0 (
            echo   ✅ 模组类文件存在
        ) else (
            echo   ❌ 模组类文件不存在
        )
        
        jar -tf "%MAIN_JAR%" | findstr "assets/gogskybox" >nul
        if %ERRORLEVEL% equ 0 (
            echo   ✅ 资源文件存在
        ) else (
            echo   ❌ 资源文件不存在
        )
    ) else (
        echo ❌ 没有找到主JAR文件
        pause
        exit /b 1
    )
) else (
    echo ❌ build\libs 目录不存在
    pause
    exit /b 1
)

echo.
echo 🎉 构建测试完成！
echo 📦 主要产物: %MAIN_JAR%
echo.
echo 💡 提示：
echo    - 将JAR文件复制到Minecraft的mods文件夹中使用
echo    - 确保已安装Minecraft Forge 1.7.10
echo    - 如果在GitHub Actions中运行，产物会被自动上传
echo.
pause