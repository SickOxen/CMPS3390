package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * Class that controls the activity that actually runs the game
 */
public class GameView extends SurfaceView implements Runnable {

    private final Background background1, background2;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX, touchY;
    private final Player player;

    /**
     * Constructor that sets the background and player instances
     * @param context android parameter
     * @param screenX width of user screen
     * @param screenY height of user screen
     */
    public GameView(Context context, int screenX, int screenY) {
        super(context);
        Resources res = getResources();

        background1 = new Background(screenX, screenY, res);
        background2 = new Background(screenX, screenY, res);
        background2.setY(screenY);
        player = new Player(res);
    }

    /**
     * Gets the (x,y) location of the user's finger
     * @param event triggered when user moves
     * @return true when motion detected
     */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        return true;
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
     * Continuously update background and player instances
     */
    private void update() {
        background1.update();
        background2.update();
        player.update(touchX, touchY);
    }

    /**
     * Continuously draw all picture assets to the screen
     */
    private void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            background1.draw(canvas);
            background2.draw(canvas);
            player.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Sleeps all threads until next function calls
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
     * Recreates and launches threads when game is resumed
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
}
