/**
 * Contains all of the necessary files to properly run javafx
 */
module a5 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.net.http;
    requires org.json;
    opens rguiles.a5;
}