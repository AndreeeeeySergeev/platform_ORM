**Учебная платформа на Spring Boot с использованием JPA/Hibernate для управления пользователями,
курсами и записями на обучение.**
<pre>
1. Технические требования
   Перед началом работы убедитесь, что у вас установлены:
Java 21
Maven 3.8+
Spring boot 4.0.2
PostgreSQL 17+ (или Docker для запуска через compose)
Hibernate
Lombok (для сокращения шаблонного кода).
Git (для клонирования репозитория)
</pre>
2. Структура проекта
Проект организован по стандартному Maven‑шаблону:

<pre>
src/main/java/platform
├── dto/               # Объекты передачи данных (DTO)
├── entity/            # сущности
├── exception/         # Пользовательские исключения и обработчики
├── repository/        # JPA-репозитории
├── service/           #  сервисы
└── resources/         # Конфигурация
</pre>

<pre>
src/test/java/
├── service/           # Тесты сервисов (юнит-тесты)
</pre>

Основные сущности (entity):
<pre>
User — пользователь (id, firstName, lastName, email, telephone, role);
Course — курс (id, title, description, startDate);
Category — категория курса (id, name).
</pre>

3. Установка и запуск
   Вариант 1. Локальный запуск (без Docker)
   Клонируйте репозиторий:
<pre>
bash
git clone https://github.com/AndreeeeeySergeev/platform_ORM.git
cd platform_ORM
</pre>
Настройте БД:
Создайте базу данных platform_db в PostgreSQL.

В файле src/main/resources/application.properties укажите:
<pre>
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/platform_db
spring.datasource.username=your_username
spring.datasource.password=your_password
</pre>
Соберите проект:
<pre>
bash
mvn clean package
</pre>
Запустите приложение:
<pre>
bash
java -jar target/platform-orm.jar
</pre>
Приложение будет доступно по адресу: http://localhost:8080.

Вариант 2. Запуск через Docker Compose
Убедитесь, что установлены Docker и Docker Compose.

В корне проекта выполните:
<pre>
bash
docker-compose up -d
</pre>
Приложение и PostgreSQL запустятся автоматически. Доступ:

Приложение: http://localhost:8080

4. Тестирование

Запуск тестов через Docker
Если используется Docker Compose, тесты можно запустить в контейнере:
<pre>
bash
docker-compose run app mvn test
</pre>
6. Настройка окружения
   application.properties
   Основные параметры:

<pre>
properties
# Подключение к БД
spring.datasource.url=jdbc:postgresql://db:5432/platform_db
spring.datasource.username=platform_user
spring.datasource.password=platform_pass
</pre>

<pre>
# Hibernate
`spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always`
</pre>

<pre>
# Порт приложения
server.port=8080
Docker Compose (docker-compose.yml)
</pre>

Настраивает:

приложение (app);

PostgreSQL (db);

pgAdmin (pgadmin).


7. Дополнительные команды
   Собрать JAR: mvn clean package

Запустить только БД: `docker-compose up -d db`

Посмотреть логи: `docker-compose logs app`

Остановить сервисы: `docker-compose down`


