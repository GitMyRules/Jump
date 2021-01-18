package com.example.mint.jump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;


public class MainActivity extends AppCompatActivity{

    private GameHandling gameHandling;
    private FullscreenFragment fullscreenFragment = new FullscreenFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullscreenFragment.setFullscreen(this);

        gameHandling = new GameHandling();

        gameHandling.windowSize(this);
        gameHandling.findAllViews(this);
        gameHandling.setScoreText();
        gameHandling.setLiveText();

    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameHandling.touch(event, this);
        fullscreenFragment.onKeyDown(event.getAction());
        return true;
    }
}
