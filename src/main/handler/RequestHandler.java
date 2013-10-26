package main.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        PrintWriter outGoing = null;
        try {
            outGoing = new PrintWriter(request.out(), true);
            Socket connSocket = new Socket(request.host(), request.port());
            PrintWriter pOut = new PrintWriter(connSocket.getOutputStream(), true);

            BufferedReader pIn = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            pOut.print(request.toString());
            pOut.flush();

            while(!pIn.ready()) {
                Thread.sleep(1);
            }

            String dataIn = Util.read(pIn);

            System.out.println(dataIn);
            outGoing.write(dataIn);
            outGoing.flush();
        } catch (IOException e) {
            e.printStackTrace();
            // swallow, abort
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return; 
    }
}
