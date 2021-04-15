package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Class that controls instances of all lasers
 */
public class Laser implements GameObject{

    private float x, y, dpi, health = 100f;
    private Bitmap laser;
    private Paint paint = new Paint();
    private final int width, height;

    /**
     * Default constructor
     * @param res resources held by android
     */
    public Laser(Resources res) {
        laser = BitmapFactory.decodeResource(res, R.mipmap.bullet);
        width = laser.getWidth();
        height = laser.getHeight();
        dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * Checks if laser is on screen or not
     * @return true or false
     */
    public boolean isOnScreen(){return !(y < getHeight());}

    /**
     * Continuously creates new lasers
     */
    public void update(){y -= 0.1f * dpi;}

    /**
     * Draws all lasers to the screen
     */
    public void draw(Canvas canvas){canvas.drawBitmap(laser, this.x, this.y, this.paint);}

    /**
     * Gets half of lasers width
     * @return midpoint
     */
    public float getMidX(){return (laser.getWidth() / 2f);}

    /**
     * Get laser width
     * @return width
     */
    @Override
    public float getWidth() {return width;}

    /**
     * Gets laser height
     * @return height
     */
    @Override
    public float getHeight(){return height;}

    /**
     * Checks if laser made contact with object or left screen
     * @return bool
     */
    @Override
    public boolean isAlive() {return health > 0f;}

    /**
     * Gets laser health (1 or 0)
     * @return health
     */
    @Override
    public float getHealth() {return health;}

    /**
     * Apply damage to laser if contact
     * @param damage subtracted from health
     * @return 0 or 1
     */
    @Override
    public float takeDamage(float damage) {return health -= damage;}

    /**
     * Add health to laser
     * @param repairAmount amount of health added
     * @return laser repaired health
     */
    @Override
    public float addHealth(float repairAmount) {return health += repairAmount;}

    /**
     * Getter
     * @return x
     */
    @Override
    public float getX() {return x;}

    /**
     * Setter
     * @param x width of laser
     */
    public void setX(float x) {this.x = x;}

    /**
     * Getter
     * @return y
     */
    @Override
    public float getY() {return y;}

    /**
     * Setter
     * @param y height of laser
     */
    public void setY(float y) {this.y = y;}
}
