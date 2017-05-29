package com.example.lucas.marines.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucas.marines.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decor =getWindow().getDecorView();
        int uiOpt = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(uiOpt);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        final Button botaoIniciar = (Button) findViewById(R.id.botaoIniciar);
        final Context context = this;
        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(context, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
