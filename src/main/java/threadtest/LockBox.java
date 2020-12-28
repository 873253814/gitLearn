package threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockBox<K> {
    private Map<K, ReentrantReadWriteLock> lockMap = new ConcurrentHashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    ThreadLocal<List<K>> threadLocal = new ThreadLocal<>();

    public void execute(K id, Runnable runnable) {
        List<K> list = threadLocal.get();
        if (list == null) {
            list = new ArrayList<>();
        }
        ReentrantReadWriteLock lock = getLock(id);
        if (lock.writeLock().tryLock()) {
            list.add(id);
            try {
                runnable.run();
            } finally {
                lock.writeLock().unlock();
                list.remove(id);
            }

        } else {
            list.forEach(kid -> {
                getLock(kid).writeLock().unlock();
            });
            list.add(id);
            list.forEach(key -> {
                getLock(key).writeLock().lock();
            });
            try {
                runnable.run();
            } finally {
                getLock(id).writeLock().unlock();
                list.remove(id);
            }
        }
    }

    public ReentrantReadWriteLock getLock(K id) {
        ReentrantReadWriteLock lock = lockMap.get(id);
        if (lock == null) {
            synchronized (lockMap) {
                lock = lockMap.get(id);
                if (lock == null) {
                    lock = new ReentrantReadWriteLock();
                    lockMap.put(id, lock);
                }
            }
        }
        return lock;
    }
}
