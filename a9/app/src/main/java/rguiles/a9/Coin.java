package rguiles.a9;

public class Coin {
    String name;
    double curValue;

    public Coin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurValue() {
        return curValue;
    }

    public void setCurValue(double curValue) {
        this.curValue = curValue;
    }
}
