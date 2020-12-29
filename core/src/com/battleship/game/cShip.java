package com.battleship.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class cShip extends cTableObject implements  iShip{

    private int length;
    private int orientation;
    private boolean isSunk;
    private int [] squareIds;
    private int[] squareIdsNoHit;

    public cShip(
            Texture texture,
            String name,
            int length
    )
    {
        this.texture=texture;
        this.name=name;
        this.length=length;

        image=null;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if(image!=null){
            image.draw(batch, parentAlpha);
        }
    }

    @Override
    public void dropShipOnTable(
            float[] coordinates,
            int Orientation,
            int [] squareId,
            float height)
    {

        image= new Image(texture);
        image.setPosition(coordinates[0], coordinates[1]);
        image.setHeight(height);

    }

    @Override
    public void shipSunk() {

    }

    @Override
    public void shipHit(float[] coordinates) {

    }


    @Override
    public boolean isSunk() {
        return false;
    }
}
