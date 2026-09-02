@echo off
REM Build script для TownyCenturiesWars (Windows)

echo === TownyCenturiesWars Build Script ===
echo Сборка плагина...

REM Проверка Maven
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Maven не установлен!
    echo Установите Maven с https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Очистка предыдущей сборки
echo Очищаю предыдущую сборку...
call mvn clean

REM Сборка
echo Собираю плагин...
call mvn package -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo ✅ Сборка успешна!
    echo JAR файл: target\towny-centuries-wars-1.0.0.jar
    echo.
    echo Как установить:
    echo 1. Скопируйте target\towny-centuries-wars-1.0.0.jar в папку plugins\
    echo 2. Перезагрузите сервер: /reload или перезагрузитесь полностью
    echo 3. Отредактируйте конфиги в plugins\TownyCenturiesWars\
    pause
) else (
    echo ❌ Ошибка при сборке!
    pause
    exit /b 1
)