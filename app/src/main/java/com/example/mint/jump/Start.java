package com.example.mint.jump;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    private Sound sound;
    private GameHandling gameHandling;
    private FullscreenFragment fullscreenFragment = new FullscreenFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        fullscreenFragment.setFullscreen(this);

        gameHandling = new GameHandling();

        sound = new Sound(this);
        sound.mainSound();

    }

    public void startGame(View view){

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sound.stopMainSound();
        startActivity(intent);
        finish();
    }


    public void closeGame(View view){

        finishAndRemoveTask();
        System.exit(0);
    }
}
