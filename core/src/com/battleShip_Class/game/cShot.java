package com.battleShip_Class.game;

import com.battelShip_Enum.game.eGameEvent;

import java.util.ArrayList;

public class cShot extends cWeapon
{
    public cShot()
    {
        super("WeaponBattleshipStandardGun.png", "to shot");
    }
    public ArrayList<Object> actionWithObject(){

        eGameEvent eventOfShot=null;
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
                eventOfShot= eGameEvent.SHIPSUNK;
            }
            else {
                eventOfShot= eGameEvent.SHIPHIT;
                squareLocation.setTextureShot();
            }
        }
        else
        {
            eventOfShot= eGameEvent.SHIPMISSED;
            squareLocation.setTextureMissed();
            //set new texture
        }

        squareLocation.setIsAvailableForAction(false);
        result.add(eventOfShot);
        result.add(s);

        return result;
    }

}
