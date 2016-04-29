import java.io.File;
import java.util.Scanner;

public class FilterTest {

  public static void main(String[] args) {
    Filter f = new Filter();

    //Get the single line of user-input
    System.out.print("Enter message (80 char limit): ");
    Scanner in = new Scanner(System.in);
    String buffer = in.nextLine();
    
    //Call Filter here
    String converted = f.convert(buffer);
    System.out.println(converted);
  }
}
