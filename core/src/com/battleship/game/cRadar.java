package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class cRadar extends cWeapon
{

    public cRadar(){
        texture= new Texture(Gdx.files.internal("radar_01.png"));
        body= new Image(texture);
        body.setSize(50,50);
    }

    @Override
    public Object action(){
        System.out.println("c readar action");
        return  null;
    }
    public void dropRadarOnTableAndAct(
            cSquare square,
            ArrayList<cSquare> squaresAround
    )
    {

    }


}
