package com.salvaedu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;


public class GameScreen extends BasicScreen {
    private Stage stage;
    private World world;
    private PlayerEntity player;
    private Enemy enemy;
    private Enemy enemy2;
    private ArrayList<Enemy> enemigos;

    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(1280, 720));
        world = new World(new Vector2(0,0),true);
        world.setContactListener(new ContactListener() {
            private boolean areCollided(Contact contact, Object UserA, Object UserB){
                return (contact.getFixtureA().getUserData().equals(UserA) && contact.getFixtureB().getUserData().equals(UserB))
                        || (contact.getFixtureA().getUserData().equals(UserB)&& contact.getFixtureB().getUserData().equals(UserA));

            }

            @Override
            public void beginContact(Contact contact) {

                if(areCollided(contact,"jugador","enemigot1")){
                    player.setAlive(false);
                    
                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        enemigos = new ArrayList<Enemy>();
    }

    @Override
    public void show() {
       stage.setDebugAll(true);
        Texture playertexture = game.getManager().get("cpquiero.png");
        player = new PlayerEntity(world,playertexture, new Vector2(1,4));
        stage.addActor(player);
        Texture enemyt1 = game.getManager().get("caca.png");
        for(int i = 0; i < 10 ; ++i ){
            enemy = new EnemyType1(world,enemyt1,new Vector2(10*i,2));
            enemigos.add(enemy);
            stage.addActor(enemy);
        }
        Texture enemyt2 = game.getManager().get("queso.png");
        enemy2 = new EnemyType2(world,enemyt2,new Vector2(15,2));
        stage.addActor(enemy2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,0.6f,0.6f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        if (player.getX()> 150) {
            stage.getCamera().translate(Constantes.SPEED_PLAYER_X * delta * Constantes.PIXELS_IN_METER, 0, 0);
        }
        world.step(delta,6,2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();

        for(int i= 0;i < enemigos.size(); ++i){
            enemigos.get(i).detach();
            enemigos.get(i).remove();
        }
        enemy2.detach();
        enemy2.remove();
    }
}
