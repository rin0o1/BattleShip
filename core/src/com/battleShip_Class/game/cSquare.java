package com.battleShip_Class.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class cSquare extends cTableObject  {

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
                ) {

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
        setTextureFromTextureTemp(textureOnHover);
    }
    public int[] getCoordinates(){return tableCoordinates;}
    public boolean getIsAvailableForAction(){return this.isAvailableForAction;}
    public void setIsAvailableForAction(boolean isAvailableForAction) {
        this.isAvailableForAction =isAvailableForAction;
    }
    public void placeObject(cTableObject object) {
        this.object=object;
    }
    public void setTextureShot(){
        Texture t= new Texture(Gdx.files.internal("shotHit.PNG"));
        setTextureFromTexture(t);
    }
    public void setTextureMissed(){
        Texture t = new Texture(Gdx.files.internal("shotMissed.PNG"));
        setTextureFromTexture(t);
    }
    public cTableObject isBusyAndObject() {
        return object;
    }
    public boolean isBusy() {
        return object!=null;
    }
    public  boolean cointainsAShip()
    {
        return  object instanceof cShip;
    }

}

