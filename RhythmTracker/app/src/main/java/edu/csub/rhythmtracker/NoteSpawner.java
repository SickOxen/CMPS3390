package edu.csub.rhythmtracker;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class NoteSpawner {

    private float x, y, dpi;
    private final Bitmap note1, note2, note3, note4;
    private Paint paint = new Paint();

    public NoteSpawner(Resources res){
        dpi = res.getDisplayMetrics().densityDpi;
        note1 = BitmapFactory.decodeResource(res, R.mipmap.note1);
        note2 = BitmapFactory.decodeResource(res, R.mipmap.note2);
        note3 = BitmapFactory.decodeResource(res, R.mipmap.note3);
        note4 = BitmapFactory.decodeResource(res, R.mipmap.note4);
    }

    public void update(){
        y += 0.05f * dpi;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(note1, this.x, this.y, this.paint);
    }
}
