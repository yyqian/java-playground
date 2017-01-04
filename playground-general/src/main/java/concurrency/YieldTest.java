package concurrency;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class YieldTest {

  private static class YieldTask implements Runnable {
    private int count = 0;
    @Override
    public void run() {
      while (!Thread.interrupted()) {
        System.out.println(Thread.currentThread().getName() + " runs " + ++count + " times");
        if (count % 10 == 0) {
          Thread.yield();
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new YieldTask());
    Thread t2 = new Thread(new YieldTask());
    t1.start();
    t2.start();
    Thread.sleep(100);
    t1.interrupt();
    t2.interrupt();
  }
}
