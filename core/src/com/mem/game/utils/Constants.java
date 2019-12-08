package com.mem.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Constants {
    public static final float VIRTUAL_WIDTH = 520.0f;
    public static final float VIRTUAL_HEIGHT = 270.0f;

    public static final float TILE_WIDTH = 32;
    public static final float TILE_HEIGHT = 32;
    
    public static final Vector2 SPAWN = new Vector2(10, 10);
    public static final Vector2 ORIGIN = new Vector2(0, 0);
    
    public static float PLAYER_ANIMATION_DELTA = 0.1f;
    
    public static float NPC_DISTANCE = 4 * TILE_WIDTH;
    
    public static final Skin SKIN = new Skin(Gdx.files.internal("skin/freezingui/freezing-ui.json"));
}
