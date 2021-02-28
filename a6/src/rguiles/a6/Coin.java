package rguiles.a6;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.XYChart;

/**
 * Coin Class that controls coin object instances
 */
public class Coin {

    private String name;
    private final DoubleProperty currentPrice;
    private XYChart.Series<Number, Number> historicalValues;

    /**
     * Custom Coin Constructor: sets appropriate variables
     * @param name Coin name
     */
    public Coin(String name) {
        historicalValues = new XYChart.Series<>();
        historicalValues.setName(name);
        currentPrice = new SimpleDoubleProperty();
        this.name = name;
    }

    /**
     * Initializes an XY Chart with number of days and values
     * @param historicalValues Chart series
     */
    public void setHistoricalValues(XYChart.Series<Number, Number> historicalValues) {
        this.historicalValues = historicalValues;
    }

    /**
     * Gets past pricing of coin in a Line Chart
     * @return Chart series of coin pricing
     */
    public XYChart.Series<Number, Number> getHistoricalValues() {return historicalValues;}

    /**
     * Sets the values of an XY Chart with past coin prices and requested days
     * @param day The amount of days set in Date Range
     * @param value The daily price of the coin
     */
    public void addHistoricalValue(int day, double value){
        historicalValues.getData().add(new XYChart.Data<>(day, value));
    }

    /**
     * Sets coin name
     * @param name Name of coin
     */
    public void setName(String name) {this.name = name;}

    /**
     * Gets the name of the coin
     * @return coin name
     */
    public String getName() {return name;}

    /**
     * Sets coin price
     * @param currentPrice Price of coin
     */
    public void setCurrentPrice(double currentPrice) {this.currentPrice.set(currentPrice);}

    /**
     * Gets current price of coin
     * @return coin price
     */
    public double getCurrentPrice() {return currentPrice.get();}

    /**
     * Binds and wraps double value
     * @return Current price of coin
     */
    public DoubleProperty currentPriceProperty() {return currentPrice;}

    /**
     * Overrides toString() function for cmdline use
     * @return prints coin name and value
     */
    @Override
    public String toString(){
        return String.format("%20s: $%-10.2f", this.name, this.currentPrice.getValue());
    }
}
