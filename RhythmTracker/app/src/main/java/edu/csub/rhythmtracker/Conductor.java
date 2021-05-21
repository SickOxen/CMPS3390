package edu.csub.rhythmtracker;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

/**
 * Class that controls assets to be synced to the beat
 */
public class Conductor {

    public float bpm, secPerBeat, dpi;
    public float songPosition, songOffset;
    private final MediaPlayer tutorialSong, level1Song;

    /**
     * Default Constructor
     * @param res Resources
     */
    public Conductor(Context context, Resources res){
        this.dpi = res.getDisplayMetrics().densityDpi;

        this.bpm = 120;
        this.secPerBeat = (60f/bpm) * 22.5f;

        tutorialSong = MediaPlayer.create(context, R.raw.tutorial);
        level1Song = MediaPlayer.create(context, R.raw.level1);
    }

    /**
     * Updates necessary beat values between levels
     */
    public void updateLevel(int level){
        if (level == 0)
            bpm = 110;

        if(level == 1)
            bpm = 120;
    }

    /**
     * Plays the correct dependent upon passed level
     * @param level Current Level
     */
    public void startSong(int level){
        if(level == 0)
            tutorialSong.start();
        if(level == 1)
            level1Song.start();
    }

    /**
     * Gets synced beat of song & screen
     * @return Quarter Beat
     */
    public float getSecPerBeat() {return secPerBeat;}

    /**
     * Sets synced beat of song & screen
     * @param secPerBeat dpi/bpm
     */
    public void setSecPerBeat(float secPerBeat) {
        this.secPerBeat = secPerBeat;
    }
}
