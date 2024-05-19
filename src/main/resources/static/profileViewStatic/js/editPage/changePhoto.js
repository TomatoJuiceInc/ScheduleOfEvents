const fileInput = document.getElementById('file-upload');
const fileNameSpan = document.getElementById('file-name');

fileInput.addEventListener('change', () => {
    const fileName = fileInput.files[0].name;
    fileNameSpan.textContent = `Файл выбран: ${fileName}`;
});