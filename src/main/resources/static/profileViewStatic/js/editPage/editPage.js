function updateHiddenField() {
    var inputValue = document.getElementById('input-password').value;
    document.getElementById('oldPassword').value = inputValue;
}

function checkPasswordValues() {

    var input1Value = document.getElementById('input-password').value;
    var input2Value = document.getElementById('repeat-password').value;
    var errorSpan = document.getElementById('repeat-password-error');

    if (input1Value !== input2Value) {
        errorSpan.textContent = 'Пароли не совпадают!';
        return false; // Отменяем отправку формы
    } else {
        errorSpan.textContent = ''; // Очищаем сообщение об ошибке, если значения совпадают
        return true; // Разрешаем отправку формы
    }
}

function checkEmailValues() {

    var input1Value = document.getElementById('new-email').value;
    var errorSpan = document.getElementById('email-error');

    if (input1Value === "") {
        errorSpan.textContent = 'Поле почта должно быть заполнено!';
        return false; // Отменяем отправку формы
    } else {
        errorSpan.textContent = ''; // Очищаем сообщение об ошибке, если значения совпадают
        return true; // Разрешаем отправку формы
    }
}

function fieldsUpdateCheck() {
    var isValid = checkPasswordValues() && checkEmailValues();
    if (isValid) {
        updateHiddenField();
    }
    return isValid;
}