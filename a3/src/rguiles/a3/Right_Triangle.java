package rguiles.a3;

/**
 * Right_Triangle Class: Contains a function to find area of a right triangle [Extends Rectangle]
 */
public class Right_Triangle extends Rectangle {
    private float height;

    /**
     * Default Constructor [Sets Type to Right_Triangle and height to zero]
     */
    public Right_Triangle() {
        super();
        this.setType(Type.RIGHT_TRIANGLE);
    }

    /**
     * Override Constructor [Sets Type, width, and height]
     *
     * @param width  float value representing width of right triangle
     * @param height float value representing height of right triangle
     */
    public Right_Triangle(float width, float height) {
        super();
        this.setType(Type.RIGHT_TRIANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Allows override of function getHeight() because width and height can be equivalent for a triangle
     *
     * @return float variable of passed value for height of triangle
     */
    @Override
    public float getHeight() {
        return this.height;
    }

    /**
     * Allows override of function setHeight() because width and height can be equivalent for a triangle
     *
     * @param height float value representing height
     */
    @Override
    public void setHeight(float height) {
        this.height = height >= 0 ? height : 0;
    }

    /**
     * Override the toString function to print custom table
     *
     * @return Formatted string that contains all properties of array shapes[]
     */
    @Override
    public double getArea() {
        return (0.5 * (this.getWidth() * this.getHeight()));
    }
}