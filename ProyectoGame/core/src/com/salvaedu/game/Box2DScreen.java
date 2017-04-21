package com.salvaedu.game;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Gdx;

public class Box2DScreen extends BasicScreen {
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body naveBody, sueloBody;
    private Fixture naveFixture,sueloFixture;

    public Box2DScreen(MainGame game) {
        super(game);
    }

    public void show(){
        world = new World(new Vector2(0,-10),true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(64,36);
        camera.translate(2,1);
        BodyDef navebodydef = createnavebodydef();
        naveBody = world.createBody(navebodydef);
        PolygonShape naveshape = new PolygonShape();
        naveshape.setAsBox(0.5f, 0.25f);
        naveFixture = naveBody.createFixture(naveshape,1);
        naveshape.dispose();
        float velocityY = naveBody.getLinearVelocity().y;
        naveBody.setLinearVelocity(8,velocityY);
        crearSuelo();
    }

    private void crearSuelo(){
        BodyDef suelobodydef = createsuelobodydef();
        sueloBody = world.createBody(suelobodydef);
        PolygonShape sueloshape = new PolygonShape();
        sueloshape.setAsBox(500, 1);
        sueloFixture = sueloBody.createFixture(sueloshape,1);
        sueloshape.dispose();
    }

    private BodyDef createsuelobodydef() {
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        return def;
    }

    private BodyDef createnavebodydef() {
        BodyDef def = new BodyDef();
        def.position.set(-2,2);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    public void dispose(){
        naveBody.destroyFixture(naveFixture);
        sueloBody.destroyFixture(sueloFixture);
        world.destroyBody(sueloBody);
        world.destroyBody(naveBody);
        world.dispose();
        renderer.dispose();

    }
    public void render(float delta){
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            world.step(delta,6,2); // cada cuanto se simula.

            renderer.render(world,camera.combined);
     }
}
