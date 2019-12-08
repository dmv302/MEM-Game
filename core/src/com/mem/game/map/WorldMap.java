package com.mem.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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

    TiledMapTileLayer collisionLayer; // assuming the layer at index on contains tiles


    public WorldMap(GameScreen gs) {
        gameScreen = gs;
        loader = new TmxMapLoader();
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        //params.
        map = loader.load("maps/map.tmx");
        mapLayers = map.getLayers();
        terrainLayer = (TiledMapTileLayer) mapLayers.get("terrain");
        collisionLayer = (TiledMapTileLayer) mapLayers.get("forest");
        renderer = new OrthogonalTiledMapRenderer(map);

        direction = new Vector2();

        layer = (TiledMapTileLayer) map.getLayers().get(0);

        // Reading map layers
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        updateCamera(camera);
//        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        renderer.render();
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

    public float getWidth() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

    public float getHeight() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

}
