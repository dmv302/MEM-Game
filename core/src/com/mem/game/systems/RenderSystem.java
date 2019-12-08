package com.mem.game.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mem.game.components.PlayerComponent;
import com.mem.game.components.TextureComponent;
import com.mem.game.components.TransformComponent;
import com.mem.game.utils.Constants;

public class RenderSystem extends EntitySystem {
    private Engine engine;
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ComponentMapper<TransformComponent> transm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<TextureComponent> textm = ComponentMapper.getFor(TextureComponent.class);

    public RenderSystem(OrthographicCamera camera){
        batch = new SpriteBatch();

        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, TextureComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        for (Entity entity : entities) {
            TextureComponent textureComponent = textm.get(entity);
            TransformComponent transformComponent = transm.get(entity);
            if (entity.getComponent(PlayerComponent.class) != null) continue;
            
            batch.draw(textureComponent.texture, transformComponent.position.x, transformComponent.position.y,
                    Constants.ORIGIN.x, Constants.ORIGIN.y,
                    textureComponent.texture.getRegionWidth(), textureComponent.texture.getRegionHeight(),
                    transformComponent.scale.x, transformComponent.scale.y, transformComponent.rotation);
        }
        Entity player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
        TextureComponent ptxc = player.getComponent(TextureComponent.class);
        TransformComponent ptc = player.getComponent(TransformComponent.class);
        batch.draw(ptxc.texture, ptc.position.x, ptc.position.y,
                Constants.ORIGIN.x, Constants.ORIGIN.y,
                ptxc.texture.getRegionWidth(), ptxc.texture.getRegionHeight(),
                ptc.scale.x, ptc.scale.y, ptc.rotation);
        batch.end();
        
    }
}