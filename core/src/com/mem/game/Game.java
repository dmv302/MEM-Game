package com.mem.game;

import com.mem.game.screens.GameScreen;
import com.mem.game.screens.MainMenuScreen;

public class Game extends com.badlogic.gdx.Game {
	private MainMenuScreen mms;
	private GameScreen gs;
	
	private enum StatesEnum { MAIN_MENU, PLAYING, EXITING};
	private StatesEnum state;
	
	@Override
	public void create () {
		state = StatesEnum.MAIN_MENU;
		
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

	
}
