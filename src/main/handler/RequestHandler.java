package main.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import main.models.Request;
import main.util.Util;

public class RequestHandler implements Runnable {

    private Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(request.host(), request.port());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeBytes(request.toString());

            request.out().writeBytes(Util.read(in));
            request.close();
            socket.close();
        } catch (IOException e) {
            // swallow, abort
        }

        return; 
    }
}
