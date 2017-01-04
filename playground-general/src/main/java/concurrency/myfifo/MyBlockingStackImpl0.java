package concurrency.myfifo;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class MyBlockingStackImpl0<T> implements MyBlockingStack<T> {

  private final int capacity;
  private final Object[] items;
  private int count;

  public MyBlockingStackImpl0(int capacity) {
    this.capacity = capacity;
    this.items = new Object[capacity];
  }

  @Override
  public T take() throws InterruptedException {
    while (count == 0) {
    }
    T item = (T)items[--count];
    items[count] = null;
    return item;
  }

  @Override
  public void put(T item) throws InterruptedException {
    while (count == capacity) {
    }
    items[count++] = item;
  }
}
