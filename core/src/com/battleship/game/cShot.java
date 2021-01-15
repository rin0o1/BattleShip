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

        String outcomeOfShot="";
        int idOutcome;
        cShip s=null;

        ArrayList<Object> result= new ArrayList<>();

        boolean isThereAShip= squareLocation.cointainsAShip();

        if(isThereAShip)
        {
            s=(cShip) squareLocation.isBusyAndObject();

            boolean issank= s.updateAndCheckState();

            if(issank)
            {
                squareLocation.setTextureForShot();
                outcomeOfShot="My ship is sank";
                idOutcome=0;
            }
            else {
                outcomeOfShot="Ship hit";
                idOutcome=1;
                squareLocation.setTextureForShot();
            }
        }
        else
        {
            outcomeOfShot="Ship missed";
            idOutcome=2;
            squareLocation.setTextureForMissed();
            //set new texture
        }

        squareLocation.setIsAvailableForAction(false);
        result.add(idOutcome);
        result.add(outcomeOfShot);
        result.add(s);

        return result;
    }



}
