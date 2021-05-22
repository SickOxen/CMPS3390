package edu.csub.rhythmtracker;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that controls the activity which runs the actual game
 */
public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private int  miss, eventAction, touchX, touchY;
    private boolean isPlaying = true;
    private final float screenWidth, screenHeight;
    private final Background background1, background2;
    private final Buttons buttons;
    private int[] buttonPositions;
    private final Conductor conductor;
    private final NoteSpawner noteSpawner;
    private ArrayList <NoteSpawner> notes;
    private GameActivity gameActivity;

    /**
     * Constructor that creates permanent game assets
     * @param context current state of the application
     * @param screenX width of user screen
     * @param screenY height of user screen
     * @param level Current level
     */
    public GameView(GameActivity context, int screenX, int screenY, int level) {
        super(context);
        Resources res = getResources();
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;
        gameActivity = context;

        background1 = new Background(screenX, screenY, level, res);
        background2 = new Background(screenX, screenY, level, res);
        background2.setY(screenY);

        conductor = new Conductor(context, res);
        buttons = new Buttons(context, res);
        noteSpawner = new NoteSpawner(context, res);
        notes = noteSpawner.getNotes();
        buttonPositions = buttons.getButtonPositions();
    }

    /**
     * Call necessary functions while game is running
     */
    @Override
    public void run() {
        conductor.startSong(1);
        while(isPlaying){
            update();
            draw();
            // sleep(); - Was messing around with not sleeping threads because of bpm
        }
    }

    /**
     * Continuously updates game assets and instances
     */
    private void update() {
        background1.update();
        background2.update();
        noteSpawner.update();
        buttons.checkClicked(touchX, touchY);
        touchX = 0;
        touchY = 0;
    }

    /**
     * Continuously draws all image assets to the screen
     */
    public void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            background1.draw(canvas);
            background2.draw(canvas);
            buttons.draw(canvas, eventAction);
            noteSpawner.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Check for the overlap of a Note and a Button
     * @param ns NoteSpawner Instance (one note)
     * @param bs Button Instance
     * @return T or F if overlap
     */
    private boolean checkNoteButtonCollision(NoteSpawner ns, Buttons bs){
        return ns.getX() < bs.getX() + bs.getWidth() &&
                ns.getX() + ns.getWidth() > bs.getX() &&
                ns.getY() < bs.getY() + bs.getHeight() &&
                ns.getY() + ns.getHeight() > bs.getY();
    }

    /**
     * Game Over when too many notes go off screen
     */
    private void isNoteOffScreen(){
        for (NoteSpawner ns : notes)
            if(ns.getY() > screenHeight){
                miss += 1;
                if (miss == 4)
                    gameActivity.gameOver();
            }
    }

    /**
     * Gets the (x,y) location of the user's position
     * @param event triggered when the user touches screen
     * @return true when motion is detected
     */
    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event){
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        eventAction = event.getAction();
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
}
