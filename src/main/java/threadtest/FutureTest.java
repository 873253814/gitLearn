package threadtest;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        FutureTest test = new FutureTest();
        List<Future<?>> futures = new ArrayList<>();
        futures.add(executorService.submit(test.task1()));
        futures.add(executorService.submit(test.task2()));
        boolean match = true;
        while(match) {
             match = futures.stream().allMatch(future -> {
                return future.isDone();
            });
        }

        executorService.submit(test.task3());
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
//                .whenComplete((v, e) -> {
//                    executorService.submit(test.task3());
//                });
//        futures.add((Future<?>) executorService.submit(test.task1()));
//        futures.add((Future<?>) executorService.submit(test.task2()));
//        PromiseCombiner promiseCombiner = new PromiseCombiner();
//        futures.forEach(promiseCombiner::add);
//        Promise<Void> promise = new DefaultPromise<>(ImmediateEventExecutor.INSTANCE);
//        promiseCombiner.finish(promise);
//
//        promise.addListener((FutureListener<Void>) future -> {
//            executorService.submit(test.task3());
//        });

    }

    public Runnable task1() {
        return () -> System.out.println(Thread.currentThread() + "1");
    }
    public Runnable task2() {
        return () -> System.out.println(Thread.currentThread() + "2");
    }
    public Runnable task3() {
        return () -> System.out.println(Thread.currentThread() + "3");
    }

}
