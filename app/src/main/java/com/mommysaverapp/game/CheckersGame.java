package com.mommysaverapp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mommysaverapp.toy.SmileyToy;
import com.mommysaverapp.toy.Toy;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by jeremy on 1/21/14.
 */
public class CheckersGame extends Game {

    private int fgColor;
    private int bgColor;
    private static final int width = 4;
    private static final int[] colors = {Color.YELLOW, Color.CYAN, Color.MAGENTA};
    public CheckersGame() {
        super();
        toys = new ArrayList<Toy>();
        Random random = new Random();
        for (int i = 0; i < 2; i++){
            toys.add(new SmileyToy(width, false, Color.YELLOW));
            //toys.add(new CircleToy(Color.RED));
        }
        for (int i = 0; i < 4; i++){
            int color = colors[random.nextInt(3)];
            toys.add(new SmileyToy(width, true, color));
        }
        fgColor = Color.BLACK;
        bgColor = Color.WHITE;
    }

    public void drawBackground(Canvas canvas){
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        Paint paint = new Paint();
        paint.setColor(bgColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,w,h,paint);
    }

    public void drawForeground(Canvas canvas){
        int row, col;
        int colWidth = canvas.getWidth() / width;
        int rowHeight = canvas.getHeight() / width;
        Paint paint = new Paint();
        paint.setColor(fgColor);
        paint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < width*width; i+=2){
            row = i / width;
            col = i % width + (row % 2);
            canvas.drawRect((float)(  col  *colWidth),
                            (float)(  row  *rowHeight),
                            (float)((col+1)*colWidth),
                            (float)((row+1)*rowHeight),
                            paint);
        }
    }
}
