package rguiles.a3;

/**
 * Rectangle Class: Creates a height (different from length) used to find the area of a rectangle [Extends Square]
 */
public class Rectangle extends Square {
    private float height;
    private final Exception rectExcept = new Exception("Width & Height are equal, use Square class");

    /**
     * Default Constructor [Sets Type to Rectangle and height to zero]
     */
    public Rectangle() {
        super();
        this.setType(Type.RECTANGLE);
        this.height = 0.0f;
    }

    /**
     * Override Constructor [Sets Type, Width, and Height]
     *
     * @param width  float value representing width of a rectangle
     * @param height float value representing height of a rectangle
     * @throws Exception Cannot be a rectangle if width and height are equal
     */
    public Rectangle(float width, float height) throws Exception {
        super();

        if (width == height)
            throw rectExcept;

        this.setType(Type.RECTANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Gets the height of the rectangle
     *
     * @return float representing height
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Sets height to passed value if greater than zero [otherwise set to zero]
     *
     * @param height float value representing height
     * @throws Exception Cannot be a rectangle if width and height are equal
     */
    public void setHeight(float height) throws Exception {
        if (height == this.getWidth())
            throw rectExcept;
        this.height = height >= 0 ? height : 0;
    }

    /**
     * Overrides getArea function from Square Class [plugs height into equation]
     *
     * @return double [width * height]
     */
    @Override
    public double getArea() {
        return (this.getWidth() * this.getHeight());
    }

    /**
     * Override the toString function to print custom table
     *
     * @return Formatted string that contains all properties of array shapes[]
     */
    @Override
    public String toString() {
        return String.format("%s Height: %-7.2f|",
                super.toString(), this.getHeight());
    }
}
