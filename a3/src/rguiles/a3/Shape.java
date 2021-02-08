package rguiles.a3;

/**
 * Shape Class: The Base Class for all other Shape Types
 */
public class Shape {
    private Type type;

    /**
     * Default Constructor [Sets Type to Shape]
     */
    public Shape() {
        this.setType(Type.SHAPE);
    }

    /**
     * Gets the Type of Shape
     *
     * @return Type enum representing the Shape type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Sets the type of Shape
     *
     * @param type Type enum representing the Shape type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Override the toString function to print custom table
     *
     * @return Formatted string that contains all properties of array shapes[]
     */
    @Override
    public String toString() {
        return String.format("Type: %-15s|", this.getType());
    }
}