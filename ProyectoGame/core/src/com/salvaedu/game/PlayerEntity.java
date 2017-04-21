package com.salvaedu.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

import static com.salvaedu.game.Constantes.PIXELS_IN_METER;

public class PlayerEntity extends Actor {


    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private  boolean alive = true;


    public PlayerEntity(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        alive = true;
        PolygonShape actor = new PolygonShape();
        actor.setAsBox(0.5f, 0.25f);
        this.fixture = body.createFixture(actor,3);
        this.fixture.setUserData("jugador");
        actor.dispose();

        setSize((.5f  * PIXELS_IN_METER) - 0.25f, (.5f * PIXELS_IN_METER)-0.125f);//cuidado el d2d dibuja desde la esquina y el box2d desde el centro
    }

    public void draw(Batch batch, float parentAlpha){
        if(alive) {
            setPosition(body.getPosition().x * PIXELS_IN_METER, body.getPosition().y * PIXELS_IN_METER);
            batch.draw(texture, getX(), getY(), getWidth(), getWidth());
        }

    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);

    }


    @Override
    public void act(float delta) {
        if(alive) {
            body.setLinearVelocity(Constantes.SPEED_PLAYER_X, 0);
            if (Gdx.input.isTouched() && (Gdx.input.getY() < Gdx.graphics.getHeight() / 2) && !(getY() >= 600)) {
                float velocityX = body.getLinearVelocity().x;
                body.setLinearVelocity(velocityX, Constantes.SPEED_PLAYER_Y);
            } else if (Gdx.input.isTouched() && (Gdx.input.getY() >= Gdx.graphics.getHeight() / 2) && !(getY() < 10)) {
                float velocityX = body.getLinearVelocity().x;
                body.setLinearVelocity(velocityX, -Constantes.SPEED_PLAYER_Y);
            } else {
                float velocityX = body.getLinearVelocity().x;
                System.out.println(velocityX);
                body.setLinearVelocity(velocityX, 0);
            }
        }
    }
}
