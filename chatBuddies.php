<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Study Buddy Chat</title>
</head>

<body>

    <div class="container">
        <div class="user-list"> 
            <h3>Users</h3>
            <ul id="user-list">
                <?php
                $users = ['User1', 'User2', 'User3']; // Replace with actual user data retrieval
                foreach ($users as $user) {
                    echo "<li><a href='#' onclick=\"openPrivateChat('$user')\">$user</a></li>";
                }
                ?>
            </ul>
        </div>

        <div class="chat-container">
            <div id="chat-sections">
                <?php
                foreach ($users as $user) {
                    echo "<div id='chat-$user' class='private-chat' style='display: none;'>
                            <h2>$user</h2>
                            <div id='private-messages-$user' class='messages'></div>
                            <form onsubmit=\"sendMessage('$user'); return false;\">
                                <input type='hidden' name='chat_type' value='private'>
                                <input type='hidden' name='recipient' value='$user'>
                                <input type='text' name='message' id='private-message-$user' placeholder='Type your message here'>
                                <button type='button' onclick=\"sendMessage('$user')\">Send</button>
                            </form>
                          </div>";
                }
                ?>
            </div>
        </div>
    </div>
</body>
</html>

<?php
// PHP code for handling chat functionality
// $chatFile = 'chat_db.txt';
// if (!file_exists($chatFile)) {
//     file_put_contents($chatFile, ""); // Create the chat file if it doesn't exist
// }

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $chat_type = $_POST['chat_type'];
    $message = trim($_POST['message']);
    $username = "User"; // Replace this with a method to get the current username
    $recipient = isset($_POST['recipient']) ? $_POST['recipient'] : '';
    
    if ($message !== '' && $recipient !== '') {
        $timestamp = date('m/d/Y h:i:s A'); // Updated to standard time format
        $entry = "$chat_type|$username|$recipient|$timestamp|$message\n";
        // file_put_contents($chatFile, $entry, FILE_APPEND);
    }
}

// $chats = file($chatFile, FILE_IGNORE_NEW_LINES);
// $privateMessages = [];
// $recipientUser = isset($_GET['recipient']) ? $_GET['recipient'] : '';
$username = "Mary.aen"; // Replace this with a method to get the current username

// foreach ($chats as $chat) {
//     list($chat_type, $user, $recipient, $timestamp, $message) = explode('|', $chat, 5);
//     if ($chat_type === 'private' && (($user === $username && $recipient === $recipientUser) || ($user === $recipientUser && $recipient === $username))) {
//         $privateMessages[$recipientUser][] = "<div><div class='timestamp'>$timestamp</div><div><strong>$user:</strong> $message</div></div>";
//     }
// }
?>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const chatSectionsDiv = document.getElementById('chat-sections');

        // Create chat sections for each user dynamically
        const users = <?php echo json_encode($users); ?>;
        users.forEach(user => {
            const chatSection = document.createElement('div');
            chatSection.id = `chat-${user}`;
            chatSection.className = 'private-chat';
            chatSection.style.display = 'none';
            chatSection.innerHTML = `
                <h2>${user}</h2>
                <div id="private-messages-${user}" class="messages"></div>
                <form onsubmit="sendMessage('${user}'); return false;">
                    <input type="hidden" name="chat_type" value="private">
                    <input type="hidden" name="recipient" value="${user}">
                    <input type="text" name="message" id="private-message-${user}" placeholder="Type your message here">
                    <button type="button" onclick="sendMessage('${user}')">Send</button>
                </form>`;
            chatSectionsDiv.appendChild(chatSection);
        });
    });

    function openPrivateChat(user) {
        // Hide all other chat sections
        const chatSections = document.querySelectorAll('.private-chat');
        chatSections.forEach(section => {
            section.style.display = 'none';
        });

        // Display the selected user's chat section
        document.getElementById(`chat-${user}`).style.display = 'block';

        // Update chat messages specific to this user
        const xhr = new XMLHttpRequest();
        xhr.open('GET', `chat.php?recipient=${encodeURIComponent(user)}`, true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                document.getElementById(`private-messages-${user}`).innerHTML = xhr.responseText;
                document.getElementById(`private-messages-${user}`).scrollTop = document.getElementById(`private-messages-${user}`).scrollHeight;
            }
        };
        xhr.send();
    }

    function sendMessage(user) {
        let messageInput = document.getElementById(`private-message-${user}`);
        let messagesDiv = document.getElementById(`private-messages-${user}`);

        const message = messageInput.value.trim();
        if (message !== '') {
            const timestamp = new Date().toLocaleString('en-US', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit',
                hour12: true
            });
            const username = 'User'; // Replace with actual username retrieval method
            const messageHTML = `<div><div class='timestamp'>${timestamp}</div><div><strong>${username}:</strong> ${message}</div></div>`;
            messagesDiv.innerHTML += messageHTML;
            messagesDiv.scrollTop = messagesDiv.scrollHeight;
            messageInput.value = '';

            // Send message to server via AJAX
            const xhr = new XMLHttpRequest();
            xhr.open('POST', 'chat.php', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send(`chat_type=private&message=${encodeURIComponent(message)}&recipient=${encodeURIComponent(user)}`);
        }
    }
</script>