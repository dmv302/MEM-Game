package com.mem.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mem.game.components.AnimationComponent;
import com.mem.game.components.TextureComponent;
import com.mem.game.components.TransformComponent;

public class AnimationSystem extends EntitySystem {
	private Engine engine;
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void update(float time) {
		ImmutableArray<Entity> array = engine.getEntitiesFor(Family.all(AnimationComponent.class, TransformComponent.class, TextureComponent.class).get());
		AnimationComponent ac;
		TransformComponent tc;
		TextureComponent txc;
		
		for (Entity entity : array) {
			ac = entity.getComponent(AnimationComponent.class);
			tc = entity.getComponent(TransformComponent.class);
			txc = entity.getComponent(TextureComponent.class);
			if (ac != null) {
				txc.set(ac.animation.getForTime(tc.time));
			}
		}
	}
}
