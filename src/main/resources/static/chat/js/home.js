function sendMessage() {
    var input = document.getElementById('userInput');
    var message = input.value.trim();
    if(message) {
        var chatbox = document.getElementById('chatbox');
        chatbox.innerHTML += '<div class="message"><strong>Вы:</strong> ' + message + '</div>';
        input.value = ''; // Очистить поле ввода

        // Здесь можно добавить вызов к API ИИ для получения ответа
        chatbox.innerHTML += '<div class="message"><strong>ИИ:</strong> Вот мой ответ на ваш вопрос...</div>';
        chatbox.scrollTop = chatbox.scrollHeight; // Прокрутка к последнему сообщению
    }
}
