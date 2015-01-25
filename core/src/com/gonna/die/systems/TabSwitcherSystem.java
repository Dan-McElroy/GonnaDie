package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gonna.die.MissionObserver;
import com.gonna.die.TabState;
import com.gonna.die.Task;
import com.gonna.die.TabSwitchedObserver;
import com.gonna.die.components.IdComponent;
import com.gonna.die.components.StateComponent;
import com.gonna.die.components.TaskComponent;
import com.gonna.die.components.TextureComponent;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TabSwitcherSystem extends IteratingSystem {

    class TabSwitched implements MissionObserver {
        @Override
        public void taskCreated(Task task) {
           addTask = task;
        }

        @Override
        public void taskRemoved(Task task) {
            removeTask = task;
        }
    }
    private int currentSelection;
    ComponentMapper<TextureComponent> tcm;
    ComponentMapper<StateComponent> scm;
    ComponentMapper<IdComponent> icm;
    ComponentMapper<TaskComponent> taskm;
    ArrayList<Entity> entities = new ArrayList<>();
    ArrayList<TabSwitchedObserver> observers = new ArrayList<>();
    Task addTask = null;
    Task removeTask = null;

    public TabSwitcherSystem(MissionSystem ms) {
        super(Family.getFor(TextureComponent.class, StateComponent.class, IdComponent.class, TaskComponent.class));
        tcm = ComponentMapper.getFor(TextureComponent.class);
        scm = ComponentMapper.getFor(StateComponent.class);
        icm = ComponentMapper.getFor(IdComponent.class);
        taskm = ComponentMapper.getFor(TaskComponent.class);
        ms.registerObserver(new TabSwitched());
    }

    public void registerObserver(TabSwitchedObserver tso) {
        observers.add(tso);
    }

    Stream<Entity> unusedEntities() {
       return entities.stream().filter((e) -> scm.get(e).state == TabState.UNUSED);
    }

    private void updateCurrentSelection(int newSelection) {
        currentSelection = newSelection;
        Task task = taskm.get(entities.get(currentSelection)).task;
        for (TabSwitchedObserver tso : observers) {
            tso.tabSwitched(task);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        boolean up = Gdx.input.isKeyJustPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);

        if (addTask != null) {
            Entity entity = unusedEntities().findFirst().get();
            StateComponent sc = scm.get(entity);
            TextureComponent tc = tcm.get(entity);
            IdComponent ic = icm.get(entity);
            TaskComponent taskc = taskm.get(entity);
            taskc.task = addTask;
            if (ic.id == 0) {
                sc.state = TabState.NORMAL_SELECTED;
                tc.region = new TextureRegion(new Texture("ui/tasks/taskAlert_B_F.png"));
                updateCurrentSelection(0);
            } else {
                sc.state = TabState.NORMAL_UNSELECTED;
                tc.region = new TextureRegion(new Texture("ui/tasks/taskAlert_B.png"));
            }
            addTask = null;
        }
        int count = (int)unusedEntities().count();

        if (removeTask != null) {
            Entity remove = entities.stream().filter((e) -> taskm.get(e).task == removeTask).findFirst().get();

            IdComponent ic = icm.get(remove);

            for (int i = ic.id; i < 3 - count; i++) {
                Entity previous = entities.get(i);
                Entity current = entities.get(i + 1);

                StateComponent previousSc = scm.get(previous);
                TextureComponent previousTc = tcm.get(previous);
                TaskComponent previousTaskc = taskm.get(previous);
                StateComponent currentSc = scm.get(current);
                TextureComponent currentTc = tcm.get(current);
                TaskComponent currentTaskc = taskm.get(current);

                previousSc.state = currentSc.state;
                previousTc.region = currentTc.region;
                previousTaskc.task = currentTaskc.task;
            }

            Entity last = entities.get(3 - count);
            StateComponent sc = scm.get(last);
            TextureComponent tc = tcm.get(last);
            TaskComponent taskc = taskm.get(last);

            sc.state = TabState.UNUSED;
            tc.region = null;
            taskc.task = null;

            if (currentSelection == 3 - count) {
                updateCurrentSelection(2 - count);
            }

            removeTask = null;
        }

        // Deselect current tab
        if (count < 4 && (up || down)) {
            Entity current = entities.get(currentSelection);
            StateComponent sc = scm.get(current);
            TextureComponent tc = tcm.get(current);
            if (sc.state == TabState.NORMAL_SELECTED) {
                sc.state = TabState.NORMAL_UNSELECTED;
                tc.region = new TextureRegion(new Texture("ui/tasks/taskAlert_B.png"));
            } else if (sc.state == TabState.CRITICAL_SELECTED) {
                sc.state = TabState.CRITICAL_UNSELECTED;
                tc.region = new TextureRegion(new Texture("ui/tasks/taskAlert_R.png"));
            }
        }

        int idx = 0;
        if (count < 4 && up) {
            idx = currentSelection == 0 ? 3 - count : (currentSelection - 1) % (4 - count);
        } else if (count < 4 && down) {
            idx = (currentSelection + 1) % (4 - count);
        }

        if (count < 4 && (up || down)) {
            // Select new tab
            updateCurrentSelection(idx);
            Entity newSelection = entities.get(currentSelection);
            StateComponent sc = scm.get(newSelection);
            TextureComponent tc = tcm.get(newSelection);
            if (sc.state == TabState.NORMAL_UNSELECTED) {
                sc.state = TabState.NORMAL_SELECTED;
                tc.region = new TextureRegion(new Texture("ui/tasks/taskAlert_B_F.png"));
            } else if (sc.state == TabState.CRITICAL_UNSELECTED) {
                sc.state = TabState.CRITICAL_SELECTED;
                tc.region = new TextureRegion(new Texture("ui/tasks/taskAlert_R_F.png"));
            }
        }

        entities.clear();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entities.add(icm.get(entity).id, entity);
    }
}
