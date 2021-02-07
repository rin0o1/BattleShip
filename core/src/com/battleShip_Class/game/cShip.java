package com.battleShip_Class.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.battelShip_Enum.game.eObjectOrientation;

import java.util.ArrayList;

public class cShip extends cTableObject {

    private int length;
    private int shotReceived;
    private eObjectOrientation orientation;
    private boolean isSunk;

    private Image iconTexture;

    private ArrayList<cSquare> mySquare;


    public cShip(
            String name,
            int length,
            Image iconTexture
    ) {
        super();
        this.iconTexture=iconTexture;

        iconTexture.setRotation(
                getRotationDegreeFromOrientation(
                        eObjectOrientation.HORIZONTAL)
        );

        this.setName(name);
        this.length=length;
        this.shotReceived=0;
    }

    public boolean updateAndCheckState(){
        shotReceived++;
        this.isSunk = shotReceived==length;
        return isSunk;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        iconTexture.draw(batch,parentAlpha);
    }

    public boolean isSunk(){return isSunk;}

    public void dropShipOnTable(
            Texture texture_,
            float[] coordinates,
            eObjectOrientation orientation,
            float squareHeight,
            ArrayList<cSquare> squares
            ) {
        this.orientation= orientation;
        this.mySquare=squares;
        body= new Image(texture_);
        body.setRotation(getRotationDegreeFromOrientation(
                orientation)
        );
        body.setPosition(coordinates[0], coordinates[1]);
        body.setHeight(squareHeight);

    }

    public  ArrayList<cSquare> getMySquare(){return  this.mySquare;}
    public int getLength(){return  this.length;}
    public Image getIconTexture(){return iconTexture;}
}
