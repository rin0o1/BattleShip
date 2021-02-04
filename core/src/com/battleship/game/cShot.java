package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class cShot extends cWeapon
{
    private String texturePath;

    public cShot()
    {
        init();
    }

    public cShot(cSquare square)
    {
        super(square);
        init();
    }

    private void init()
    {
        Texture baseTexture= new Texture(Gdx.files.internal("WeaponBattleshipStandardGun.png"));
        setTextureFromTexture(baseTexture);
        body.setSize(50,50);
    }

    @Override
    public ArrayList<Object> actionWithObject(){

        gameEvent eventOfShot=null;
        cShip s=null;

        ArrayList<Object> result= new ArrayList<>();

        boolean isThereAShip= squareLocation.cointainsAShip();

        if(isThereAShip)
        {
            s=(cShip) squareLocation.isBusyAndObject();

            boolean isSunk= s.updateAndCheckState();

            if(isSunk)
            {
                squareLocation.setTextureForShot();
                eventOfShot=gameEvent.SHIPSUNK;
            }
            else {
                eventOfShot=gameEvent.SHIPHIT;
                squareLocation.setTextureForShot();
            }
        }
        else
        {
            eventOfShot=gameEvent.SHIPMISSED;
            squareLocation.setTextureForMissed();
            //set new texture
        }

        squareLocation.setIsAvailableForAction(false);
        result.add(eventOfShot);
        result.add(s);

        return result;
    }



}
