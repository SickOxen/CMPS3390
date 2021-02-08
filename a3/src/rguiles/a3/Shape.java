package rguiles.a3;

public class Shape
{
    private Type type;

    public Type getType() {return this.type;}
    public void setType(Type type) {this.type = type;}

    @Override
    public String toString(){
        return String.format("Type: %-15s|", this.getType());
    }
}