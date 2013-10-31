package main.dns.cache;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class RecordStore {

    private static HashMap<String, Record> records = new HashMap<String, Record>();

    public static InetAddress get(String host) {
        return records.get(host) == null ? null : records.get(host).address();
    }

    public static InetAddress create(String host) throws UnknownHostException {
        Record record = new Record(InetAddress.getByName(host));
        records.put(host, record);
        return record.address();
    }
}
