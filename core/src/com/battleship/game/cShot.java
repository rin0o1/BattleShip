package com.battleship.game;

import java.util.ArrayList;

public class cShot extends cWeapon
{

    public cShot()
    {
        super("WeaponBattleshipStandardGun.png", "to shot");
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
                squareLocation.setTextureShot();
                eventOfShot=gameEvent.SHIPSUNK;
            }
            else {
                eventOfShot=gameEvent.SHIPHIT;
                squareLocation.setTextureShot();
            }
        }
        else
        {
            eventOfShot=gameEvent.SHIPMISSED;
            squareLocation.setTextureMissed();
            //set new texture
        }

        squareLocation.setIsAvailableForAction(false);
        result.add(eventOfShot);
        result.add(s);

        return result;
    }



}
