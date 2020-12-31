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
            objectOrientation Orientation,
            ArrayList<cSquare> mySquare,
            float squareHeight)
    {

        image= new Image(texture);
        image.setRotation(getRotationDegreeFromOrientation(
                Orientation)
        );
        image.setPosition(coordinates[0], coordinates[1]);
        image.setHeight(squareHeight);

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
