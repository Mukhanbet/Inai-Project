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
    const fileInput = document.getElementById('fileInput');

    if (fileInput.files.length > 0) { // Проверяем, есть ли выбранный файл
        const file = fileInput.files[0];

        const formData = new FormData();
        formData.append('prompt', userInput);
        formData.append('file', file);

        fetch('/test/chat-with-file', { // Исправлен адрес запроса на соответствующий серверному коду
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                var chatbox = document.getElementById('chatbox');
                chatbox.innerHTML += '<div class="message"><strong>Вы:</strong> ' + userInput + '</div>'; // Используем userInput
                chatbox.innerHTML += '<div class="message"><strong>ИИ:</strong> ' + data.response + '</div>'; // Обращаемся к data.response
                chatbox.scrollTop = chatbox.scrollHeight; // Прокрутка к последнему сообщению
            })
            .catch((error) => {
                console.error('Error:', error);
            });

        document.getElementById('userInput').value = ''; // Очищаем поля ввода
        fileInput.value = ''; // Очищаем выбор файла
    } else {
        console.error('No file selected');
    }
}


function sendDataToEndPoint() {
    var dataToSend = {
    };

    var endpointUrl = 'YOUR_ENDPOINT_URL';

    // Making a POST request to the endpoint
    fetch(endpointUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

