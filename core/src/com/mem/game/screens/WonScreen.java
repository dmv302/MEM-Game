package com.mem.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mem.game.Game;

public class WonScreen extends MemScreen {
	private Stage ui;
	private SpriteBatch batch;
	private Skin skin;
	private Viewport viewport;

	private Label label;
	private TextButton start;


	public WonScreen(Game game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/freezingui/freezing-ui.json"));
		batch = new SpriteBatch();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ui = new Stage(viewport, batch);
		game.addInput(ui);

		start = new TextButton("Exit", skin);
		start.setPosition((Gdx.graphics.getWidth() - start.getWidth()) / 2,
				Gdx.graphics.getHeight() / 10);
		start.addListener(exitListener);
		ui.addActor(start);
		
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		label = new Label("SANTA CLAUS: Great job! You managed to collect all of the presents and completed my trial! Now I see that you are indeed a great elf and that you deserve to bear a title of Santa's assistant! ", skin);
		label.setFontScale(1.2f);
		label.setWrap(true);
		label.setWidth(viewport.getWorldWidth());
		label.setPosition(0, viewport.getWorldHeight() - label.getHeight() * 3);
		ui.addActor(label);
		
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ui.act(delta);
		ui.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	private ClickListener exitListener = new ClickListener() {
		public void clicked(InputEvent event, float x, float y) {
			game.exit();
		}
	};
}
