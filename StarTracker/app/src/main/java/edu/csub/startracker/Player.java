package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

public class Player {

    private float x, y, prevX, prevY;
    private final Bitmap playerImg, playerLeft, playerRight;
    private Bitmap curImage;
    private Paint paint = new Paint();
    private final float dpi;
    private int frameTicks = 0;

    public Player(Resources res){
        playerImg = BitmapFactory.decodeResource(res, R.mipmap.player);
        playerLeft = BitmapFactory.decodeResource(res, R.mipmap.player_left);
        playerRight = BitmapFactory.decodeResource(res, R.mipmap.player_right);
        curImage = playerImg;

        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;
        x = (dm.widthPixels / 2f) - (playerImg.getHeight() / 2f);
        y = (dm.heightPixels * 0.75f);
    }

    public void update(int touchX, int touchY){

        if(touchX > 0 && touchY > 0){
            this.x = touchX - (playerImg.getWidth() / 2f);
            this.y = touchY - (playerImg.getHeight() * 2f);
        }

        if(Math.abs(x - prevX) < 0.04 * dpi){
            frameTicks++;
        } else if (frameTicks > 5){
            frameTicks = 0;
        }

        if(this.x < prevX - (0.04 * dpi)){
            curImage = playerLeft;
        }
        else if(this.x > prevX + (0.04 * dpi)){
            curImage = playerRight;
        } else {
            curImage = playerImg;
        }

        prevX = x;
        prevY = y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(curImage, this.x, this.y, this.paint);
    }
}
