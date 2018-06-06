let id = id => document.getElementById(id);

let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");
ws.onmessage = msg => updateChat(msg);                              //when receiving from pipe
ws.onclose = () => alert("WebSocket connection closed");

let wsBet = new WebSocket("ws://" + location.hostname + ":" + location.port + "/bet");

id("send").addEventListener("click", () => sendAndClear(id("message").value));          //on sending through pipe
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) {
        sendAndClear(e.target.value);
    }
});

function sendAndClear(message) {
    if (message !== "") {
        ws.send(message);
        //wsBet.send(message);            //to be interpreted by server
        id("message").value = "";
    }
}

function updateChat(msg) {
    let data = JSON.parse(msg.data);
    id("chat").insertAdjacentHTML("afterbegin", data.userMessage);
    id("userList").innerHTML = data.userList.map(user => "<li class='list-group-item'>" + user + "</li>").join("");
}
