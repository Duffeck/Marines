package com.example.lucas.marines.objects;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by developer on 24/04/17.
 */

public class XWing extends AnimatedImageGameObject {
    public boolean existe = true;
    Matrix matrix = new Matrix();
    public float velocity = 300;
    public int direcao = 0;

    public XWing(AssetManager manager){
        loadImages("images/xwing.png", manager, 1, 1);
        existe = true;
    }

    @Override
    public void update(float deltaTime) {
        System.out.println(x);
        super.update(deltaTime);
    }

    public void mudarDirecao(int direcao){
        this.direcao = direcao;
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
        float largura = ((float) canvas.getWidth() / 1.5f) / 960;
        float altura = ((float) canvas.getHeight() / 1.5f) / 1528;
        matrix.preScale(largura, altura);
        System.out.println(w);
        if(existe) {
            x = canvas.getWidth() / 2 - w * largura / 2;
            y = (canvas.getHeight() / 6 * 5) - h * altura / 2;
            existe = false;
        }else{
            if(direcao == 1){
                x -= velocity / 100.0f;
                if (x  < 0) {
                    x = 0.0f;
                    direcao = 0;
                }
            }else if(direcao == 2){
                x += velocity / 100.0f;
                if(x+largura*w > canvas.getWidth()){
                    x = canvas.getWidth()-largura*w;
                    direcao = 0;
                }
            }
        }
        matrix.postTranslate(x, y);
        canvas.drawBitmap(anim[currentFrame], matrix, paint);
    }

}