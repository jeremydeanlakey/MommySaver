package com.mommysaverapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


public class MainActivity extends Activity implements MediaPlayer.OnPreparedListener {

    private Playground myPlayGround;
    private MediaPlayer mediaPlayer;
    private int songLocation;
    private boolean muted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songLocation = 0;
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ViewGroup main = (ViewGroup)findViewById(R.id.main_activity);
        myPlayGround = new Playground(this);
        main.addView(myPlayGround);
        LayoutInflater.from(this).inflate(R.layout.button_bar, main);
        fullImmersiveMode();
    }

   public void fullImmersiveMode(){
       // Android 4.4 and later
       if (Build.VERSION.SDK_INT >= 19) {
           myPlayGround.setSystemUiVisibility(
                   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                   | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                   | View.SYSTEM_UI_FLAG_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
       }
       // Android 4.1 and higher, but less than 4.4
       else if (Build.VERSION.SDK_INT >= 16) {
           int uiOptions =
                   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                   | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
           myPlayGround.setSystemUiVisibility(uiOptions);
           ActionBar actionBar = getActionBar();
           if (actionBar != null) actionBar.hide();
       }
       else {
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                   WindowManager.LayoutParams.FLAG_FULLSCREEN);
       }
   }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) fullImmersiveMode();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    private void getThisPartyStarted(){
        mediaPlayer = MediaPlayer.create(this, R.raw.furelise);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        mediaPlayer.seekTo(songLocation);
    }

    private void stopTheMusic(){
        if (mediaPlayer == null)
            return;
        songLocation = mediaPlayer.getCurrentPosition();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!muted)
            getThisPartyStarted();
    }

    @Override
    protected void onPause(){
        super.onPause();
        stopTheMusic();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    public void musicButtonPressed(View v) {

        muted = !muted;
        if (muted)
            stopTheMusic();
        else
            getThisPartyStarted();
    }

}
