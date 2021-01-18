package com.example.mint.jump;

import android.view.View;

import java.util.ArrayList;


public abstract class ObjectMoves {

    public void moves(int outOfScreen, int speed, int frameHeight, int screenWidth, ArrayList<View> img){

        //Fixme: change positions while moving to not overlap the ball objects each other.

         for (int i = 0; i < img.size(); i++) {

            int secondX = (int)img.get(i).getX();
            int secondY = (int)img.get(i).getY();

            secondX -= speed;

            if (secondX < outOfScreen){
                int newXpos = screenWidth + (int)Math.floor(Math.random() * (screenWidth - img.get(i).getWidth()));
                int newYPos =  (int)Math.floor(Math.random() * (frameHeight - img.get(i).getHeight()));

                secondX = newXpos;
                secondY = newYPos;

            }

            img.get(i).setX(secondX);
            img.get(i).setY(secondY);

        }

    }
}