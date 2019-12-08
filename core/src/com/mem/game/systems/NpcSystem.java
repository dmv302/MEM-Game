package com.mem.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.mem.game.components.NpcComponent;
import com.mem.game.screens.GameScreen;

public class NpcSystem extends EntitySystem {
	private Entity npc;
	private GameScreen screen;
	ComponentMapper<NpcComponent> nm;
	
	public NpcSystem(Entity npc, GameScreen screen) {
		this.npc = npc;
		this.screen = screen;
	}
	
	@Override
	public void update(float time) {
		// TODO
		
	}
}
