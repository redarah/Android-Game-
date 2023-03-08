package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bird2 extends Bird1{
    Bitmap[] bird = new Bitmap[8];
    public Bird2(Context context) {
        super(context);
        bird[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_1);
        bird[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_2);
        bird[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_3);
        bird[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_31);
        bird[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_4);
        bird[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_5);
        bird[6]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_6);
        bird[7]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bird2_7);
        resetPosition();

    }

    @Override
    public Bitmap getBitmap() {
        return bird[birdFrame];
    }

    @Override
    public int getWidth() {
        return bird[0].getWidth();
    }

    @Override
    public int getheight() {
        return bird[0].getHeight();
    }

    @Override
    public void resetPosition() {
        birdX = GameView.dwidth + random.nextInt(1500);
        birdY = random.nextInt(400);
        velocity= 16 + random.nextInt(19);
    }
}
