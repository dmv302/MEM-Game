package com.mem.game.podarki;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mem.game.components.TextureComponent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Podarok {
    public Rectangle rectangle;
    public Texture texture = new Texture("present.png");
    public static final ArrayList<Podarok> podarki = new ArrayList<>();
    public Podarok(Rectangle rect){
        rectangle = rect;
    }
}
