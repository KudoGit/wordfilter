
/**
 * A word filter. Takes a message and filters out forbidden words listed in "curses.txt"
 * Ignores words that are in "exceptions.txt" in case a forbidden word is part of
 * an acceptable, longer word (i.e. "ass"->"***", "class"->"class")
 *
 * Meant to mimic and improve the word filter present in Kingdom Hearts UnchainedX.
 * As a result, there is a 80-character limit per message.
 *
 * James Kuch 
 * Version 1.0 -local only edition
 */
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Filter
{   
    public static void main() {
        String[] curses = null;
        String[] exceptions = null;
        String userDirectory = System.getProperty("user.dir");

        //load the list of curse words and exception words from files
        try{
            Scanner csc = new Scanner(new File(userDirectory + "/curses.txt"));
            List<String> lines = new ArrayList<String>();
            while (csc.hasNextLine()) {
                lines.add(csc.nextLine());
            }
            curses = lines.toArray(new String[0]);
        
            Scanner sc = new Scanner(new File(userDirectory + "/exceptions.txt"));
            List<String> linesE = new ArrayList<String>();
            while (sc.hasNextLine()) {
                linesE.add(sc.nextLine());
            }
            exceptions = linesE.toArray(new String[0]);
        } catch(Exception e) {
            System.out.println("Oh, files not found.");
        }

        //Get the single line of user-input
        System.out.print("Enter message (80 char limit): ");
        Scanner in = new Scanner(System.in);
        String buffer = in.nextLine();
        
        char[] result = new char[80];

        int wordIndex = 0;
        int subIndex  = 0;
        
        //make a copy of the user's message in lower-case for scanning
        String lcBuffer = buffer.toLowerCase();

        //these arrays keep track of which characters indexs are safe or censored.
        boolean[] safe  = new boolean[buffer.length()];
        boolean[] curse = new boolean[buffer.length()];
        for(int i=0; i < buffer.length(); i++) {
            safe[i]  = false;
            curse[i] = false;
        }
        
        //handle exception words. Mark characters as "safe" if exception word is matched.
        for(int i=0; i < exceptions.length; i++) {
            while(true) {
                if((subIndex+exceptions[i].length()) > lcBuffer.length()) break;
                wordIndex = lcBuffer.substring(subIndex).indexOf(exceptions[i]);
                if(wordIndex < 0) break; //word not found in remaining message, go to next word
                for(int j=0; j < exceptions[i].length(); j++) {
                   safe[subIndex+wordIndex+j] = true;
                }
                subIndex += wordIndex + exceptions[i].length();
            }
            wordIndex = 0;
            subIndex  = 0;
        }
        
        //handle remaining curse words and mark them to be censored
        //even if some chars of a curse word are marked safe,remaining chars are still censored
        //TO-DO: loop is extremely similar to above loop, make function to cover both? 
        for(int i=0; i < curses.length; i++) {
            while(true) {
                if((subIndex+curses[i].length()) > lcBuffer.length()) break;
                wordIndex = lcBuffer.substring(subIndex).indexOf(curses[i]);
                if(wordIndex < 0) break;
                for(int j=0; j < curses[i].length(); j++) {
                    if(!safe[subIndex+wordIndex+j]) curse[subIndex+wordIndex+j] = true;
                }
                subIndex += wordIndex + curses[i].length();
            }
            wordIndex = 0;
            subIndex  = 0;
        }

        //rebuilds the message based on censors.
        for(int i=0; i < buffer.length() || i < 80; i++) {
            if(curse[i]) {
                result[i] = '*';
            } else {
                result[i] = buffer.charAt(i);
            }
        }

        System.out.println(result);
    }
}
