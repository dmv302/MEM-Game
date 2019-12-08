package com.mem.game.components;

import com.badlogic.ashley.core.Component;

/*
 * Stores the type of entity this is
 */
public class TypeComponent implements Component {
    public static final int PLAYER = 0;
    public static final int TREE = 1;
    public static final int OTHER = 2;

    public int type = OTHER;
}

