package com.battleship.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class cShip extends cTableObject implements  iShip{

    private int length;
    private objectOrientation orientation;
    private boolean isSunk;
    private ArrayList<cSquare> mySquare;
    private ArrayList<cSquare> squareNoHit;

    public cShip(
            Texture texture,
            String name,
            int length
    )
    {
        this.texture=texture;
        this.setName(name);
        this.length=length;

        body =null;

    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if(body !=null){
            body.draw(batch, parentAlpha);
        }
    }

    @Override
    public void dropShipOnTable(
            float[] coordinates,
            objectOrientation Orientation,
            ArrayList<cSquare> mySquare,
            float squareHeight)
    {

        body = new Image(texture);

        body.setRotation(getRotationDegreeFromOrientation(
                Orientation)
        );
        body.setPosition(coordinates[0], coordinates[1]);
        body.setHeight(squareHeight);
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


    public int getLength(){return  this.length;}
}
