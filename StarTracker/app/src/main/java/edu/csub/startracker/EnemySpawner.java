package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Class that controls the amount of enemy ships that are spawned per wave
 */
public class EnemySpawner {

    private ArrayList<GameObject> enemies;
    Resources res;
    float x, y = 0;
    int screenWidth, wave = 1;
    int frameTick = 0, waveTick = 0, spawnTick;
    int enemy01Spawned = 0, enemy02Spawned = 0;
    private Paint paint = new Paint();
    private final MediaPlayer dead;

    /**
     * Default Constructor
     * @param res android resources
     */
    public EnemySpawner(Resources res, Context context){
        enemies = new ArrayList<>();
        screenWidth = res.getDisplayMetrics().widthPixels;
        this.res = res;
        spawnTick = new Random().nextInt(120-60) + 60;
        paint.setColor(Color.WHITE);
        paint.setTextSize(screenWidth * 0.05f);
        dead = MediaPlayer.create(context, R.raw.bang);
    }

    /**
     * Continuously checks the amount of enemies spawned and endlessly creates more
     */
    public void update(){
        frameTick++;

        if(frameTick >= spawnTick){
            frameTick = 0;
            spawnTick = new Random().nextInt(120-60) + 60;
            x = new Random().nextInt((int)(screenWidth * 0.6f - screenWidth * 0.1f)) + screenWidth * 0.1f;
            int tmp = (int)Math.round(Math.random());

            if(tmp == 0 && enemy01Spawned < wave) {
                enemies.add(new Enemy01(res, x, y));
                enemy01Spawned++;
            } else {
               tmp = 1;
            }

            if(tmp == 1 && enemy02Spawned < wave){
                enemies.add(new Enemy02(res, x, y));
                enemy02Spawned++;
            }
        }

        if(enemy01Spawned >= wave && enemy02Spawned >= wave){
            waveTick++;
            if(waveTick >= 240) {
                wave++;
                waveTick = 0;
                enemy01Spawned = 0;
                enemy02Spawned = 0;
            }
        }

        for(Iterator<GameObject> iterator = enemies.iterator(); iterator.hasNext();){
            GameObject go = iterator.next();
            go.update();
            if(!go.isAlive()) {
                dead.start();
                iterator.remove();
            }
        }
    }

    /**
     * Continuously display enemy ships and wave count
     * @param canvas android activity of the game
     */
    public void draw(Canvas canvas){
        canvas.drawText("WAVE: " + wave, screenWidth * 0.05f, screenWidth * 0.05f, paint);
        for(GameObject go : enemies)
            go.draw(canvas);
    }

    /**
     * Getter function
     * @return all live enemies
     */
    public ArrayList<GameObject> getEnemies(){return enemies;}
}
