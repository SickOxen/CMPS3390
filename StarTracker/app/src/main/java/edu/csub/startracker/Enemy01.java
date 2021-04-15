package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Class that controls instances of the blue enemy ship
 */
public class Enemy01 implements GameObject {

    private float x, y, ySpeed, health = 100;
    private final float width, height, dpi;
    private final Bitmap enemy, enemy_left, enemy_right;
    private Bitmap curImage;
    private int screenWidth, screenHeight;
    private Paint paint = new Paint();

    /**
     * Default Constructor
     * @param res android resources
     * @param x axis position
     * @param y axis position
     */
    public Enemy01(Resources res, float x, float y){
        this.x = x;
        this.y = y;
        enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy01);
        enemy_left = BitmapFactory.decodeResource(res, R.mipmap.enemy01_left);
        enemy_right = BitmapFactory.decodeResource(res, R.mipmap.enemy01_right);
        curImage = enemy;
        width = curImage.getWidth();
        height = curImage.getHeight();

        dpi = res.getDisplayMetrics().densityDpi;
        screenHeight = res.getDisplayMetrics().heightPixels;
        screenWidth = res.getDisplayMetrics().widthPixels;

        ySpeed = 0.02f * dpi;
    }

    /**
     * Updates when the enemy ship changes direction
     */
    @Override
    public void update() {
        float xOff = (float) (0.02f * screenWidth * Math.sin(y / (0.05f * screenHeight)));
        x += xOff;
        curImage = xOff > 0f ? enemy_left : enemy_right;

        if(Math.abs(xOff) < 2.5)
            curImage = enemy;

        y += ySpeed;
    }

    /**
     * Continuously displays enemy assets to the screen
     * @param canvas android activity of the game
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curImage, x, y, paint);
    }

    /**
     * Getter
     * @return x
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Getter
     * @return y
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * Getter
     * @return width
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * Getter
     * @return height
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Check if enemy still has health
     * @return bool to see if enemy should be alive
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

    /**
     * Getter
     * @return enemy's remaining health
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * Updates enemy health
     * @param damage amount of damage from player
     * @return health - damage
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * updates enemy health
     * @param repairAmount amount of health re-applied
     * @return health + restoration
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }
}
