package concurrency.myfifo;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class Producer<T> implements Runnable {

  private final MyBlockingStack<T> stack;

  public Producer(MyBlockingStack<T> stack) {
    this.stack = stack;
  }

  @Override
  public void run() {
    while (true) {
      T item = (T)new Object(); // produce item here
      try {
        stack.put(item);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("I produced an item: " + item.toString());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
