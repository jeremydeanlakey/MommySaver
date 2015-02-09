package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.mommysaverapp.toy.CircleToy;
import com.mommysaverapp.toy.FallingCircleToy;
import com.mommysaverapp.toy.LaserToy;
import com.mommysaverapp.toy.Toy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jeremy on 1/21/14.
 */
public class LasersAndRainGame extends Game {

    private Random random = new Random();
    private int bgColor;

    public LasersAndRainGame(boolean blackOnWhite) {
        super();

        toys = new ArrayList<Toy>();
        for (int i = 0; i < 4; i++)
            toys.add(new FallingCircleToy(Color.WHITE));
        for (int i = 0; i < 3; i++)
            toys.add(new LaserToy(0.05f, 0.5f, Color.WHITE));
        for (int i = 0; i < 4; i++)
            toys.add(new FallingCircleToy(Color.WHITE));

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
