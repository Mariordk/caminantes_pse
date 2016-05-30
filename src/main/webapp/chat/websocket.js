/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = 'ws://' + document.location.host
+ document.location.pathname.substr(0,
document.location.pathname.indexOf("/faces")) + '/websocket';
console.log(wsUri);
var websocket = new WebSocket(wsUri); //Inicializa el websocket
var textField = document.getElementById("texto");
var users = document.getElementById("users");
var chatlog = document.getElementById("chatlog");
var output = document.getElementById("output");
var username;

function join() {
username = textField.value;
websocket.send(username + " joined");
document.getElementById("alias").style.setProperty("visibility","hidden");
document.getElementById("unirse").style.setProperty("visibility","hidden");
document.getElementById("enviar").style.removeProperty("visibility");
document.getElementById("desconectar").style.removeProperty("visibility");

}
function send_message() {
websocket.send(username + ": " + textField.value);
}
function disconnect() {
    document.getElementById("alias").style.removeProperty("visibility");
    document.getElementById("unirse").style.removeProperty("visibility");
    document.getElementById("enviar").style.setProperty("visibility","hidden");
    document.getElementById("desconectar").style.setProperty("visibility","hidden");
    websocket.close();
}

websocket.onopen = function (evt) {
onOpen(evt);
};
websocket.onmessage = function (evt) {
onMessage(evt);
};
websocket.onerror = function (evt) {
onError(evt);
};
websocket.onclose = function (evt) {
onClose(evt);
};

function onOpen() {
writeToScreen("CONNECTED");
}
function onClose() {
writeToScreen("DISCONNECTED");
}
function onMessage(evt) {
writeToScreen("RECEIVED: " + evt.data);
if (evt.data.indexOf("joined") !== -1) {
users.innerHTML += evt.data.substring(0, evt.data.indexOf("joined")) + "\n";
} else {
chatlog.innerHTML += evt.data + "\n";
}
}
function onError(evt) {
writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}
function writeToScreen(message) {
var pre = document.createElement("p");
pre.style.wordWrap = "break-word";
pre.innerHTML = message;
output.appendChild(pre);
}

