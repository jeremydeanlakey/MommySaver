package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.mommysaverapp.toy.FallingCircleToy;
import com.mommysaverapp.toy.LaserToy;
import com.mommysaverapp.toy.StarToy;
import com.mommysaverapp.toy.Toy;

import java.util.ArrayList;
import java.util.Random;

//package com.example.bennysapp;


/**
 * Created by jeremy on 1/21/14.
 */
public class SpaceGame extends Game {

    private Random random = new Random();
    private int bgColor;

    public SpaceGame() {
        super();
        toys = new ArrayList<Toy>();
        for (int i = 0; i < 40; i++){
            toys.add(new StarToy());
        }
        bgColor = Color.BLACK;
    }

    private void setBlackOnWhite(){
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
