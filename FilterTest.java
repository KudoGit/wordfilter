import java.io.File;
import java.util.Scanner;

public class FilterTest {

  public static void main(String[] args) {
    Filter f = new Filter();

    String fileName = "";
    if(args.length > 0) {
      fileName = args[0];
    }

    /*
    //Get the single line of user-input
    System.out.print("Enter message (80 char limit): ");
    Scanner in = new Scanner(System.in);
    String buffer = in.nextLine();
    in.close();
    //Call Filter here
    String converted = f.convert(buffer);
    System.out.println(converted);
    */
    
    String userDirectory = System.getProperty("user.dir");
    try{
        Scanner csc = new Scanner(new File(userDirectory + "/" + fileName));
        while (csc.hasNextLine()) {
            String line = csc.nextLine();
            System.out.println(f.convert(line));
        }
        csc.close();
    } catch(Exception e) {
        System.out.println("Oh, file not found.");
    }
    
  }
}
