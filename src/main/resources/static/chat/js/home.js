function sendMessage() {
    var input = document.getElementById('userInput');
    var message = input.value.trim();
    if (message) {
        fetch(`/test/chat?prompt=${encodeURIComponent(message)}`)
            .then(response => response.json())
            .then(data => {
                var chatbox = document.getElementById('chatbox');
                chatbox.innerHTML += '<div class="message"><strong>Вы:</strong> ' + message + '</div>';
                chatbox.innerHTML += '<div class="message"><strong>ИИ:</strong> ' + data.response + '</div>'; // Обращаемся к data.response
                chatbox.scrollTop = chatbox.scrollHeight; // Прокрутка к последнему сообщению
                input.value = ''; // Очистить поле ввода
            })
            .catch(error => console.error('Error:', error)); // Добавлена обработка ошибок
    }
}

function sendMessageWithFile() {
    const userInput = document.getElementById('userInput').value;
    const fileInput = document.getElementById('fileInput').files[0];

    const formData = new FormData();
    formData.append('prompt', userInput);
    formData.append('file', fileInput);

    fetch('/chat-with-file', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // Добавьте логику для отображения ответа и файла в чате
        })
        .catch((error) => {
            console.error('Error:', error);
        });

    // Очистите поля ввода после отправки
    document.getElementById('userInput').value = '';
    document.getElementById('fileInput').value = '';
}
