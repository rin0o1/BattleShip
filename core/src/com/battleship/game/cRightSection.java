package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

public class cRightSection extends Actor
{
    private ArrayList<cShip> sankShips;
    private ArrayList<cShip> unsankShips;

    private TextButton sankWritten;
    private TextButton unksankWritten;

    private Stage stage;
    private float startX;

    public  cRightSection (Stage stage, float startX)
    {
        this.startX=startX;
        this.stage=stage;
        sankShips= new ArrayList<>();
        unsankShips=new ArrayList<>();

        float screenHeight= Gdx.graphics.getHeight();
        sankWritten = new TextButton("sank",
                cBattleShip.gameSkin);

        sankWritten.setBounds(startX,screenHeight-50,0,0);
        stage.addActor(sankWritten);
    }

    public void addShips(int list, cShip s)
    {
        //create new ship as copy of s

    }

    public  ArrayList<cShip> getList(int list){
        return  null;
    }
}
