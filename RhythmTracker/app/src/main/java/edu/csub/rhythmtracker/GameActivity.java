package edu.csub.rhythmtracker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;

/**
 * Class that creates and controls the game activity
 */
public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    /**
     * Constructor that launches the Game View
     * @param savedInstanceState Data from previous instance (Null if DNE)
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        int level = 1;
        gameView = new GameView(this, point.x, point.y, level);
        setContentView(gameView);
    }

    /**
     * Reset Game on finish
     */
    public void gameOver(){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { finish();}
        }, 3000);
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