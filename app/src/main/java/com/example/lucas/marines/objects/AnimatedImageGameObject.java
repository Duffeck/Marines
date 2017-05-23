package com.example.lucas.marines.objects;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.lucas.marines.*;

import java.io.InputStream;

/**
 * Created by developer on 27/03/17.
 */

public class AnimatedImageGameObject extends com.example.lucas.marines.GameObject {
    Bitmap anim[];
    int frames;
    float elapsedTime = 0f;
    public int timeToNextFrame = 125;
    int currentFrame = 0;

    public void loadImages(String file, AssetManager manager, int framesW, int framesH){
        try{
            InputStream is = manager.open(file);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            frames = framesW*framesH;
            currentFrame = frames-1;

            anim = new Bitmap[frames];
            w = bitmap.getWidth()/framesW;
            h = bitmap.getHeight()/framesH;
            int indice = 0;
            for(int i=0; i<framesW; i++){
                for(int j=0; j<framesH; j++){
                    anim[indice++] = Bitmap.createBitmap(bitmap, i*w, j*h, w, h);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        elapsedTime += deltaTime;
        if(elapsedTime > timeToNextFrame){
            elapsedTime = 0;
            currentFrame --;
            if(currentFrame < 0){
                currentFrame = frames-1;
            }
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        canvas.drawBitmap(anim[currentFrame], x, y, paint);
    }
}
