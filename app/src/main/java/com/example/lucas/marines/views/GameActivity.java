package com.example.lucas.marines.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lucas on 15/05/2017.
 */

public class GameActivity extends AppCompatActivity {
    RenderView renderView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decor =getWindow().getDecorView();
        int uiOpt = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(uiOpt);

        getSupportActionBar().hide();
        renderView = new RenderView(this);
        setContentView(renderView);
    }

    @Override
    public void onBackPressed() {
        renderView.stopMusic();
        super.onBackPressed();
    }
}
