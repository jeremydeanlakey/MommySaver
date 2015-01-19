package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by jeremy on 1/21/14.
 */
public class FallingCircleToy extends Toy {

    // TODO either make speedScale static or add setter
    private float speedScale;
    private float x;
    private float y;
    private float radius;
    private float vX;
    private float vY;
    private static float gravity = 1.0f / 50 / 100;


    public FallingCircleToy(int color){
        defaultColor = color;
        currentColor = color;

        x = random.nextFloat();
        y = random.nextFloat();
        radius = 1 / 10.0f;

        speedScale = 1.0f / 75;
        // start with speeds ranging from -speedScale to +speedScale
        vX = 2 * (random.nextFloat() - 0.5f) * speedScale;
        vY = 2 *(random.nextFloat() - 0.5f) * speedScale;

        colorTimer = 0;
        highlightColors = new ArrayList<Integer>();
//        highlightColors.add(Color.RED);
        highlightColors.add(Color.CYAN);
//        highlightColors.add(Color.YELLOW);
    }

    public void move(){
        x += vX;
        y += vY;

        if (x > 1 + radius){
            x = 0 - radius;
        } else if (x< 0 - radius){
            x = 1 + radius;
        }

        if (y > 1 + radius) {
            y = 0 - radius;
            vY = 0;
        }

        accelerate();
    }

    public void accelerate(){
        vY += gravity;
    }

    public void draw(Canvas canvas){
        fadeColor();
        move();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int scale = Math.min(h,w);
        float r = scale * radius;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(currentColor);
        float xScaled = w * x;
        float yScaled = h * y;
        canvas.drawCircle(xScaled, yScaled, r, paint);
    }

   public void onTouch(float touchX, float touchY, int w, int h){
        float xDistance = w * x - touchX;
        float yDistance = h * y - touchY;
        int scale = Math.min(h,w);
        float impactRadius = scale / 2.0f;
        float distance = (float) Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        if (distance < impactRadius){
            highlight();
            vX = ((w*x) - touchX) / distance * speedScale;
            vY = ((y*h) - touchY) / distance * speedScale;
        }
    }

}
