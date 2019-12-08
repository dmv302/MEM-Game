package com.mem.game.components;

import com.badlogic.ashley.core.Component;
import com.mem.game.utils.Animation;
import com.mem.game.utils.Constants;

public class PlayerComponent implements Component {
	public enum StatesEnum { STILL, MOVING };
	public enum DirectionsEnum { UP, DOWN, LEFT, RIGHT };
	public StatesEnum state = StatesEnum.STILL;
	public DirectionsEnum dir = DirectionsEnum.DOWN;
	public Animation northAnimation = new Animation(Constants.PLAYER_ANIMATION_DELTA, "hero/north_still.png", "hero/north_state1.png", "hero/north_state2.png");
	public Animation southAnimation = new Animation(Constants.PLAYER_ANIMATION_DELTA, "hero/south_still.png", "hero/south_state1.png", "hero/south_state2.png");
	public Animation eastAnimation = new Animation(Constants.PLAYER_ANIMATION_DELTA, "hero/east_still.png", "hero/east_state1.png");
	public Animation westAnimation = new Animation(Constants.PLAYER_ANIMATION_DELTA, "hero/west_still.png", "hero/west_state1.png");
}
