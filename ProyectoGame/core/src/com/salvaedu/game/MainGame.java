package com.salvaedu.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;


public class MainGame extends Game {

    private AssetManager manager;

    public AssetManager getManager() {
        return manager;
    }

    public void create() {

        manager = new AssetManager();
        manager.load("cpquiero.png", Texture.class);
        manager.load("caca.png", Texture.class);
        manager.load("queso.png", Texture.class);
        manager.finishLoading();
        setScreen(new GameScreen(this));
    }
}

