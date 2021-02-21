package rguiles.a5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BTCController {

    @FXML
    ImageView btcBackArrow;

    public void onBackArrowClick(MouseEvent mouseEvent) throws IOException {
            System.out.println("Change to Main Page");
            Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
            Stage primaryStage = (Stage) btcBackArrow.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 475));
    }
}
