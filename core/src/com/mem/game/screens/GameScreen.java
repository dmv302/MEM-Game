package com.mem.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.audio.Sound;
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
import com.mem.game.actors.NpcTextActor;
import com.mem.game.components.*;
import com.mem.game.input.NpcInputProcessor;
import com.mem.game.input.PlayerInputProcessor;
import com.mem.game.map.WorldMap;
import com.mem.game.systems.NpcSystem;
import com.mem.game.systems.PlayerSystem;
import com.mem.game.systems.RenderSystem;
import com.mem.game.systems.TimeSystem;
import com.mem.game.utils.Constants;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mem.game.utils.NpcDialog;

public class GameScreen extends MemScreen {
    Stage ui;
    SpriteBatch batch;
    Engine engine;
    RenderSystem render;
    Sound talkingSound;
    
    WorldMap map;
    Viewport viewport;
    OrthographicCamera cam;
    Label label;
    
    NpcTextActor giftsCounterBack;
    Label giftsCounterLabel;

    static public int BOX_COUNTER = 0;
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
        engine.addSystem(new TimeSystem());
    
        giftsCounterLabel = new Label("Presents picked: 0 / 12", Constants.SKIN);
        giftsCounterLabel.setFontScale(0.6f);
        giftsCounterBack = new NpcTextActor((int) giftsCounterLabel.getWidth(), (int) giftsCounterLabel.getHeight());
        Container<Label> c1 = new Container<>(giftsCounterLabel);
        c1.pad(20);
        c1.left();
        Container<NpcTextActor> c2 = new Container<>(giftsCounterBack);
        c2.pad(20);
        c2.left();
        
        //ui.addActor(giftsCounterBack);
        ui.addActor(giftsCounterLabel);
        
        cam.position.x = map.getWidth()/2;
        cam.position.y = map.getHeight()/2;
        createPlayer();
        createNpcs();
    }
    
    private void createNpcs() {
        createNpc(new TextureRegion(new Texture("npc/squirrel.png")), "squirrel", 5, 7,
                "Hello, elf! Are you one of the Santa's assistants?",
                "Ah, I see. He wants you to find all of the Christmas gifts.",
                "You may want to walk around the woods for some time. Maybe you'll find some of them.");
        createNpc(new TextureRegion(new Texture("npc/snake.png")), "snake", 17, 3,
                "Greeeeetings-s-s-s-s, friend. Looking for pres-s-sent-s-s-s?...",
                "I s-s-s-ink I s-s-saw z-z-z-zem s-s-somewhere near z-z-ze pond...");
        createNpc(new TextureRegion(new Texture("npc/frog.png")), "frog", 13, 25,
                "Who are you? What do you want for me?",
                "Are you looking for presents? You're in a wrong place. ",
                "Even if I would have seen one, I wouldn't tell you. Get lost...");
        createNpc(new TextureRegion(new Texture("npc/rabbit.png")), "rabbit", 28, 20,
                "Hippity-hop! Presents? Haven't heard of them!", "I just wanna play around and have fun!");
        createNpc(new TextureRegion(new Texture("npc/hedgehog.png")), "hedgehog", 7, 13,
                "What? You know how to talk to animals? You must be Santa's elf!",
                "Once people knew how to talk to animals too, but then they forgot the knowledge, and now elves are the ones who keep it.",
                "I've seen some presents thrown around the forest, look around.");
        
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
        return player;
    }
    
    private Entity createNpc(TextureRegion texture, String name, int x, int y, String... phrases) {
        Entity npc = new Entity();
        TransformComponent tc = new TransformComponent();
        tc.position.x = x * Constants.TILE_WIDTH;
        tc.position.y = y * Constants.TILE_HEIGHT;
        TextureComponent txc = new TextureComponent().set(texture);
        AnimationComponent ac = new AnimationComponent();
        NpcComponent nc = new NpcComponent();
        NpcInputProcessor nip = new NpcInputProcessor(npc, nc);
        nc.dialog = new NpcDialog(phrases);
        nc.input = nip;
        game.addInput(nip);
        npc.add(tc);
        npc.add(txc);
        npc.add(ac);
        npc.add(nc);
        engine.addEntity(npc);
        engine.addSystem(new NpcSystem(npc, this));
        return npc;
    }
    
    public void displayNpcText(String text) {
        removeNpcText();
        isNpcTextOnScreen = true;
        NpcTextActor textBack = new NpcTextActor((int)viewport.getWorldWidth(), (int)viewport.getWorldHeight() / 4);
        ui.addActor(textBack);
        Label label = new Label(text, Constants.SKIN);
        label.setFontScale(0.7f);
        label.setWidth((int)viewport.getWorldWidth() - 20);
        label.setWrap(true);
        label.setPosition(10, viewport.getWorldHeight() / 4 - label.getHeight());
        ui.addActor(label);
    }
    
    public void removeNpcText() {
        isNpcTextOnScreen = false;
        ui.clear();
        ui.addActor(giftsCounterLabel);
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
        giftsCounterLabel.setText("Presents picked: " + BOX_COUNTER + " / 12");
        giftsCounterBack.width = (int)giftsCounterLabel.getWidth();
        giftsCounterBack.height = (int)giftsCounterLabel.getHeight();
        giftsCounterLabel.setY(viewport.getWorldHeight() - giftsCounterLabel.getHeight());
        giftsCounterBack.setY(viewport.getWorldHeight() - giftsCounterBack.getHeight());
        
        
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
