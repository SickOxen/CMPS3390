package rguiles.a5;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rguiles.a6.Coin;
import rguiles.a6.UpdateCoinTimerTask;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller Class used to control contents of the Main page
 */
public class DetailsController {
    @FXML
    Label labelBTCvalue;
    @FXML
    Label labelETCvalue;
    @FXML
    VBox btcVBox;
    @FXML
    VBox etcVBox;

    Coin bitcoin, ethereum;
    Timer bitcoinTimer, ethereumTimer;

    /**
     * Initializes Coin Objects & creates a timer which updates the coin price every 5 seconds
     */
    public void initialize() {
        System.out.println("Initializer");

        this.bitcoin = new Coin("bitcoin");
        this.ethereum = new Coin("ethereum");

        labelBTCvalue.textProperty().bind(Bindings.format("$%-10.2f", bitcoin.currentPriceProperty()));
        labelETCvalue.textProperty().bind(Bindings.format("$%-10.2f", ethereum.currentPriceProperty()));

        bitcoinTimer = new Timer();
        bitcoinTimer.schedule(new TimerTask() {
            @Override
            public void run() {Platform.runLater(new UpdateCoinTimerTask(bitcoin));}
        }, 0,5000);

        ethereumTimer = new Timer();
        ethereumTimer.schedule(new TimerTask() {
            @Override
            public void run() {Platform.runLater(new UpdateCoinTimerTask(ethereum));}
        }, 0,5000);
    }

    /**
     * Default Constructor
     */
    public DetailsController() {System.out.println("Constructor");}

    /**
     * Switches to the Line Chart page by clicking corresponding buttons
     * @param mouseEvent When mouse clicked on BTC or ETC emblem, moves to next page
     * @throws IOException extended from FXML
     */
    public void onDetailButtonClicked(MouseEvent mouseEvent) throws IOException {
        shutdown();
        System.out.println("Change to Chart");
        Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
        Stage primaryStage = (Stage) btcVBox.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 700, 475));
    }

    /**
     * Stops the REST GET operation where coin prices are continuously refreshed
     */
    public void shutdown(){
        System.out.println("Shutdown Initiated : Stopping Timers");
        bitcoinTimer.cancel();
        ethereumTimer.cancel();
    }
}
