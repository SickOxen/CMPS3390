package rguiles.a5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rguiles.a6.Coin;
import rguiles.a6.CoinGecko;

import java.io.IOException;

/**
 * Controller Class used to control contents of Line Chart page
 */
public class ChartController {
    @FXML
    ImageView btcBackArrow;
    @FXML
    LineChart<Number, Number> priceChart;
    @FXML
    ComboBox cbDateRange;
    @FXML
    ComboBox cbCoinSelector;

    Coin bitcoin, ethereum;

    /**
     * Initializes a line chart and inserts usable data
     */
    public void initialize(){

        priceChart.setAnimated(false);
        bitcoin = new Coin("bitcoin");
        ethereum = new Coin("ethereum");

        CoinGecko.updatePriceHistory(bitcoin, 365);
        CoinGecko.updatePriceHistory(ethereum, 365);

        priceChart.getData().add(bitcoin.getHistoricalValues());
    }

    /**
     * Switches back to main page from LineChart page by clicking back button
     * @param mouseEvent When mouse clicked on back arrow, returns to previous page
     * @throws IOException extended from FXML
     */
    public void onBackArrowClick(MouseEvent mouseEvent) throws IOException {
            System.out.println("Return to Main Page");
            Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
            Stage primaryStage = (Stage) btcBackArrow.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 475));
    }

    /**
     * Updates the Price History of the coin depenedent upon the specified number of days
     * @param actionEvent Selecting a different Date Range updates Price History of coins
     */
    public void onDateRangeChange(ActionEvent actionEvent) {
        int days = 0;
        switch ((String)cbDateRange.getValue()){
            case "Year":
                days = 365;
                break;
            case "90 Days":
                days = 90;
                break;
            case "60 Days":
                days = 60;
                break;
            case "30 Days":
                days = 30;
                break;
            case "Week":
                days = 7;
                break;
        }

        CoinGecko.updatePriceHistory(bitcoin, days);
        CoinGecko.updatePriceHistory(ethereum, days);
    }

    /**
     * Clears the Line Chart and then re-displays the desired choice from CoinSelector ComboBox
     */
    private void updateChart(){
        priceChart.getData().clear();
        switch((String) cbCoinSelector.getValue()){
            case "Bitcoin":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                break;
            case "Ethereum":
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;
            case "ALL":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;
        }
    }

    /**
     * Calls the updateChart() function and passes the Coin Selected
     * @param actionEvent Selecting a different coin updates Line Chart
     */
    public void onCoinSelectionChanged(ActionEvent actionEvent) {
        updateChart();
    }
}


