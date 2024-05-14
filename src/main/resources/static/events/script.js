function toggleList() {
    var button = document.getElementById('filter'); // Замените 'filter' на актуальный ID кнопки
    var list = document.getElementById('listContainer');

    function handleClickOutside(event) {
        if (!list.contains(event.target) && event.target !== button) {
            list.style.display = 'none';
            document.removeEventListener('click', handleClickOutside);
        }
    }

    if (list.style.display === 'none' || list.style.display === '') {
        var buttonRect = button.getBoundingClientRect();
        list.style.position = 'absolute';
        list.style.top = buttonRect.bottom + window.scrollY + 'px'; /* Положение top будет под кнопкой */
        list.style.left = buttonRect.left + 'px'; /* Положение left будет выровнено с левым краем кнопки */
        list.style.display = 'block';
        list.style.zIndex = '999';
        setTimeout(function () {
            document.addEventListener('click', handleClickOutside);
        }, 0);
    } else {
        list.style.display = 'none';
        document.removeEventListener('click', handleClickOutside);
    }
}
