package example.example.creation.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class IdGeneratorMulti {


}


class BackendServer {
    private long serverNo;
    private String serverAddress;

    private static final int SERVER_COUNT = 3;
    private static final Map<Long, BackendServer> serverInstances = new HashMap<>();

    static {
        serverInstances.put(1L, new BackendServer(1L, "192.134.22.138:8080"));
        serverInstances.put(2L, new BackendServer(2L, "192.134.22.139:8080"));
        serverInstances.put(3L, new BackendServer(3L, "192.134.22.140:8080"));
    }

    private BackendServer(long serverNo, String serverAddress) {
        this.serverNo = serverNo;
        this.serverAddress = serverAddress;
    }

    public BackendServer getInstance(long serverNo) {
        return serverInstances.get(serverNo);
    }

    public BackendServer getRandomInstance() {
        Random r = new Random();
        int no = r.nextInt(SERVER_COUNT)+1;
        return serverInstances.get(no);
    }
}


/**
 * 这种多例模式的理解方式有点类似工厂模式。它跟工厂模式的不同之处是，多例模式创建的对象都是同一个类的对象，而工厂模式创建的是不同子类的对象
 */
class LoggerMulti {
    private static final ConcurrentHashMap<String, LoggerMulti> instances = new ConcurrentHashMap<>();

    private LoggerMulti() {}

    public static LoggerMulti getInstance(String loggerName) {
        instances.putIfAbsent(loggerName, new LoggerMulti());
        return instances.get(loggerName);
    }

    public void log() {
        //...
    }
}