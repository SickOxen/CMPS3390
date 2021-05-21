package edu.csub.rhythmtracker;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Class that controls the notes displayed according to the music
 */
public class NoteSpawner {

    private ArrayList <NoteSpawner> notes;
    private Conductor conductor;
    private int note;
    private float x, y, dpi;
    private final int noteWidth, noteHeight;
    private final Random randInt;
    private final Bitmap note1, note2, note3, note4;
    private Paint paint = new Paint();
    private MediaPlayer miss;

    /**
     * Default Constructor
     * @param res Resources
     */
    public NoteSpawner(Context context, Resources res){
        dpi = res.getDisplayMetrics().densityDpi;

        notes = new ArrayList<>();
        note1 = BitmapFactory.decodeResource(res, R.mipmap.note1);
        note2 = BitmapFactory.decodeResource(res, R.mipmap.note2);
        note3 = BitmapFactory.decodeResource(res, R.mipmap.note3);
        note4 = BitmapFactory.decodeResource(res, R.mipmap.note4);

        randInt = new Random();
        noteWidth = note1.getWidth();
        noteHeight = note1.getHeight();

        conductor = new Conductor(context, res);
        miss = MediaPlayer.create(context, R.raw.miss);
    }

    /**
     * Updates Note instances based on song
     */
    public void update(){

        y += conductor.getSecPerBeat();
        if(y >= getScreenHeight())
            y = 0;

        note = randInt.nextInt(5);
        //notes.add(new note);

        for(Iterator<NoteSpawner> iterator = notes.iterator(); iterator.hasNext();){
            NoteSpawner ns = iterator.next();
            ns.update();
            if(ns.isOffScreen()){
                miss.start();
                iterator.remove();
            }
        }
    }

    /**
     * Draws Notes to the screen
     * @param canvas GameView
     */
    public void draw(Canvas canvas) {
        if (note == 1)
            canvas.drawBitmap(note1, this.x, this.y, this.paint);

        if (note == 2)
            canvas.drawBitmap(note2, this.x + 85, this.y, this.paint);

        if (note == 3)
            canvas.drawBitmap(note3, this.x + 175, this.y, this.paint);

        if (note == 4)
            canvas.drawBitmap(note4, this.x + 265, this.y, this.paint);
    }

    /**
     * Checks if note goes off screen
     * @return T or F
     */
    public boolean isOffScreen(){return (y > getScreenHeight());}

    /**
     * Gets exact height of user screen
     * @return screen height
     */
    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
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
    public void setX(){}

    /**
     * Get y-position of note
     * @return y
     */
    public float getY(){return  y;}

    /**
     * Set current y-position of note
     */
    public void setY(){}
}
