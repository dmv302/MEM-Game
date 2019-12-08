package com.mem.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mem.game.Game;

public class MainMenuScreen extends MemScreen {
	private Stage ui;
	private SpriteBatch batch;
	private Skin skin;
	private Viewport viewport;
	private Music theme;

	private Label title;
	private TextButton start;
	private TextButton exit;
	private Label madeBy;
	
	public MainMenuScreen(Game game) {
		super(game);
		skin = Constants.SKIN;
		theme = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.ogg"));
		theme.setLooping(true);
		theme.setVolume(0.3f);
		theme.play();
		batch = new SpriteBatch();
		viewport = new ScreenViewport();
		ui = new Stage(viewport, batch);
		game.addInput(ui);
		
		initComponents();
	}
	
	private void initComponents() {
		title = new Label("MEM GAME", skin);
		madeBy = new Label("Made by MEM team", skin);
		start = new TextButton("Play", skin);
		exit = new TextButton("Exit", skin);
		
		updatePositions();
		
		ui.addActor(title);
		ui.addActor(start);
		ui.addActor(exit);
		ui.addActor(madeBy);
		
		start.addListener(startListener);
		exit.addListener(exitListener);
	}
	
	private void updatePositions() {
		title.setPosition((Gdx.graphics.getWidth() - title.getWidth()) / 2,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);
		start.setPosition((Gdx.graphics.getWidth() - start.getWidth()) / 2,
				Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 10));
		exit.setPosition((Gdx.graphics.getWidth() - exit.getWidth()) / 2,
				Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 2 + exit.getHeight() + Gdx.graphics.getHeight() / 10));
		madeBy.setPosition(Gdx.graphics.getWidth() / 20,
				Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 20));
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ui.act(delta);
		ui.draw();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
	
	private ClickListener startListener = new ClickListener() {
		public void clicked(InputEvent event, float x, float y){
			game.toCutscene();
		}
	};
	private ClickListener exitListener = new ClickListener() {
		public void clicked(InputEvent event, float x, float y){
			game.exit();
		}
	};
}
