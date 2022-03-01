package example.example.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorDoubleCheck {
    private final AtomicLong atomicLong = new AtomicLong(0);
    /**
     * 因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，并且赋值给 instance 之后，还没来得及初始化（执行构造函数中的代码逻辑），就被另一个线程使用了
     * 解决这个问题，我们需要给 instance 成员变量加上 volatile 关键字，禁止指令重排序才行
     * 实际上，只有很低版本的 Java 才会有这个问题。我们现在用的高版本的 Java 已经在 JDK 内部实现中解决了这个问题（解决的方法很简单，只要把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序）
     */
    private static volatile IdGeneratorDoubleCheck idGeneratorDoubleCheck;
    private IdGeneratorDoubleCheck() {}

    public static IdGeneratorDoubleCheck getInstance() {
        if(idGeneratorDoubleCheck == null) {
            synchronized (IdGeneratorDoubleCheck.class) {
                if(idGeneratorDoubleCheck == null) {
                    idGeneratorDoubleCheck = new IdGeneratorDoubleCheck();
                }
            }
        }
        return idGeneratorDoubleCheck;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
