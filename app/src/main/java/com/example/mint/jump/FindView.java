package com.example.mint.jump;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;


import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FindView {


    private ArrayList<View> ballList = new ArrayList<>();
    private ArrayList<View> energyBall = new ArrayList<>();
    private ArrayList<View>  evilballs = new ArrayList<>();
    private ArrayList<View>  trees = new ArrayList<>();

    private pl.droidsonroids.gif.GifImageView nimbus;
    private TextView scoreLabel;
    private TextView liveLabel;
    private TextView startLabel;


    public void findEvilBalls(Activity activity, FrameLayout frameLayout){

        for (int i = 0; i < 6; i++){

            View evilBallView = LayoutInflater.from(activity).inflate(R.layout.evilballs, null);
            ImageView evil = evilBallView.findViewById(R.id.evil);

            if (evil.getParent() != null)
                ((ViewGroup)evil.getParent()).removeView(evil);

            frameLayout.addView(evil);
            evilballs.add(evil);

            Log.d(TAG, "findEvils: --------------------> "+evilballs.get(i));

        }
    }


    public void findTrees(Activity find){
        trees.add(find.findViewById(R.id.tree1));
        trees.add(find.findViewById(R.id.tree2));
        trees.add(find.findViewById(R.id.tree3));
        trees.add(find.findViewById(R.id.tree4));
        trees.add(find.findViewById(R.id.tree5));
        trees.add(find.findViewById(R.id.tree6));
        trees.add(find.findViewById(R.id.tree7));
        trees.add(find.findViewById(R.id.tree8));
        trees.add(find.findViewById(R.id.tree9));
        trees.add(find.findViewById(R.id.plant1));
        trees.add(find.findViewById(R.id.plant2));
        trees.add(find.findViewById(R.id.plant3));

    }

    public void findBalls(Activity find){
        ballList.add(find.findViewById(R.id.ball1));
        ballList.add(find.findViewById(R.id.ball2));
        ballList.add(find.findViewById(R.id.ball3));
        ballList.add(find.findViewById(R.id.ball4));
        ballList.add(find.findViewById(R.id.ball5));
        ballList.add(find.findViewById(R.id.ball6));
        ballList.add(find.findViewById(R.id.ball7));
        energyBall.add(find.findViewById(R.id.energyBall));

    }

    public void findTextViews(Activity find){
        liveLabel = find.findViewById(R.id.liveLabel);
        scoreLabel = find.findViewById(R.id.scoreLabel);
        startLabel = find.findViewById(R.id.startLabel);
    }

    public void findNimbus(Activity view){
        nimbus = view.findViewById(R.id.nimbus);
    }

    public void findenergyBall(Activity activity){
        energyBall.add(activity.findViewById(R.id.energyBall));
    }

    public ImageView getNimbus() {
        return nimbus;
    }
    public ArrayList<View> getBalls() {
        return ballList;
    }
    public ArrayList<View> getEnergyBalls() {
        return energyBall;
    }
    public ArrayList<View> getTrees() {
        return trees;
    }
    public ArrayList<View> getEvilballs() {
            return evilballs;
    }

    public TextView getScoreLabel() { return scoreLabel; }
    public TextView getStartLabel() { return startLabel; }
    public TextView getLiveLabel() { return liveLabel; }
    public void setScoreLabelString(String text) { scoreLabel.setText(text); }
    public void setLiveLabelString(String text) { liveLabel.setText(text); }

}