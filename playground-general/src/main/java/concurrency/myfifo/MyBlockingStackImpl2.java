package concurrency.myfifo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class MyBlockingStackImpl2<T> implements MyBlockingStack<T> {

  private final int capacity;
  private final Object[] items;
  private int count;
  private final Lock lock;
  private final Condition condition;

  public MyBlockingStackImpl2(int capacity) {
    this.capacity = capacity;
    this.items = new Object[capacity];
    this.lock = new ReentrantLock();
    this.condition = lock.newCondition();
  }

  @Override
  public T take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        condition.await();
      }
      T item = (T)items[--count];
      items[count] = null;
      condition.signal();
      return item;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void put(T item) throws InterruptedException {
    lock.lock();
    try {
      while (count == capacity) {
        condition.await();
      }
      items[count++] = item;
      condition.signal();
    } finally {
      lock.unlock();
    }
  }
}
