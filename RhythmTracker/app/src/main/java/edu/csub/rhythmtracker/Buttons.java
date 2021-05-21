package edu.csub.rhythmtracker;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintSet;

/**
 * Class that controls button usage and reactivity
 */
public class Buttons {

    private int button = 0;
    private Paint paint = new Paint();
    private int b1x = 0, b2x = 85, b3x = 175, b4x = 265, y = 470;
    private final MediaPlayer click;
    private final Bitmap button1, button2, button3, button4;
    private final Bitmap b1Click, b2Click, b3Click, b4Click;

    /**
     * Constructor that creates the 4 buttons (standard & clicked)
     * @param res image assets
     */
    public Buttons(Context context, Resources res) {
        button1 = BitmapFactory.decodeResource(res, R.mipmap.button1);
        button2 = BitmapFactory.decodeResource(res, R.mipmap.button2);
        button3 = BitmapFactory.decodeResource(res, R.mipmap.button3);
        button4 = BitmapFactory.decodeResource(res, R.mipmap.button4);
        b1Click = BitmapFactory.decodeResource(res, R.mipmap.b1clicked);
        b2Click = BitmapFactory.decodeResource(res, R.mipmap.b2clicked);
        b3Click = BitmapFactory.decodeResource(res, R.mipmap.b3clicked);
        b4Click = BitmapFactory.decodeResource(res, R.mipmap.b4clicked);
        click = MediaPlayer.create(context, R.raw.click);
        click.setVolume(0.7f, 0.7f);
    }

    /**
     * Draws buttons to the canvas
     * @param canvas android class that holds 'draw' calls
     * @param event
     */
    public void draw(Canvas canvas, int event){
        canvas.drawBitmap(button1, this.b1x, this.y, paint);
        canvas.drawBitmap(button2, this.b2x, this.y, paint);
        canvas.drawBitmap(button3, this.b3x, this.y, paint);
        canvas.drawBitmap(button4, this.b4x, this.y, paint);

        switch (button){
            case 1:
                    canvas.drawBitmap(b1Click, this.b1x, this.y, paint);
                break;
            case 2:
                    canvas.drawBitmap(b2Click, this.b2x, this.y, paint);
                break;
            case 3:
                    canvas.drawBitmap(b3Click, this.b3x, this.y, paint);
                break;
            case 4:
                    canvas.drawBitmap(b4Click, this.b4x, this.y, paint);
                break;
        }
        button = 0;
    }

    /**
     * Returns reference to which button needs to be updated
     * @param touchX x position of user thumb
     * @param touchY y position of user thumb
     * @return int representing which button is touched
     */
    public boolean checkClicked(int touchX, int touchY) {
        boolean bool = false;

        if(touchX > 0 && touchX < 85 && touchY >= 470 && touchY <= 550) {
            button = 1;
            click.start();
            bool = true;
        }

        if(touchX >= 85 && touchX <= 175 && touchY >= 470 && touchY <= 550) {
            button = 2;
            click.start();
            bool = true;
        }

        if(touchX >= 175 && touchX <= 265 && touchY >= 470 && touchY <= 550) {
            button = 3;
            click.start();
            bool = true;
        }

        if(touchX > 265 && touchX < 355 && touchY >= 470 && touchY <= 550) {
            button = 4;
            click.start();
            bool = true;
        }

        return bool;
    }
}
