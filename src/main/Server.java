package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import main.handler.RequestHandler;
import main.models.Request;
import main.util.Util;

public class Server {

    private static final Integer PORT = 5000;
    private static final Integer ERROR_ON_STARTUP = -1;

    public static void main(String[] args) {
        ServerSocket socket = null;

        try {
            socket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(ERROR_ON_STARTUP);
        }

        while(true) {
            Socket connection;
            try {
                connection = socket.accept();
                RequestHandler handler = new RequestHandler(connection);
                handler.run();
            } catch (Exception e) {
                e.printStackTrace();
                // swallow, drop packet
            }
        }
    }
}
