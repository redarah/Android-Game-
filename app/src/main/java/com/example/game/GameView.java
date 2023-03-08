package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    Bitmap background ;
    Rect rect;
    static int dwidth,dheight;
    Handler handler ;
    Runnable runnable;
    final long UPDATE_MILIS = 30;
    ArrayList<Bird1> bird1;
    ArrayList<Bird2> bird2;
    Bitmap ball,target;
    float ballX,ballY;
    float sX,sY;
    float fX,fY;
    float dX,dY;
    float tempX,tempY;
    Paint borderPaint;
    int score ;
    int life =2;
    Context context;
    boolean gameState = true;


    public GameView(Context context) {
        super(context);
        background= BitmapFactory.decodeResource(getResources(),R.drawable.backround);
        Display display=((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        dwidth=size.x;
        dheight=size.y;
        rect=new Rect(0,0,dwidth,dheight);
        bird1 = new ArrayList<>();
        bird2 = new ArrayList<>();
        for (int i=0 ;i<2;i++){
            Bird1 bird_1=new Bird1(context);
            bird1.add(bird_1);
            Bird2 bird_2=new Bird2(context);
            bird2.add(bird_2);
        }
        ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        target = BitmapFactory.decodeResource(getResources(),R.drawable.sniper);
        ballX = ballY = 0 ;
        sX = sY = fY = fX = 0;
        dX = dY = 0 ;
        tempX = tempY =0;
        score = 0;

        borderPaint = new Paint();
        borderPaint.setColor(Color.RED);
        borderPaint.setStrokeWidth(5);
        this.context = context;

        handler = new Handler();
        runnable =  new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(life<1){
            gameState = false;
            Intent intent = new Intent(context,GameOver.class);
            intent.putExtra("score",score);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        canvas.drawBitmap(background ,null,rect,null);
        for (int i=0 ; i<bird1.size()  ;i++){
            canvas.drawBitmap(bird1.get(i).getBitmap(),bird1.get(i).birdX,bird1.get(i).birdX,null);
            canvas.drawBitmap(bird2.get(i).getBitmap(),bird2.get(i).birdX,bird2.get(i).birdX,null);

            bird1.get(i).birdFrame++;
            bird2.get(i).birdFrame++;

            if (bird1.get(i).birdFrame >7 ){
                bird1.get(i).birdFrame=0;
            }

            if (bird2.get(i).birdFrame > 7){
                bird2.get(i).birdFrame=0;
            }
            bird1.get(i).birdX -= bird1.get(i).velocity;
            bird2.get(i).birdX -= bird2.get(i).velocity;
            if (bird1.get(i).birdX<bird1.get(i).birdWidth  ){
                bird1.get(i).resetPosition();
                life --;
            }
            if (bird2.get(i).birdX<bird2.get(i).birdWidth) {
                bird2.get(i).resetPosition();
                life -- ;
            }
            if(ballX <= (bird1.get(i).birdX + bird1.get(i).getWidth()) && ballX + ball.getWidth() >= bird1.get(i).birdX
                && ballY <= (bird1.get(i).birdY + bird1.get(i).getheight()) && ballY + ball.getHeight() >= bird1.get(i).birdY ){
                bird1.get(i).resetPosition();
                score++;
            }
            if(ballX <= (bird2.get(i).birdX + bird2.get(i).getWidth()) && ballX + ball.getWidth() >= bird2.get(i).birdX
                    && ballY <= (bird2.get(i).birdY + bird2.get(i).getheight()) && ballY + ball.getHeight() >= bird2.get(i).birdY ){
                bird2.get(i).resetPosition();
                score++;

            }

        }
        if(sX > 0 && sY > dheight*0.75f){
            canvas.drawBitmap(target,sX-target.getWidth()/2,sY-target.getHeight()/2,null);
        }
        if((Math.abs(fX-sX)>0 || Math.abs(fY-sY)>0)&& fY>0 && fY > dheight*0.75f) {
            canvas.drawBitmap(target,fX-target.getWidth()/2,fY-target.getHeight()/2,null);
        }
        if((Math.abs(dX)>10 || Math.abs(dY)>10 )&& sY > dheight*0.75f && fY > dheight*0.75f) {
            ballX = fX - ball.getWidth()/2-tempX;
            ballY= fY - ball.getHeight()/2-tempY;
            canvas.drawBitmap(ball,ballX,ballY,null);
            tempX += dX ;
            tempY += dY ;
        }
        canvas.drawLine(0,dheight*0.75f,dwidth,dheight*0.75f,borderPaint);
        if(gameState)
        handler.postDelayed(runnable,UPDATE_MILIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                dX = dY = fY = fX = tempX = tempY = 0;
                sX = event.getX();
                sY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                fX = event.getX();
                fY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                ballX = event.getX();
                ballY = event.getY();
                dX = fX-sX;
                dY = fY-sY;

                break;

        }
        return true;
    }
}
