package com.mem.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mem.game.screens.CutSceneScreen;
import com.mem.game.screens.GameScreen;
import com.mem.game.screens.MainMenuScreen;
import com.mem.game.screens.WonScreen;

public class Game extends com.badlogic.gdx.Game {
	private MainMenuScreen mms;
	private GameScreen gs;
	private CutSceneScreen cs;
	private WonScreen ws;
	public InputMultiplexer inputProcessor;
	
	private enum StatesEnum { MAIN_MENU, CUTSCENE_SCRENE, PLAYING, FINAL_SCENE, EXITING};
	private StatesEnum state;
	
	@Override
	public void create () {
		state = StatesEnum.MAIN_MENU;
		
		inputProcessor = new InputMultiplexer();
		mms = new MainMenuScreen(this);
		gs = new GameScreen(this);
		cs = new CutSceneScreen(this);
		setScreen(mms);
	}
	
	public void toCutscene() {
		if (state == StatesEnum.MAIN_MENU) {
			setScreen(cs);
			state = StatesEnum.CUTSCENE_SCRENE;
		}
	}

	public void play() {
		if (state == StatesEnum.CUTSCENE_SCRENE) {
			setScreen(gs);
			state = StatesEnum.PLAYING;
		}
	}

	public void won() {
		if (state == StatesEnum.PLAYING) {
			setScreen(ws);
			state = StatesEnum.FINAL_SCENE;
		}
	}
	
	public void exit() {
		state = StatesEnum.EXITING;
	}

	public void addInput(InputProcessor ip) {
		inputProcessor.addProcessor(ip);
		Gdx.input.setInputProcessor(inputProcessor);
	}
}
