package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;

import main.models.Request;

public class Server {

    private static final Integer PORT = 5000;

    private PriorityQueue<Request> requests;

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(PORT);
        while(true) {
            Socket connection = socket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
    }
}
