package example.example.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorHunger {
    private final AtomicLong id = new AtomicLong(0);
    private static final IdGeneratorHunger idg = new IdGeneratorHunger();

    private IdGeneratorHunger() {}

    public static IdGeneratorHunger getInstance() {
        return idg;
    }

    public Long getId() {
        return id.incrementAndGet();
    }
}
