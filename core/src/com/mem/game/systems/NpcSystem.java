package com.mem.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.mem.game.components.NpcComponent;
import com.mem.game.components.PlayerComponent;
import com.mem.game.components.TransformComponent;
import com.mem.game.screens.GameScreen;
import com.mem.game.utils.Constants;

public class NpcSystem extends EntitySystem {
	private Engine engine;
	private Entity npc;
	private GameScreen screen;
	ComponentMapper<NpcComponent> nm;
	
	public NpcSystem(Entity npc, GameScreen screen) {
		this.npc = npc;
		this.screen = screen;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}
	
	@Override
	public void update(float time) {
		ImmutableArray<Entity> npcs = engine.getEntitiesFor(Family.all(NpcComponent.class).get());
		Vector3 playerPos = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first().getComponent(TransformComponent.class).position;
		double closestDist = Float.MAX_VALUE;
		Entity closestNpc = null;
		for (Entity npc : npcs) {
			NpcComponent nc = npc.getComponent(NpcComponent.class);
			Vector3 npcPos = npc.getComponent(TransformComponent.class).position;
			double dist = Math.sqrt(Math.pow(playerPos.x - npcPos.x, 2) + Math.pow(playerPos.y - npcPos.y,2));
			if (dist <= Constants.NPC_DISTANCE) {
				if (nc.state == NpcComponent.NpcState.DISABLED)
					nc.state = NpcComponent.NpcState.SILENT;
			} else {
				nc.state = NpcComponent.NpcState.DISABLED;
			}
			if (dist < closestDist) {
				closestDist = dist;
				closestNpc = npc;
			}
		}
		if (closestNpc != null) {
			if (closestNpc.getComponent(NpcComponent.class).state == NpcComponent.NpcState.TALKING) {
				screen.displayNpcText(closestNpc.getComponent(NpcComponent.class).dialog.getCurrPhrase());
			} else {
				screen.removeNpcText();
			}
		}
	}
}
