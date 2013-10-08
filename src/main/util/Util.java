package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static Pattern url = Pattern.compile("^*\\s*([a-zA-Z0-9\\-\\.]+\\.(?:com|org|net|mil|edu|COM|ORG|NET|MIL|EDU))(?::([0-9]+))?$");

    public static String read(BufferedReader in) throws IOException {
        String data = "";
        String line;

        while(!(line = in.readLine()).equals("")) {
            data = data + line + "\n";
        }

        return data;
    }

    public static String host(String raw) {
        Matcher matcher = url.matcher(raw);

        if (matcher.matches()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    //public static integer port(string raw)
}
