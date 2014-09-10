import processing.net.*;

Server myServer;

int val = 0;

void setup(){
 myServer = new Server(this, 2048);
 size(200,200);
 textSize(32);
}

void draw(){
 val = (val + 1) % 255;
 myServer.write(val); 
 background(val);
 text(myServer.ip(),10,30);
}
