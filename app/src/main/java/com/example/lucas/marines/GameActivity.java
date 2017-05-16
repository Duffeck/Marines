package com.example.lucas.marines;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lucas on 15/05/2017.
 */

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decor =getWindow().getDecorView();
        int uiOpt = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(uiOpt);

        getSupportActionBar().hide();
        setContentView(new RenderView(this));
    }

}
