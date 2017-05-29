package com.example.lucas.marines.objects;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.InputStream;

public class ParallaxGameObject extends GameObject {
    Bitmap bitmap,reversedBitmap;
    float speed = 100;

    public void loadImage(String filename,
                          AssetManager manager,int width,int height,
                          boolean matchW,boolean matchH){
        try{
            InputStream is = manager.open(filename);
            bitmap = BitmapFactory.decodeStream(is);
            w = bitmap.getWidth();
            h = bitmap.getHeight();
            is.close();

            if(matchH){
                int newWidth = (int)(w*height/h);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        newWidth,height,true);
                w = bitmap.getWidth();
                h = bitmap.getHeight();
                Matrix mirrorMatrix = new Matrix();
                mirrorMatrix.setScale(-1,1);
                reversedBitmap = Bitmap.createBitmap(bitmap,
                        0,0,(int)w,(int)h,
                        mirrorMatrix,true);
            }else{
                int newHeight = (int)(w*height/w);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        width,newHeight,true);
                w = bitmap.getWidth();
                h = bitmap.getHeight();
                Matrix mirrorMatrix = new Matrix();
                mirrorMatrix.setScale(-1,1);
                reversedBitmap = Bitmap.createBitmap(bitmap,
                        0,0,(int)w,(int)h,
                        mirrorMatrix,true);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        y -= speed*deltaTime/10000.0f;
        if(y <= (-2*h)){
            y = 0;
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        if(y > -h)
            canvas.drawBitmap(bitmap,x,y,paint);
        canvas.drawBitmap(reversedBitmap,x+w,y,paint);
        canvas.drawBitmap(bitmap,x+2*w,y,paint);
        if(y < -h)
            canvas.drawBitmap(reversedBitmap,x+3*w,y,paint);
    }
}