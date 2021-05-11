package edu.csub.rhythmtracker;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Class that creates and controls the game activity
 */
public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private int level = 1;

    /**
     * Constructor that creates the Game Activity
     * @param savedInstanceState Data from previous instance (Null if DNE)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y, level);
        setContentView(gameView);
    }

    /**
     * Pauses game when sent to background
     */
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    /**
     * Resumes game when brought to foreground
     */
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }

}