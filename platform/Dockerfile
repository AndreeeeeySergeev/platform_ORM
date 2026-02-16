FROM eclipse-temurin:17-jre-alpine

# Создаём директорию для приложения
WORKDIR /app


COPY target/platform-orm.jar /app/platform-orm.jar


EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "platform-orm.jar"]