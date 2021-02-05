package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class cShot extends cWeapon
{

    public cShot()
    {
        super("WeaponBattleshipStandardGun.png");
    }
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
