package com.example.lucas.marines.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Lucas on 15/05/2017.
 */

public class BotaoGameObject extends GameObject {
    String texto;
    Rect rect;
    Color color;

    public BotaoGameObject(String texto, int x, int y, int w, int h) {
        this.texto = texto;
        this.x = x;
        this.y = y;
        this. w = w;
        this. h = h;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
    //System.out.println("Funciona caramba");
        super.draw(canvas, paint);
        paint.setColor(Color.RED);
        Paint paintText = new Paint();
        paintText.setColor(Color.BLUE);
        paintText.setTextSize(50);
        rect = new Rect();
        canvas.drawRect(x-w/2, y-h/2, x+w/2, y+h/2, paint);
        canvas.drawText(texto, x*2-w/2, y*2-h/2, paintText);
    }
}
