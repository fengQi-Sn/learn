package example.example.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;
/**
 * 通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性
 */
public enum IdGeneratorEnum {
    INSTANCE;

    private final AtomicLong atomicLong = new AtomicLong(0);

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
