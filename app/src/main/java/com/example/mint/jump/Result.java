package com.example.mint.jump;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    private FullscreenFragment fullscreenFragment = new FullscreenFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        fullscreenFragment.setFullscreen(this);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highscore);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText("score: "+score);
        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore){
            highScoreLabel.setText("High Score: "+score);

            //save high score
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }else{
            highScoreLabel.setText("High Score: "+ highScore);
        }

    }

    public void tryAgain(View view){
        Intent intent = new Intent(this, Start.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void closeGame(View view){
        finishAndRemoveTask();
        System.exit(0);
    }
}