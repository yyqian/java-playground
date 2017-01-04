package concurrency.myfifo;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class MyBlockingStackImpl1<T> implements MyBlockingStack<T> {

  private final int capacity;
  private final Object[] items;
  private int count;

  public MyBlockingStackImpl1(int capacity) {
    this.capacity = capacity;
    this.items = new Object[capacity];
  }

  @Override
  public synchronized T take() throws InterruptedException {
    while (count == 0) {
      wait();
    }
    T item = (T)items[--count];
    items[count] = null;
    notifyAll();
    return item;
  }

  @Override
  public synchronized void put(T item) throws InterruptedException {
    while (count == capacity) {
      wait();
    }
    items[count++] = item;
    notifyAll();
  }
}
