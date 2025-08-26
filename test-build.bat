@echo off
echo Testing GogSkybox build...
echo.

REM Check if Java is available
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install JDK 8 and add it to your PATH
    pause
    exit /b 1
)

echo Java found, starting build test...
echo.

REM Try to build
echo Running: gradlew setupDecompWorkspace
call gradlew.bat setupDecompWorkspace
if errorlevel 1 (
    echo ERROR: Failed to setup workspace
    pause
    exit /b 1
)

echo.
echo Running: gradlew build
call gradlew.bat build
if errorlevel 1 (
    echo ERROR: Build failed
    pause
    exit /b 1
)

echo.
echo Build test completed successfully!
echo.

if exist "build\libs\*.jar" (
    echo Generated jar files:
    dir /b "build\libs\*.jar"
    echo.
    echo The mod is ready for use!
) else (
    echo WARNING: No jar files found in build\libs\
)

echo.
echo You can now push this to GitHub and the Actions will build it automatically.
pause