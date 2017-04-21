package com.salvaedu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.salvaedu.game.Constantes.PIXELS_IN_METER;

//enemigo tipo 1
public class EnemyType1 extends Enemy {


    boolean sube ;

    public EnemyType1(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        this.body = world.createBody(def);
        this.sube = true;
        this.body.setLinearVelocity(0,4);
        PolygonShape actor = new PolygonShape();
        actor.setAsBox(0.25f, 0.25f);
        this.fixture = body.createFixture(actor,3);
        this.fixture.setUserData("enemigot1");
        alive = true;
        actor.dispose();
        setSize((.5f  * PIXELS_IN_METER) - 0.125f, (.5f * PIXELS_IN_METER)-0.125f);//cuidado el d2d dibuja desde la esquina y el box2d desde el centro
    }

    public void act(float delta) {
        movimiento();
    }

    private void movimiento(){
        if((body.getPosition().y - 0.3) < 0 && !sube){
            sube = true;
            body.setLinearVelocity(0,Constantes.SPEED_ENEMY_SUBE_BAJA);
        }
        else if((body.getPosition().y + 0.3) > 4 && sube){
            sube = false;
            body.setLinearVelocity(0,-Constantes.SPEED_ENEMY_SUBE_BAJA);

        }

    }



}
