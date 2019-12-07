package com.mem.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {
	public TextureRegion texture = null;
	
	public TextureComponent set(TextureRegion region) {
		texture = region;
		return this;
	}
}
