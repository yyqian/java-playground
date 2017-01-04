package concurrency;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class InterruptTest {

  public static void main(String[] args) throws InterruptedException {
    MyTask task = new MyTask();
    Thread thread = new Thread(task);
    thread.start();
    Thread.sleep(10);
    thread.interrupt();
  }

  private static class MyTask implements Runnable {
    private int count = 0;

    public void run() {
      while (!Thread.interrupted()) {
        System.out.println(Thread.currentThread().getName() + " runs " + ++count + " times.");
      }
    }
  }
}

