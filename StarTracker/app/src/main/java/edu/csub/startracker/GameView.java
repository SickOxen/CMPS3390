package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Class that controls the activity that actually runs the game
 */
public class GameView extends SurfaceView implements Runnable {

    private final Background background1, background2;
    private final float screenWidth, screenHeight;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX, touchY;

    private final Player player;
    private EnemySpawner spawner;
    private ArrayList<Laser> lasers;
    private ArrayList<GameObject> enemies;
    private GameActivity gameActivity;
    private Paint textPaint = new Paint();
    //private final MediaPlayer boom;

    /**
     * Constructor that sets the background and player instances
     * @param context android parameter
     * @param screenX width of user screen
     * @param screenY height of user screen
     */
    public GameView(GameActivity context, int screenX, int screenY) {
        super(context);
        Resources res = getResources();
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(screenWidth * 0.1f);
        background1 = new Background(screenX, screenY, res);
        background2 = new Background(screenX, screenY, res);
        background2.setY(screenY);
        player = new Player(res, context);
        spawner = new EnemySpawner(res, context);
        lasers = player.getLasers();
        enemies = spawner.getEnemies();
        gameActivity = context;
        //boom = MediaPlayer.create(context, R.raw.boom);
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
        player.updateTouch(touchX, touchY);
        player.update();
        spawner.update();
        checkAllCollisions();
        checkEnemyOffScreen();
    }

    private void checkEnemyOffScreen() {
        for(GameObject go : enemies)
            if(go.getY() > screenHeight){
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
    }

    /**
     * Checks for collisions against: laser vs. enemies & player vs. enemies
     */
    private void checkAllCollisions() {
        for(Laser laser : lasers)
            for(GameObject go : enemies)
                if(checkCollision(laser, go)) {
                    // boom.start();
                    laser.takeDamage(100);
                    go.takeDamage(25);
                }

        for(GameObject go : enemies)
            if(checkCollision(player, go)) {
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
    }

    /**
     * Collision check between two objects
     * @param g1 first object
     * @param g2 second object
     * @return bool if objects collide
     */
    private boolean checkCollision(GameObject g1, GameObject g2){
        return g1.getX() < g2.getX() + g2.getWidth() &&
                g1.getX() + g1.getWidth() > g2.getX() &&
                g1.getY() < g2.getY() + g2.getHeight() &&
                g1.getY() + g1.getHeight() > g2.getY();
    }


    /**
     * Continuously draw all picture assets to the screen
     */
    private void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            background1.draw(canvas);
            background2.draw(canvas);

            if(!player.isAlive())
                canvas.drawText("GAME OVER", screenWidth / 4f, screenHeight / 2f, textPaint);

            player.draw(canvas);
            spawner.draw(canvas);
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
