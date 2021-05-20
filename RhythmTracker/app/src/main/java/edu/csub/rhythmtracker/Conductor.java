package edu.csub.rhythmtracker;
import android.content.res.Resources;

/**
 * Class that controls assets to be synced to the beat
 */
public class Conductor {

    public float bpm, quarterBeat, dpi;
    public float songPosition, songOffset;

    /**
     * Default Constructor
     * @param res Resources
     */
    public Conductor(Resources res){
        this.bpm = 110;  // bpm of tutorial song
        this.dpi = res.getDisplayMetrics().densityDpi;
        this.quarterBeat = (dpi/bpm) * 6;
    }

    /**
     * Updates necessary beat values between levels
     */
    public void update(){

    }

    /**
     * Gets synced beat of song & screen
     * @return Quarter Beat
     */
    public float getQuarterBeat() {return quarterBeat;}

    /**
     * Sets synced beat of song & screen
     * @param quarterBeat dpi/bpm
     */
    public void setQuarterBeat(float quarterBeat) {
        this.quarterBeat = quarterBeat;
    }
}
