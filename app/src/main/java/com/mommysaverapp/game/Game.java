package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.mommysaverapp.toy.Toy;

import java.util.List;

/**
 * Created by jeremy on 1/28/14.
 */
public abstract class Game{

    protected List<Toy> toys;

    public void onTouchEvent(View view, MotionEvent event){
        int w, h;
        w = view.getWidth();
        h = view.getHeight();

        float x = 0.0f, y = 0.0f;
        for (int i=0; i < event.getPointerCount(); i++){
            x = event.getX(i);
            y = event.getY(i);
            for (Toy t: toys){
                t.onTouch(x, y, w, h);
            }
        }
    }

    protected void drawBackground(Canvas canvas){}

    protected void drawForeground(Canvas canvas){}

    protected void drawToys(Canvas canvas){
        for (Toy t: toys){
            t.draw(canvas);
        }
    }

    public void onDraw(Canvas canvas){
        drawBackground(canvas);
        drawToys(canvas);
        drawForeground(canvas);
    }
}
