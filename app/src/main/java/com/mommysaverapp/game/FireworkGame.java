package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.mommysaverapp.toy.FireworkToy;
import com.mommysaverapp.toy.StarToy;
import com.mommysaverapp.toy.Toy;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by jeremy on 1/21/14.
 */
public class FireworkGame extends Game {

    private Random random = new Random();
    private int bgColor;

    public FireworkGame() {
        super();
        toys = new ArrayList<Toy>();
        for (int i = 0; i < 3; i++){
            toys.add(new FireworkToy());
        }
        bgColor = Color.BLACK;
    }

    @Override
    protected void drawBackground(Canvas canvas) {
        Rect bg = new Rect();
        bg.set(0, 0, canvas.getWidth(), canvas.getHeight());
        Paint bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(bg, bgPaint);

    }
}
