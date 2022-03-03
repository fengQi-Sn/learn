package example.example.creation.singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorThread {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private static final ConcurrentHashMap<Long, IdGeneratorThread> instances = new ConcurrentHashMap<>();
    private IdGeneratorThread() {

    }

    public static IdGeneratorThread getInstance() {
        Long currentThreadId = Thread.currentThread().getId();
        instances.putIfAbsent(currentThreadId, new IdGeneratorThread());
        return instances.get(currentThreadId);
    }

    public long getId() {
        return atomicLong.incrementAndGet();
    }
}
