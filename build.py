import os
import subprocess
import sys

def build_jar():
    """Собирает JAR файл проекта с помощью Gradle"""
    
    print("=" * 50)
    print("🔨 TownyCenturiesWars JAR Builder")
    print("=" * 50)
    print()
    
    # Проверяем наличие gradlew.bat
    if not os.path.exists("gradlew.bat"):
        print("❌ Ошибка: gradlew.bat не найден!")
        print("   Убедитесь, что скрипт находится в корне проекта")
        sys.exit(1)
    
    print("✅ Найден gradlew.bat")
    print()
    
    # Очистка старых сборок
    print("🧹 Очистка старых файлов...")
    try:
        subprocess.run(["gradlew.bat", "clean"], check=True)
        print("✅ Очистка завершена")
    except subprocess.CalledProcessError as e:
        print(f"⚠️  Ошибка при очистке: {e}")
        print("   Продолжаем сборку...")
    
    print()
    print("🔨 Сборка проекта...")
    print()
    
    # Сборка JAR
    try:
        result = subprocess.run(["gradlew.bat", "build"], check=True)
        
        print()
        print("=" * 50)
        print("✅ СБОРКА УСПЕШНА!")
        print("=" * 50)
        print()
        
        # Ищем собранный JAR
        jar_path = "build/libs/TownyCenturiesWars-1.0.0.jar"
        if os.path.exists(jar_path):
            file_size = os.path.getsize(jar_path) / (1024 * 1024)  # В МБ
            print(f"📦 JAR файл создан: {jar_path}")
            print(f"   Размер: {file_size:.2f} МБ")
            print()
            print("✨ Готово к использованию!")
        else:
            print("⚠️  JAR файл не найден в ожидаемом месте")
            print("   Проверьте папку build/libs/")
        
    except subprocess.CalledProcessError as e:
        print()
        print("=" * 50)
        print("❌ ОШИБКА ПРИ СБОРКЕ!")
        print("=" * 50)
        print()
        print(f"Код ошибки: {e.returncode}")
        print()
        print("📝 Возможные решения:")
        print("   1. Убедитесь, что Java установлена: java -version")
        print("   2. Проверьте путь JAVA_HOME")
        print("   3. Проверьте интернет-соединение (нужно скачать зависимости)")
        sys.exit(1)
    
    print()
    input("Нажмите Enter для выхода...")

if __name__ == "__main__":
    build_jar()
