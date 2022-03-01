package example.example.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorLazy {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private static IdGeneratorLazy idGeneratorLazy;
    private IdGeneratorLazy() {}

    public static synchronized IdGeneratorLazy getInstance() {
        if(idGeneratorLazy == null) {
            idGeneratorLazy = new IdGeneratorLazy();
        }
        return idGeneratorLazy;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
