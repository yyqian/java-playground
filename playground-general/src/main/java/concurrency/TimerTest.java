package concurrency;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class TimerTest {

  private static class MyTask extends TimerTask {
    @Override
    public void run() {
      System.out.println("Boom..." + LocalDateTime.now().getSecond());
      new Timer().schedule(new MyTask(), 1000);
    }
  }

  public static void main(String[] args) {
    Timer timer = new Timer();
    timer.schedule(new MyTask(), 1000);
  }
}
