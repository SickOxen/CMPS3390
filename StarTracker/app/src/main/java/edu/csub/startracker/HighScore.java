package edu.csub.startracker;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that controls the high scores of all players on server
 */
public final class HighScore {

    private static final HighScore INSTANCE = new HighScore();
    private int curScore = 0;
    private int highScore = 0;
    private String name = "Player";
    private FirebaseFirestore db;

    /**
     * Default Constructor
     */
    private HighScore(){
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Getter
     * @return Current Games High Score
     */
    public static HighScore getInstance(){
        return INSTANCE;
    }

    /**
     * Measures player score and sets if record is broken
     * @param score player score
     */
    public void addScore(int score){
        curScore += score;
        if(curScore > highScore)
            highScore = curScore;
    }

    /**
     * Getter
     * @return Current Score
     */
    public int getCurScore() {return curScore;}

    /**
     * Getter
     * @return High Score
     */
    public int getHighScore() {return highScore;}

    /**
     * Sets current score to zero
     */
    public void resetCurScore() {curScore = 0;}

    /**
     * Setter
     * @param playerName Name user enters on home screen
     */
    public void setPlayerName(String playerName) {name = playerName;}

    /**
     * Getter
     * @return Player Name
     */
    public String getPlayerName() {return name;}

    /**
     * Retrieves ArrayList of all-time Highest Scores from Firebase db
     * @param howMany pre-decided number of scores to grab
     * @param highScores list of scores
     * @param context android context
     */
    public void getHighScores(int howMany, ListView highScores, Context context){
        ArrayList<String> topScores = new ArrayList<>();

        db.collection("HighScore")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(howMany)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                String tmpString = String.format("%s : %s", doc.getId(),
                                        doc.get("score"));
                                topScores.add(tmpString);
                                Log.d("SCORE", tmpString);
                            }
                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(context,
                                    android.R.layout.simple_list_item_1, topScores);
                            highScores.setAdapter(itemsAdapter);
                        }
                    }
                });
    }

    /**
     * Displays High Scores on Home Screen
     */
    public void postHighScore(){
        Map<String, Integer> hs = new HashMap<>();
        hs.put("score", highScore);
        db.collection("HighScore")
                .document(name)
                .set(hs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("data", name + "'s score was set");
                    }
                });
    }
}
