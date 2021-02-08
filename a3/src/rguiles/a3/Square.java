package rguiles.a3;

/**
 * Square Class: Creates the width of a square and calculates its area [Extends Shape]
 */
public class Square extends Shape {
    private float width;

    /**
     * Default Constructor [Sets Type to Square and width to zero]
     */
    public Square() {
        super();
        this.setType(Type.SQUARE);
        this.width = 0.0f;
    }

    /**
     * Override Constructor [Sets Type to Square and width to passed value]
     *
     * @param width float value representing one side of a square
     */
    public Square(float width) {
        super();
        this.setType(Type.SQUARE);
        this.setWidth(width);
    }

    /**
     * Gets value of width
     *
     * @return float value representing one side of a square
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Sets width to passed value if greater than zero [otherwise set to zero]
     *
     * @param width float value representing one side of a square
     */
    public void setWidth(float width) {
        this.width = width >= 0 ? width : 0;
    }

    /**
     * Calculates area of the square
     *
     * @return double [width * width]
     */
    public double getArea() {
        return (width * width);
    }

    /**
     * Override the toString function to print custom table
     *
     * @return Formatted string that contains all properties of array shapes[]
     */
    @Override
    public String toString() {
        return String.format("%s Area: %-8.2f| Width: %-8.2f|",
                super.toString(), this.getArea(), this.getWidth());
    }
}
