package test.util;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import main.util.Util;

import org.junit.Test;

public class UtilTest {

    private static final Integer EIGHTY = 80;
    private static final Integer FOUR_THOUSAND = 4000;

    @Test
    public void host() {
        try {
            assertEquals("www.google.com", Util.host("www.google.com"));
            assertEquals("www.google.com", Util.host("http://www.google.com"));
            assertEquals("www.mail.google.com", Util.host("www.mail.google.com"));
            assertEquals("www.google.com", Util.host("www.google.com/mail"));
            assertEquals("www.google.com", Util.host("www.google.com:4000"));
            assertEquals("www.google.com", Util.host("www.google.com:4000/news"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void port() {
        try {
            assertEquals(EIGHTY, Util.port("www.google.com"));
            assertEquals(EIGHTY, Util.port("http://www.google.com"));
            assertEquals(EIGHTY, Util.port("www.mail.google.com"));
            assertEquals(EIGHTY, Util.port("www.google.com/mail"));
            assertEquals(FOUR_THOUSAND, Util.port("www.google.com:4000"));
            assertEquals(FOUR_THOUSAND, Util.port("www.google.com:4000/news"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
