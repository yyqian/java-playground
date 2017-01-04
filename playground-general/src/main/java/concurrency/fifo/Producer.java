package concurrency.fifo;

import java.util.concurrent.BlockingQueue;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class Producer<T> implements Runnable {

  private final BlockingQueue<T> queue;

  public Producer(BlockingQueue<T> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (true) {
        T item = (T)new Object(); // produce item here
        queue.put(item);
        System.out.println("I produced an item: " + item.toString());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
