package concurrency.myfifo;

/**
 * Created on 29/11/2016.
 *
 * @author Yinyin Qian
 */
public interface MyBlockingStack<T> {
  T take() throws InterruptedException;

  void put(T item) throws InterruptedException;
}
