package com.gonna.die;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GonnaDieGame extends Game {

    @Override
    public void create () {
        MainScreen ms = new MainScreen();
        setScreen(ms);
    }

    @Override
    public void render () {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }
}
