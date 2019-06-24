package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by infuntis on 20/08/17.
 */
public class Obstacles {
    class WallPair {
        Vector2 position;
        float speed;
        int offset;
        Rectangle emptySpace;


        public WallPair(Vector2 pos){
            position = pos;
            speed = 2;
            offset = new Random().nextInt(500);
//            emptySpace = new Rectangle(position.x - offset + 300, position.y, betweenDistance, 50);
            emptySpace = new Rectangle(position.x + 600 - offset, position.y, betweenDistance, 50);
        }

        public void update(){
            position.y -= speed;
            if(position.y < - 50){
                position.y = 750;
            }
            emptySpace.y = position.y;
        }
    }

    static WallPair[] obs;
    Texture txt;
    int betweenDistance;

    public Obstacles(){
        txt = new Texture("wall.png");
        obs = new WallPair[4];
        betweenDistance = 130;
        int startPosY = 400;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPair(new Vector2(0,startPosY));
            startPosY += 200;
        }

    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < obs.length; i++) {
//            batch.draw(txt, obs[i].position.x - obs[i].offset, obs[i].position.y);
            batch.draw(txt, obs[i].position.x - obs[i].offset, obs[i].position.y);
//            batch.draw(txt, obs[i].position.x  + betweenDistance + txt.getHeight() - obs[i].offset, obs[i].position.y);
            batch.draw(txt, obs[i].position.x  + betweenDistance + 600 - obs[i].offset, obs[i].position.y);
        }
    }

    public void update(){
        for (int i = 0; i < obs.length; i++) {
            obs[i].update();
        }
    }

    public void recreate(){
        int startPosY = 400;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPair(new Vector2(0,startPosY));
            startPosY += 200;
        }
    }
}
