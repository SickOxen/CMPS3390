package edu.csub.rhythmtracker;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Class that controls button usage and reactivity
 */
public class Buttons {

    private int b1x = 0, b2x = 85, b3x = 175, b4x = 265;
    private int b1y = 470, b2y = 470, b3y = 470, b4y = 470;
    private final Bitmap button1, button2, button3, button4;
    private Paint paint = new Paint();

    /**
     * Constructor that creates the 4 required buttons
     * @param res image assets
     */
    public Buttons(Resources res) {
        button1 = BitmapFactory.decodeResource(res, R.mipmap.button1);
        button2 = BitmapFactory.decodeResource(res, R.mipmap.button2);
        button3 = BitmapFactory.decodeResource(res, R.mipmap.button3);
        button4 = BitmapFactory.decodeResource(res, R.mipmap.button4);
    }

    public void update(boolean clicked){

    }

    /**
     * Draws the buttons to the canvas
     * @param canvas android class that holds 'draw' calls
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(button1, this.b1x, this.b1y, paint);
        canvas.drawBitmap(button2, this.b2x, this.b2y, paint);
        canvas.drawBitmap(button3, this.b3x, this.b3y, paint);
        canvas.drawBitmap(button4, this.b4x, this.b4y, paint);
    }
}
