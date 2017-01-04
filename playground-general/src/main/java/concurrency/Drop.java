package concurrency;

/**
 * Created on 11/20/16.
 *
 * @author Yinyin Qian
 */
public class Drop {
  private String message;
  private boolean empty = true;

  public synchronized String take() {
    while (empty) {
      try {
        wait();
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
    empty = true;
    notifyAll();
    return message;
  }

  public synchronized void put(String message) {
    while (!empty) {
      try {
        wait();
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
    empty = false;
    this.message = message;
    notifyAll();
  }
}
