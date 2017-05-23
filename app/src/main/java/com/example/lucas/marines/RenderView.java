package com.example.lucas.marines;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

import com.example.lucas.marines.objects.XWing;

/**
 * Created by developer on 24/04/17.
 */

public class RenderView extends View {
    MediaPlayer mediaPlayer;
    //SoundPool soundPool;
    //int soundEffect;
    Paint paint = new Paint();
    public  int r=200,g=200,b=200;
    float startTime;

    com.example.lucas.marines.objects.XWing XWing;
    public RenderView(Context context) {
        super(context);
        startTime = System.nanoTime();
        XWing = new XWing(context.getAssets());
        GameResources.getInstance().addObject(XWing);

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
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
            }else{
                AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
                soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
            }

            AssetFileDescriptor descriptorMP3 = context.getAssets().openFd("sounds/backsong.mp3");
            soundEffect = soundPool.load(descriptorMP3, 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    //soundPool.play(soundEffect, 1, 1, 0, 1, 1);
                }
            });
            */
        }catch (Exception e){
            e.printStackTrace();
        }
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
                System.out.println("Ué");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float deltaTime = (System.nanoTime()-startTime)/1000000.0f;
        startTime = System.nanoTime();

        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);
        invalidate();
    }
}
