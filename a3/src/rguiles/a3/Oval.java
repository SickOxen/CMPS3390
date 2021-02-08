package rguiles.a3;

public class Oval extends Circle
{
    private float radius2;

    public Oval(){
        super();
        this.setType(Type.OVAL);
        this.radius2 = 0.0f;
    }

    public Oval(float radius, float radius2){
        super();
        this.setType(Type.OVAL);
        this.setRadius(radius);
        this.setRadius2(radius2);
    }

    public float getRadius2() {return this.radius2;}
    public void setRadius2(float radius2){
        this.radius2 = radius2 >= 0 ? radius2 : 0;
    }

    @Override
    public double getArea(){
        return (3.14 * (this.getRadius() * this.getRadius2()));
    }

    @Override
    public String toString() {
        return String.format("%s Radius2: %-6.2f|",
                super.toString(), this.getRadius2());
    }
}