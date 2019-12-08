package com.mem.game.screens;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mem.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mem.game.components.PlayerComponent;
import com.mem.game.components.TextureComponent;
import com.mem.game.components.TransformComponent;
import com.mem.game.components.VelocityComponent;
import com.mem.game.input.PlayerInputProcessor;
import com.mem.game.map.WorldMap;
import com.mem.game.systems.PlayerSystem;
import com.mem.game.systems.RenderSystem;
import com.mem.game.utils.Constants;

public class GameScreen extends MemScreen {
    SpriteBatch batch;
    Texture img;
    Engine engine;
    RenderSystem render;

    WorldMap map;
    Viewport viewport;
    OrthographicCamera cam;

    public GameScreen(Game game){
        super(game);
        engine = new Engine();
        batch = new SpriteBatch();
        map = new WorldMap(this);
        cam = new OrthographicCamera(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);
        render = new RenderSystem(cam);
        engine.addSystem(render);
        
        
        cam.position.x = 0;
        cam.position.y = 0;

        viewport = new FillViewport(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);

        
        createPlayer();
    }

    private Entity createPlayer() {
        Entity player = new Entity();
        img = new Texture("hero.png");
        TextureRegion region = new TextureRegion(img);
        TransformComponent component = new TransformComponent();
        component.position.set(0, 0, 0);
        player.add(component);
        player.add(new TextureComponent().set(region));
        player.add(new VelocityComponent(5 * Constants.TILE_WIDTH, 5 * Constants.TILE_HEIGHT));
        player.add(new PlayerComponent());
        PlayerInputProcessor pic = new PlayerInputProcessor(player);
        player.add(pic);
        game.addInput(pic);
        PlayerSystem playerSystem = new PlayerSystem(player);
        engine.addEntity(player);
        engine.addSystem(playerSystem);
        return player;
    }
    
    public Vector3 getPlayerPosition() {
        Entity player = engine.getEntitiesFor(Family.one(PlayerComponent.class).get()).first();
        Vector3 pos = player.getComponent(TransformComponent.class).position.cpy();
        TextureRegion region = player.getComponent(TextureComponent.class).texture;
        pos.x += region.getRegionWidth() / 2;
        pos.y += region.getRegionHeight() / 2;
        return pos;
    }
    
    
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        map.render(cam);
        batch.begin();
        engine.update(delta);
       // batch.draw(img,cam.position.x - img.getWidth()/2f,cam.position.y - img.getHeight()/2f);
        batch.end();
        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        batch.setProjectionMatrix(cam.combined);
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
        batch.dispose();
    }
}
