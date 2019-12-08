package com.mem.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mem.game.screens.GameScreen;
import com.mem.game.screens.MainMenuScreen;

public class Game extends com.badlogic.gdx.Game {
	private MainMenuScreen mms;
	private GameScreen gs;
	public InputMultiplexer inputProcessor;
	
	private enum StatesEnum { MAIN_MENU, PLAYING, EXITING};
	private StatesEnum state;
	
	@Override
	public void create () {
		state = StatesEnum.MAIN_MENU;
		
		inputProcessor = new InputMultiplexer();
		mms = new MainMenuScreen(this);
		gs = new GameScreen(this);
		
		setScreen(mms);
	}
	
	public void play() {
		if (state == StatesEnum.MAIN_MENU) {
			setScreen(gs);
			state = StatesEnum.PLAYING;
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
