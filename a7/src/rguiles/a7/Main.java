package rguiles.a7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Driver Class for a7
 * @author Richard Guiles
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Creates the Chat app scene used to send messages across a server
     * @param primaryStage Stage object to start scene method
     * @throws Exception try/catch of controller
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("CMPS 3390 Chat");
        primaryStage.setScene(scene);
        primaryStage.setOnHiding(e-> {
            try {
                controller.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        primaryStage.show();
    }

    /**
     * Grabs arguments from the cmdline
     * @param args message passed by user
     */
    public static void main(String[] args) {
        launch(args);
    }
}
