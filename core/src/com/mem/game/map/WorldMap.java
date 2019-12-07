package com.mem.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mem.game.utils.Constants;

public class WorldMap {

    private static final float CAMERA_SPEED = 100f;

    private TiledMap map;
    private TmxMapLoader loader;
    private OrthogonalTiledMapRenderer renderer;
    private Vector2 direction;
    TiledMapTileLayer layer; // assuming the layer at index on contains tiles


    public WorldMap() {
        loader = new TmxMapLoader();
        map = loader.load("maps/world_map_test.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        direction = new Vector2();
        layer = (TiledMapTileLayer) map.getLayers().get(0);
    }

    public void render(OrthographicCamera camera) {
        updateCamera(camera);
        renderer.setView(camera);
        renderer.render();
    }

    private void updateCamera(OrthographicCamera camera) {
        direction.set(0.0f, 0.0f);
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched() &&
                mouseX < width * 0.25f)) {
            direction.x = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.
                isTouched() && mouseX > width * 0.75f)) {
            direction.x = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || (Gdx.input.isTouched() &&
                mouseY < height * 0.25f)) {
            direction.y = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || (Gdx.input.
                isTouched() && mouseY > height * 0.75f)) {
            direction.y = -1;
        }
        direction.nor().scl(CAMERA_SPEED * Gdx.graphics.getDeltaTime());
        camera.position.x += direction.x;
        camera.position.y += direction.y;
        camera.update();
    }

    public float getWidth() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

    public float getHeight() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

}
