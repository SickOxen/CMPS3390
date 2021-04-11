package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Class that controls the state of the game
 */
public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    /**
     * Sets actual game to fullscreen and instantiates view for game
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);
    }

    /**
     * When app is moved to background, game-play is paused
     */
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    /**
     * When app is brought back from background, game-play is resumed
     */
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}