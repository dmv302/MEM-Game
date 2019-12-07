package com.mem.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mem.game.map.WorldMap;
import com.mem.game.utils.Constants;

public class Game extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;

	WorldMap map;
	OrthographicCamera cam;

	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new WorldMap();
		cam = new OrthographicCamera(Constants.VIRTUAL_WIDTH,Constants.VIRTUAL_HEIGHT);
		cam.position.x = map.getWidth()/2;
		cam.position.y = map.getHeight()/2;
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		map.render(cam);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
