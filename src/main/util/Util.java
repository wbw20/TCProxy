package main.util;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

    public static String read(BufferedReader in) throws IOException {
        String data = "";
        String line = in.readLine();

        while(line != null && line != "") {
            data = data + line;
            line = in.readLine();
        }

        return data;
    }
}
