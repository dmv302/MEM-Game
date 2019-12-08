package com.mem.game.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component {
	public enum StatesEnum { STILL, MOVING };
	public enum DirectionsEnum { UP, DOWN, LEFT, RIGHT };
	public StatesEnum state = StatesEnum.STILL;
	public DirectionsEnum dir = DirectionsEnum.DOWN;
}
