package rguiles.a3;

public class Rectangle extends Square
{
    private float height;
    private final Exception rectExcept = new Exception("Width & Height are equal, use Square class");

    public Rectangle(){
        super();
        this.setType(Type.RECTANGLE);
        this.height = 0.0f;
    }

    public Rectangle(float width, float height) throws Exception {
        super();

        if(width == height)
            throw rectExcept;

        this.setType(Type.RECTANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    public float getHeight() {return this.height;}
    public void setHeight(float height) throws Exception{
        if(height == this.getWidth())
            throw rectExcept;
        this.height = height >= 0 ? height : 0;
    }

    @Override
    public double getArea(){
        return (this.getWidth() * this.getHeight());
    }

    @Override
    public String toString(){
        return String.format("%s Height: %-7.2f|",
                super.toString(), this.getHeight());
    }
}
