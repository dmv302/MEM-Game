package com.mem.game.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
	public double velocityX = 0;
	public double velocityY = 0;
	public VelocityComponent(double velocityX, double velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
}
