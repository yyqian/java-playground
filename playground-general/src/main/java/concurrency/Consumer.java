package concurrency;

/**
 * Created on 11/20/16.
 *
 * @author Yinyin Qian
 */
public class Consumer implements Runnable {

  private Drop drop;

  public Consumer(Drop drop) {
    this.drop = drop;
  }

  @Override
  public void run() {
    for (String msg = drop.take(); !msg.equals("DONE"); msg = drop.take()) {
      System.out.format("MESSAGE RECEIVED: %s%n", msg);
    }
  }
}
