package rguiles.a5;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Driver Class for a5
 * @author Richard Guiles
 * @version 1.2
 */
public class Main extends Application {

    /**
     * Creates instance of javafx, opens a new scene, and displays application
     * @param primaryStage pop-out window representation
     * @throws Exception url address FXML
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        primaryStage.setTitle("To The Moon");
        primaryStage.setScene(new Scene(root, 700, 475));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
