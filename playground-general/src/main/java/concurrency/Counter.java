package concurrency;

/**
 * Created on 20/11/2016.
 *
 * @author Yinyin Qian
 */
public class Counter {

  private int count = 0;

  public void increment() {
    synchronized (this) {
      count++;
    }
  }

  public void decrement() {
    synchronized (this) {
      count--;
    }
  }

  public int value() {
    synchronized (this) {
      return count;
    }
  }

  /**
   * Make a test.
   */
  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();
    Thread threadA = new Thread(() -> {
      counter.increment();
      System.out.println("Thread[A] done, count=" + counter.value());
    });
    Thread threadB = new Thread(() -> {
      counter.decrement();
      System.out.println("Thread[B] done, count=" + counter.value());
    });
    threadA.start();
    threadB.start();
    //threadA.join();
    //threadB.join();
    System.out.println("Thread[main] done, count=" + counter.value());
  }
}
