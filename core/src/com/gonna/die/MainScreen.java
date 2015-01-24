package com.gonna.die;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.StateComponent;
import com.gonna.die.components.TextureComponent;
import com.gonna.die.systems.BlueprintSystem;
import com.gonna.die.systems.RenderSystem;

class MainScreen extends ScreenAdapter {
    Engine engine;
    public MainScreen() {
        engine = new Engine();
        engine.addSystem(new RenderSystem());
        engine.addSystem(new BlueprintSystem());
        engine.addEntity(createBlueprintEntity());
        engine.addEntity(createBackgroundEntity());
    }

    private Entity createBackgroundEntity() {
        Entity entity = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = new TextureRegion(new Texture("man_of_war_bay_dorset_england.jpg"));

        PositionComponent pc = new PositionComponent();
        pc.position.z = -100;

        entity.add(tc);
        entity.add(pc);

        return entity;
    }
    private Entity createBlueprintEntity() {
        Entity entity = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = new TextureRegion(new Texture("annapurna_south_nepal.jpg"));

        StateComponent sc = new StateComponent();

        PositionComponent pc = new PositionComponent();
        pc.position.x = 0;
        //pc.position.y = -25000; // Why the hell is it this number?
        pc.position.y = -400; // Why the hell is it this number?
        pc.position.z = 100;

        entity.add(tc);
        entity.add(sc);
        entity.add(pc);
        return entity;
    }
    @Override
    public void render(float delta) {
        engine.update(delta);
        //update(delta);
        //draw();
    }
}
