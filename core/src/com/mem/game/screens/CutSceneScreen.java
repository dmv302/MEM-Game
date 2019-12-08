package com.mem.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.mem.game.Game;

public class CutSceneScreen extends MemScreen {
	private Stage ui;
	private SpriteBatch batch;
	private Skin skin;
	private Viewport viewport;

	private Label label;
	private TextButton start;

	public CutSceneScreen(Game game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/freezingui/freezing-ui.json"));
		batch = new SpriteBatch();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		ui = new Stage(viewport, batch);
		game.addInput(ui);

		start = new TextButton("Start", skin);
		start.setPosition((Gdx.graphics.getWidth() - start.getWidth()) / 2,
				Gdx.graphics.getHeight() / 10);
		start.addListener(toGameListener);
		ui.addActor(start);

		label = new Label("SANTA CLAUS: Young elf, you have been studying your craft with me for a long ten years, and now the time has come for you to prove that I can trust you in important tasks. I have prepared a challenge for you: there are 12 hidden packages across the woods, and you need to find them all. If you succeed, you'll become my assistant. Good luck!", skin);
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

	private ClickListener toGameListener = new ClickListener() {
		public void clicked(InputEvent event, float x, float y) {
			game.play();
		}
	};

}
