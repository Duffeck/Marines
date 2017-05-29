package com.example.lucas.marines.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import com.example.lucas.marines.objects.GameResources;
import com.example.lucas.marines.objects.LaserGameObject;
import com.example.lucas.marines.objects.ParallaxGameObject;
import com.example.lucas.marines.objects.TieFighter;
import com.example.lucas.marines.objects.XWing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by developer on 24/04/17.
 */

public class RenderView extends View {
    MediaPlayer mediaPlayer, laserBlast;
    SoundPool soundPool;
    public int laserEffect;
    Paint paint = new Paint();
    public  int r=200,g=200,b=200;
    float startTime;
    Context context;
    com.example.lucas.marines.objects.XWing XWing;
    LaserGameObject laser;
    List<LaserGameObject> lasers = new ArrayList<LaserGameObject>(0); //array para controlar os disparaos da nave
    List<TieFighter> inimigos = new ArrayList<TieFighter>(0); //array para controlar os disparos
    float ultimoTiro = 0f;
    ParallaxGameObject parallaxGameObject;
    int limiteInimigos = 5;
    int totalInimigos = 0;
    int score = 0;

    public RenderView(Context context) {
        super(context);
        this.context = context;
        startTime = System.nanoTime(); //pega o tempo inicial
        GameResources.getInstance().cleanResources();

        mediaPlayer = new MediaPlayer();
        try{
            AssetFileDescriptor descriptor = context.getAssets().openFd("sounds/backsong.mp3");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                                        descriptor.getStartOffset(),
                                        descriptor.getLength());

            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
/*
            descriptor = context.getAssets().openFd("sounds/laser_blast.mp3");
            laserBlast.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength());

            laserBlast.prepare();
            */
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
            }else{
                AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
                soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
            }

            AssetFileDescriptor descriptorMP3 = context.getAssets().openFd("sounds/laser_blast.mp3");
            laserEffect = soundPool.load(descriptorMP3, 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(parallaxGameObject != null)
            return;

        parallaxGameObject = new ParallaxGameObject();
        parallaxGameObject.
                loadImage("images/espaco.jpg",
                        getContext().getAssets(),
                        getWidth(),getHeight(),
                        false,true);
        parallaxGameObject.layer = 1;
        GameResources.
                getInstance().
                addObject(parallaxGameObject);

        //Nave (Jogador)------------------------------------------------------------
        XWing = new XWing(context.getAssets()); // criar Nave
        GameResources.getInstance().addObject(XWing); //adicionar Nave ao GameResources
        //--------------------------------------------------------------------------
    }


    public void stopMusic(){
        mediaPlayer.pause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            float deltaTime = (System.nanoTime()-startTime)/1000000.0f;
            startTime = System.nanoTime();
            if(event.getX()< this.getWidth()/2){
                XWing.mudarDirecao(1);
            }else if(event.getX()> this.getWidth()/2){
                XWing.mudarDirecao(2);
            }
            soundPool.play(laserEffect, 0.2f, 0.2f, 0, 0, 1);
            LaserGameObject laser = new LaserGameObject(context.getAssets());
            laser.x = XWing.x-20f;
            laser.y = XWing.y-80f;
            lasers.add(laser);
            GameResources.getInstance().addObject(laser);
try {
    laserBlast.start();
}catch(Exception e){
    e.printStackTrace();
}
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float deltaTime = (System.nanoTime()-startTime)/1000000.0f;
        startTime = System.nanoTime();

        Iterator<LaserGameObject> iLasers = lasers.iterator();
        while (iLasers.hasNext()){
            LaserGameObject laser = iLasers.next();
            if(laser.saiuTela){
                GameResources.getInstance().removeObject(laser);
                iLasers.remove();
            }
        }

        Iterator<TieFighter> iInimigos = inimigos.iterator();
        while (iInimigos.hasNext()){
            TieFighter tieFighter = iInimigos.next();

            if(tieFighter.saiuTela){
                GameResources.getInstance().removeObject(tieFighter);
                iInimigos.remove();
                totalInimigos--;
                score -= 10;
            }else{
                for(LaserGameObject laser : lasers){
                    System.out.println((tieFighter.x>=laser.x));
                    System.out.println((tieFighter.x+tieFighter.w<=laser.x+laser.w));
                    System.out.println((tieFighter.y>=laser.y));
                    System.out.println((tieFighter.y+tieFighter.h<=laser.y+laser.h));
                    System.out.println(tieFighter.getBoundingBox());
                    if(tieFighter.getBoundingBox().intersect(laser.getBoundingBox())){
                    //if((tieFighter.x>=laser.x && tieFighter.x+tieFighter.w<=laser.x+laser.w) || (tieFighter.y>=laser.y && tieFighter.y+tieFighter.h<=laser.y+laser.h)){
                        System.out.println("TieFighter:" + tieFighter.x + " X " + tieFighter.y);
                        System.out.println("Laser:" + laser.x + " X " + laser.y);
                        GameResources.getInstance().removeObject(tieFighter);
                        iInimigos.remove();
                        totalInimigos--;
                        GameResources.getInstance().removeObject(laser);
                        lasers.remove(laser);
                        score += 10;
                    }
                    break;
                }
                //if(tieFighter.getBoundingBox().intersect(XWing.getBoundingBox())){
                if(false){
                    if(context!=null) {
                        Intent intent = new Intent().setClass(context, GameOverActivity.class);
                        ((Activity) context).startActivity(intent);
                    }
                }
            }
        }

        if(totalInimigos < limiteInimigos){
            Random random = new Random();
            TieFighter tieFighter = new TieFighter(context.getAssets());
            tieFighter.x = (random.nextInt(canvas.getWidth()+(int)(tieFighter.largura*2f)));
            tieFighter.y = 0;
            inimigos.add(tieFighter);
            GameResources.getInstance().addObject(tieFighter);
            totalInimigos ++;
        }

        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);
        invalidate();
    }
}
