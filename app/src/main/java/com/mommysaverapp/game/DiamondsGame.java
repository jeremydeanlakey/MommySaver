package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;


//package com.example.bennysapp;

import android.view.View;

/**
 * Created by jeremy on 1/21/14.
 */

/**
 * Created by jeremy on 1/21/14.
 */
public class DiamondsGame extends Game {

    float progress ;

    public DiamondsGame(){
        progress = 0;
    }

    @Override
    public void onTouchEvent(View view, MotionEvent event){
    }

    @Override
    public void onDraw(Canvas canvas) {
        Rect bg = new Rect();
        bg.set(0, 0, canvas.getWidth(), canvas.getHeight());
        Paint fillWhite = new Paint();
        fillWhite.setStyle(Paint.Style.FILL);
        fillWhite.setColor(Color.WHITE);
        canvas.drawRect(bg, fillWhite);


        Paint fillStripe = new Paint();
        fillStripe.setStyle(Paint.Style.FILL);
        fillStripe.setColor(Color.BLACK);

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int stripeCount = 5 * 2;
        int stripeWidth = w * 2 / stripeCount;
        //int segmentLength = canvas.getHeight();
        for (int i = 0; i < stripeCount; i++){
            // draw from segmentLength * (progress - 1), segmentWidth * (i-1)
            //      to segmentLength * progress, segmentWidth * (i+1)
            Path stripe = new Path();
            stripe.reset();
            stripe.moveTo(-w + (i+progress) * stripeWidth,0);
            stripe.lineTo(-w + (i+progress) * stripeWidth,0);
            stripe.lineTo((i+progress+1) * stripeWidth,h);
            stripe.lineTo((i+progress) * stripeWidth,h);
            stripe.lineTo(-w + (i+progress) * stripeWidth,0);
            canvas.drawPath(stripe,fillStripe);
        }
        progress += 0.10;
        if (progress >= 1) progress = 0;
    }

}
