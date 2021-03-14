package rguiles.a8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.util.logging.SocketHandler;

import jforsythe.Message;
import jforsythe.MessageType;

/**
 * Main Entry point of Chat Application
 * @author Richard Guiles
 * @version 1.1
 */
public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private EditText textInput;
    private EditText textOutput;
    private String name;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private ServerListener serverListener;

    /**
     * Generates a new thread when application is launched
     * @param savedInstanceState Possibility of passing data from previous app usage
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        this.textInput = findViewById(R.id.textInput);
        this.textInput.setOnEditorActionListener(this);
        this.textOutput = findViewById(R.id.textOutput);

        getUserName();
    }

    /**
     * Force closes all streams in order to securely shutdown the application
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        serverListener.running = false;
        try{
            objectOutputStream.close();
            outputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Connects the socket to the port and creates new message instance
     */
    private void connect() {
        try {
            socket = new Socket("odin.cs.csub.edu", 3390);
            outputStream = socket.getOutputStream();
            outputStream.flush();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.flush();

            serverListener = new ServerListener(socket, textOutput);
            serverListener.start();

            Message connect = new Message(MessageType.CONNECT, name, "Hi from Android");
            objectOutputStream.writeObject(connect);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Upon entry, generates window to ask user for their username
     */
    private void getUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Name");
        EditText userNameInput = new EditText(this);
        userNameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(userNameInput);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = userNameInput.getText().toString();
                Log.d("USER NAME", name);
                if(name.equals("")) getUserName();
                else connect();
            }
        });
        builder.show();
    }

    /**
     * Gets each user message from the textbox and stores it for use
     * @param v TextView from the cmdline of the app
     * @param actionId Identification for each unique message
     * @param event Keyboard usage
     * @return True when function is finished running
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(event == null || event.getAction() == KeyEvent.ACTION_UP) {
            Message temp = new Message(MessageType.MESSAGE, name, textInput.getText().toString());
            try {
                objectOutputStream.writeObject(temp);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textInput.setText("");
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return true;
    }
}