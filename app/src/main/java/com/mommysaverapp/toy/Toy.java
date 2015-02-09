package com.mommysaverapp.toy;

import android.graphics.Canvas;

import java.util.List;
import java.util.Random;

/**
 * Created by jeremy on 1/28/14.
 */
public abstract class Toy {

    protected int colorTimer;
    protected int defaultColor;
    protected int currentColor;
    protected List<Integer> highlightColors;
    protected Random random = new Random();

    public Toy() {

    }

    protected void fadeColor(){
        if (colorTimer > 0 ){
            colorTimer--;
            if (colorTimer == 0){
                currentColor = defaultColor;
            }
        }
    }

    protected void highlight(){
        if (highlightColors.isEmpty()) return;
        if (colorTimer > 1) return;
        currentColor = highlightColors.get(random.nextInt(highlightColors.size()));
        colorTimer = 20;
    }

    public void setColor(int newColor){
        defaultColor = newColor;
        currentColor = newColor;
    }

    public abstract void draw(Canvas canvas);

    public abstract void onTouch(float touchX, float touchY, int w, int h);

}
