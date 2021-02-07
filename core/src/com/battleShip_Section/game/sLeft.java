package com.battleShip_Section.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.batteShip_View.game.vFinal;
import com.cBattleShip;

public class sLeft extends Actor  {

    private TextButton score;
    private TextButton quit;
    private TextButton scoreValue;
    private int scoreValueDouble;
    private Stage stage;
    private Game game;

    public sLeft(Stage stage, Game game)
    {

        this.setWidth(200);
        this.stage=stage;
        this.game=game;
        float screenHeight=Gdx.graphics.getHeight();
        float screenWidth= Gdx.graphics.getWidth();

        scoreValueDouble=0;
        quit = new TextButton("QUIT",
                cBattleShip.gameSkin);

        quit.setBounds(10,100,100,70);

        score = new TextButton("SCORE",
                cBattleShip.gameSkin);


        score.setBounds(50,screenHeight-50,0,0);

        quit.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button)
            {

                finalView();
            }
            @Override
            public boolean touchDown (InputEvent event,
                                      float x,
                                      float y,
                                      int pointer,
                                      int button)
            {
                return true;
            }
        });

        scoreValue = new TextButton(String.valueOf(scoreValueDouble),
                cBattleShip.gameSkin);

        scoreValue.setBounds(50,(screenHeight-50)-80,0,0);


        this.stage.addActor(quit);
        this.stage.addActor(score);
        this.stage.addActor(scoreValue);


    }
    public void finalView()
    {
        vFinal finalView=new vFinal(game, scoreValueDouble );
        game.setScreen(finalView);
    }

    public void updateScore(double value, boolean isToIncrement)
    {
        if(!isToIncrement && scoreValueDouble>0)
        {
            scoreValueDouble-=value;
        }

        scoreValueDouble+=value;

        scoreValue.setText(String.valueOf(scoreValueDouble));
    }


}
