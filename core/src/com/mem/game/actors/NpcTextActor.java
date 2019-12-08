package com.mem.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NpcTextActor extends Actor {
	private ShapeRenderer shapeRenderer;
	static private boolean projectionMatrixSet;
	private int width;
	private int height;
	
	public NpcTextActor(int width, int height){
		shapeRenderer = new ShapeRenderer();
		projectionMatrixSet = false;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		batch.end();
		if(!projectionMatrixSet){
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		}
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.LIGHT_GRAY);
		shapeRenderer.rect(0, 0, width, height);
		shapeRenderer.end();
		batch.begin();
		
	}
}
