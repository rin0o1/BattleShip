package com.battleship.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface iTableSection {

    void initSection();

    void renderSection(SpriteBatch batch, float deltaTime);

    void buildTable( );

    void draw(Batch batch, float parentAlpha);

    void displayShips();

    void showMessage( String message);

    //on player click

}
