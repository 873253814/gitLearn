package threadtest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool {

    private int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    private TimeUnit unit;

    private ArrayBlockingQueue<Runnable> workerQueue;

    CustomThreadPoolExecutor executors;

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;

        createPool();
    }

    private void createPool() {
        if (workerQueue == null) {
            workerQueue = new ArrayBlockingQueue<>(this.corePoolSize);
        } else {
            ArrayBlockingQueue<Runnable> newQueue = new ArrayBlockingQueue<>(this.corePoolSize);
            workerQueue.drainTo(newQueue);
            workerQueue = newQueue;
        }
        executors = CustomThreadPoolExecutor.create(this.corePoolSize,this.maximumPoolSize
        ,this.keepAliveTime, this.unit, this.workerQueue);
    }

    public void execute(Runnable runnable) {
        executors.execute(runnable);
    }

    public Future<?> submit(Runnable runnable) {
        return executors.submit(runnable);
    }
}
