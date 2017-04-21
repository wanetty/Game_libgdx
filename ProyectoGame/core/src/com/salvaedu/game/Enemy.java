package com.salvaedu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.salvaedu.game.Constantes.PIXELS_IN_METER;

//Cualquien enemigo?
public abstract class Enemy extends Actor {

    protected  Texture texture;
    protected  World world;
    protected Body body;
    protected  Fixture fixture;
    protected   boolean alive = true;

    public Enemy(){

    }
    public void draw(Batch batch, float parentAlpha){
        if(alive) {
            setPosition(body.getPosition().x * PIXELS_IN_METER, body.getPosition().y * PIXELS_IN_METER);
            batch.draw(texture, getX(), getY(), getWidth(), getWidth());
        }

    }
    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);

    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public void act(float delta) {
           }




}