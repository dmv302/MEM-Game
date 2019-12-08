package com.mem.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mem.game.components.TransformComponent;

public class TimeSystem extends EntitySystem {
	private Engine engine;
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void update(float time) {
		ImmutableArray<Entity> array = engine.getEntitiesFor(Family.all(TransformComponent.class).get());
		for (Entity entity : array) {
			entity.getComponent(TransformComponent.class).time += time;
		}
	}
}
