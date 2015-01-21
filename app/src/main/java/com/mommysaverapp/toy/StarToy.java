package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by jeremy on 1/21/14.
 */
public class StarToy extends Toy {

    // TODO either make speedScale static or add setter
    private static float MAX_VELOCITY  = 1.0f / 75;
    private static float MAX_DISTANCE = 1.414f;
    private float radius;
    float angle;
    float position;
    float velocity;

    public StarToy(){
        defaultColor = Color.WHITE;
        currentColor = Color.WHITE;

        radius = 1 / 6.0f;

        angle = (float) (random.nextFloat() * 2 * Math.PI);
        position = random.nextFloat() * MAX_DISTANCE;
        velocity = (random.nextFloat() * 0.5f + 0.5f) * MAX_VELOCITY;

        colorTimer = 0;
        highlightColors = new ArrayList<Integer>();
        highlightColors.add(Color.CYAN);
        highlightColors.add(Color.YELLOW);
        highlightColors.add(Color.GREEN);
    }

    public void move(){
        position += velocity;
        if (position >= MAX_DISTANCE) position = 0;
    }

    public void draw(Canvas canvas){
        fadeColor();
        move();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int scale = Math.min(h,w);
        float effectiveRadius = radius * position / MAX_DISTANCE;
        float r = scale * effectiveRadius;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(currentColor);
        float xScaled = w * x();
        float yScaled = h * y();
        canvas.drawCircle(xScaled, yScaled, r, paint);
    }

    private float x(){
        float relative = (float) (Math.cos(angle) * position);
        return relative + 0.5f;
    }

    private float y(){
        float relative = (float) (Math.sin(angle) * position);
        return relative + 0.5f;
    }

    public void onTouch(float touchX, float touchY, int w, int h){
        float xDistance = w * x() - touchX;
        float yDistance = h * y() - touchY;
        int scale = Math.min(h,w);
        float impactRadius = scale / 3.0f;
        float distance = (float) Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        if (distance < impactRadius){
            highlight();
        }
    }

}
