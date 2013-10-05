package main.handler;

import java.net.Socket;

import main.models.Request;

public class RequestHandler implements Runnable {

    private Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        Socket socket = new Socket();
    }
}
