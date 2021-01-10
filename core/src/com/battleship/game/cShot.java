package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class cShot extends cWeapon
{

    public cShot(){
        texture= new Texture(Gdx.files.internal("WeaponBattleshipStandardGun.png"));
        body= new Image(texture);
        body.setSize(50,50);
    }


    @Override
    public Object action(){
        System.out.println("shot effect");
        return null;
    }



}
