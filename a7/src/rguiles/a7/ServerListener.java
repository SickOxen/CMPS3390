package rguiles.a7;

import jforsythe.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ServerListener Class: Client side operations that wait for response from server
 */
public class ServerListener extends Thread {

    private final Socket socket;
    private final InputStream inputStream;
    private final ObjectInputStream objectInputStream;
    Controller controller;

    /**
     * Constructor that sets the controller, socket, and streams
     * @param socket server side port
     * @param controller controls flow of data
     * @throws IOException
     */
    public ServerListener(Socket socket, Controller controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }

    /**
     * Continuous loop of the program, endlessly checking for message from user
     */
    @Override
    public void run(){
        try{
            while(true){
                Message temp = (Message) objectInputStream.readObject();
                controller.addMessage(String.format("%s: %s%n", temp.getName(), temp.getMessage()));
            }
        } catch (ClassNotFoundException | IOException e){
            System.err.println("Disconnected from server");
        } finally {
            try {
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
