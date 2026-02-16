Учебная платформа на Spring Boot с использованием JPA/Hibernate для управления пользователями, курсами и записями на обучение.

1. Технические требования
   Перед началом работы убедитесь, что у вас установлены:
Java 21
Maven 3.8+
Spring boot 4.0.2
PostgreSQL 17+ (или Docker для запуска через compose)
Hibernate
Lombok (для сокращения шаблонного кода).
Git (для клонирования репозитория)

2. Структура проекта
Проект организован по стандартному Maven‑шаблону:

src/main/java/
├── controller/          # REST-контроллеры (API-эндпоинты)
├── dto/               # Объекты передачи данных (DTO)
├── exception/         # Пользовательские исключения и обработчики
├── model/             # Сущности БД (JPA-модели)
├── repository/          # JPA-репозитории
├── service/            # Бизнес-логика и сервисы
└── config/            # Конфигурация Spring (БД, MVC, Security)


src/test/java/
├── controller/         # Тесты контроллеров (интеграционные)
├── service/           # Тесты сервисов (юнит-тесты)
└── repository/          # Тесты репозиториев


src/main/resources/
├── application.properties  # Конфигурация приложения
├── db/migration/       # SQL-миграции (если используются)
└── static/              # Статические ресурсы (опционально)
Основные сущности (model):

User — пользователь (id, name, email, role);
Course — курс (id, title, description, teacherId);
Enrollment — запись на курс (user, course);
Category — категория курса (id, name).

3. Установка и запуск
   Вариант 1. Локальный запуск (без Docker)
   Клонируйте репозиторий:

`bash
git clone https://github.com/AndreeeeeySergeev/platform_ORM.git
cd platform_ORM`

Настройте БД:
Создайте базу данных platform_db в PostgreSQL.

В файле src/main/resources/application.properties укажите:

`properties
spring.datasource.url=jdbc:postgresql://localhost:5432/platform_db
spring.datasource.username=your_username
spring.datasource.password=your_password`

Соберите проект:

`bash
mvn clean package`

Запустите приложение:

`bash
java -jar target/platform-orm.jar`
Приложение будет доступно по адресу: http://localhost:8080.

Вариант 2. Запуск через Docker Compose
Убедитесь, что установлены Docker и Docker Compose.

В корне проекта выполните:

`bash
docker-compose up -d`
Приложение и PostgreSQL запустятся автоматически. Доступ:

Приложение: http://localhost:8080

4. Тестирование

Запуск тестов через Docker
Если используется Docker Compose, тесты можно запустить в контейнере:

`bash
docker-compose run app mvn test`

6. Настройка окружения
   application.properties
   Основные параметры:

properties
# Подключение к БД
`spring.datasource.url=jdbc:postgresql://db:5432/platform_db
spring.datasource.username=platform_user
spring.datasource.password=platform_pass`


# Hibernate
`spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always`


# Порт приложения
server.port=8080
Docker Compose (docker-compose.yml)

Настраивает:

приложение (app);

PostgreSQL (db);

pgAdmin (pgadmin).

7. Дополнительные команды
   Собрать JAR: mvn clean package

Запустить только БД: `docker-compose up -d db`

Посмотреть логи: `docker-compose logs app`

Остановить сервисы: `docker-compose down`


