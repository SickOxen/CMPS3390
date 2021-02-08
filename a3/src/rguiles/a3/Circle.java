package rguiles.a3;

public class Circle extends Shape
{
    private float radius;

    public Circle() {
        super();
        this.setType(Type.CIRCLE);
        this.radius = 0.0f;
    }

    public Circle(float radius){
        super();
        this.setType(Type.CIRCLE);
        this.setRadius(radius);
    }

    public float getRadius() {return this.radius;}
    public void setRadius(float radius){
        this.radius = radius >= 0 ? radius : 0;
    }

    public double getArea(){
        return (3.14 * (this.getRadius() * 2));
    }

    @Override
    public String toString() {
        return String.format("%s Area: %-8.2f| Radius: %-7.2f|",
                super.toString(), this.getArea(), this.radius);
    }
}
