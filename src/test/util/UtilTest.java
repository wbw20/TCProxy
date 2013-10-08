package test.util;

import static org.junit.Assert.assertEquals;

import main.util.Util;

import org.junit.Test;

public class UtilTest {

    @Test
    public void host() {
        assertEquals("www.google.com", Util.host("www.google.com"));
    }

}
