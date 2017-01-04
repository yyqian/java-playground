package concurrency;

/**
 * Created on 20/11/2016.
 *
 * @author Yinyin Qian
 */
public class HelloThread extends Thread {
  @Override
  public void run() {
    System.out.println("Hello from a thread!");
  }

  public static void main(String[] args) {
    new HelloThread().start();
  }
}
