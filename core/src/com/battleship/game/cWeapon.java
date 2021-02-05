package com.battleship.game;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class cWeapon extends  cTableObject
{
    protected Texture textureOnSelected;
    protected cSquare squareLocation;

    public  cWeapon(){};

    public cWeapon(cSquare squareLocation)
    {
        super();
        this.squareLocation=squareLocation;
    }

    public Object actionWithObject(ArrayList<cSquare> squareRequired){
        return  null;
    }

    public ArrayList<Object> actionWithObject(){
        System.out.println("general weapon action");
        return null;
    }

    public void action(ArrayList<cSquare> squareRequired){

    }

    public void action()
    {
        System.out.println("general weapon action");

    }
}
