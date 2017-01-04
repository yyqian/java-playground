package concurrency.fifo;

import java.util.concurrent.BlockingQueue;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class Consumer<T> implements Runnable {

  private final BlockingQueue<T> queue;

  public Consumer(BlockingQueue<T> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (true) {
        T item = queue.take();
        System.out.println("I consumed an item: " + item.toString()); // process item here
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
