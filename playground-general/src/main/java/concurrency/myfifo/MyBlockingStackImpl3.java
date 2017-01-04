package concurrency.myfifo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public class MyBlockingStackImpl3<T> implements MyBlockingStack<T> {

  private final int capacity;
  private final Object[] items;
  private int count;
  private final Lock lock;
  private final Condition notEmpty;
  private final Condition notFull;

  public MyBlockingStackImpl3(int capacity) {
    this.capacity = capacity;
    this.items = new Object[capacity];
    this.lock = new ReentrantLock();
    this.notEmpty = lock.newCondition();
    this.notFull = lock.newCondition();
  }

  @Override
  public T take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      T item = (T)items[--count];
      items[count] = null;
      notFull.signal();
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
        notFull.await();
      }
      items[count++] = item;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }
}
