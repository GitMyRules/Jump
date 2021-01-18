package com.example.mint.jump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;



public class GameHandling {

    private BallAndTreeMoves ballAndTree = new BallAndTreeMoves();

    private int frameHeight;
    private int screenWidth;
    private int screenHeight;
    private int nimbusSpeed;
    private int ballSpeed, energyBallSpeed;
    private int evilBallSpeed;
    private int treeSpeed;
    private int nimbusY;
    private Sound sound;
    private boolean actionFlag = false;
    private boolean startFlag = false;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    FrameLayout frame;

    FindView findView = new FindView();

    public void findAllViews(Activity activity){
        frame = activity.findViewById(R.id.frame);
        findView.findBalls(activity);
        findView.findTrees(activity);
        findView.findTextViews(activity);
        findView.findNimbus(activity);
        findView.findEvilBalls(activity, frame);
        findView.findenergyBall(activity);
        sound = new Sound(activity);
        sound.playSound();

        nimbusSpeed = Math.round(screenHeight/ 70);
        ballSpeed = Math.round(screenWidth/ 65);
        energyBallSpeed = Math.round(screenWidth/ 80);
        evilBallSpeed = Math.round(screenWidth/65);
        treeSpeed = Math.round(screenWidth/ 80);

        ballAndTree.setBallAndTreePositions(findView);
        ballAndTree.setLivePoints(20);
    }

    public void windowSize(Activity activity){

        WindowManager wm = activity.getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;
    }
    public void hitCheck(Activity activity){

        for (int i = 0; i < findView.getBalls().size(); i++) {
            ballAndTree.nimbusBallCollision(i+1, findView.getNimbus().getWidth(), findView.getNimbus().getHeight(),
                    (int)findView.getNimbus().getY(), findView.getBalls().get(i), sound);
            if (ballAndTree.getHit()) {
                findView.getBalls().remove(i);
                ballSpeed += 2.2f;
                evilBallSpeed += 2.2f;
                treeSpeed += 1.5f;
            }
        }

        for (int i = 0; i < findView.getEnergyBalls().size(); i++) {
            ballAndTree.nimbusEnergyBallCollision(findView.getNimbus().getWidth(), findView.getNimbus().getHeight(),
                    (int)findView.getNimbus().getY(), findView.getEnergyBalls().get(i), sound);
           if (ballAndTree.getEnergyHit()) {
                findView.getEnergyBalls().remove(i);
                ballSpeed += 1.5f;
                evilBallSpeed += 1.5f;
                treeSpeed += 1f;
            }
        }

        for (int i = 0; i < findView.getEvilballs().size(); i++) {
            ballAndTree.nimbusEvilBallCollision(i+1, findView.getNimbus().getWidth(), findView.getNimbus().getHeight(),
                    (int)findView.getNimbus().getY(), findView.getEvilballs().get(i), sound);
        }

        gameOver(activity);
    }

    public void gameOver(Activity activity){

        if(ballAndTree.getLivePoints() == 0) {
            sound.stopPlaySound();
            sound.stopBallSound();
            sound.stopEvilBallSound();
            Intent intent = new Intent(activity, Result.class);
            intent.putExtra("SCORE", ballAndTree.getSaveHighScore());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
            activity.finish();

        }
    }

    public void touch(MotionEvent touch, final Activity activity){

        if(!startFlag && ballAndTree.getLivePoints() > 0){
            frameHeight = frame.getHeight();
            startFlag = true;
            findView.getStartLabel().setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(ballAndTree.getLivePoints() > 0) {
                                changePosition(activity);
                            }
                            else{
                                timer = null;
                            }
                        }
                    });
                }
            },0,20);
        }else {
            if (touch.getAction() == MotionEvent.ACTION_DOWN) {
                actionFlag = true;
            } else if (touch.getAction() == MotionEvent.ACTION_UP)
                actionFlag = false;
            if (touch.getAction() == KeyEvent.KEYCODE_BACK) {
                sound.stopPlaySound();
                sound.stopBallSound();
                sound.stopEvilBallSound();
            }
        }
    }

    public void changePosition(Activity activity){

        ballAndTree.moves(-20,ballSpeed,frameHeight, screenWidth, findView.getBalls());

        ballAndTree.moves(-20,energyBallSpeed,frameHeight, screenWidth, findView.getEnergyBalls());

        ballAndTree.moves(-20,evilBallSpeed,frameHeight, screenWidth, findView.getEvilballs());

        ballAndTree.moves(-1000,treeSpeed,frameHeight, screenWidth, findView.getTrees());


        if (actionFlag == true)
            nimbusY -= nimbusSpeed;
        else
            nimbusY += nimbusSpeed;

        if(nimbusY < 0)
            nimbusY = 0;
        if (nimbusY > frameHeight-findView.getNimbus().getHeight())
            nimbusY = (frameHeight-findView.getNimbus().getHeight());

        findView.getNimbus().setY(nimbusY);
        hitCheck(activity);

        findView.getLiveLabel().setText("live: "+ ballAndTree.getLivePoints());
        findView.getScoreLabel().setText("Score: "+ ballAndTree.getSaveHighScore());
    }

    public void setLiveText(){ findView.setLiveLabelString(""); }
    public void setScoreText(){ findView.setScoreLabelString(""); }

}
