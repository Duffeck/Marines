package com.example.lucas.marines.objects;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Lucas on 28/05/2017.
 */

public class TieFighter extends ImageGameObject {
    Matrix matrix = new Matrix();
    public boolean existe = true;
    public float velocity = 300;
    public int direcao = 0;
    public float ultimoTiro = 0;

    public TieFighter(AssetManager manager){
        loadImage("images/tiefighter.png", manager);
        existe = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public void mudarDirecao(int direcao){
        this.direcao = direcao;
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
        largura = ((float) canvas.getWidth() / 2.5f) / 960;
        altura = ((float) canvas.getHeight() / 2.5f) / 1528;
        matrix.preScale(largura, altura);
        y += velocity / 80.0f;

        w = (int)largura;
        h = (int)altura;

        if(y+altura*h > canvas.getHeight()) {
            saiuTela = true;
        }
        matrix.postTranslate(x, y);
        canvas.drawBitmap(bitmap, matrix, paint);
    }
}
