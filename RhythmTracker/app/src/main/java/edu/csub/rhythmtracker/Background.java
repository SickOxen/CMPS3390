package edu.csub.rhythmtracker;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Class that controls any Background instances
 */
public class Background {

    private Bitmap background;
    private int screenX, screenY;
    private Paint paint = new Paint();
    private float dpi;

    private float x = 0f;
    private float y = 0f;

    /**
     * Constructor that creates Background Activity
     * @param screenX x position of user screen
     * @param screenY y position of user screen
     * @param res Android Folder containing assets
     */
    public Background(int screenX, int screenY, Resources res){
        this.screenX = screenX;
        this.screenY = screenY;
        this.background = BitmapFactory.decodeResource(res, R.mipmap.bg1norm);
        this.background = Bitmap.createScaledBitmap(this.background, screenX, screenY, false);
        this.dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * Get screen width
     * @return users screen height x
     */
    public float getX() {return x;}

    /**
     * Set screen width
     * @param x passed int of width x
     */
    public void setX(float x) {this.x = x;}

    /**
     * Get screen height
     * @return users screen height y
     */
    public float getY() {return y;}

    /**
     * Set screen height
     * @param y passed int of height y
     */
    public void setY(float y) {this.y = y;}

    /**
     * Updates the background by scrolling the image downwards
     */
    public void update() {
        this.y += 0.006f * dpi;
        if(this.y > screenY){
            this.y = -screenY;
        }
    }

    /**
     * Draws the background to the canvas
     * @param canvas android class that holds 'draw' calls
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.background, this.x, this.y, paint);
    }
}
