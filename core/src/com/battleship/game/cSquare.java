package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class cSquare extends cTableObject implements iSquare {

    private Texture textureOnHover;

    private cTableObject object;

    public cSquare(
                float width,
                float height,
                float [] coordinates,
                String name
                )
    {
        this.object=null;
        this.width=width;
        this.height=height;
        this.name=name;
        this.coordinates=coordinates;

        setTouchable(Touchable.enabled);

        textureOnHover = new Texture(Gdx.files.internal("squareOver.PNG"));
        texture = new Texture(Gdx.files.internal("oceanNew.gif"));

        image =  new Image(textureOnHover);
        image.setPosition(coordinates[0], coordinates[1]);
        image.setSize(width, height);
        image.setTouchable(Touchable.enabled);

    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.draw(batch, parentAlpha);
    }

    @Override
    public  void act(float delta){
        super.act(delta);
    }

    @Override
    public void setState() {

    }

    @Override
    public void placeObject(cTableObject object) {

    }

    @Override
    public void removeCurrentObject() {

    }

    @Override
    public cTableObject isBusy() {
        return null;
    }


    public  cTableObject getIsBusy(){
        return this.object;
    }



}
