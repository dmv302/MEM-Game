package com.mem.game.input;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mem.game.components.PlayerComponent;

public class PlayerInputProcessor implements InputProcessor, Component {
	private Entity player;
	private PlayerComponent pc;
	
	public PlayerInputProcessor(Entity player) {
		this.player = player;
		this.pc = player.getComponent(PlayerComponent.class);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		pc.state = PlayerComponent.StatesEnum.MOVING;
		switch (keycode) {
			case Input.Keys.W:
				pc.dir = PlayerComponent.DirectionsEnum.UP;
				break;
			case Input.Keys.S:
				pc.dir = PlayerComponent.DirectionsEnum.DOWN;
				break;
			case Input.Keys.A:
				pc.dir = PlayerComponent.DirectionsEnum.LEFT;
				break;
			case Input.Keys.D:
				pc.dir = PlayerComponent.DirectionsEnum.RIGHT;
				break;
			default:
				pc.state = PlayerComponent.StatesEnum.STILL;
				break;
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.W:
			case Input.Keys.S:
			case Input.Keys.A:
			case Input.Keys.D:
				pc.state = PlayerComponent.StatesEnum.STILL;
				break;
		}
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
