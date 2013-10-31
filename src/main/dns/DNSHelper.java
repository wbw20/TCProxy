package main.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;

import main.dns.cache.RecordStore;

public class DNSHelper {

    public static InetAddress lookup(String host) {
        if (RecordStore.get(host) != null) {
            return RecordStore.get(host);
        } else {
            try {
                return RecordStore.create(host);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
