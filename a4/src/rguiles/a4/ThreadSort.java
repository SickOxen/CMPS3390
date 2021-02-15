package rguiles.a4;
import rguiles.a3.*;

/**
 * ThreadSort Class: Sorts multiple Shape[] arrays using a basic Bubble Sort [Extends Thread]
 */
public class ThreadSort extends Thread
{
    private final Shape[] tShapes;

    /**
     * Override Constructor [Sets Array Bounds]
     * @param shapes An array of random shapes
     * @param lowerBounds Number that represents start of array
     * @param upperBounds Number that represents end of array
     */
    public ThreadSort(Shape[] shapes, int lowerBounds, int upperBounds) {
        this.tShapes = new Shape[upperBounds - lowerBounds];
        System.arraycopy(shapes, lowerBounds, this.tShapes, 0,upperBounds - lowerBounds);
    }

    /**
     * Gets the Array of sorted shapes
     * @return ^^^
     */
    public Shape[] getThreadShapes() {return tShapes;}

    /**
     * Overrides run() function that allows the call of a thread that was
     * constructed using a separate runnable object in order to sort an array
     */
    @Override
    public void run(){
        System.out.println("Thread Start");

        Shape temp;
        int length = this.tShapes.length;

        // Swap Shape objects based on Area from smallest to largest
        for(int outer = 0; outer < length; outer++)
            for(int inner = 1; inner < length; inner++)
                if(this.tShapes[inner - 1].getArea() > this.tShapes[inner].getArea()){
                    temp = this.tShapes[inner - 1];
                    this.tShapes[inner - 1] = this.tShapes[inner];
                    this.tShapes[inner] = temp;
                }

        System.out.println("Thread Complete");
    }
}
