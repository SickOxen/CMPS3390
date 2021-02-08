package rguiles.a3;

public class Right_Triangle extends Rectangle
{
    public Right_Triangle(){
        super();
        this.setType(Type.RIGHT_TRIANGLE);
    }

    public Right_Triangle(float width, float height) throws Exception {
        super();
        this.setType(Type.RIGHT_TRIANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public double getArea(){
        return (0.5 * (this.getWidth() * this.getHeight()));
    }
}
