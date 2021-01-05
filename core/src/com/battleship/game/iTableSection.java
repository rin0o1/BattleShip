package com.battleship.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface iTableSection {

    void initSection(Stage stage);

    void renderSection(SpriteBatch batch, float deltaTime);

    void buildTable( );

    void draw(Batch batch, float parentAlpha);

    void displayShips();

    void showMessage( String message);

    //on player click

}
