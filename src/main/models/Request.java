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
    private String initial;
    private Socket socket;
    private DataOutputStream stream;

    private static Map<String, String> parse(String data) {
        Map<String, String> toReturn = new HashMap<String, String>();
        for (String line : data.split("\r\n")) {
            if (!Util.isInitialLine(line)) {
                String key = line.split(":")[0];

                if (key != null && line.trim().length() > 0) {
                    toReturn.put(key, line.substring(key.length() + 2));
                }
            }
        }

        return toReturn;
    }

    private static String initial(String data) {
        String[] lines = data.split("\n");

        if (lines.length > 0) {
            return lines[0];
        }

        return null;
    }

    public Request(String data, Socket socket) {
        this.initial = initial(data);
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
        String toReturn = initial + "\n";

        for (String key : headers.keySet()) {
            if (key.equals("Proxy-Connection")) {
                // change this to just connection to prevent 400
                toReturn = toReturn + "Connection:" + headers.get(key) + "\n";
            } else if (key.equals("Accept-Encoding")) {
                // leave this header out
            } else {
                toReturn = toReturn + key + ":" + headers.get(key) + "\n";
            }
        }

        return toReturn + "\r\n";
    }
}
