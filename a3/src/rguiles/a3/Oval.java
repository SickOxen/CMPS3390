package rguiles.a3;

/**
 * Oval Class: Adds an additional radius to represent an oval [Extends Circle]
 */
public class Oval extends Circle {
    private float radius2;
    private final Exception ovalExcept = new Exception("Both radii are equal, use Circle class");

    /**
     * Default Constructor [Sets Type to Oval and radius2 to zero]
     */
    public Oval() {
        super();
        this.setType(Type.OVAL);
        this.radius2 = 0.0f;
    }

    /**
     * Override Constructor [Sets Type, radius, and radius2]
     *
     * @param radius  float representing first radius of an oval
     * @param radius2 float representing second radius of an oval
     */
    public Oval(float radius, float radius2) throws Exception {
        super();

        if (radius == radius2)
            throw ovalExcept;

        this.setType(Type.OVAL);
        this.setRadius(radius);
        this.setRadius2(radius2);
    }

    /**
     * Gets the second radius of the oval
     *
     * @return float representing radius2
     */
    public float getRadius2() {
        return this.radius2;
    }

    /**
     * Sets radius2 to passed value if greater than zero [otherwise set to zero]
     *
     * @param radius2 float value representing second radius of an oval
     */
    public void setRadius2(float radius2) throws Exception{
        if (radius2 == this.getRadius())
            throw ovalExcept;
        this.radius2 = radius2 >= 0 ? radius2 : 0;
    }

    /**
     * Overrides getArea function from Circle Class [Plugs radius2 into equation]
     *
     * @return double [pi * radius1 * radius2]
     */
    @Override
    public double getArea() {
        return (3.14 * (this.getRadius() * this.getRadius2()));
    }

    /**
     * Override the toString function to print custom table
     *
     * @return Formatted string that contains all properties of array shapes[]
     */
    @Override
    public String toString() {
        return String.format("%s Radius2: %-6.2f|",
                super.toString(), this.getRadius2());
    }
}