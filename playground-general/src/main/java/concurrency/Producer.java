package concurrency;

/**
 * Created on 11/20/16.
 *
 * @author Yinyin Qian
 */
public class Producer implements Runnable {

  private final Drop drop;

  public Producer(Drop drop) {
    this.drop = drop;
  }

  @Override
  public void run() {
    String[] importantInfo = {"Mares eat oats", "Does eat oats", "Little lambs eat ivy",
        "A kid will eat ivy too"};
    for (String str: importantInfo) {
      drop.put(str);
      try {
        Thread.sleep(5000);
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
    drop.put("DONE");
  }
}
