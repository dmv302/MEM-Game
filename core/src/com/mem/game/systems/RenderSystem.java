package com.mem.game.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    //private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    //private ComponentMapper<TextureComponent> vm = ComponentMapper.getFor(TextureComponent.class);
    //private ComponentMapper<SizeComponent> sm = ComponentMapper.getFor(SizeComponent.class);

    public RenderSystem(OrthographicCamera camera){
        batch = new SpriteBatch();

        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        //entities = engine.getEntitiesFor(Family.all(TransformComponent.class, TextureComponent.class, SizeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        batch.end();
    }
}