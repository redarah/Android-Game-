package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Bird1 {
    Bitmap bird[] = new Bitmap[8];
    int birdX,birdY,velocity,birdFrame,birdWidth;
    Random random;
    public Bird1(Context context) {
        bird[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_1);
        bird[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_2);
        bird[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_3);
        bird[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_4);
        bird[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_5);
        bird[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_6);
        bird[6]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_7);
        bird[7]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird1_8);
        random = new Random();
        birdFrame=0;
        resetPosition();
    }

    public Bitmap getBitmap(){
        return bird[birdFrame];
    }
    public int getWidth(){
        return bird[0].getWidth();
    }
    public int getheight(){
        return bird[0].getHeight();
    }
    public void resetPosition(){
        birdX = GameView.dwidth + random.nextInt(1200);
        birdY = random.nextInt(300);
        velocity= 14 + random.nextInt(17);
    }
}
