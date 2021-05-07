package edu.csub.rhythmtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Class that controls the contents of the first level
 */
public class Level1 extends AppCompatActivity {

    private GameView gameView;

    /**
     * Constructor that creates level 01 Activity
     * @param savedInstanceState Data from previous instance (Null if DNE)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);
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

    public void onB1Click(View view) {
    }

    public void onB2Click(View view) {
    }

    public void onB3Click(View view) {
    }

    public void onB4Click(View view) {
    }
}