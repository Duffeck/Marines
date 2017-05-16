package com.example.lucas.marines;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 27/03/17.
 */
public class GameResources {
    private static GameResources ourInstance = new GameResources();
    public List<GameObject> gameObjectList = new ArrayList<>();

    public static GameResources getInstance() {
        return ourInstance;
    }

    private GameResources() {
    }

    public void addObject(GameObject gameObject){
        gameObjectList.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        gameObjectList.remove(gameObject);
    }

    public void updateAndDraw(float deltaTime, Canvas canvas, Paint paint){
        for(GameObject obj : gameObjectList){
            obj.update(deltaTime);
            obj.draw(canvas, paint);
        }
    }
}
