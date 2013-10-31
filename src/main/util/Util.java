package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static Pattern url = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?(:[0-9]+)?\\S*");
    private static Pattern url_with_port = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?(:[0-9]+)\\S*");
    private static Pattern initial = Pattern.compile("^(GET|HEAD|POST|PUT|DELETE|TRACE)\\s.*");

    public static String read(BufferedReader in) throws IOException {
         char[] buf = new char[1000000];
         int bytesRead = in.read(buf);
         String read = "";

         if(bytesRead > 0) {
             read = new String(buf, 0, bytesRead);
         }

         return read;
    }

    private static String stringify(ArrayList<Character> chars) {
        char[] primitives = new char[chars.size()];

        for (int i = 0; i < primitives.length; i++) {
            primitives[i] = chars.get(i);
        }

        return new String(primitives);
    }

    public static Boolean isInitialLine(String line) {
        return initial.matcher(line).matches();
    }

    private static Boolean empty(Integer num) {
        return num == -1;
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
