package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Class that controls instances of the faster yellow enemy ship
 */
public class Enemy02 implements GameObject{

    private float x, y, ySpeed, health = 50;
    private Bitmap curImage;
    private final Bitmap enemy, enemy_fast;
    private final int screenWidth, screenHeight, width, height, dpi;
    private Paint paint = new Paint();
    private int frameTick = 0, launchTick;

    /**
     * Default Constructor
     * @param res android resources
     * @param x axis position
     * @param y axis position
     */
    public Enemy02(Resources res, float x, float y){
        dpi = res.getDisplayMetrics().densityDpi;
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;
        enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy02);
        enemy_fast = BitmapFactory.decodeResource(res, R.mipmap.enemy02_fast);
        curImage = enemy;
        width = curImage.getWidth();
        height = curImage.getHeight();
        this.x = x;
        this.y = y;
        ySpeed = 0.025f * dpi;
        launchTick = new Random().nextInt(120-30) + 30;
    }

    /**
     * Updates when speed of enemy ship increases
     */
    @Override
    public void update() {
        frameTick++;

        if(frameTick == launchTick / 3){
            curImage = enemy_fast;
            ySpeed *= 4f;
        }

        y += ySpeed;
    }

    /**
     * Continuously displays enemy assets to the screen
     * @param canvas android activity of the game
     */
    @Override
    public void draw(Canvas canvas) {canvas.drawBitmap(curImage, x, y, paint);}

    /**
     * Getter
     * @return x
     */
    @Override
    public float getX() {return x;}

    /**
     * Getter
     * @return y
     */
    @Override
    public float getY() {return y;}

    /**
     * Getter
     * @return width
     */
    @Override
    public float getWidth() {return width;}

    /**
     * Getter
     * @return height
     */
    @Override
    public float getHeight() {return height;}

    /**
     * Check if enemy still has health
     * @return bool to see if enemy should be alive
     */
    @Override
    public boolean isAlive() {return health > 0f;}

    /**
     * Getter
     * @return enemy's remaining health
     */
    @Override
    public float getHealth() {return health;}

    /**
     * Updates enemy health
     * @param damage amount of damage from player
     * @return health - damage
     */
    @Override
    public float takeDamage(float damage) {return health -= damage;}

    /**
     * Updates enemy health
     * @param repairAmount amount of health re-applied
     * @return health + restoration
     */
    @Override
    public float addHealth(float repairAmount) {return health += repairAmount;}
}
