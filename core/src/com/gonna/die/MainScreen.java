package com.gonna.die;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.StateComponent;
import com.gonna.die.components.TextureComponent;
import com.gonna.die.components.TickerComponent;
import com.gonna.die.systems.BlueprintSystem;
import com.gonna.die.systems.RenderSystem;
import com.gonna.die.systems.TickerSystem;

class MainScreen extends ScreenAdapter {
    Engine engine;
    Camera camera;
    Viewport viewport;

    public MainScreen() {
        engine = new Engine();
        engine.addSystem(new RenderSystem());
        engine.addSystem(new BlueprintSystem());
        engine.addSystem(new TickerSystem());

        engine.addEntity(createBlueprintEntity());

        engine.addEntity(createBackgroundEntity());
        engine.addEntity(createShipLayoutEntity());
        engine.addEntity(createTextReadoutEntity());
        engine.addEntity(createStatusReadoutEntity());
        engine.addEntity(createMissionProgressEntity());

        camera = new PerspectiveCamera();
        viewport = new FitViewport(1280, 800, camera);
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

    private Entity createShipLayoutEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = new TextureRegion(new Texture("todo.jpg"));

        PositionComponent pc = new PositionComponent();
        pc.position.x = 30;
        pc.position.y = 300;

        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private Entity createTextReadoutEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.text = "some test\nthat just keeps going on and on and on\nuntil it's done\n";
        tc.width = 200;
        tc.height = 200;

        long current = System.currentTimeMillis();
        TickerComponent tkc = new TickerComponent();
        tkc.tasks.add(new Task("Task 1", current + 3000));
        tkc.tasks.add(new Task("Task 2", current + 4000));
        tkc.tasks.add(new Task("Task 3", current + 5000));

        PositionComponent pc = new PositionComponent();
        pc.position.x = 400;
        pc.position.y = 540;

        entity.add(tkc);
        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private Entity createStatusReadoutEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = new TextureRegion(new Texture("todo.jpg"));

        PositionComponent pc = new PositionComponent();
        pc.position.x = 400;
        pc.position.y = 50;

        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private Entity createMissionProgressEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = new TextureRegion(new Texture("todo.jpg"));

        PositionComponent pc = new PositionComponent();
        pc.position.x = 30;
        pc.position.y = 50;

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
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            toggleFullscreen();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void toggleFullscreen() {
        if (!Gdx.graphics.isFullscreen()) {
            Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,
                    Gdx.graphics.getDesktopDisplayMode().height, true);
        } else {
            Gdx.graphics.setDisplayMode(640, 400, false);
        }
    }
}
