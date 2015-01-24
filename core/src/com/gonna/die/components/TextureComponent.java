package com.gonna.die.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {
    // Either:
    public TextureRegion region = null;

    // Or:
    public String text = null;
    public float width = 50.0f;
    public float height = 50.0f;
}