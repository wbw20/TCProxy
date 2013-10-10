package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static Pattern url = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?(:[0-9]+)?\\S*");

    public static String read(BufferedReader in) throws IOException {
        String data = "";
        String line;

        while(!(line = in.readLine()).equals("")) {
            data = data + line + "\n";
        }

        return data;
    }

    public static String host(String raw) throws MalformedURLException {
        Matcher matcher = url.matcher(raw);

        if (matcher.matches()) {
            if (!raw.substring(0, 7).equals("http://")) {
                raw = "http://" + raw;
            }

            return new URL(raw).getHost();
        } else {
            return null;
        }
    }

    //public static integer port(string raw)
}
