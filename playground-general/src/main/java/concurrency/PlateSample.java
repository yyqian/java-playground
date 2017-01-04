package concurrency;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class PlateSample {
  static class PutTask implements Runnable {
    private Plate plate;

    public PutTask(Plate plate) {
      this.plate = plate;
    }

    @Override
    public void run() {
      plate.putEgg(new Object());
    }
  }

  static class GetTask implements Runnable {
    private Plate plate;

    public GetTask(Plate plate) {
      this.plate = plate;
    }

    @Override
    public void run() {
      plate.getEgg();
    }
  }

  public static void main(String[] args) {
    Plate plate = new Plate(3);
    for (int i = 0; i < 10; i++) {
      new Thread(new PutTask(plate)).start();
    }
    for (int i = 0; i < 10; i++) {
      new Thread(new GetTask(plate)).start();
    }
  }
}
