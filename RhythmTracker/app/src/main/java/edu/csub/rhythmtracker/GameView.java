package edu.csub.rhythmtracker;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.constraintlayout.widget.ConstraintSet;

/**
 * Class that controls the activity which runs the actual game
 */
public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying = true;
    private final Background background1;
    private final Background background2;

    private int touchX, touchY;
    private final Buttons button1, button2, button3, button4;

    /**
     * Constructor that creates permanent game assets
     * @param context current state of the application
     * @param screenX width of user screen
     * @param screenY height of user screen
     */
    public GameView(Context context, int screenX, int screenY) {
        super(context);
        Resources res = getResources();

        background1 = new Background(screenX, screenY, res);
        background2 = new Background(screenX, screenY, res);
        background2.setY(screenY);

        button1 = new Buttons(res);
        button2 = new Buttons(res);
        button3 = new Buttons(res);
        button4 = new Buttons(res);
    }

    /**
     * Endlessly call necessary functions while game is running
     */
    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    /**
     * Continuously updates background and note instances
     */
    private void update() {
        background1.update();
        background2.update();
    }

    /**
     * Continuously draws all image assets to the screen
     */
    public void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            background1.draw(canvas);
            background2.draw(canvas);
            button1.draw(canvas);
            button2.draw(canvas);
            button3.draw(canvas);
            button4.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Sleeps all threads at rate of 60 fps
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the (x,y) location of the user's finger
     * @param event triggered when the user touches screen
     * @return true when motion is detected
     */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        return true;
    }

    /**
     * Stops all threads if game is moved to background
     */
    public void pause(){
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Relaunches threads when game is continued
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
}
