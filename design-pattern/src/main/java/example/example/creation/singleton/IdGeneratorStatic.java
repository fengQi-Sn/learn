package example.example.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorStatic {
    private final AtomicLong atomicLong = new AtomicLong(0);

    private IdGeneratorStatic() {}

    /**
     * 当外部类 IdGeneratorStatic 被加载的时候，并不会创建 SingletonHolder 实例对象。只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance。
     * instance 的唯一性、创建过程的线程安全性，都由 JVM 来保证
     */
    private static class SingletonHolder {
        private static final IdGeneratorStatic idGeneratorStatic = new IdGeneratorStatic();
    }

    public static IdGeneratorStatic getInstance() {
        return SingletonHolder.idGeneratorStatic;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }

}
