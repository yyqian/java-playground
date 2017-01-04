package concurrency.myfifo;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class ProducerConsumerExample {
  public static void main(String[] args) {
    MyBlockingStack<Object> queue = new MyBlockingStackImpl3<>(1);
    new Thread(new Producer<>(queue)).start();
    new Thread(new Producer<>(queue)).start();
    new Thread(new Producer<>(queue)).start();
    new Thread(new Producer<>(queue)).start();
    new Thread(new Producer<>(queue)).start();
    new Thread(new Consumer<>(queue)).start();
    new Thread(new Consumer<>(queue)).start();
    new Thread(new Consumer<>(queue)).start();
    new Thread(new Consumer<>(queue)).start();
    new Thread(new Consumer<>(queue)).start();
  }
}
