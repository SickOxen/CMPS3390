package rguiles.a5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Driver Class for a5
 * @author Richard Guiles
 * @version 1.3
 */
public class Main extends Application {

    /**
     * Default Main
     * @param args Launch args from cmdline
     */
    public static void main(String[] args) {launch(args);}

    /**
     * Creates instance of javafx, opens a new scene, and displays application.
     * Also automatically calls Shutdown to stop Timer Task when app is closed
     * @param primaryStage pop-out window representation
     * @throws Exception url address FXML
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        Parent root = loader.load();
        DetailsController controller = loader.getController();
        primaryStage.setTitle("To The Moon");
        Scene scene = new Scene(root, 700, 475);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnHidden(event -> controller.shutdown());
    }
}
