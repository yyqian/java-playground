package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class LockTest {

  private static class Printer {
    private Lock lock = new ReentrantLock();
    public void output(String str) {
      lock.lock();
      try {
        for (char c : str.toCharArray()) {
          System.out.print(c + " ");
          Thread.sleep(1000);
        }
        System.out.print("\n");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    final Printer printer = new Printer();
    new Thread(() -> printer.output("zhangsan")).start();
    new Thread(() -> printer.output("lisi")).start();
  }
}
