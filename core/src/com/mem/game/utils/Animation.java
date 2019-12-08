package com.mem.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Animation {
	private TextureRegion[] regions;
	private float delta;
	
	public Animation(float delta, String... regionPath) {
		regions = new TextureRegion[regionPath.length];
		for (int i = 0; i < regionPath.length; i++) {
			regions[i] = new TextureRegion(new Texture(regionPath[i]));
		}
		this.delta = delta;
	}
	
	public TextureRegion getForTime(float time) {
		return regions[(int)(time / delta) % (regions.length)];
	}
}
