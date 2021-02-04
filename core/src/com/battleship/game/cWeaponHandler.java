package com.battleship.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class cWeaponHandler
{
    private cTableSection section;
    private ArrayList<cWeapon> weapons;
    private cWeapon weaponSelected;

    private Stage stage;

    public  cWeaponHandler(cTableSection section){
        this.section=section;
    }

    public void initWeapon(){}

    public void setWeaponSelected(){}

    public int shotFunction(cSquare square)
    {
        cShot shot=new cShot(square);

        ArrayList<Object> res= shot.actionWithObject();
        gameEvent event=(gameEvent)res.get(0);

        int point=0;

        if (event==null){return -1;}

        switch (event)
        {
            case SHIPHIT:
                System.out.println("My ship hit");
                point=1;
                break;
            case SHIPSUNK:
                cShip ship= (cShip) res.get(1);
                int shipLength= ship.getLength();
                point= shipLength*2;
                System.out.println("Ship sunk");
                stage.addActor(ship);
                ArrayList<cSquare> squares=ship.getMySquare();
                for (cSquare s: squares)
                {
                    float[] sLocation=s.getPosition();
                    section.addExplosion(sLocation[0], sLocation[1]);
                }
                section.updateShipList(ship,true);
                break;
            case SHIPMISSED:
                point=1;
                System.out.println("Ship missed");
                break;
        }


        weaponSelected=null;

        return point;
    }

    public void onWeaponSelected(cWeapon weapon, Image icon){

    }
}
