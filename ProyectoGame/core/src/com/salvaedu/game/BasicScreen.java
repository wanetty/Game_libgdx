package com.salvaedu.game;

import com.badlogic.gdx.Screen;

/**
 * Created by Papilomavirus on 08/04/2017.
 */

public abstract class BasicScreen implements Screen {

    protected MainGame game;

     public BasicScreen(MainGame game){
        this.game = game;
     }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
