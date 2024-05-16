function updateHiddenField() {
    var inputValue = document.getElementById('input-password').value;
    document.getElementById('oldPassword').value = inputValue;

    var inputValue2 = document.getElementById('file-upload').value;
    document.getElementById('oldAvatar').value = inputValue2;

}