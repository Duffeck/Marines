package com.example.lucas.marines.objects;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Lucas on 22/05/2017.
 */

public class LaserGameObject extends AnimatedImageGameObject {
    public boolean existe = true;
    Matrix matrix = new Matrix();
    public float angle = 0;
    public float velocity = 1000;
//    /public float frequenciaTiro = 70f;

    public LaserGameObject(AssetManager manager){
        loadImages("images/laser.png", manager, 1, 11);
        existe = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        //x += Math.cos(angle) * deltaTime * velocity/1000;
        //y += Math.sin(angle) * deltaTime * velocity/1000;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
        float largura = ((float) canvas.getWidth() / 1.5f) / 960*1.2f;
        float altura = ((float) canvas.getHeight() / 1.5f) / 1528*1.2f;
        matrix.preScale(largura, altura);

        y -= velocity / 100.0f;
        if(y+altura*h < 0f) {
            //y = canvas.getHeight() - altura * h;
        }

        matrix.postTranslate(x, y);
        matrix.preRotate((((float) angle + (float) Math.PI / 2f) * 180.0f / (float) Math.PI), w / 2, h / 2);
        canvas.drawBitmap(anim[currentFrame], matrix, paint);
    }

}
