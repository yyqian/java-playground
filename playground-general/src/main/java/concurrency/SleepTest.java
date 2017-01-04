package concurrency;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class SleepTest {

  private static class SleepTask implements Runnable {
    private final Object lock;

    public SleepTask(Object lock) {
      this.lock = lock;
    }

    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      synchronized (lock) {
        System.out.println(threadName + "任务开始");
        System.out.println(threadName + "开始睡觉");
        try {
          Thread.sleep(10000); // 注意：线程 sleep 的时候是持有锁的，这点和 wait 不同
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(threadName + "睡醒了，任务完成");
      }
    }
  }

  public static void main(String[] args) {
    Object lock = new Object();
    new Thread(new SleepTask(lock)).start();
    new Thread(new SleepTask(lock)).start();
  }
}
