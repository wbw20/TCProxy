package main.models;

import java.net.Socket;

public class Request {

    String data;
    Socket socket;

    public Request(String data, Socket socket) {
        this.data = data;
        this.socket = socket;
    }
}
