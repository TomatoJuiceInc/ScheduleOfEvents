function toggleList() {
    var button = document.getElementById('filter'); // Replace 'yourButtonId' with the actual button ID
    var list = document.getElementById('listContainer');
    if (list.style.display === 'none' || list.style.display === '') {
        var buttonRect = button.getBoundingClientRect();
        list.style.position = 'absolute';
        list.style.top = buttonRect.top + window.scrollY + 'px'; /* Match button's top position */
        list.style.left = buttonRect.right + 10 + 'px'; /* Position to the right of the button with a 10px gap */
        list.style.transform = 'translate(-50%, -50%)';
        list.style.display = 'block';
        list.style.zIndex = '999';
    } else {
        list.style.display = 'none';
    }
}