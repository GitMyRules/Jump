package com.example.mint.jump;

import android.view.View;
import java.util.ArrayList;


public class BallAndTreeMoves extends ObjectMoves{

    private int livePoints = 0;
    private int saveHighscore = 0;

    private boolean hit = false;
    private boolean energyHit = false;
    private int oneDistanceX = 70, twoDistanceX = 90, oneDistanceY = 97, twoDistanceY = 78;

    public void nimbusBallCollision(int points, int nimbusWidth, int nimbusHeight, int nimbusY, View ball, Sound sound) {

        int ballCenterX = (int) ball.getX() + ball.getWidth()/2;
        int ballCenterY = (int) ball.getY() + ball.getHeight()/2;

        if (ballCenterX >= 0 && ballCenterX <= nimbusWidth-oneDistanceX && ballCenterX >= nimbusWidth-twoDistanceX &&
                ballCenterY >= nimbusY+oneDistanceY && ballCenterY <= (nimbusY)+nimbusHeight-twoDistanceY){

                saveHighscore += points;
                sound.seekTo(0);
                sound.playBallSound();
                ball.setVisibility(View.GONE);
            hit = true;
        }else hit =false;

    }

    public void nimbusEnergyBallCollision(int nimbusWidth, int nimbusHeight, int nimbusY, View ball, Sound sound) {

        int ballCenterX = (int) ball.getX() + ball.getWidth()/2;
        int ballCenterY = (int) ball.getY() + ball.getHeight()/2;

        if (ballCenterX >= 0 && ballCenterX <= nimbusWidth-oneDistanceX && ballCenterX >= nimbusWidth-twoDistanceX &&
                ballCenterY >= nimbusY+oneDistanceY && ballCenterY <= (nimbusY)+nimbusHeight-twoDistanceY){

            livePoints += 10;
            sound.seekTo(0);
            sound.playBallSound();

            ball.setVisibility(View.GONE);
            energyHit = true;
        } else energyHit = false;

    }

    public void nimbusEvilBallCollision(int points, int nimbusWidth, int nimbusHeight, int nimbusY, View ball, Sound sound) {

        int ballCenterX = (int) ball.getX() + ball.getWidth()/2;
        int ballCenterY = (int) ball.getY() + ball.getHeight()/2;

        if (ballCenterX >= 0 && ballCenterX <= nimbusWidth-oneDistanceX && ballCenterX >= nimbusWidth-twoDistanceX &&
                ballCenterY >= nimbusY+oneDistanceY && ballCenterY <= (nimbusY)+nimbusHeight-twoDistanceY){
            this.livePoints -= points;
            sound.seekTo(0);
            sound.playEvilBallSound();
            ball.setX(-100);

        }
    }

    @Override
    public void moves(int outOfScreen, int speed, int frameHeight, int screenWidth, ArrayList<View> img) {
        super.moves(outOfScreen, speed, frameHeight, screenWidth, img);
    }

    public void setBallAndTreePositions(FindView findView){

    //set the balls and trees out of screen
        for(View view : findView.getBalls()){
            view.setX(-70);
            view.setY(-70);
        }
        for(View eachBalls : findView.getEvilballs()){
            eachBalls.setX(-70);
            eachBalls.setY(-70);
        }

        for (View eachBalls: findView.getEnergyBalls()) {
            eachBalls.setX(-160);
            eachBalls.setY(-160);
        }

        FindView findFollingTree = new FindView();
        for(int i = 0; i < findView.getTrees().size(); i++){
            findView.getTrees().get(i).setX((int) Math.floor(Math.random() * (i*800)));
            if(findView.getTrees().get(i).getX()/2 >= findFollingTree.getTrees().get(++i).getX() && i <= findView.getTrees().size());
                findView.getTrees().get(i).setX((int) Math.floor(Math.random() * (i*800)));
            findView.getTrees().get(i).setY(((int) Math.floor(Math.random() * (i*1100))));
        }
    }

    public void setLivePoints(int live){
        livePoints = live;
    }
    public int getLivePoints(){
        if (livePoints <= 0 )
            livePoints = 0;
        return livePoints;
    }
    public int getSaveHighScore() { return saveHighscore; }

    public boolean getHit(){
        return hit;
    }
    public boolean getEnergyHit(){
        return energyHit;
    }
}
