package rguiles.a4;
import rguiles.a3.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Main Driver Class for a4
 * @author Richard Guiles
 * @version 1.0
 */
public class Main
{
    /**
     * Main Entry point of Application. Sorts an array of random shapes
     * generated from the previous assignment
     * @param args String array holding arguments passed from cmdline
     * @throws Exception Checks for inconsistent variables
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Run [S]ingle, [D]ual, or [Q]uad Thread Sort: ");
        char choice = scan.next().charAt(0);
        while (choice != 's' && choice != 'S' && choice != 'd'
                && choice != 'D' && choice != 'q' && choice != 'Q') {
            System.out.print("Incorrect Option. Enter [S] or [D] or [Q]: ");
            choice = scan.next().charAt(0);
        }

        System.out.print("How many Shapes: ");
        int count = scan.nextInt();
        System.out.println();
        Shape[] shapes = new Shape[count];

        for (int loop = 0; loop < count; loop++){
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

        switch (choice){
            case 's':
            case 'S':
                singleSort(shapes);
                break;
            case 'd':
            case 'D':
                dualSort(shapes);
                break;
            case 'q':
            case 'Q':
                quadSort(shapes);
                break;
        }
    }

    /**
     * Sorts a randomly sized array of shape objects using a single thread
     * @param shapes An array of random shapes
     * @throws InterruptedException Inherited from a3 classes that check variable equivalence
     */
    private static void singleSort(Shape[] shapes) throws InterruptedException {
        ThreadSort threadSort = new ThreadSort(shapes, 0, shapes.length);
        long startTime = System.nanoTime();
        threadSort.start();
        threadSort.join();
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);

        System.out.println("\n|-------------------------- SINGLE THREAD -----------------------------|");
        for(Shape s : threadSort.getThreadShapes())
            System.out.println(s);
        System.out.println("\nSingle Thread Sort Duration: " + duration);
    }

    /**
     * Sorts a randomly sized array of shape objects using two separate threads
     * @param shapes An array of random shapes
     * @throws InterruptedException Inherited from a3 classes that check variable equivalence
     */
    private static void dualSort(Shape[] shapes) throws InterruptedException {
        int mid = Math.round(shapes.length / 2);
        ThreadSort t1 = new ThreadSort(shapes,0, mid);
        ThreadSort t2 = new ThreadSort(shapes, mid, shapes.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("\n|----------------------------- THREAD 01 ------------------------------|");
        for(Shape s : t1.getThreadShapes())
            System.out.println(s);

        System.out.println("\n|----------------------------- THREAD 02 ------------------------------|");
        for(Shape s : t2.getThreadShapes())
            System.out.println(s);
        System.out.println();

        MergeSort merge = new MergeSort(t1.getThreadShapes(), t2.getThreadShapes());
        merge.start();
        merge.join();
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);

        System.out.println("\n|---------------------------- DUAL THREADS ----------------------------|");
        for(Shape s : merge.getSortedShapes())
            System.out.println(s);
        System.out.println("\nMerge Thread Sort Duration: " + duration);
    }

    /**
     * Sorts an array of random shape objects using four separate threads
     * @param shapes An array of random shapes
     * @throws InterruptedException Inherited from a3 Classes that check variable equivalence
     */
    private static void quadSort(Shape[] shapes) throws InterruptedException {
        int count = shapes.length / 4;
        int remainingCount = shapes.length - (count * 3);

        ThreadSort[] sortThreads = new ThreadSort[4];

        long startTime = System.nanoTime();
        for (int loop = 0; loop < 3; loop++) {
            sortThreads[loop] = new ThreadSort(shapes, loop * count,
                    loop * count + count);
            sortThreads[loop].start();
        }
        sortThreads[3] = new ThreadSort(shapes, shapes.length - remainingCount, shapes.length);
        sortThreads[3].start();

        for (ThreadSort t : sortThreads)
            t.join();

        // All four threads complete at this point
        MergeSort m1 = new MergeSort(sortThreads[0].getThreadShapes(), sortThreads[1].getThreadShapes());
        MergeSort m2 = new MergeSort(sortThreads[2].getThreadShapes(), sortThreads[3].getThreadShapes());
        m1.start();
        m2.start();
        m1.join();
        m2.join();

        MergeSort m3 = new MergeSort(m1.getSortedShapes(), m2.getSortedShapes());
        m3.start();
        m3.join();

        long endTime = System.nanoTime();
        long duration = ((endTime -  startTime) / 1000000);

        System.out.println("\n|---------------------------- QUAD THREADS ----------------------------|");
        for(Shape s : m3.getSortedShapes())
            System.out.println(s);
        System.out.println("\nMerge Thread Sort Duration: " + duration);
    }
}