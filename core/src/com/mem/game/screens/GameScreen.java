package com.mem.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mem.game.components.TextureComponent;
import com.mem.game.components.TransformComponent;
import com.mem.game.map.WorldMap;
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
        map = new WorldMap();
        cam = new OrthographicCamera(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);
        render = new RenderSystem(cam);
        engine.addSystem(render);
        
        
        cam.position.x = 0;
        cam.position.y = 0;

        viewport = new FillViewport(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);

        img = new Texture("hero.png");
        TextureRegion region = new TextureRegion(img);
        
        
        // test
        Entity playerMaybe = new Entity();
        TransformComponent component = new TransformComponent();
        component.position.set(0, 0, 0);
        playerMaybe.add(new TextureComponent().set(region));
        playerMaybe.add(component);
        engine.addEntity(playerMaybe);
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
