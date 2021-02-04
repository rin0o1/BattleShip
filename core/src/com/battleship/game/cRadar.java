package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class cRadar extends cWeapon
{

    private Texture radarDiscovered;

    public cRadar(){
        init();
    }

    public cRadar(cSquare square)
    {
        super(square);
        init();
        radarDiscovered= new Texture(Gdx.files.internal("radarDiscovered.gif"));
    }

    private void init(){
        Texture baseTexture= new Texture(Gdx.files.internal("radar_01.png"));
        setTextureFromTexture(baseTexture);
        body.setSize(50,50);

    }

    public ArrayList<String> getSquareCoordinatesRequired()
    {
        ArrayList<String> res= new ArrayList<>();
        int [] coordinates= squareLocation.getCoordinates();
        int start_x= coordinates[0];
        int start_Y= coordinates[1];
        String coordinates1= buildString(start_x-1, start_Y+1);
        String coordinates2= buildString(start_x,start_Y+1);
        String coordinates3= buildString(start_x+1, start_Y+1);
        String coordinates4= buildString(start_x+1, start_Y);
        String coordinates5= buildString(start_x+1, start_Y-1);
        String coordinates6= buildString(start_x, start_Y-1);
        String coordinates7= buildString(start_x-1, start_Y-1);
        String coordinates8= buildString(start_x-1, start_Y);
        res.add(coordinates1);
        res.add(coordinates2);
        res.add(coordinates3);
        res.add(coordinates4);
        res.add(coordinates5);
        res.add(coordinates6);
        res.add(coordinates7);
        res.add(coordinates8);
        return  res;
    }

    private String buildString(int start_X, int start_Y)
    {
        return String.valueOf(start_X).concat(",").concat(String.valueOf(start_Y));
    }

    @Override
    public void action(ArrayList<cSquare> squareRequired)
    {

        for (cSquare s: squareRequired)
        {
            if(!s.isBusy()){continue;}
            if (!s.cointainsAShip()){continue;}
            cShip ship= (cShip) s.isBusyAndObject();
            if (ship.isSunk()){continue;}

            s.setTextureFromTexture(radarDiscovered);
            ship.updateAndCheckState();
            s.setIsAvailableForAction(false);
            System.out.println("Square discovered:" + s.getName());
            return;
        }


    }

}
