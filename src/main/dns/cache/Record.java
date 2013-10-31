package main.dns.cache;

import java.net.InetAddress;

public class Record {

    private Long DEFAULT_TTL = 30L*1000L; // 30 seconds

    private InetAddress address;
    private Long created;

    protected Record(InetAddress address) {
        this.address = address;
        this.created = System.currentTimeMillis();
    }

    protected InetAddress address() {
        return address;
    }

    protected Boolean expired() {
        return Math.abs(System.currentTimeMillis() - this.created) >= DEFAULT_TTL;
    }
}
