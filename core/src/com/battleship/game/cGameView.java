package com.battleship.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;


public class cGameView implements Screen {

    private Stage stage;

    private cTableSection tableSection;
    private cRightSection rightSection;
    private cLeftSection leftSection;

    private SpriteBatch batch;

    public cGameView(Game game) {

        batch= new SpriteBatch();

        tableSection= new cTableSection(this);

        stage = new Stage( new ScreenViewport());
        stage.addActor(tableSection);

        leftSection= new cLeftSection(stage,game);

        rightSection= new cRightSection(stage,
                (50*10)+leftSection.getWidth()+110);

        tableSection.initSection(stage);

        stage.addActor(leftSection);
        float tableSectionWidth=tableSection.getWidth();
        float positionX= tableSection.getX();

        stage.addActor(rightSection);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta)
    {
        batch.begin();

        Gdx.gl.glClearColor(0,240, 6,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        tableSection.renderSection(batch,delta);
        stage.draw();
        stage.act();
        batch.end();
    }

    public void updateScore(double score, boolean isToIncrement)
    {
        leftSection.updateScore(score, isToIncrement);
    }

    public void updateShipList(cShip ship, boolean isSank) { rightSection.addShips(ship, isSank); }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() {
        stage.dispose();
    }
}

