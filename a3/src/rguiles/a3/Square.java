package rguiles.a3;

public class Square extends Shape
{
    private float width;

    public Square(){
        super();
        this.setType(Type.SQUARE);
        this.width = 0.0f;
    }

    public Square(float width){
        super();
        this.setType(Type.SQUARE);
        this.setWidth(width);
    }

    public float getWidth() {return this.width;}
    public void setWidth(float width){
        this.width = width >= 0 ? width : 0;
    }

    public double getArea(){
        return (width * width);
    }

    @Override
    public String toString(){
        return String.format("%s Area: %-8.2f| Width: %-8.2f|",
                super.toString(), this.getArea(), this.getWidth());
    }
}
