# Техническое задание
В качестве задания предлагается выполнить проект "Расписание мероприятий". Этот сервис предоставляет возможность отслеживания мероприятий в КСК УНИКС.

## Основные сущности
- Пользователь – электронная почта, никнейм, пароль, аватарка.
- Мероприятие - название, статус, дата проведения, длительность, описание,категория.
- Зал - кол-во мест, название

## Основные страницы и формы
1. **Страница всех мероприятий** с пагинацией по 20 вопросов на странице. Необходимо реализовать сортировку по дате добавления и рейтингу (2 вида сортировки). В шапке сайта находятся: логотип, поисковая строка (для быстрого поиска по заголовку и содержимому вопроса), кнопка задать вопрос (доступна только авторизованным). В правой части шапки - юзерблок. Для авторизованного пользователя юзерблок содержит его ник, аватарку, ссылки на “выход” и на страницу с его профилем. Для неавторизованных - ссылки “войти” и “регистрация”. В правой колонке - информационные блоки “Популярные тэги” и “Лучшие пользователи” (описание ниже). Во всех листингах присутствуют кнопки “лайк/дизлайк”, позволяющие менять рейтинг вопроса.

  ![Листинг вопросов](../img/main.png)

2. **Страница админа**. Доступна только для авторизованных пользователей. В форма вводится заголовок, текст вопроса и теги, через запятую. С вопросом может быть связано не более 3 тегов. Для подсказки при выборе тега можно использовать готовый jquery плагин. Готовые django приложения для тегов использовать запрещается. При обработке формы обязательно проверка валидности данных. Если вопрос успешно добавлен - пользователя перебрасывает на страницу вопроса, если возникли ошибки - их нужно отобразить в форме.

  ![Страница добавления вопроса](../img/ask.png)

3. **Страница вопроса со списком ответов**. На странице вопроса можно добавить ответ. Ответы сортируются по рейтингу и дате добавления при равном рейтинге. Ответы разбиваются по 30 штук на странице. Форма добавления ответа находится на странице вопроса. Отображается только для авторизованных пользователей. После добавления ответа, автор вопроса должен получить email с уведомление от новом ответе. В этом письме должна быть ссылка для перехода на страницу вопроса. Автор вопроса может пометить один из ответов как правильный. Пользователи могут голосовать за вопросы и ответы с помощью лайков «+» или «–». Один пользователь может голосовать за 1 вопрос и ответ только 1 раз, однако может отменить свой выбор или переголосовать неограниченное число раз.

  ![Страница вопроса со списком ответов](../img/single.png)

4. **Страница пользователя** содержит его настройки - email, nick и аватарку. Каждый пользователь может смотреть только свою страницу. У пользователя должна быть возможность изменить email, nick и аватарку.

  ![Страница пользователя](../img/settings.png)

5. **Форма авторизации**. Состоит из поля логин и пароль. Дополнительно есть ссылка на форму регистрации. При успешной авторизации пользователь перебрасывается на исходную страницу, при неуспешной авторизации в форме выводятся сообщения об ошибках формы. Для авторизованных пользователей вместо этой формы должна показываться кнопка “Выйти”.

  ![Форма авторизации](../img/login.png)

6. **Страница регистрации**. Любой пользователь может зарегистрироваться на сайте, заполнив форму с электронной почтой, никнеймом, аватаркой и паролем. Аватарка загружается на сервер и отображается рядом с вопросами и ответами пользователя. При неудачной регистрации в форме необходимо выводить сообщения об ошибках.

  ![Страница регистрации](../img/reg.png)

7. **Блок популярных тегов**. В правой колонке сайте находится облако из 20 наиболее популярных тегов. Популярными считаются те теги, которые были использованы в наибольшем количестве вопросов. Генерация этого блока занимает много времени, поэтому этот блок необходимо генерировать в фоне, с помощью cron скрипта.

8.**Welcome Page** Первоначальная страница открывающаяся при переходе на сайт 

9.**Навигация по сайту** создание шапки с основными кнопками для перехода на сайте

10.**Страница ошибки** страница при отображении которой возникли ошибки со стороны пользователя или сервера 

## Требования к проекту
1. Структура проекта должна быть понятна пользователям. Переходы по страницам осуществляются по ссылкам. Обработка форм должна осуществляться с редиректом.
2. Код проекта должен быть аккуратным и без дублирования. Наличие больших повторяющихся фрагментов кода или шаблонов могут быть причиной снижения баллов.
3. Верстка проекта должна быть выполнена с помощью css фреймворка Twitter Bootstrap.
4. Код приложения должен быть чувствителен к входным данным и выдавать соответствующие коды и тексты ошибок. Сообщение пользователям. Ответ сервера с кодом 500 может быть причиной снижения баллов.
5. Время генерации страницы не должно зависеть от объема данных в базе.
6. Страницы проекты не должны отдаваться более 1 секунды.

## Используемые технологии
- Код приложения пишется на **Java Spring Boot**.
- Приложение запускается под управлением сервера **Gunicorn**.
- База данных – **MySQL/PostgreSQL**.
- Для отдачи статики используется **nginx**.
- Верстка выполняется с использованием **Twitter Bootstrap**.
- Взаимодействие интерфейса с пользователем обеспечивается **JavaScript/jQuery**.
