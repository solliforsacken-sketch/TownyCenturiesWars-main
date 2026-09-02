# TownyCenturiesWars - Инструкция по сборке

## Требования

- **Java Development Kit (JDK) 11 или выше**
- **Maven 3.6+**

## Проверка установки

### Linux/macOS
```bash
java -version
mvn -version
```

### Windows
```cmd
java -version
mvn -version
```

Если команды не работают, установите:
- JDK: https://www.oracle.com/java/technologies/downloads/
- Maven: https://maven.apache.org/download.cgi

## Сборка

### Способ 1: Автоматическая сборка (рекомендуется)

**Linux/macOS:**
```bash
chmod +x build.sh
./build.sh
```

**Windows:**
```cmd
build.bat
```

### Способ 2: Ручная сборка через Maven

```bash
mvn clean package
```

## Результат

После успешной сборки файл будет расположен по пути:
```
target/towny-centuries-wars-1.0.0.jar
```

## Установка плагина

1. **Скопируйте JAR в папку plugins:**
   ```bash
   cp target/towny-centuries-wars-1.0.0.jar /path/to/server/plugins/
   ```

2. **Перезагрузите сервер:**
   - В консоли сервера: `/reload`
   - Или перезагрузитесь полностью

3. **Проверьте загрузку:**
   - В консоли должно появиться: `✓ TownyCenturiesWars Plugin успешно загружен!`
   - Используйте `/century` для проверки

4. **Отредактируйте конфиги:**
   - Конфиги создадутся в: `plugins/TownyCenturiesWars/`
   - Отредактируйте необходимые параметры
   - Перезагрузитесь: `/reload`

## Кастомная сборка

Если вы хотите изменить параметры сборки, отредактируйте `pom.xml`:

```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

### Сборка без тестов (быстрее):
```bash
mvn clean package -DskipTests
```

### Сборка с отладкой:
```bash
mvn clean package -X
```

### Сборка конкретной верси��:
```bash
mvn clean package -Drevision=1.0.1
```

## Решение проблем

### Ошибка: "Maven not found"
- Установите Maven с https://maven.apache.org/download.cgi
- Добавьте Maven в PATH

### Ошибка: "Java version not supported"
- Проверьте версию JDK: `java -version`
- Должна быть 11 или выше
- Обновите JDK на https://www.oracle.com/java/technologies/downloads/

### Ошибка: "Cannot find symbol"
- Убедитесь, что все зависимости загружены: `mvn dependency:resolve`
- Попробуйте очистить кэш: `mvn clean`

### Ошибка при компиляции исходников
- Проверьте, что все файлы скачаны из репозитория
- Попробуйте: `mvn clean install`

## Структура проекта для сборки

```
towny-centuries-wars/
├── pom.xml                    # Конфигурация Maven
├── build.sh                   # Скрипт сборки (Linux/macOS)
├── build.bat                  # Скрипт сборки (Windows)
├── BUILD_INSTRUCTIONS.md      # Этот файл
├── src/
│   ├── main/
│   │   ├── java/             # Исходный код Java
│   │   └── resources/        # Ресурсы (plugin.yml и т.д.)
│   └── test/                 # Тесты (опционально)
├── target/                    # Выходная папка (создается при сборке)
│   └── towny-centuries-wars-1.0.0.jar  # Готовый плагин
└── README.md
```

## Дополнительные команды Maven

```bash
# Очистка
mvn clean

# Сборка
mvn compile

# Упаковка
mvn package

# Установка в локальный репозиторий
mvn install

# Проверка зависимостей
mvn dependency:tree

# Форматирование кода
mvn fmt:format
```

## Создание собственной версии

Чтобы создать версию с другим названием, отредактируйте `pom.xml`:

```xml
<artifactId>towny-centuries-wars</artifactId>
<version>1.0.1</version>
<name>TownyCenturiesWars</name>
```

Тогда JAR будет называться: `towny-centuries-wars-1.0.1.jar`

## Автоматическая сборка (CI/CD)

Для автоматической сборки при каждом коммите используйте GitHub Actions.
Создайте файл `.github/workflows/build.yml`:

```yaml
name: Build

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'
    - name: Build with Maven
      run: mvn clean package
    - name: Upload artifact
      uses: actions/upload-artifact@v2
      with:
        name: plugin-jar
        path: target/*.jar
```

## Поддержка

Если у вас есть проблемы при сборке:
1. Проверьте версии Java и Maven
2. Очистите кэш: `mvn clean`
3. Обновите зависимости: `mvn dependency:resolve`
4. Создайте Issue на GitHub

## Лицензия

MIT
