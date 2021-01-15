package com.battleship.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.ArrayList;

public class cShip extends cTableObject {

    private int length;
    private int shotReceived;
    private objectOrientation orientation;
    private boolean issank;

    private ArrayList<cSquare> mySquare;


    public cShip(
            String name,
            int length
    )
    {
        super();
        this.setName(name);
        this.length=length;
        this.shotReceived=0;

    }

    public boolean updateAndCheckState(){
        shotReceived++;
        this.issank= shotReceived==length;
        return  issank;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public boolean issank(){return  issank;}

    public void dropShipOnTable(
            Texture texture_,
            float[] coordinates,
            objectOrientation orientation,
            float squareHeight,
            ArrayList<cSquare> squares
            )
    {
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
}
