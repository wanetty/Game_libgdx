package com.salvaedu.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.salvaedu.game.Constantes.PIXELS_IN_METER;

/**
 * Created by Papilomavirus on 12/04/2017.
 */

public class EnemyType2 extends Enemy {


    public EnemyType2(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        this.body = world.createBody(def);
        PolygonShape actor = new PolygonShape();
        actor.setAsBox(0.5f, 0.5f);
        this.fixture = body.createFixture(actor,3);
        this.fixture.setUserData("enemigot1");
        actor.dispose();
        setSize((.5f  * PIXELS_IN_METER) - 0.25f, (.5f * PIXELS_IN_METER)-0.25f);//cuidado el d2d dibuja desde la esquina y el box2d desde el centro
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x*PIXELS_IN_METER,body.getPosition().y *PIXELS_IN_METER);
        batch.draw(texture,getX(),getY(),getWidth(),getWidth());
    }

    public void act(float delta) {

    }





}

