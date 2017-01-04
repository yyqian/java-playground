package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class MyBlockingQueue<E> {

  private final ReentrantLock lock;
  private final Condition notEmpty;
  private final Condition notFull;
  private final int capacity;

  public MyBlockingQueue(int capacity) {
    this.capacity = capacity;
    this.lock = new ReentrantLock(false);
    this.notEmpty = lock.newCondition();
    this.notFull = lock.newCondition();
  }

  private static void checkNotNull(Object obj) {
    if (obj == null) {
      throw new NullPointerException();
    }
  }
}
