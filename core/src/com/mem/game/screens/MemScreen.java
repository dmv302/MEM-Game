package com.mem.game.screens;

import com.badlogic.gdx.Screen;
import com.mem.game.Game;

public abstract class MemScreen implements Screen {
    Game game;

    public MemScreen(Game game) {
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
