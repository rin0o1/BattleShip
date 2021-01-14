package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class cSquare extends cTableObject implements iSquare {

    private Texture textureOnHover;
    private cTableObject object;
    private boolean isAvailableForAction;
    private int [] tableCoordinates;

    public cSquare(
                float width,
                float height,
                float [] position,
                int [] tableCoordinates,
                String name
                )
    {

        super();

        this.object=null;
        this.isAvailableForAction =true;

        this.width=width;
        this.height=height;

        this.tableCoordinates=tableCoordinates;
        this.setName(name);
        this.position =position;


        textureOnHover = new Texture(Gdx.files.internal("squareOver.png"));
        Texture baseTexture = new Texture(Gdx.files.internal("squareTexture.gif"));
        setTextureFromTexture(baseTexture);

        body.setPosition(position[0], position[1]);
        body.setSize(width, height);

    }

    public void squareTouchUp()
    {
        setTextureFromTexture(texture);
    }

    public void squareTouchDown()
    {
        setTextureFromTexture(textureOnHover, true);
    }

    public  int[] getCoordinates(){return tableCoordinates;}

    public boolean getIsAvailableForAction(){return this.isAvailableForAction;}

    public  void setIsAvailableForAction(boolean isAvailableForAction)
    {
        this.isAvailableForAction =isAvailableForAction;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public  void act(float delta){
        super.act(delta);
    }

    @Override
    public void setState() {

    }

    @Override
    public void placeObject(cTableObject object) {
        this.object=object;
    }

    @Override
    public void removeCurrentObject() {

    }

    public void setTextureForShot(){
        Texture t= new Texture(Gdx.files.internal("shotHit.PNG"));
        setTextureFromTexture(t);
    }

    public void setTextureForMissed(){
        Texture t = new Texture(Gdx.files.internal("shotMissed.PNG"));
        setTextureFromTexture(t);
    }

    @Override
    public cTableObject isBusyAndObject() {
        return object;
    }

    @Override
    public boolean isBusy() {
        return object!=null;
    }

    public  boolean cointainsAShip()
    {
        return  object instanceof cShip;
    }



}
