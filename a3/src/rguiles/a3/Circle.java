package rguiles.a3;

/**
 * Circle Class: Creates a radius used to represent a perfect circle [Extends Shape]
 */
public class Circle extends Shape {
    private float radius;

    /**
     * Default Constructor [Sets Type to Circle and radius to zero]
     */
    public Circle() {
        super();
        this.setType(Type.CIRCLE);
        this.radius = 0.0f;
    }

    /**
     * Override Constructor [Sets Type to Circle and Radius to passed value]
     *
     * @param radius float value representing radius of a circle
     */
    public Circle(float radius) {
        super();
        this.setType(Type.CIRCLE);
        this.setRadius(radius);
    }

    /**
     * Gets the value of radius
     *
     * @return float value representing radius of a circle
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * Sets radius to passed value if greater than zero [otherwise set to zero]
     *
     * @param radius float value representing radius of a circle
     */
    public void setRadius(float radius) {
        this.radius = radius >= 0 ? radius : 0;
    }

    /**
     * Calculates Area of a Circle
     *
     * @return double [pi * radius^2]
     */
    public double getArea() {
        return (3.14 * (this.getRadius() * 2));
    }

    /**
     * Override the toString function to print custom table
     *
     * @return Formatted string that contains all properties of array shapes[]
     */
    @Override
    public String toString() {
        return String.format("%s Area: %-8.2f| Radius: %-7.2f|",
                super.toString(), this.getArea(), this.radius);
    }
}
