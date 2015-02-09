package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jeremy on 1/21/14.
 */
public class FireWorkSpark {

    private float angle, distance;
    private Random random;

    private int colorTimer;
    private int currentColor;
    private static int COLOR_TIMER_MAX = 10;
    private static int[] COLORS = {Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.GREEN};

    public FireWorkSpark(){
        currentColor = Color.WHITE;

        random = new Random();
        angle = (float) (random.nextFloat() * 2 * Math.PI);
        distance = random.nextFloat();

        colorTimer = random.nextInt(COLOR_TIMER_MAX);
    }

    private void changeColor(){
        colorTimer = (colorTimer + 1) % COLOR_TIMER_MAX;
        if (colorTimer == COLOR_TIMER_MAX / 2){
            currentColor = COLORS[random.nextInt(4)];

        } else if (colorTimer < COLOR_TIMER_MAX / 2){
            currentColor = Color.WHITE;
        }
    }

    public void draw(Canvas canvas, float fireworkX, float fireworkY, float outerRadius){
        changeColor();
        int sparkX = (int) (fireworkX + offsetX(outerRadius));
        int sparkY = (int) (fireworkY + offsetY(outerRadius));
        int sparkRadius = (int) (0.1f * outerRadius);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(currentColor);

        canvas.drawCircle(sparkX, sparkY, sparkRadius, paint);
    }

    private int offsetX(float outerRadius){
        return (int) (Math.cos(angle) * distance * outerRadius);
    }

    private int offsetY(float outerRadius){
        return (int) (Math.sin(angle) * distance * outerRadius);
    }

}
