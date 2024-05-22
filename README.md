# Техническое задание
В качестве задания предлагается выполнить проект "Расписание мероприятий". Этот сервис предоставляет возможность отслеживания мероприятий в КСК УНИКС.

## Основные сущности
- Пользователь – имя, фамилия, отчество, пароль, электронная почта, номер телефона, возраст.
- Мероприятие - название, статус, дата проведения, длительность, описание,категория, возрастной ценз.
- Зал - кол-во мест, номер.
- Билет - ряд, место в ряду.

## Основные страницы и формы
1. **Страница всех мероприятий** с пагинацией по 6 мероприятий на странице. Необходимо реализовать кнопку сортировки по дате проведения мероприятия, по  возрасту и по возрасту на убывание. Реализовать поиск мероприятия по названию и категориии в отдельной поисковой строке. Важно осуществить сортировку и поиск независимым друг от друга образом с возможностью вывести мероприятия сначала используя поиск,а затем применить одну из сортировок.

2. **Страница админа**. Доступна только для авторизованных пользователей....


3. **...*. 


4. **Страница пользователя** содержит его настройки - email, nick и аватарку. Каждый пользователь может смотреть только свою страницу. У пользователя должна быть возможность изменить email, nick и аватарку.


5. **Форма авторизации**. Состоит из поля логин и пароль. Дополнительно есть ссылка на форму регистрации. При успешной авторизации пользователь перебрасывается на исходную страницу, при неуспешной авторизации в форме выводятся сообщения об ошибках формы. Для авторизованных пользователей вместо этой формы должна показываться кнопка “Выйти”.


6. **Страница регистрации**. Любой пользователь может зарегистрироваться на сайте, заполнив форму с электронной почтой, никнеймом, аватаркой и паролем. Аватарка загружается на сервер и отображается рядом с вопросами и ответами пользователя. При неудачной регистрации в форме необходимо выводить сообщения об ошибках.

7.**...**

8.**Welcome Page** Первоначальная страница открывающаяся при переходе на сайт

9.**Навигация по сайту** создание шапки с основными кнопками для перехода на сайте.В шапке сайта находятся: логотип, кнопка купить билеты и кнопка входа в аккаунт.
10.**Страница ошибки** страница при отображении которой возникли ошибки со стороны пользователя или сервера

## Требования к проекту
1. Структура проекта должна быть понятна пользователям. Переходы по страницам осуществляются по ссылкам. Обработка форм должна осуществляться с редиректом.
2. Код проекта должен быть аккуратным и без дублирования. Наличие больших повторяющихся фрагментов кода или шаблонов могут быть причиной снижения баллов.
3. Верстка проекта должна быть выполнена с помощью языка гипертекстовой разметки текста html и css фреймворка Twitter Bootstrap.
4. Код приложения должен быть чувствителен к входным данным и выдавать соответствующие коды и тексты ошибок. Сообщение пользователям. Ответ сервера с кодом 500 может быть причиной снижения баллов.
5. Время генерации страницы не должно зависеть от объема данных в базе.
6. Страницы проекты не должны отдаваться более 1 секунды.

## Используемые технологии
- Код приложения пишется на **Java Spring Boot**.
- Приложение запускается под управлением сервера **...**.
- База данных – **MySQL/PostgreSQL**.
- Верстка выполняется с использованием **Twitter Bootstrap**.
- Взаимодействие интерфейса с пользователем обеспечивается **JavaScript/jQuery**.
