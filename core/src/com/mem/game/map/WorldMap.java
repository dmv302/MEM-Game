package com.mem.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mem.game.podarki.Podarok;
import com.mem.game.screens.GameScreen;
import com.mem.game.utils.Constants;

public class WorldMap {

    private static final float CAMERA_SPEED = 100f;
    private GameScreen gameScreen;
    private TiledMap map;
    private TmxMapLoader loader;
    private OrthogonalTiledMapRenderer renderer;
    private Vector2 direction;

    MapLayers mapLayers;
    TiledMapTileLayer layer; // assuming the layer at index on contains tiles

    TiledMapTileLayer terrainLayer; // assuming the layer at index on contains tiles

    TiledMapTileLayer collisionLayer;

    TiledMapTileLayer npcLayer;


    public WorldMap(GameScreen gs) {
        gameScreen = gs;
        loader = new TmxMapLoader();
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        //params.
        map = loader.load("maps/map.tmx");
        mapLayers = map.getLayers();
        terrainLayer = (TiledMapTileLayer) mapLayers.get("terrain");
        collisionLayer = (TiledMapTileLayer) mapLayers.get("forest");
        npcLayer =  (TiledMapTileLayer)mapLayers.get("npc");
        renderer = new OrthogonalTiledMapRenderer(map);

        direction = new Vector2();

        layer = (TiledMapTileLayer) map.getLayers().get(0);

        initPodarki();

        // Reading map layers
    }

    private void initPodarki(){
        for(MapObject obj : map.getLayers().get("podarki").getObjects()){
            if(obj instanceof RectangleMapObject){
                Podarok pod = new Podarok(((RectangleMapObject) obj).getRectangle());
                Podarok.podarki.add(pod);
            }

        }
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {

        updateCamera(camera);
//        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        renderer.render();
        batch.end();
        batch.begin();
        for(Podarok pod :Podarok.podarki){
            batch.draw(pod.texture, pod.rectangle.x,pod.rectangle.y);
        }
    }

    private void updateCamera(OrthographicCamera camera) {
        Vector3 ppos = gameScreen.getPlayerPosition();
        
        camera.position.x = ppos.x;
        camera.position.y = ppos.y;
        
        camera.update();
    }

    public TiledMapTileLayer collisionLayer(){
        return collisionLayer;
    }

    public TiledMapTileLayer npcLayer(){
        return npcLayer;
    }

    public float getWidth() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

    public float getHeight() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

}
