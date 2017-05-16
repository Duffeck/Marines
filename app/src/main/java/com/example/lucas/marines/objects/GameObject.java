package com.example.lucas.marines.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Lucas on 15/05/2017.
 */

public class GameObject {
    public float x, y;
    public int w,h;
    public float angle = 0;
    public String name = "";

    public Rect getBoundingBox(){
        Rect r = new Rect((int)(x-w/2), (int)(y-h/2), (int)(x+w/2), (int)(y+h/2));
        return r;
    }

    public void update(float deltaTime){

    }

    public void draw(Canvas canvas, Paint paint){

    }

}

