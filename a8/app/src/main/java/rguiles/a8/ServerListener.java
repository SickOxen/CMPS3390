package rguiles.a8;

import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import jforsythe.Message;

/**
 * Controller Class for ServerListener. Creates connection to Odin. Extends Thread
 */
public class ServerListener extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private EditText output;
    public boolean running = true;

    /**
     * Constructor: Sets socket, output, and input streams
     * @param socket Generates new socket for connection
     * @param output Message to be sent across stream
     */
    public ServerListener(Socket socket, EditText output){
        this.socket = socket;
        this.output = output;
        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Infinite loop that outputs user messages to the Chat History until app is closed
     * Closes streams when exited.
     */
    @Override
    public void run(){
        try{
            while(running){
                Message temp = (Message)objectInputStream.readObject();
                output.append(String.format("%s: %s%n", temp.getName(), temp.getMessage()));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
