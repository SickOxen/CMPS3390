package rguiles.a7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import jforsythe.Message;
import jforsythe.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * Controller Class: Contains the functions for IO operations
 */
public class Controller {
    @FXML
    TextField textInput;

    @FXML
    TextArea textOutput;

    private String name;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    /**
     * Initializes sockets and outputStreams
     * @throws IOException
     */
    public void initialize() throws IOException {
        TextInputDialog nameInput = new TextInputDialog("Enter Username");
        nameInput.setHeaderText("Welcome to CMPS 3390 Chat");
        nameInput.showAndWait();
        name = nameInput.getResult();
        System.out.println(this.name);

        socket = new Socket("odin.cs.csub.edu", 3390);
        outputStream = socket.getOutputStream();
        outputStream.flush();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();

        ServerListener serverListener = new ServerListener(this.socket, this);
        serverListener.start();

        Message temp = new Message(MessageType.CONNECT, name, "Appropriate Connect Message");
        objectOutputStream.writeObject(temp);
        objectOutputStream.flush();
    }

    /**
     * String output whenever an event is registered from the textfield in chat.fxml
     * @param actionEvent user message
     * @throws IOException
     */
    public void onInputAction(ActionEvent actionEvent) throws IOException {
        Message temp = new Message(MessageType.MESSAGE, name, textInput.getText());
        textInput.clear();
        objectOutputStream.writeObject(temp);
        objectOutputStream.flush();
    }

    /**
     * Closes all open sockets and streams
     * @throws IOException
     */
    public void exit() throws IOException {
        objectOutputStream.close();
        outputStream.close();
        socket.close();
    }

    /**
     * Appends any sent message to the text area for display history
     * @param msg user message
     */
    public void addMessage(String msg) {
        textOutput.appendText(msg);
    }
}
