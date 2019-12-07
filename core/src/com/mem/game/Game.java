package com.mem.game;

import com.mem.game.screens.GameScreen;

public class Game extends com.badlogic.gdx.Game {

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

}
