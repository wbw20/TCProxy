package main.util;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

    public static String read(BufferedReader in) throws IOException {
        String data = "";
        String line;

        while(!(line = in.readLine()).equals("")) {
            data = data + line + "\n";
        }

        return data;
    }
}
