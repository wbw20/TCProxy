package main.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import main.dns.DNSHelper;
import main.models.Request;
import main.util.Util;

public class RequestHandler implements Runnable {

    private Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        PrintWriter outGoing = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input = Util.read(in); // from client

            if ("".equals(input)) { return; } // empty check

            Request request = new Request(input, connection);
            outGoing = new PrintWriter(request.out(), true);
            Socket connSocket = new Socket(DNSHelper.lookup(request.host()).getHostName(), request.port());
            PrintWriter out = new PrintWriter(connSocket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            out.print(request.toString()); // to server
            out.flush();

            String dataIn = Util.read(reader); // from server

            System.out.println(dataIn);
            outGoing.write(dataIn); // to client
            outGoing.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return; 
    }
}
