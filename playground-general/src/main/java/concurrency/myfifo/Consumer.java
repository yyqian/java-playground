package concurrency.myfifo;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class Consumer<T> implements Runnable {

  private final MyBlockingStack<T> stack;

  public Consumer(MyBlockingStack<T> stack) {
    this.stack = stack;
  }

  @Override
  public void run() {
    while (true) {
      T item = null;
      try {
        item = stack.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("I consumed an item: " + item.toString()); // process item here
    }
  }
}
