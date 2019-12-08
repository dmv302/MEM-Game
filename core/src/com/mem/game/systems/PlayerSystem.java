package com.mem.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mem.game.components.PlayerComponent;
import com.mem.game.components.TextureComponent;
import com.mem.game.components.TransformComponent;
import com.mem.game.components.VelocityComponent;

public class PlayerSystem extends EntitySystem {
	private Entity player;
	
	public PlayerSystem(Entity player) {
		super(0);
		this.player = player;
	}
	
	public void update(float delta) {
		PlayerComponent pc = player.getComponent(PlayerComponent.class);
		TransformComponent pt = player.getComponent(TransformComponent.class);
		VelocityComponent vc = player.getComponent(VelocityComponent.class);
		TextureComponent txc = player.getComponent(TextureComponent.class);
		// update position
		if (pc.state == PlayerComponent.StatesEnum.MOVING) {
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
	
}
