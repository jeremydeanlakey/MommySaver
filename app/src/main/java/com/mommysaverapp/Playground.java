package com.mommysaverapp;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.mommysaverapp.game.CheckersGame;
import com.mommysaverapp.game.CirclesGame;
import com.mommysaverapp.game.FireworkGame;
import com.mommysaverapp.game.Game;
import com.mommysaverapp.game.LasersAndRainGame;
import com.mommysaverapp.game.SpaceGame;
import com.mommysaverapp.game.StripesGame;

import java.util.Random;

/**
 * Created by jeremy on 1/21/14.
 */
public class Playground extends View{

    Game game;
    int countdownToGameChange;
    int lastGame;
    Random gameSelector;

    Playground(Context context){
        super(context);
        gameSelector = new Random();
        lastGame = -1;
        changeGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        game.onTouchEvent(this, event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        countdownToGameChange--;
        if (countdownToGameChange <= 0){
            changeGame();
        }
        game.onDraw(canvas);
        invalidate();
    }


    void changeGame(){
        int nextGame;

        do{
            nextGame = gameSelector.nextInt(7);
        } while (nextGame == lastGame);

        switch (nextGame){
            case 0:
                game = new FireworkGame();
                countdownToGameChange = 500;
                break;
            case 1:
                game = new CirclesGame(true);
                countdownToGameChange = 500;
                break;
            case 2:
                game = new CheckersGame();
                countdownToGameChange = 500;
                break;
            case 3:
                game = new StripesGame();
                countdownToGameChange = 500;
                break;
            case 4:
                game = new LasersAndRainGame(true);
                countdownToGameChange = 400;
                break;
            case 5:
                game = new SpaceGame();
                countdownToGameChange = 500;
                break;
            default:
                game = new CirclesGame(false);
                countdownToGameChange = 500;
                break;
        }
        lastGame = nextGame;

    }
}
