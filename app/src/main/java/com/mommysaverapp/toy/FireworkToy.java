package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremy on 1/21/14.
 */
public class FireworkToy extends Toy {

    private static int SPARK_COUNT = 20;
    private List<FireWorkSpark> sparks;

    private static float CRITICAL_RADIUS = 0.2f;
    private static float AVG_GROWTH_RATE = 0.003f;
    private static float growthRate;

    private float centerX, centerY;
    private float radius;

    public FireworkToy(){
        defaultColor = Color.WHITE;
        currentColor = Color.WHITE;

        centerX = random.nextFloat() * 0.8f + 0.2f;
        centerY = random.nextFloat() * 0.8f + 0.2f;
        radius = random.nextFloat() * CRITICAL_RADIUS;
        growthRate = (random.nextFloat() + 0.5f) * AVG_GROWTH_RATE;

        sparks = new ArrayList<FireWorkSpark>();
        for (int i=0; i<SPARK_COUNT; i++){
            sparks.add(new FireWorkSpark());
        }

        colorTimer = 0;
        highlightColors = new ArrayList<Integer>();
        highlightColors.add(Color.RED);
        highlightColors.add(Color.CYAN);
        highlightColors.add(Color.YELLOW);
    }

    private void move(){
        radius += growthRate;
        if (radius >= CRITICAL_RADIUS * 2){
            radius = 0;
            fadeColor();
            centerX = random.nextFloat() * 0.8f + 0.2f;
            centerY = random.nextFloat() * 0.8f + 0.2f;
        }
    }

    @Override
    protected void fadeColor(){
        if (radius >= CRITICAL_RADIUS){
            colorTimer = 0;
            currentColor = defaultColor;
        }
    }

    @Override
    public void draw(Canvas canvas){
        fadeColor();
        move();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int scale = Math.min(h,w);
        float x = w * centerX;
        float y = h * centerY;
        float r = scale * radius;
        if (radius < CRITICAL_RADIUS) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(currentColor);
            canvas.drawCircle(x, y, r, paint);
        } else if (sparks != null) {
            for (FireWorkSpark spark: sparks){
                spark.draw(canvas, x, y, r);
            }
        }
    }

    private void pop(){
        radius = Math.max(radius, CRITICAL_RADIUS);
    }

   @Override
   public void onTouch(float touchX, float touchY, int w, int h){
        float x = w * centerX;
        float y = h * centerY;
        float distance = (float) Math.sqrt((x - touchX) * (x - touchX) + (y - touchY) * (y - touchY));

        int scale = Math.min(h,w);
        float impactRadius = radius + 0.05f * scale;
        if (distance < impactRadius ) pop();
    }

}
