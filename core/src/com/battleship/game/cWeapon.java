package com.battleship.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class cWeapon extends  cTableObject
{
    protected cSquare squareLocation;

    public cWeapon(String internalTexture, String name)
    {
        super();
        setName(name);
        init(internalTexture);
    }

    private void init(String internalTexture){
        body.setSize(50,50);
        Texture baseTexture= new Texture(Gdx.files.internal(internalTexture));
        setTextureFromTexture(baseTexture);
    }

    public void setSquareLocation(cSquare squareLocation){
        this.squareLocation=squareLocation;
    }

}
