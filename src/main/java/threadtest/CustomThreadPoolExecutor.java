package threadtest;

import java.util.Map;
import java.util.concurrent.*;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    private Map<Thread, Long> recordThreadMap = new ConcurrentHashMap<>();

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static CustomThreadPoolExecutor create(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return new CustomThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }


}
