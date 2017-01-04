package concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class CallableAndFuture {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService threadPool = Executors.newCachedThreadPool();
    Callable<Integer> callable = () -> {
      Thread.sleep(3000);
      return new Random().nextInt(100);
    };
    Future<Integer> future = threadPool.submit(callable);
    System.out.println("Waiting for future");
    System.out.println(future.get());
    threadPool.shutdown();
  }
}
