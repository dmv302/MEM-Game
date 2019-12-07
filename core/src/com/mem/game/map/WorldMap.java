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

        boolean toLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched() &&
                mouseX < width * 0.25f);

        boolean toRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.
                isTouched() && mouseX > width * 0.75f);

        boolean toUp = Gdx.input.isKeyPressed(Input.Keys.UP) || (Gdx.input.isTouched() &&
                mouseY < height * 0.25f);

        boolean toDown = Gdx.input.isKeyPressed(Input.Keys.DOWN) || (Gdx.input.
                isTouched() && mouseY > height * 0.75f);

        if ((camera.position.x <= Constants.VIRTUAL_WIDTH / 2 && toLeft) || (camera.position.y <= Constants.VIRTUAL_HEIGHT / 2 && toDown) ||
                (camera.position.x >= getWidth() - Constants.VIRTUAL_WIDTH / 2 && toRight) || (camera.position.y >= getHeight() - Constants.VIRTUAL_HEIGHT / 2 && toUp)
        ) {
            if (toLeft && camera.position.x <= Constants.VIRTUAL_WIDTH / 2) {
                camera.position.x = Constants.VIRTUAL_WIDTH / 2;
            }
            if (toDown && camera.position.y <= Constants.VIRTUAL_HEIGHT / 2) {
                camera.position.y = Constants.VIRTUAL_HEIGHT / 2;
            }
            if (toRight &&camera.position.x >= getWidth() - Constants.VIRTUAL_WIDTH / 2 ) {
                camera.position.x = getWidth() - Constants.VIRTUAL_WIDTH / 2;
            }
            if (toUp && camera.position.y >= getHeight() - Constants.VIRTUAL_HEIGHT / 2) {
                camera.position.y = getHeight() - Constants.VIRTUAL_HEIGHT / 2;
            }

        } else {
            if (toLeft) {
                direction.x = -1;
            } else if (toRight) {
                direction.x = 1;
            }
            if (toUp) {
                direction.y = 1;
            } else if (toDown) {
                direction.y = -1;
            }
            //direction.nor().scl(CAMERA_SPEED * Gdx.graphics.getDeltaTime());???
            camera.position.x += direction.x;
            camera.position.y += direction.y;
        }
        camera.update();
    }

    public float getWidth() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

    public float getHeight() {
        return layer.getHeight() * Constants.TILE_WIDTH;
    }

}