package rguiles.a5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController {
    @FXML
    Label labelBTCvalue;
    @FXML
    Label labelETCvalue;
    @FXML
    VBox btcVBox;
    @FXML
    VBox etcVBox;

    public void initialize() {
        labelBTCvalue.setText("$48,212.01");
        labelETCvalue.setText("$1,836.56");
        System.out.println("Initializer");
    }

    public DetailsController() {
        System.out.println("Constructor");
    }

    public void onDetailButtonClicked(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == btcVBox){
            System.out.println("Change to BTC");
            Parent root = FXMLLoader.load(getClass().getResource("BTC.fxml"));
            Stage primaryStage = (Stage) btcVBox.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 475));
        }

        if (mouseEvent.getSource() == etcVBox)
            System.out.println("Change to ETC");
    }
}
