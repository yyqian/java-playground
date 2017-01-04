package concurrency.fifo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class ProducerConsumerExample {
  public static void main(String[] args) {
    BlockingQueue<Object> queue = new LinkedBlockingDeque<>(10);
    new Thread(new Producer<>(queue)).start();
    new Thread(new Consumer<>(queue)).start();
  }
}
