package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

public class cRightSection extends Actor
{
    private ArrayList<cShip> sunkShips;
    private ArrayList<cShip> unSunkShips;

    private TextButton sunkWritten;
    private TextButton unkSunkWritten;

    private Stage stage;
    private float startX;

    public  cRightSection (Stage stage, float startX)
    {
        this.startX=startX;
        this.stage=stage;
        sunkShips= new ArrayList<>();
        unSunkShips=new ArrayList<>();

        float screenHeight= Gdx.graphics.getHeight();
        sunkWritten = new TextButton("SUNK",
                cBattleShip.gameSkin);

        sunkWritten.setBounds(startX,screenHeight-50,0,0);
        stage.addActor(sunkWritten);
    }

    public void addShips(int list, cShip s)
    {
        //create new ship as copy of s

    }

    public  ArrayList<cShip> getList(int list){
        return  null;
    }
}
