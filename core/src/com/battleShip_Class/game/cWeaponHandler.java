package com.battleShip_Class.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.battelShip_Enum.game.eGameEvent;
import com.battleShip_Section.game.sTable;
import com.cBattleShip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class cWeaponHandler
{
    private sTable section;
    private ArrayList<cWeapon> weapons;
    private cWeapon weaponSelected;
    private int radarCount;
    private LinkedList<cExplosion> explosionList;
    private Texture explosionTexture;

    public  cWeaponHandler(sTable section){
        this.section=section;
        weapons= new ArrayList<>();
        initWeapon();
        explosionTexture = new Texture("explosion.png");
        explosionList= new LinkedList<>();

        radarCount=4;
    }
    private void initWeapon() {
        cRadar r= new cRadar();
        cShot s= new cShot();
        weapons.add(s);
        weapons.add(r);
    }
    public int shotFunction(cSquare square) {
        cShot shot=new cShot();
        shot.setSquareLocation(square);
        ArrayList<Object> res= shot.actionWithObject();
        eGameEvent event=(eGameEvent)res.get(0);

        int point=0;

        if (event==null){return -1;}

        switch (event)
        {
            case SHIPHIT:
                cMessageOutput.showMessage("You hit my ship!");
                point=1;
                break;
            case SHIPSUNK:
                cShip ship= (cShip) res.get(1);
                int shipLength= ship.getLength();
                point= shipLength*2;
                cMessageOutput.showMessage("You sunk my ship!");
                section.getStage().addActor(ship);
                ArrayList<cSquare> squares=ship.getMySquare();
                for (cSquare s: squares)
                {
                    float[] sLocation=s.getPosition();
                    this.addExplosion(sLocation[0], sLocation[1]);
                }
                section.updateShipList(ship,true);
                break;
            case SHIPMISSED:
                point=1;
                cMessageOutput.showMessage("Ship missed!");
                break;
        }

        section.updateScore(point);
        weaponSelected=null;

        return point;
    }
    public void addExplosion(float locX, float locY) {
        float [] squareDimensions= section.getSquareDimensions();
        explosionList.add(new cExplosion(
                explosionTexture,
                new Rectangle(
                        locX,
                        locY,
                        squareDimensions[0],
                        squareDimensions[1]),
                2f));
    }
    public void placeRadarFunction(cSquare square){
        int [] coordinates= square.getCoordinates();
        int square_X= coordinates[0];
        int square_Y= coordinates[1];
        if(!isRadarAvailable()){cMessageOutput.showMessage("You have not enough radars"); return ;}

        boolean canBePlaced=
                square_X>0 &&
                        square_X < section.getColumsNum()-1 &&
                        square_Y> 0 &&
                        square_Y< section.getRowsNum()-1;

        if(!canBePlaced){cMessageOutput.showMessage("I am sorry, the radar cannot be placed here"); return ;}

        cRadar r=new cRadar();
        r.setSquareLocation(square);
        ArrayList<cSquare> squares=new ArrayList<>();
        ArrayList<String> squareCoordinatesRequired;
        squareCoordinatesRequired=r.getSquareCoordinatesRequired();

        for (String sC: squareCoordinatesRequired)
        {
            String [] squareCoordinates=sC.split(",");
            int s_x=Integer.valueOf(squareCoordinates[0]);
            int s_y=Integer.valueOf(squareCoordinates[1]);

            String fromCoordinatesToName= section.createSquareNameFromCoordinates(
                    s_x,
                    s_y
            );
            cSquare singleSquareRequired= section.getSquareFromName(fromCoordinatesToName);

            squares.add(singleSquareRequired);
        }

        r.action(squares);
        radarCount-=1;
        cMessageOutput.showMessage("Radar available " + radarCount);

        weaponSelected=null;

    }
    public void weaponHandler(cSquare square){
        if(weaponSelected==null){ cMessageOutput.showMessage("Please select a weapon before"); return;}

        boolean isShot= weaponSelected instanceof cShot
                && square.getIsAvailableForAction();
        if(isShot) { shotFunction(square); }

        boolean isRadarSelected= weaponSelected instanceof cRadar;
        if(isRadarSelected) { placeRadarFunction(square);}
    }
    private boolean isRadarAvailable()
    {
        return radarCount>0;
    }
    public void onWeaponSelected(cWeapon weapon) {
        //if is available
        weaponSelected =weapon;
        cMessageOutput.showMessage("You select " + weapon.getName());

    }
    public void renderAnimations(SpriteBatch batch, float deltaTime) {
        ListIterator<cExplosion> explosionListIterator = explosionList.listIterator();

        //rendering explosions
        while (explosionListIterator.hasNext()) {
            cExplosion explosion = explosionListIterator.next();
            explosion.update(deltaTime);
            explosion.draw(batch);
        }
    }
    public void createWeaponList(float startWidth, float startHeight){
        //add weapons
        TextButton weaponWritten=new TextButton("WEAPONs",
                cBattleShip.gameSkin);
        float [] squareDimensions= section.getSquareDimensions();
        float width=squareDimensions[0];
        float heigth=squareDimensions[1];
        int rowsNum= section.getRowsNum();
        int columsNum= section.getColumsNum();
        float weaponWritten_x=(startWidth / 2 - (width*rowsNum)/2);
        float weaponWritten_y=(startHeight / 2 - (heigth*columsNum)/2)-50;
        weaponWritten.setBounds(
                weaponWritten_x,
                weaponWritten_y,
                0,
                0);
        section.getStage().addActor(weaponWritten);

        //designin
        for(int i=1; i<weapons.size()+1;i++)
        {
            final cWeapon object=weapons.get(i-1);
            final Image icon=object.getBody();

            icon.setPosition(weaponWritten_x+80*i,weaponWritten_y-20);

            icon.addListener(new InputListener(){
                public void touchUp(
                        InputEvent event,
                        float offsetX,
                        float offsetY,
                        int pointer,
                        int button)
                {
                    onWeaponSelected(object);
                }

                public boolean touchDown(
                        InputEvent event,
                        float offsetX,
                        float offsetY,
                        int pointer,
                        int button)
                {
                    return true;
                }
            });
            section.getStage().addActor(icon);
        }
    }
}
