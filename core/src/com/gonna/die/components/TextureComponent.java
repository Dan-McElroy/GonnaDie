package com.gonna.die.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {
    // Either:
    public TextureRegion region = null;

    // Or:
    public String text = null;
    public BitmapFont font = null;
    public long timeShown = 0;
    public boolean tickIn = false;
}
