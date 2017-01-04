package concurrency;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class Plate {
  private Queue<Object> bag = new LinkedList<>();
  private final int CAPACITY;

  public Plate(int capacity) {
    this.CAPACITY = capacity;
  }

  public synchronized Object getEgg() {
    while (bag.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Object egg = bag.poll();
    notify();
    System.out.println("got an egg");
    return egg;
  }

  public synchronized void putEgg(Object egg) {
    while (bag.size() == CAPACITY) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    bag.offer(egg);
    notify();
    System.out.println("put an egg");
  }

}
