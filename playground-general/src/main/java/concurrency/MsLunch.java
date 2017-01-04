package concurrency;

/**
 * Created on 20/11/2016.
 *
 * @author Yinyin Qian
 */
public class MsLunch {
  private long c1 = 0;
  private long c2 = 0;
  private final Object lock1 = new Object();
  private final Object lock2 = new Object();

  /**
   * thread-safe inc c1.
   */
  public void inc1() {
    synchronized (lock1) {
      c1++;
    }
  }

  /**
   * thread-safe inc c2.
   */
  public void inc2() {
    synchronized (lock2) {
      c2++;
    }
  }
}
