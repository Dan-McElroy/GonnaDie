package com.gonna.die;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public final static TextureRegion TASK_TAB_CRITICAL_SELECTED = new TextureRegion(new Texture("ui/tasks/taskAlert_R_F.png"));
    public final static TextureRegion TASK_TAB_CRITICAL_UNSELECTED = new TextureRegion(new Texture("ui/tasks/taskAlert_R.png"));
    public final static TextureRegion TASK_TAB_NORMAL_SELECTED = new TextureRegion(new Texture("ui/tasks/taskAlert_B_F.png"));
    public final static TextureRegion TASK_TAB_NORMAL_UNSELECTED = new TextureRegion(new Texture("ui/tasks/taskAlert_B.png"));
    public final static TextureRegion TASK_BACKGROUND = new TextureRegion(new Texture("ui/tasks/taskScreen_bg.png"), 500, 250);
    public final static TextureRegion STATUS_BACKGROUND = new TextureRegion(new Texture("ui/status/statusScreen_bg.png"), 500, 400);
    public final static TextureRegion HEALTH_BAR = new TextureRegion(new Texture("healthBar.jpg"), 40, 160);
    public final static TextureRegion SHIP_BACKGROUND = new TextureRegion(new Texture("ui/ship/shipScreen_bg.png"), 630, 500);
    public final static TextureRegion SHIP_BRIDGE = new TextureRegion(new Texture("ui/ship/shipBridge.png"));
    public final static TextureRegion SHIP_ENGINES = new TextureRegion(new Texture("ui/ship/shipEngines.png"));
    public final static TextureRegion SHIP_PODS_BOTTOM = new TextureRegion(new Texture("ui/ship/shipPods_B.png"));
    public final static TextureRegion SHIP_PODS_TOP = new TextureRegion(new Texture("ui/ship/shipPods_T.png"));
    public final static TextureRegion SHIP_REACTORS_BOTTOM = new TextureRegion(new Texture("ui/ship/shipReactors_B.png"));
    public final static TextureRegion SHIP_REACTORS_TOP = new TextureRegion(new Texture("ui/ship/shipReactors_T.png"));
    public final static TextureRegion SHIP_TRUSS = new TextureRegion(new Texture("ui/ship/shipTruss.png"));
}
