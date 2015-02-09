package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jeremy on 1/21/14.
 */
public class LaserToy extends Toy {

    private float y0, y1;
    private float x0, x1;
    private float w, h;
    private Random random;
    private float speed;

    public LaserToy(int color){
        new LaserToy(0.05f, 0.5f, color);
    }

    public LaserToy(float height, float width, int color){
        defaultColor = color;
        currentColor = color;
        w=width;
        h=height;
        random = new Random();
        defaultColor = Color.RED;
        currentColor = Color.RED;
        y0 = random.nextFloat() - h/2;
        y1 = y0 + h;
        x0 = random.nextFloat() - w/2;
        x1 = x0 + w;
        speed = 1f/50;
        speed *= random.nextFloat() * 0.5 + 0.5;
        if (random.nextBoolean()) speed *= -1;
        colorTimer = 0;
        highlightColors = new ArrayList<Integer>();
        highlightColors.add(Color.YELLOW);
    }

    private void move(){
        x0 += speed;
        x1 += speed;
        if (x0 > 1){
            x0 = 0 - w;
            x1 = 0;
            y0 = random.nextFloat() - h/2;
            y1 = y0 + h;
        }
        if (x1 < 0){
            x0 = 1;
            x1 = 1 + w;
            y0 = random.nextFloat() - h/2;
            y1 = y0 + h;
        }
    }

    @Override
    public void draw(Canvas canvas){
        move();
        fadeColor();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(currentColor);
        int y0int = Math.round(h * y0);
        int y1int = Math.round(h * y1);
        int x0int = Math.round(h * x0);
        int x1int = Math.round(h * x1);
        canvas.drawRect(x0int, y0int, x1int, y1int, paint);
    }

    @Override
    public void onTouch(float touchX, float touchY, int w, int h){
        float y0 = this.y0 * h;
        float y1 = this.y1 * h;
        float x0 = this.x0 * w;
        float x1 = this.x1 * w;
        if (y0 <= touchY && touchY <= y1 &&
            x0 <= touchX && touchX <= x1)
                highlight();
    }
}
