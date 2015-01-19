package com.mommysaverapp;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.mommysaverapp.game.CheckersGame;
import com.mommysaverapp.game.CirclesGame;
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
        /*
        do{
            nextGame = gameSelector.nextInt(4);
        } while (nextGame == lastGame);
        */
        nextGame = 0;
        switch (nextGame){
            case 0:
//                game = new StripesGame();
//                game = new LasersAndRainGame(true);
                game = new SpaceGame();
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
            default:
                game = new CirclesGame(false);
                countdownToGameChange = 500;
                break;
        }
        lastGame = nextGame;

    }
}
