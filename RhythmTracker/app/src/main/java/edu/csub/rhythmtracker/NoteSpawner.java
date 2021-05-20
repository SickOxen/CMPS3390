package edu.csub.rhythmtracker;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.ArrayList;

/**
 * Class that controls the notes displayed according to the music
 */
public class NoteSpawner {

    private float x, y, dpi;
    private Conductor beat;
    private final Bitmap note1, note2, note3, note4;
    private final int noteWidth, noteHeight;
    private ArrayList <NoteSpawner> notes;
    private Paint paint = new Paint();

    /**
     * Default Constructor
     * @param res Resources
     */
    public NoteSpawner(Resources res){
        notes = new ArrayList<>();
        note1 = BitmapFactory.decodeResource(res, R.mipmap.note1);
        note2 = BitmapFactory.decodeResource(res, R.mipmap.note2);
        note3 = BitmapFactory.decodeResource(res, R.mipmap.note3);
        note4 = BitmapFactory.decodeResource(res, R.mipmap.note4);
        beat = new Conductor(res);
        dpi = res.getDisplayMetrics().densityDpi;
        noteWidth = note1.getWidth();
        noteHeight = note1.getHeight();
    }

    /**
     * Updates Note instances based on song
     */
    public void update(){
        y += beat.getQuarterBeat();
    }

    /**
     * Draws Notes to the screen
     * @param canvas GameView
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(note1, this.x, this.y, this.paint);
        canvas.drawBitmap(note2, this.x + 85, this.y, this.paint);
        canvas.drawBitmap(note3, this.x + 175, this.y, this.paint);
        canvas.drawBitmap(note4, this.x + 265, this.y, this.paint);
    }

    /**
     * Checks if note goes off screen
     * @return T or F
     */
    public boolean isOffScreen(){return !(y > getScreenHeight());}

    /**
     * Gets exact height of user screen
     * @return screen height
     */
    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * Get width of note
     * @return width
     */
    public float getWidth(){return noteWidth;}

    /**
     * Get length of note
     * @return height
     */
    public float getHeight(){return noteHeight;}

    /**
     * Get x-position of note
     * @return x
     */
    public float getX(){return  x;}

    /**
     * Set current x-position of note
     */
    public void setX(){this.x = x;}

    /**
     * Get y-position of note
     * @return y
     */
    public float getY(){return  y;}

    /**
     * Set current y-position of note
     */
    public void setY(){this.x = y;}
}
