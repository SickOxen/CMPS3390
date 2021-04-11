package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Main Driver for StarTracker: Program starts here
 * @author Richard Guiles
 * @version 1.2
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Activates full screen mode when game is launched
     * @param savedInstanceState data from previous launch
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Creates Instance when PLAY button is clicked
     * @param view Activity that displays game to screen
     */
    public void onPlayButtonClick(View view) {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }
}