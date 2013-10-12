package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static Pattern url = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?(:[0-9]+)?\\S*");
    private static Pattern url_with_port = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?(:[0-9]+)\\S*");

    public static String read(BufferedReader in) throws IOException {
        String data = "";
        String line;

        while(empty((line = in.readLine()))) {
            data = data + line + "\n";
        }

        return data;
    }

    private static Boolean empty(String string) {
        return !(string == null || string.equals(""));
    }

    public static String host(String raw) {
        raw = raw.replace(" ", "");
        Matcher matcher = url.matcher(raw);

        if (matcher.matches()) {
            if (!raw.substring(0, 7).equals("http://")) {
                raw = "http://" + raw;
            }

            try {
                return new URL(raw).getHost();
            } catch (MalformedURLException e) {
                // swallow, return null
            }
        }

        return null;
    }

    public static Integer port(String raw) {
        Matcher matcher = url_with_port.matcher(raw);

        if (matcher.matches()) {
            if (!raw.substring(0, 7).equals("http://")) {
                raw = "http://" + raw;
            }

            try {
                return new URL(raw).getPort();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  -1;
            }
        }

        return 80;
    }
}
