1. using sts create the existing maven project
2. clean install and build the war
3. then mvn spring-boot:run so that spring boot is initialized
4. check in the console http://localhost:8080/ so that it prints the welcome in the screen console
5. then to check the web socket.
6. follow the below instructions.
a) open the developer tools
b) in the console type below program and enter
      
      class WebSocketClient {
    
    constructor(protocol, hostname, port, endpoint) {
        
        this.webSocket = null;
        
        this.protocol = protocol;
        this.hostname = hostname;
        this.port     = port;
        this.endpoint = endpoint;
    }
    
    getServerUrl() {
        return this.protocol + "://" + this.hostname + ":" + this.port + this.endpoint;
    }
    
    connect() {
        try {
            this.webSocket = new WebSocket(this.getServerUrl());
            
            //
            // Implement WebSocket event handlers!
            //
            this.webSocket.onopen = function(event) {
                console.log('onopen::' + JSON.stringify(event, null, 4));
            }
            
            this.webSocket.onmessage = function(event) {
                var msg = event.data;
                console.log('onmessage::' + JSON.stringify(msg, null, 4));
            }
            this.webSocket.onclose = function(event) {
                console.log('onclose::' + JSON.stringify(event, null, 4));                
            }
            this.webSocket.onerror = function(event) {
                console.log('onerror::' + JSON.stringify(event, null, 4));
            }
            
        } catch (exception) {
            console.error(exception);
        }
    }
    
    getStatus() {
        return this.webSocket.readyState;
    }
    send(message) {
        
        if (this.webSocket.readyState == WebSocket.OPEN) {
            this.webSocket.send(message);
            
        } else {
            console.error('webSocket is not open. readyState=' + this.webSocket.readyState);
        }
    }
    disconnect() {
        if (this.webSocket.readyState == WebSocket.OPEN) {
            this.webSocket.close();
            
        } else {
            console.error('webSocket is not open. readyState=' + this.webSocket.readyState);
        }
    }
}

d) then type below and enter for each command

var client = new WebSocketClient('ws', '127.0.0.1', 8080, '/WebSocketServer/endpoint');

client.connect();

ouput:
-----
undefined
VM414:25 onopen::{
    "isTrusted": true
}

client.send('Hello Server!');

output:
----------
undefined
VM414:30 onmessage::"Hello Client message from server0!"


f) in the spring boot sts or eclipse log you can see


onOpen::0
onMessage::From=0 Message=Hello Server!
inside / controller
