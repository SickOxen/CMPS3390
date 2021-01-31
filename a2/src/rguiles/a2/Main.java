package rguiles.a2;
import java.util.Scanner;

/**
 * Main Driver Class for a2
 * @author Richard Guiles
 * @version 1.0
 */

public class Main
{
    /**
     * Main Entry for Application
     * @param args   // String array holding arguments passed from cmdline
     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("[Problem syntax is 'value operator value' (2 + 2)]");
        System.out.println("Enter Math Problem or Quit: ");

        // Loop until user types quit
        String line;
        while(!(line = scan.nextLine()).equalsIgnoreCase("quit")){
            System.out.println(Calc.evaluate(line));
        }
    }
}
