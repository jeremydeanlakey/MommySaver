package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.mommysaverapp.toy.StripeToy;
import com.mommysaverapp.toy.Toy;

import java.util.ArrayList;

//package com.example.bennysapp;


/**
 * Created by jeremy on 1/21/14.
 */
public class StripesGame extends Game {

    private int stripeColor;
    private float progress ;
    private static final int numberOfStripes = 10;
    private static final float velocity = 0.05f;

    public StripesGame() {
        super();
        toys = new ArrayList<Toy>();
        for (int i = 0; i < numberOfStripes; i++){
            float step = 1.0f/numberOfStripes;
            toys.add(new StripeToy(i * step, step));
        }
        stripeColor = Color.BLACK;
        progress = 0;
    }

    @Override
    protected void drawForeground(Canvas canvas){
        Rect r;
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int y0, y1;
        Paint paint = new Paint();
        paint.setColor(stripeColor);
        paint.setStyle(Paint.Style.FILL);
        int progressInt = (int) (progress * h / numberOfStripes);
        for (int i = -1; i < numberOfStripes; i+=2){
            y0 = i * h / numberOfStripes + progressInt;
            y1 = (i+1) * h / numberOfStripes + progressInt;
            canvas.drawRect(0, y0, w, y1, paint);
        }
        progress += velocity;
        if (progress >= 2) progress = 0;
    }
}
