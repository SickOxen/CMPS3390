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
                    float f = rand.nextInt(20);
                    shapes[loop] = new Circle(f);
                    break;
                case 1:
                    float f1 = rand.nextInt(20);
                    float f2 = rand.nextInt(20);
                    shapes[loop] = new Oval(f1, f2);
                    break;
                case 2:
                    float f3 = rand.nextInt(20);
                    shapes[loop] = new Square(f3);
                    break;
                case 3:
                    float f4 = rand.nextInt(20);
                    float f5 = rand.nextInt(20);
                    shapes[loop] = new Rectangle(f4, f5);
                    break;
                case 4:
                    float f6 = rand.nextInt(20);
                    float f7 = rand.nextInt(20);
                    shapes[loop] = new Right_Triangle(f6, f7);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + next);
            }
        }

        for (int loop = 0; loop < count; loop++)
            System.out.println(shapes[loop]);
    }
}
