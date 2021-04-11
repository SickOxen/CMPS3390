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
public class Laser {

    private float x, y;
    private Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();

    /**
     * Default constructor
     * @param res resources held by android
     */
    public Laser(Resources res) {
        laser = BitmapFactory.decodeResource(res, R.mipmap.bullet);
        dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * Checks if laser is on screen or not
     * @return true or false
     */
    public boolean isOnScreen(){
        return !(y < getHeight());
    }

    /**
     * Continuously creates new lasers
     */
    public void update(){
        y -= 0.1f * dpi;
    }

    /**
     * Draws all lasers to the screen
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(laser, this.x, this.y, this.paint);
    }

    /**
     * Gets half of lasers width
     * @return x
     */
    public float getMidX(){
        return (laser.getWidth() / 2f);
    }

    /**
     * Gets lasers height
     * @return y
     */
    public float getHeight(){
        return laser.getHeight();
    }

    /**
     * Getter
     * @return x
     */
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
    public float getY() {return y;}

    /**
     * Setter
     * @param y height of laser
     */
    public void setY(float y) {this.y = y;}
}
