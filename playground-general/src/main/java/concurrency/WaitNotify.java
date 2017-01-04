package concurrency;

/**
 * Created on 11/20/16.
 *
 * @author Yinyin Qian
 */
public class WaitNotify {
  public static void main(String[] args) throws InterruptedException {
    Object lock = new Object();
    Runnable task = () -> {
      synchronized (lock) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(Thread.currentThread().getName() + " Done");
    };
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    System.out.println("Main thread will release lock in 1 sec.");
    Thread.sleep(1000);
    synchronized (lock) {
      lock.notifyAll();
    }
  }
}
