package com.example.lucas.marines;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.provider.Settings;

/**
 * Created by developer on 24/04/17.
 */

public class Submarine extends AnimatedImageGameObject {
    Matrix matrix = new Matrix();
    public float velocity = 80;

    public Submarine(AssetManager manager){
        loadImages("images/orangeship3.png", manager, 1, 1);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        x += Math.cos(angle) * deltaTime * velocity/1000;
        y += Math.sin(angle) * deltaTime * velocity/1000;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
        System.out.println(canvas.getWidth());
        System.out.println(canvas.getHeight());
        float largura = (float)canvas.getWidth()/960;
        float altura = (float)canvas.getHeight()/1528;
        matrix.preScale(largura, altura);
        matrix.postTranslate(canvas.getWidth()/2-w*largura/2, (canvas.getHeight()/6*5)-h*altura/2);
        canvas.drawBitmap(anim[currentFrame], matrix, paint);
    }

}
