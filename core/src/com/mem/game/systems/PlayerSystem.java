package com.mem.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.mem.game.components.PlayerComponent;
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
	}
	
}
