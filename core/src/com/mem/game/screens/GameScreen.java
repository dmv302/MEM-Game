package com.mem.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mem.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mem.game.actors.NpcTextActor;
import com.mem.game.components.*;
import com.mem.game.input.PlayerInputProcessor;
import com.mem.game.map.WorldMap;
import com.mem.game.systems.PlayerSystem;
import com.mem.game.systems.RenderSystem;
import com.mem.game.systems.TimeSystem;
import com.mem.game.utils.Constants;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import javax.swing.*;

public class GameScreen extends MemScreen {
    Stage ui;
    SpriteBatch batch;
    Engine engine;
    RenderSystem render;

    WorldMap map;
    Viewport viewport;
    OrthographicCamera cam;
    Label label;

    boolean isNpcTextOnScreen = false;
    
    public GameScreen(Game game){
        super(game);
        engine = new Engine();
        batch = new SpriteBatch();
        map = new WorldMap(this);
        cam = new OrthographicCamera(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);
        render = new RenderSystem(cam);
        engine.addSystem(render);
        viewport = new FitViewport(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);
        ui = new Stage(viewport, batch);
        
        cam.position.x = map.getWidth()/2;
        cam.position.y = map.getHeight()/2;
        createPlayer();
        
        displayNpcText("LOL KEK TESTOVEK");
    }

    private Entity createPlayer() {
        Entity player = new Entity();
        TransformComponent component = new TransformComponent();
        component.position.set(Constants.TILE_WIDTH * Constants.SPAWN.x, Constants.TILE_HEIGHT * Constants.SPAWN.y, 0);
        player.add(component);
        player.add(new TextureComponent().set(new TextureRegion(new Texture("hero/south_still.png"))));
        player.add(new VelocityComponent(5 * Constants.TILE_WIDTH, 5 * Constants.TILE_HEIGHT));
        PlayerComponent pc = new PlayerComponent();
        pc.dir = PlayerComponent.DirectionsEnum.DOWN;
        pc.state = PlayerComponent.StatesEnum.STILL;
        player.add(pc);
        PlayerInputProcessor pic = new PlayerInputProcessor(player);
        player.add(pic);
        game.addInput(pic);
        PlayerSystem playerSystem = new PlayerSystem(player,this);
        engine.addEntity(player);
        engine.addSystem(playerSystem);
        engine.addSystem(new TimeSystem());
        return player;
    }
    
    private Entity createNpc(TextureRegion texture, String name, String... phrases) {
        Entity npc = new Entity();
        TransformComponent tc = new TransformComponent();
        TextureComponent txc = new TextureComponent();
        AnimationComponent ac = new AnimationComponent();
        NpcComponent nc = new NpcComponent();
        npc.add(tc);
        npc.add(txc);
        npc.add(ac);
        npc.add(nc);
        engine.addEntity(npc);
        return npc;
    }
    
    public void displayNpcText(String text) {
        isNpcTextOnScreen = true;
        NpcTextActor textBack = new NpcTextActor((int)viewport.getWorldWidth(), (int)viewport.getWorldHeight() / 4);
        ui.addActor(textBack);
        Label label = new Label(text, Constants.SKIN);
        label.setFontScale(0.7f);
//        label.setPosition(viewport.getCamera().viewportWidth / 10, viewport.getCamera().viewportHeight - viewport.getCamera().viewportHeight / 4);
        Container labelContainer = new Container(label);
        labelContainer.bottom();
        labelContainer.left();
        labelContainer.pad(20);
        ui.addActor(label);
    }
    
    public void removeNpcText() {
        isNpcTextOnScreen = false;
        ui.clear();
    }
    
    public Vector3 getPlayerPosition() {
        Entity player = engine.getEntitiesFor(Family.one(PlayerComponent.class).get()).first();
        Vector3 pos = player.getComponent(TransformComponent.class).position.cpy();
        TextureRegion region = player.getComponent(TextureComponent.class).texture;
        if (region != null) {
            pos.x += region.getRegionWidth() / 2;
            pos.y += region.getRegionHeight() / 2;
        }
        return pos;
    }
    
    public WorldMap getMap(){
        return map;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(cam,batch);
        engine.update(delta);
        batch.end();
        ui.act(delta);
        ui.draw();
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
