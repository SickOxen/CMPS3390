package rguiles.a9;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Coin Class: Instantiates coin objects
 */
public class Coin extends BaseObservable {

    private String name;
    private double curValue;
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Default constructor
     * @param name Cryptocurrency Coin
     */
    public Coin(String name) {
        this.name = name;
    }

    /**
     * Getter function - gets coin
     * @return coin name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter function - sets coin
     * @param name coin name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current coin price
     * @return price of crypto coin
     */
    @Bindable
    public String getCurValue() {
        return numberFormat.format(curValue);
    }

    /**
     * Sets the current coin value and notifies the manifest of the change
     * @param curValue price of crypto coin
     */
    public void setCurValue(double curValue) {
        this.curValue = curValue;
        notifyPropertyChanged(BR.curValue);
    }
}
