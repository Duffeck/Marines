package com.example.lucas.marines.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lucas.marines.R;

/**
 * Created by Lucas on 15/05/2017.
 */

public class GameOverActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View decor =getWindow().getDecorView();
        int uiOpt = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(uiOpt);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        final Button botaoVoltar = (Button) findViewById(R.id.botaoVoltar);
        final Context context = this;
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
