package com.gonna.die;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gonna.die.components.*;
import com.gonna.die.systems.*;

import java.util.ArrayList;

class MainScreen extends ScreenAdapter {
    Engine engine;
    Viewport viewport;

    public MainScreen() {
        engine = new Engine();
        engine.addSystem(new RenderSystem());
        engine.addSystem(new BlueprintSystem());
        engine.addSystem(new RoomSystem());
        //engine.addSystem(new TickerSystem());
        engine.addSystem(new TimerSystem());
        MissionSystem ms = new MissionSystem();

        TabSwitcherSystem tss = new TabSwitcherSystem(ms);
        engine.addSystem(tss);
        engine.addSystem(ms);
        engine.addSystem(new BarSystem(ms.ship));
        engine.addSystem(new GameOverSystem(ms.ship));
        engine.addSystem(new ProgressSystem());
        engine.addSystem(new AlertSystem());
        engine.addSystem(new FlashyBoxSystem());

        engine.addEntity(createBlueprintEntity());
        engine.addEntity(createHealthBarEntity(Room.LIFE_SUPPORT));
        engine.addEntity(createHealthBarEntity(Room.ENGINES));
        engine.addEntity(createHealthBarEntity(Room.REACTOR));
        engine.addEntity(createHealthBarEntity(Room.BRIDGE));

        engine.addEntity(createBackgroundEntity());
        engine.addEntity(createShipLayoutEntity());
        engine.addEntity(createShipTrussEntity());
        engine.addEntity(createTextReadoutEntity());
        engine.addEntity(createStatusReadoutEntity());

        createExclamationEntities(engine, ms.ship);
        createOverlayEntity(engine);
        createGameOverEntity(engine);
        createFlashyBoxes(engine);
        createMissionProgressEntities(engine);
        createTextReadoutTabs(engine);
        createTextReadoutText(engine, tss);
        createShipRoomsEntities(engine, ms);

        viewport = new FitViewport(1280, 800);
    }

    private void createFlashyBoxes(Engine engine) {
        TextureRegion[] regions = {Assets.ALERT_CMO, Assets.ALERT_DRM, Assets.ALERT_PNS,
                                   Assets.ALERT_SLD, Assets.ALERT_SLR, Assets.ALERT_TST};
        float[] xs = {1060, 1060, 1140, 1140, 1060, 1140};
        float[] ys = {195, 275, 195, 355, 355, 275};
        for (int i = 0; i < 6; i++) {
            Entity e = new Entity();
            TextureComponent tc = new TextureComponent();
            PositionComponent pc = new PositionComponent();
            pc.position.x = xs[i];
            pc.position.y = ys[i];

            FlashyBoxComponent fbc = new FlashyBoxComponent();
            fbc.tr = regions[i];
            e.add(tc);
            e.add(pc);
            e.add(fbc);
            engine.addEntity(e);
        }
    }

    private void createExclamationEntities(Engine engine, Ship ship) {
        for (int i = 0; i < 4; i++) {
            Entity exclamation = new Entity();
            
            TextureComponent tc = new TextureComponent();
            tc.region = Assets.ALERT_SMALL;

            PositionComponent pc = new PositionComponent();
            pc.position.x = 775 + (i * 70);
            pc.position.y = 210;
            pc.position.z = 1;

            AlertComponent ac = new AlertComponent();
            ac.room = ship.getRoom(i);

            exclamation.add(tc);
            exclamation.add(pc);
            exclamation.add(ac);

            engine.addEntity(exclamation);
        }
    }

    private void createOverlayEntity(Engine engine) {
        Entity entity = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = Assets.OVERLAY_BACKGROUND;
        PositionComponent pc = new PositionComponent();
        pc.position.x = 0;
        pc.position.y = 0;
        pc.position.z = -200;
        entity.add(tc);
        entity.add(pc);
        engine.addEntity(entity);
    }

    private Entity createBackgroundEntity() {
        Entity entity = new Entity();

        PositionComponent pc = new PositionComponent();
        pc.position.z = -100;

        entity.add(pc);

        return entity;
    }

    private Entity createShipLayoutEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = Assets.SHIP_BACKGROUND;

        PositionComponent pc = new PositionComponent();
        pc.position.x = 50;
        pc.position.y = 250;
        pc.position.z = -50;

        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private Entity createShipRoom(MissionSystem ms, int roomId, TextureRegion region, float x, float y, float z) {
        Entity room = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = region;

        PositionComponent pc = new PositionComponent();
        pc.position.x = x;
        pc.position.y = y;
        pc.position.z = z;

        RoomComponent rc = new RoomComponent();
        rc.room = ms.ship.getRoom(roomId);

        room.add(new AlphaComponent());
        room.add(tc);
        room.add(pc);
        room.add(rc);

        return room;
    }

    private void createMissionProgressEntities(Engine engine) {
        Entity backgroundEntity = new Entity();
        TextureComponent backgroundTc = new TextureComponent();
        backgroundTc.region = Assets.PROGRESS_BACKGROUND;
        PositionComponent backgroundPc = new PositionComponent();
        backgroundPc.position.x = 50;
        backgroundPc.position.y = 50;
        backgroundPc.position.z = -150;
        backgroundEntity.add(backgroundTc);
        backgroundEntity.add(backgroundPc);

        Entity progressEntity = new Entity();
        TextureComponent progressTc = new TextureComponent();
        progressTc.region = Assets.PROGRESS_SHIP;
        PositionComponent progressPc = new PositionComponent();
        progressPc.position.x = 150;
        progressPc.position.y = 125;
        progressPc.position.z = -100;
        progressEntity.add(progressPc);
        progressEntity.add(progressTc);
        progressEntity.add(new MissionComponent(120000, 2000));

        Entity warpSpeed = new Entity();
        TextureComponent warpSpeedTc = new TextureComponent();
        warpSpeedTc.region = Assets.WARP_SPEED;
        PositionComponent warpSpeedPc = new PositionComponent();
        warpSpeedPc.position.x = 125;
        warpSpeedPc.position.y = 55;
        warpSpeed.add(warpSpeedTc);
        warpSpeed.add(warpSpeedPc);

        Entity distance = new Entity();
        PositionComponent distancePc = new PositionComponent();
        distancePc.position.x = 530;
        distancePc.position.y = 87;
        TextureComponent countdownText = new TextureComponent();
        countdownText.text = "3456Mkm";
        FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(Gdx.files.local("fonts/Futura Koyu.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 18;
        countdownText.font = ftfg.generateFont(param);
        countdownText.font.setColor(0.8f, 0.859f, 0.22f, 1.0f);
        distance.add(countdownText);
        //TimerComponent countdownTimer = new TimerComponent(null);
        //distance.add(countdownTimer);
        distance.add(distancePc);

        SoundComponent sounds = new SoundComponent();
        sounds.sounds = new ArrayList<>();
        sounds.sounds.add(Assets.SOUND_AMBIENT);
        sounds.sounds.get(0).setVolume(0.2f);
        sounds.sounds.add(Assets.SOUND_ALARM1);
        sounds.sounds.add(Assets.SOUND_ALARM2);
        sounds.sounds.add(Assets.SOUND_ALARM3);
        sounds.sounds.add(Assets.SOUND_ALARM4);
        progressEntity.add(sounds);
        engine.addEntity(backgroundEntity);
        engine.addEntity(progressEntity);
        engine.addEntity(warpSpeed);
        engine.addEntity(distance);
    }

    private void createShipRoomsEntities(Engine engine, MissionSystem ms) {
        engine.addEntity(createShipRoom(ms, Room.BRIDGE, Assets.SHIP_BRIDGE, 391, 450, -49));
        engine.addEntity(createShipRoom(ms, Room.ENGINES, Assets.SHIP_ENGINES, 64, 450, -49));
        engine.addEntity(createShipRoom(ms, Room.LIFE_SUPPORT, Assets.SHIP_PODS_BOTTOM, 230, 484, -49));
        engine.addEntity(createShipRoom(ms, Room.LIFE_SUPPORT, Assets.SHIP_PODS_TOP, 230, 566, -49));
        engine.addEntity(createShipRoom(ms, Room.REACTOR, Assets.SHIP_REACTORS_BOTTOM, 150, 400, -49));
        engine.addEntity(createShipRoom(ms, Room.REACTOR, Assets.SHIP_REACTORS_TOP, 150, 628, -49));
    }

    private Entity createShipTrussEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = Assets.SHIP_TRUSS;

        PositionComponent pc = new PositionComponent();
        pc.position.x = 130;
        pc.position.y = 400;
        pc.position.z = -49;

        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private void createTextReadoutText(Engine engine, TabSwitcherSystem tss) {
        Entity title = new Entity();
        PositionComponent titlePosition = new PositionComponent();
        titlePosition.position.x = 1010;
        titlePosition.position.y = 720;
        title.add(titlePosition);
        TextureComponent titleText = new TextureComponent();

        FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(Gdx.files.local("fonts/Futura.ttc"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 24;
        titleText.font = ftfg.generateFont(param);
        ftfg.dispose();
        titleText.font.setColor(0.008f, 0.659f, 0.953f, 1.0f);
        title.add(titleText);
        engine.addEntity(title);

        Entity description = new Entity();
        PositionComponent descPosition = new PositionComponent();
        descPosition.position.x = 1020;
        descPosition.position.y = 680;
        description.add(descPosition);
        TextureComponent descText = new TextureComponent();

        ftfg = new FreeTypeFontGenerator(Gdx.files.local("fonts/Pica.ttf"));
        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 14;
        descText.font = ftfg.generateFont(param);
        ftfg.dispose();
        descText.font.setColor(0.008f, 0.659f, 0.953f, 1.0f);
        description.add(descText);

        Entity countdown = new Entity();
        PositionComponent countdownPosition = new PositionComponent();
        countdownPosition.position.x = 1106;
        countdownPosition.position.y = 548;
        countdown.add(countdownPosition);

        TextureComponent countdownText = new TextureComponent();
        ftfg = new FreeTypeFontGenerator(Gdx.files.local("fonts/Futura Koyu.ttf"));
        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 21;
        countdownText.font = ftfg.generateFont(param);
        countdownText.font.setColor(0.953f, 0.239f, 0.208f, 1.0f);
        countdown.add(countdownText);
        TimerComponent countdownTimer = new TimerComponent(null);
        countdown.add(countdownTimer);

        tss.registerObserver(new TabSwitchedObserver() {
            /* may want to see if we can avoid this being called on new task add */

            @Override
            public void tabSwitched(Task task) {
                titleText.text = task.message;
                titleText.timeShown = System.currentTimeMillis();
                titleText.tickIn = true;
                descText.text = task.getSubTaskSummary();
                descText.timeShown = System.currentTimeMillis();
                descText.tickIn = true;
                countdownTimer.task = task;
            }
        });

        engine.addEntity(description);
        engine.addEntity(countdown);
    }

    private void createTextReadoutTabs(Engine engine) {
        for (int i = 0; i < 4; i++) {
            Entity entity = new Entity();

            TextureComponent tc = new TextureComponent();
            StateComponent sc = new StateComponent();
            IdComponent id = new IdComponent(i);
            PositionComponent pc = new PositionComponent();
            TaskComponent taskc = new TaskComponent();
            pc.position.x = 735;
            pc.position.y = 505 + (3 - i) * 60;

            entity.add(tc);
            entity.add(sc);
            entity.add(id);
            entity.add(pc);
            entity.add(taskc);

            engine.addEntity(entity);
        }
    }

    private Entity createTextReadoutEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = Assets.TASK_BACKGROUND;
        //tc.text = "some test\nthat just keeps going on and on and on\nuntil it's done\n";
        //tc.width = 200; // FIXME This is wrong
        //tc.height = 200;

        long current = System.currentTimeMillis();

        TickerComponent tkc = new TickerComponent();
        /*tkc.tasks.add(new Task("Task 1", current + 3000));
        tkc.tasks.add(new Task("Task 2", current + 4000));
        tkc.tasks.add(new Task("Task 3", current + 5000));*/

        PositionComponent pc = new PositionComponent();
        pc.position.x = 730;
        pc.position.y = 500;
        pc.position.z = -50;

        entity.add(tkc);
        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private Entity createStatusReadoutEntity() {
        Entity entity = new Entity();

        TextureComponent tc = new TextureComponent();
        tc.region = Assets.STATUS_BACKGROUND;

        PositionComponent pc = new PositionComponent();
        pc.position.x = 730;
        pc.position.y = 50;
        pc.position.z = -50;

        entity.add(tc);
        entity.add(pc);

        return entity;
    }

    private Entity createBlueprintEntity() {
        Entity entity = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = Assets.BLUEPRINT;


        StateComponent sc = new StateComponent();

        PositionComponent pc = new PositionComponent();
        pc.position.x = 0;
        pc.position.y = -775;
        pc.position.z = 100;

        entity.add(tc);
        entity.add(sc);
        entity.add(pc);
        entity.add(new BlueprintComponent());
        return entity;
    }

    private Entity createHealthBarEntity(int roomId) {
        Entity entity = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = Assets.getHealthBar();

        PositionComponent pc = new PositionComponent();
        pc.position.x = 780 + (70 * roomId);        //40 wide, 160 tall
        pc.position.y = 255;
        pc.position.z = -150;

        entity.add(tc);
        entity.add(pc);
        entity.add(new BarComponent(roomId));

        return entity;
    }

    private void createGameOverEntity(Engine engine) {
        Entity entity = new Entity();
        TextureComponent tc = new TextureComponent();
        tc.region = Assets.GAME_OVER;
        PositionComponent pc = new PositionComponent();
        pc.position.x = 0;
        pc.position.y = 0;
        pc.position.z = 999;
        AlphaComponent ac = new AlphaComponent();
        ac.alpha = 0.0f;
        entity.add(tc);
        entity.add(pc);
        entity.add(ac);
        entity.add(new GameOverComponent());
        engine.addEntity(entity);
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
            Gdx.graphics.setDisplayMode(1280, 800, false);
        }
    }
}
