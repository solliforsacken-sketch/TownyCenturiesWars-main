#!/bin/bash
# Build script для TownyCenturiesWars

echo "=== TownyCenturiesWars Build Script ==="
echo "Сборка плагина..."

# Проверка Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven не установлен!"
    echo "Установите Maven с https://maven.apache.org/download.cgi"
    exit 1
fi

# Очистка предыдущей сборки
echo "Очищаю предыдущую сборку..."
mvn clean

# Сборка
echo "Собираю плагин..."
mvn package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Сборка успешна!"
    echo "JAR файл: target/towny-centuries-wars-1.0.0.jar"
    echo ""
    echo "Как установить:"
    echo "1. Скопируйте target/towny-centuries-wars-1.0.0.jar в папку plugins/"
    echo "2. Перезагрузите сервер: /reload или перезагрузитесь полностью"
    echo "3. Отредактируйте конфиги в plugins/TownyCenturiesWars/"
else
    echo "❌ Ошибка при сборке!"
    exit 1
fi