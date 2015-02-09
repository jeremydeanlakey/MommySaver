package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by jeremy on 1/21/14.
 */
public class SmileyToy extends Toy {

    private int xStart, xFinish;
    private int yStart, yFinish;
    private float progress;
    private float x, y;
    private static boolean everybodyRun;
    private boolean hider;
    private int numberSpaces;

    public SmileyToy(int checkersWidth, boolean hider, int color){
        defaultColor = color;
        currentColor = color;
        do {
            xFinish = random.nextInt(4);
            yFinish = random.nextInt(4);
        } while ((xFinish + yFinish) % 2 == 1);
        progress = random.nextFloat();
        everybodyRun = false;
        this.hider = hider;
        numberSpaces = checkersWidth;
        changeDirection();
        move();
    }

    private void changeDirection(){
        xStart = xFinish;
        yStart = yFinish;
        do {
            int direction = random.nextInt(4);
            switch(random.nextInt(4)){
                case 0:
                    xFinish = xStart + 2;
                    yFinish = yStart;
                    break;
                case 1:
                    xFinish = xStart;
                    yFinish = yStart - 2;
                    break;
                case 2:
                    xFinish = xStart - 2;
                    yFinish = yStart;
                    break;
                case 3:
                    xFinish = xStart;
                    yFinish = yStart + 2;
                    break;
            }
        } while (xFinish < 0 || xFinish >= numberSpaces || yFinish < 0 || yFinish >= numberSpaces);
    }

    private void move(){
        if (progress != 0.0f || !hider || everybodyRun){
            progress += 0.015f;
            if (progress >= 1.0f) progress = 0.0f;
            if (progress == 0.0f) changeDirection();
            if (progress == 0.0f && !hider) everybodyRun = false;
        }
        x = (progress * xFinish + (1-progress) * xStart + 0.5f) / numberSpaces;
        y = (progress * yFinish + (1-progress) * yStart + 0.5f) / numberSpaces;
    }

    @Override
    public void draw(Canvas canvas){
        move();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int scale = Math.min(h,w);
        float r = scale / 10.0f;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(currentColor);
        canvas.drawCircle(x*w, y*h, r, paint);

        Paint altPaint = new Paint();
        altPaint.setStyle(Paint.Style.FILL);
        altPaint.setColor(Color.BLACK);

        // Draw mouth
        RectF smile = new RectF();
        smile.set(x*w-r/2, y*h, x*w+r/2, y*h+r/2);
        canvas.drawArc(smile,0,180,false,altPaint);

        // Draw eyes
        //canvas.drawCircle(x-3/4*r, y-3/4*r, x-1/4*r, y-1/4*r);
        canvas.drawCircle(x*w-r*3/8, y*h-r*3/8, r*3/16, altPaint);
        canvas.drawCircle(x*w+r*3/8, y*h-r*3/8, r*3/16, altPaint);
    }

    @Override
   public void onTouch(float touchX, float touchY, int w, int h){
        /*
        int scale = Math.min(h,w);
        float x = w * this.x;
        float y = h * this.y;
        float impactRadius = scale / 2.0f;
        float distance = (float) Math.sqrt((x - touchX) * (x - touchX) + (y - touchY) * (y - touchY));
        if (distance < impactRadius){
            everybodyRun = true;
        }
        */
       everybodyRun = true;
    }

}
