package main.models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import main.util.Util;

public class Request {

    private static final String HOST = "Host";

    Map<String, String> headers;
    private Socket socket;
    private DataOutputStream stream;

    private static Map<String, String> parse(String data) {
        Map<String, String> toReturn = new HashMap<String, String>();
        for (String line : data.split("\n")) {
            String key = line.split(":")[0];

            if (key != null) {
                toReturn.put(key, line.substring(key.length() + 1));
            }
        }

        return toReturn;
    }

    public Request(String data, Socket socket) {
        this.headers = parse(data);
        this.socket = socket;
    }

    public String host() throws MalformedURLException {
        return Util.host(headers.get(HOST));
    }

    public Integer port() {
        return Util.port(headers.get(HOST));
    }

    public DataOutputStream out() throws IOException {
        if (stream == null) {
            stream = new DataOutputStream(socket.getOutputStream());
        }

        return stream;
    }

    public void close() throws IOException {
        socket.close();
    }

    @Override
    public String toString() {
        String toReturn = "";

        for(String key : headers.keySet()) {
            toReturn += key + ": " + headers.get(key) + "\n";
        }

        return toReturn;
    }
}
