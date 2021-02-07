package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

public class cRightSection extends Actor
{
    private ArrayList<cShip> sunkShips;
    private ArrayList<cShip> unsunkShips;
    private TextButton sunkWritten;
    private TextButton unksunkWritten;
    private Stage stage;
    private float startX;
    private float lastFloatPositionY;

    public  cRightSection (Stage stage, float startX) {
        this.startX=startX;
        this.stage=stage;
        sunkShips = new ArrayList<>();
        unsunkShips =new ArrayList<>();

        float screenHeight= Gdx.graphics.getHeight();
        sunkWritten = new TextButton("UNKSANK SHIP",
                cBattleShip.gameSkin);

        sunkWritten.setBounds(startX+50,screenHeight-50,0,0);
        stage.addActor(sunkWritten);

        unksunkWritten =null;
        lastFloatPositionY=750;

    }
    public void addShips( cShip s, boolean isSunk) {
        cShip ship= s;

        if(isSunk)
        {
            unsunkShips.add(s);
            if(unksunkWritten ==null){
                unksunkWritten = new TextButton("SANK SHIP",
                        cBattleShip.gameSkin);

                lastFloatPositionY-=50;
                unksunkWritten.setBounds(startX+50,lastFloatPositionY,0,0);
                stage.addActor(unksunkWritten);
            }

            sunkShips.remove(s);
            lastFloatPositionY-=30;
            s.setPosition(startX-50,lastFloatPositionY );
        }

        sunkShips.add(s);
        Image img= s.getIconTexture();
        lastFloatPositionY-=50;
        img.setPosition(startX-50, lastFloatPositionY);

    }

}
