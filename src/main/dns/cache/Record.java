package main.dns.cache;

import java.net.InetAddress;

public class Record {

    private InetAddress address;

    protected Record(InetAddress address) {
        this.address = address;
    }

    protected InetAddress address() {
        return address;
    }
}
