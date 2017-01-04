package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 21/11/2016.
 *
 * @author Yinyin Qian
 */
public class WriteList {
  private final int SIZE = 5;
  private final List<Integer> list = new ArrayList<>();

  public void writeList() {
    PrintWriter out = null;
    try {
      System.out.println("Entering try statement");
      out = new PrintWriter(new FileWriter("/user/yyqian/Download/out.txt"));
      for (int i = 0; i < SIZE; i++) {
        out.println("Value at: " + i + " = " + list.get(i));
      }
    } catch (IndexOutOfBoundsException e) {
      System.err.println("Caught IndexOutOfBoundsException: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Caught IOException: " + e.getMessage());
    } finally {
      if (out != null) {
        System.out.println("Closing PrintWriter");
        out.close();
      } else {
        System.out.println("PrintWriter not open");
      }
    }
  }

  public static void main(String[] args) {
    WriteList writeList = new WriteList();
    writeList.writeList();
  }
}
