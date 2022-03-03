package example.example.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 把这个单例对象序列化并存储到外部共享存储区（比如文件）。进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区
 * 为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对对象的加锁
 */
public class IdGeneratorCluster {
   /* private AtomicLong id = new AtomicLong(0);
    private static IdGeneratorCluster instance;
    private static SharedObjectStorage storage = FileSharedObjectStorage(*//*入参省略，比如文件地址*//*);
    private static DistributedLock lock = new DistributedLock();

    private IdGeneratorCluster() {
    }

    public synchronized static IdGeneratorCluster getInstance() {
        if (instance == null) {
            lock.lock();
            instance = storage.load(IdGeneratorCluster.class);
        }
        return instance;
    }

    public synchronized void freeInstance() {
        storage.save(this, IdGeneratorCluster.class);
        instance = null; //释放对象
        lock.unlock();
    }

    public long getId() {
        return id.incrementAndGet();
    }*/
}