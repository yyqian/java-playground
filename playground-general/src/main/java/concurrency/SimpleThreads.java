package concurrency;

/**
 * Created on 20/11/2016.
 *
 * @author Yinyin Qian
 */
public class SimpleThreads {

  private static void threadMessage(String message) {
    String threadName = Thread.currentThread().getName();
    System.out.format("%s: %s%n", threadName, message);
  }

  private static class MessageLoop implements Runnable {
    public void run() {
      String[] importantInfo = {"Mares eat oats", "Does eat oats", "Little lambs eat ivy",
          "A kid will eat ivy too"};
      try {
        for (String info : importantInfo) {
          Thread.sleep(4000);
          threadMessage(info);
        }
      } catch (InterruptedException exception) {
        threadMessage("I wasn't done!");
      }
    }
  }

  /**
   * Make a test.
   **/
  public static void main(String[] args) throws InterruptedException {
    long patience = 1000 * 60 * 60;
    if (args.length > 0) {
      try {
        patience = Long.parseLong(args[0]) * 1000;
      } catch (NumberFormatException exception) {
        System.err.println("Argument must be an integer.");
        System.exit(1);
      }
    }
    threadMessage("Starting MessageLoop thread");
    long startTime = System.currentTimeMillis();
    Thread thread = new Thread(new MessageLoop());
    thread.start();
    threadMessage("Waiting for MessageLoop thread to finish");
    while (thread.isAlive()) {
      threadMessage("Still waiting...");
      thread.join(1000);
      if (((System.currentTimeMillis() - startTime) > patience) && thread.isAlive()) {
        threadMessage("Tired of waiting!");
        thread.interrupt();
        thread.join();
      }
    }
    threadMessage("Finally!");
  }
}
