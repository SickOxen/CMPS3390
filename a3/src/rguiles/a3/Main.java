package rguiles.a3;
import java.util.Random;
import java.util.Scanner;

/**
 * Main Driver Class for a3
 *
 * @author Richard Guiles
 * @version 1.2
 */
public class Main {
    /**
     * Main Entry point of Application. Creates an array of random shapes
     * with random constructor values and prints them to the screen.
     *
     * @param args String array holding arguments passed from cmdline
     * @throws Exception Checks for inconsistent variables
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Num of Shapes to Display: ");
        int count = scan.nextInt();
        Random rand = new Random();
        Shape[] shapes = new Shape[count];

        for (int loop = 0; loop < count; loop++) {
            int next = rand.nextInt(5);
            switch (next) {
                case 0:
                    shapes[loop] = new Circle(rand.nextFloat() * 20f);
                    break;
                case 1:
                    shapes[loop] = new Oval(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
                case 2:
                    shapes[loop] = new Square(rand.nextFloat() * 20f);
                    break;
                case 3:
                    shapes[loop] = new Rectangle(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
                case 4:
                    shapes[loop] = new Right_Triangle(rand.nextFloat() * 20f, rand.nextFloat() * 20f);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + next);
            }
        }

        for (int loop = 0; loop < count; loop++)
            System.out.println(shapes[loop]);
    }
}
