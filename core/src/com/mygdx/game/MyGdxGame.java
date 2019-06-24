package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background bg;
    private Bird bird;
    private Obstacles obstacles;
    private boolean gameOver;
    private Texture restartTexture;
    int score;
    int scoreFinish;
    private BitmapFont FontRed1;


    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Background();
        bird = new Bird();
        obstacles = new Obstacles();
        gameOver = false;
        restartTexture = new Texture("RestartBtn.png");
        score = 0;
        FontRed1 = new BitmapFont();
        FontRed1.setColor(Color.RED); //Красный
        FontRed1.getData().setScale(1, 1);
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bg.render(batch);
        obstacles.render(batch);
        FontRed1.draw(batch, String.valueOf("Score: " + scoreFinish), 700, 590);
        if (!gameOver) {
            bird.render(batch);
        } else {
            batch.draw(restartTexture, 200, 200);
        }
        batch.end();
    }

    private void update() {
        bg.update();
        bird.update();
        obstacles.update();
        for (int i = 0; i < Obstacles.obs.length; i++) {
            if (bird.position.x > Obstacles.obs[i].position.x && bird.position.x < Obstacles.obs[i].position.x + 50) {
                if (!Obstacles.obs[i].emptySpace.contains(bird.position)) {
                    gameOver = true;
                } else {
                    score++;
                }
            }
        }
        if (bird.position.x < 0 || bird.position.x > 800) {
            gameOver = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver) {
            recreate();
        }
        scoreFinish = score/(Obstacles.obs.length*6);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void recreate() {
        bird.recreate();
        obstacles.recreate();
        gameOver = false;
        score = 0;
    }
}
