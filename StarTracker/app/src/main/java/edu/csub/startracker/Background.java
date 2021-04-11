package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Class that controls game Background effects
 */
public class Background {

    private Bitmap background;
    private int screenX, screenY;
    private Paint paint = new Paint();
    private float dpi;
    private float x = 0f;
    private float y = 0f;

    /**
     * Param Constructor
     * @param screenX size of screen width
     * @param screenY size of screen height
     * @param res game resources saved by android
     */
    public Background(int screenX, int screenY, Resources res){
        this.screenX = screenX;
        this.screenY = screenY;
        this.background = BitmapFactory.decodeResource(res, R.mipmap.background);
        this.background = Bitmap.createScaledBitmap(this.background, screenX, screenY, false);
        this.dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * Getter function
     * @return screen width
     */
    public float getX() {return x;}

    /**
     * Setter function
     * @param x screen width
     */
    public void setX(float x) {this.x = x;}

    /**
     * Getter function
     * @return screen height
     */
    public float getY() {return y;}

    /**
     * Setter function
     * @param y screen height
     */
    public void setY(float y) {this.y = y;}

    /**
     * Continuously scrolls background image
     */
    public void update() {
        this.y += 0.006f * dpi;
        if(this.y > screenY)
            this.y = -screenY;
    }

    /**
     * Draws the image onto the activity
     * @param canvas android canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.background, this.x, this.y, paint);
    }
}
