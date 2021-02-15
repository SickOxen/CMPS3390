package rguiles.a4;
import rguiles.a3.*;

/**
 * MergeSort Class: Passes in two Shape[] arrays and merges them together [Extends Thread]
 */
public class MergeSort extends Thread
{
    private final Shape[] shapes1;
    private final Shape[] shapes2;
    private final Shape[] sortedShapes;

    /**
     * Override Constructor: [Sets two given arrays and creates
     * a third array with the total length of the arrays passed]
     * @param s1 An array of Shape Objects
     * @param s2 Another array of Shape Objects
     */
    public MergeSort(Shape[] s1, Shape[] s2){
        this.shapes1 = s1;
        this.shapes2 = s2;
        this.sortedShapes = new Shape[s1.length + s2.length];
    }

    /**
     * Gets the array of Sorted Shapes
     * @return ^^^
     */
    public Shape[] getSortedShapes() {return sortedShapes;}

    /**
     * Overrides run() function that allows the call of a thread that was
     * constructed using a separate runnable object in order to sort arrays
     */
    @Override
    public void run(){
        System.out.println("Merge Thread Start");
        int indexS1 = 0;
        int indexS2 = 0;
        int indexSS = 0;

        // Merge Shape Arrays
        while(indexS1 < shapes1.length && indexS2 < shapes2.length){
            if(this.shapes1[indexS1].getArea() < this.shapes2[indexS2].getArea())
                this.sortedShapes[indexSS++] = this.shapes1[indexS1++];
            else {
                this.sortedShapes[indexSS++] = this.shapes2[indexS2++];
            }
        }

        while(indexS1 < this.shapes1.length){
            this.sortedShapes[indexSS++] = this.shapes1[indexS1++];
        }

        while(indexS2 < this.shapes2.length){
            this.sortedShapes[indexSS++] = this.shapes2[indexS2++];
        }

        System.out.println("Merge Thread Complete");
    }
}
