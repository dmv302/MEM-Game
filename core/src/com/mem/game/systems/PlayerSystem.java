package com.mem.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mem.game.components.PlayerComponent;
import com.mem.game.components.TextureComponent;
import com.mem.game.components.TransformComponent;
import com.mem.game.components.VelocityComponent;
import com.mem.game.screens.GameScreen;
import com.mem.game.utils.Constants;

public class PlayerSystem extends EntitySystem {
	private Entity player;
	private GameScreen screen;

	public PlayerSystem(Entity player, GameScreen screen) {
		super(0);
		this.screen = screen;
		this.player = player;
	}
	
	public void update(float delta) {
		PlayerComponent pc = player.getComponent(PlayerComponent.class);
		TransformComponent pt = player.getComponent(TransformComponent.class);
		VelocityComponent vc = player.getComponent(VelocityComponent.class);
		TextureComponent txc = player.getComponent(TextureComponent.class);
		// update position
		if (canMove(pt, pc, vc,txc, delta) && pc.state == PlayerComponent.StatesEnum.MOVING) {
			switch (pc.dir) {
				case DOWN:
					pt.position.y -= vc.velocityY * delta;
					break;
				case UP:
					pt.position.y += vc.velocityY * delta;
					break;
				case LEFT:
					pt.position.x -= vc.velocityX * delta;
					break;
				case RIGHT:
					pt.position.x += vc.velocityX * delta;
					break;
			}
		}
		// update textures
		switch (pc.dir) {
			case DOWN:
				txc.texture = pc.southAnimation.getForTime(pt.time * pc.state.ordinal());
				break;
			case UP:
				txc.texture = pc.northAnimation.getForTime(pt.time * pc.state.ordinal());
				break;
			case LEFT:
				txc.texture = pc.westAnimation.getForTime(pt.time * pc.state.ordinal());
				break;
			case RIGHT:
				txc.texture = pc.eastAnimation.getForTime(pt.time * pc.state.ordinal());
				break;
		}
	}

	private boolean canMove(TransformComponent pt, PlayerComponent pc, VelocityComponent vc,TextureComponent tc, float delta) {
		TiledMapTileLayer.Cell cell;
		int w = tc.texture.getRegionWidth();
		int h = tc.texture.getRegionHeight();
		switch (pc.dir) {
			case DOWN:
				cell = screen.getMap().collisionLayer().getCell((int) (pt.position.x + w/2) / (int) Constants.TILE_WIDTH, (int) (pt.position.y - vc.velocityY * delta) / (int) Constants.TILE_HEIGHT);
				if (cell != null)

					return false;

				break;
			case UP:
				cell = screen.getMap().collisionLayer().getCell((int) (pt.position.x + w/2) / (int) Constants.TILE_WIDTH, (int) (pt.position.y + vc.velocityY * delta) / (int) Constants.TILE_HEIGHT);
				if (cell != null) {

					return false;

				}
				break;
			case LEFT:
				cell = screen.getMap().collisionLayer().getCell((int) (pt.position.x + w/2 - vc.velocityX * delta) / (int) Constants.TILE_WIDTH, (int) pt.position.y / (int) Constants.TILE_HEIGHT);
				if (cell != null)

					return false;

				break;
			case RIGHT:
				cell = screen.getMap().collisionLayer().getCell((int) (pt.position.x + w/2 + vc.velocityX * delta) / (int) Constants.TILE_WIDTH, (int) pt.position.y / (int) Constants.TILE_HEIGHT);
				if (cell != null)

					return false;

				break;
		}
		return true;
	}
}
