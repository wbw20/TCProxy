package test.util;

import static org.junit.Assert.assertEquals;

import main.util.Util;

import org.junit.Test;

public class UtilTest {

    @Test
    public void host() {
        assertEquals("www.google.com", Util.host("www.google.com"));
        assertEquals("www.google.com", Util.host("http:www.google.com"));
        assertEquals("www.google.com", Util.host("smtp://www.google.com"));
        assertEquals("www.google.com", Util.host("www.mail.google.com"));
        assertEquals("www.google.com", Util.host("www.google.com/mail"));
        assertEquals("www.google.com", Util.host("www.google.com:4000"));
        assertEquals("www.google.com", Util.host("www.google.com:4000/news"));
    }
}
