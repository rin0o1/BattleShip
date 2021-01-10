package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.w3c.dom.Text;

public class cLeftSection extends Actor  {

    private TextButton quitButton;
    private TextButton score;
    private TextButton scoreValue;

    private Stage stage;

    public  cLeftSection(Stage stage)
    {

        this.setWidth(200);
        this.stage=stage;

        float screenHeight=Gdx.graphics.getHeight();
        float screenWidth= Gdx.graphics.getWidth();


        score = new TextButton("QUIT",
                cBattleShip.gameSkin);

        score.setBounds(10,100,100,70);

        quitButton = new TextButton("SCORE",
                cBattleShip.gameSkin);

        quitButton.setBounds(50,screenHeight-50,0,0);

        scoreValue = new TextButton("0",
                cBattleShip.gameSkin);

        scoreValue.setBounds(50,(screenHeight-50)-80,0,0);


        this.stage.addActor(quitButton);
        this.stage.addActor(score);
        this.stage.addActor(scoreValue);

    }

    private void updateScore(float score){
        scoreValue.setText(String.valueOf(score));
    }

}
