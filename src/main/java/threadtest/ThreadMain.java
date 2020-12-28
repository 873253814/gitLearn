package threadtest;

import io.netty.bootstrap.Bootstrap;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadMain {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ThreadMain threadMain =  new ThreadMain();
        LockBox<Integer> lockBox = new LockBox<>();
        ArrayBlockingQueue<Runnable> workerQueue = new ArrayBlockingQueue<>(3);
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(3,4,10L, TimeUnit.HOURS,workerQueue);
        long currentTime = System.currentTimeMillis();
        executor.getQueue().offer(getRunnable());
        executor.getQueue().offer(getRunnable1());
        executor.getQueue().offer(getRunnable2());

        while (!executor.getQueue().isEmpty()) {
            Future<?> submit = (Future<?>) executor.submit(executor.getQueue().poll()).get();
            System.out.println(submit);
        }


        long endTime = System.currentTimeMillis();

        System.out.println(currentTime + ":" + endTime);

    }

    public static Runnable getRunnable() {
        return () -> {
            for (int i = 0; i < 10; i++) {

            }
        };
    }

    public static Runnable getRunnable1() {
        return () -> {
            for (int i = 0; i < 10; i++) {

            }
        };
    }

    public static Runnable getRunnable2() {
        return () -> {
            while (true) {

            }
        };
    }

    public void execute(Runnable runnable) {
        lock.writeLock().lock();
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
