package com.mommysaverapp.toy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by jeremy on 1/21/14.
 */
public class StripeToy extends Toy {

    private float y0;
    private float y1;

    public StripeToy(float start, float height){
        defaultColor = Color.WHITE;
        currentColor = Color.WHITE;
        y0 = start;
        y1 = start + height;
        colorTimer = 0;
        highlightColors = new ArrayList<Integer>();
        highlightColors.add(Color.RED);
    }

    public void setBlack(){
        defaultColor = Color.BLACK;
        currentColor = Color.BLACK;
    }

    public void setWhite(){
        defaultColor = Color.WHITE;
        currentColor = Color.WHITE;
    }

    @Override
    public void draw(Canvas canvas){
        fadeColor();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(currentColor);
        int y0int = Math.round(h * y0);
        int y1int = Math.round(h * y1);
        canvas.drawRect(0, y0int, w, y1int, paint);
    }

    public void onTouch(float touchX, float touchY, int w, int h){
        float y0 = this.y0 * h;
        float y1 = this.y1 * h;
        if (y0 <= touchY && touchY <= y1) highlight();
    }
}
