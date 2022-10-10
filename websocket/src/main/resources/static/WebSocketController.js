class WebSocketController {

	constructor() {
		this._onConnected = this._onConnected.bind(this);
	}

	_onConnected(frame) {
        this.setConnected(true);
        console.log('Connected: ' + frame);
        this.stompClient.subscribe('/topic/greetings', this.showMessage, {});
        this.stompClient.subscribe('/user/queue/messages', this.showMessage, {});
	}

	setConnected(connected) {
	    document.getElementById('connect').disabled = connected;
	    document.getElementById('disconnect').disabled = !connected;
	    document.getElementById('mural').style.visibility = connected ? 'visible' : 'hidden';
	    document.getElementById('response').innerHTML = '';
	}

	connect() {
	    var socket = new SockJS('/websocket?jwt=' + document.getElementById('jwt').value);
	    this.stompClient = Stomp.over(socket);
	    this.stompClient.heartbeat.outgoing = 0;
	    this.stompClient.connect({}, this._onConnected);
	}

	disconnect() {
	    if(this.stompClient != null) {
	        this.stompClient.disconnect(null, {jwt:"ABC"});
	    }
	    this.setConnected(false);
	    console.log("Disconnected");
	}

	sendMessage() {
	    var message = document.getElementById('text').value;
	    this.stompClient.send("/app/hello", {jwt:"ABC"}, message);
	}

	sendMessageToUser() {
    	    var message = document.getElementById('message').value;
    	    var to = document.getElementById('user').value;
    	    this.stompClient.send("/app/messages", {jwt:"ABC"}, JSON.stringify({'to':to, 'message':message}));
    }

	showMessage(message) {
	    var response = document.getElementById('response');
	    var p = document.createElement('p');
	    p.style.wordWrap = 'break-word';
	    p.appendChild(document.createTextNode(message.body));
	    response.appendChild(p);
	}

}
var webSocket = new WebSocketController();