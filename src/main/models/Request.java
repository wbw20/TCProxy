package main.models;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private static final String HOST = "Host";

    Map<String, String> headers;
    Socket socket;

    private static Map<String, String> parse(String data) {
        Map<String, String> toReturn = new HashMap<String, String>();
        for (String line : data.split("\n")) {
            String key = line.split(":")[0];

            if (key != null) {
                toReturn.put(key, line.substring(key.length()));
            }
        }

        return toReturn;
    }

    public Request(String data, Socket socket) {
        this.headers = parse(data);
        this.socket = socket;
    }

    public String host() {
        return headers.get(HOST);
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
