# Дипломный проект по профессии «Тестировщик»
## Запуск приложения
1. Необходимо предварительно установить и настроить Intellij Idea, Docker, плагин Docker к Intellij Idea
2. Скопировать данный репозиторий через git clone
3. Открыть проект в Intellij Idea
4. Запустить контейнеры командой docker-compose up
5. Запустить SUT контейнер в отдельном окне терминала.
    * При тестировании на mysql командой `java -jar artifacts/aqa-shop.jar --spring.profiles.active=mysql`
    * При тестировании на postgres командой `java -jar artifacts/aqa-shop.jar --spring.profiles.active=postgres`
    * Если при запуске джарника появится ошибка запуска, то необходимо добавить подключение к БД командой `docker container run -d -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=app -e MYSQL_USER=app -e MYSQL_PASSWORD=pass mysql`
6. Приложение должно запуститься по адресу http://localhost:8080/

## Запуск автотестов
В терминале запустить автотесты командой:
 * `./gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/mysql` - для MySQL
 * `./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/postgres` - для PostgreSQL

## Формирование отчета Allure
В терминале запустить запустить построение отчета командой `.\gradlew allureServe`

## Документация

[План тестирования](https://github.com/ewan132/QAATDiplom/blob/main/Documents/Plan.md)

[Отчет по тестированию](https://github.com/ewan132/QAATDiplom/blob/main/Documents/Report.md)

[Отчет Allure](https://github.com/ewan132/QAATDiplom/blob/main/Documents/Allure%20report.md)

[Отчет по итогам автоматизации](https://github.com/ewan132/QAATDiplom/blob/main/Documents/Summary.md)
