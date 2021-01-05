package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class cSquare extends cTableObject implements iSquare {

    private Texture textureOnHover;
    private cTableObject object;

    private Stage stage;

    public cSquare(
                float width,
                float height,
                float [] coordinates,
                String name,
                Stage stage
                )
    {
        this.object=null;
        this.width=width;
        this.height=height;
        this.stage=stage;

        this.setName(name);
        this.coordinates=coordinates;

        textureOnHover = new Texture(Gdx.files.internal("squareOver.png"));
        texture = new Texture(Gdx.files.internal("squareTexture.gif"));

        body =  new Image(texture);

        body.setPosition(coordinates[0], coordinates[1]);
        body.setSize(width, height);
        body.setTouchable(Touchable.enabled);
        stage.addActor(body);
    }

    public void squareTouchUp(){
        body.setDrawable(new TextureRegionDrawable(texture));
    }

    public void squareTouchDown(){
        body.setDrawable(new TextureRegionDrawable(textureOnHover));
    }

    public float[] getCoordinates(){return  coordinates;}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        body.draw(batch, parentAlpha);
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
        this.object=object;
    }

    @Override
    public void removeCurrentObject() {

    }

    @Override
    public cTableObject isBusyAndObject() {
        return object;
    }

    @Override
    public boolean isBusy() {
        return object!=null;
    }



}
