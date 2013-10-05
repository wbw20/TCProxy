package main.handler;

import main.models.Request;

public class RequestHandler implements Runnable {

    private Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        
    }
}
