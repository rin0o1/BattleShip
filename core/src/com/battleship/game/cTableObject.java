package com.battleship.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import org.graalvm.compiler.graph.Node;

public class cTableObject extends Actor {

    protected float[] position;

    protected float width;
    protected float height;
    protected Texture texture;
    protected Image body;
    protected Texture lastTexture;

    public cTableObject()
    {
        body= new Image();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        body.draw(batch, parentAlpha);
    }

    public Image getBody(){
        return body;
    }

    public float[] getPosition(){
        return position;
    }

    public void setTextureFromTexture(Texture texture)
    {
        lastTexture = (this.texture!=null) ? this.texture:texture;

        this.texture=texture;
        body.setDrawable(new TextureRegionDrawable(texture));
    }

    public void setTextureFromTexture(Texture texture, boolean isClicked)
    {
        body.setDrawable(new TextureRegionDrawable(texture));
    }

    public float getRotationDegreeFromOrientation(
            objectOrientation orientation
    ){
        switch (orientation){
            case VERTICAL:return 0;
            case HORIZONTAL: return 270;
        }
        return 0;
    }


}
