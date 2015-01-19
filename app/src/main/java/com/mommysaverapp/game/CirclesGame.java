package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;


//package com.example.bennysapp;

import com.mommysaverapp.toy.CircleToy;
import com.mommysaverapp.toy.Toy;


/**
 * Created by jeremy on 1/21/14.
 */
public class CirclesGame extends Game {

    private Random random = new Random();
    private int bgColor;

    public CirclesGame(boolean blackOnWhite) {
        super();
        toys = new ArrayList<Toy>();
        for (int i = 0; i < 15; i++){
            toys.add(new CircleToy(Color.WHITE));
        }
        bgColor = Color.BLACK;
        if(blackOnWhite){ setBlackOnWhite();}
    }

    private void setBlackOnWhite(){
        bgColor = Color.WHITE;
        for (Toy t: toys){
            t.setColor(Color.BLACK);
        }
    }

    @Override
    public void drawBackground(Canvas canvas) {
        Rect bg = new Rect();
        bg.set(0, 0, canvas.getWidth(), canvas.getHeight());
        Paint bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(bg, bgPaint);

    }
}
