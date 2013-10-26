package main.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import main.models.Request;

public class RequestHandler implements Runnable {

    private Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        PrintWriter outGoing = null;
        try {
            outGoing = new PrintWriter(request.out(), true);
            String dataIn = "";
            char[] buf = new char[100000];
            Socket connSocket = new Socket(request.host(), request.port());
            System.out.println(request.host() + ":" + request.port());
            PrintWriter pOut = new PrintWriter(connSocket.getOutputStream(), true);

            BufferedReader pIn = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            pOut.print(request.toString());
            pOut.flush();
            int bytesRead = pIn.read(buf);

            if(bytesRead > 0) {
               dataIn = new String(buf, 0, bytesRead);
            }

            System.out.println(dataIn);
            outGoing.write(dataIn);
        } catch (IOException e) {
            e.printStackTrace();
            // swallow, abort
        }

        return; 
    }
}
