package com.battleship.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class cTableObject extends Actor {

    protected float[] coordinates;
    protected String name;
    protected float width;
    protected float height;
    protected Texture texture;
    protected Image image;


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}
