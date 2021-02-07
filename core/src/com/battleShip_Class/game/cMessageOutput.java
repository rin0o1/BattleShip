package com.battleShip_Class.game;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.battleShip_Section.game.sTable;
import com.cBattleShip;


public class cMessageOutput {

    private static TextButton messageBoxText;
    private static String baseMessage;

    public  cMessageOutput(sTable section , float startWidth, float startHeight) {
        baseMessage="OUPUT: ";
        messageBoxText=new TextButton(baseMessage + " WELCOME!",
                cBattleShip.gameSkin);

        messageBoxText.setBounds(
                350,
                100,
                0,
                0);
        section.getStage().addActor(messageBoxText);
    }

    public  static void showMessage(String message){
        messageBoxText.setText(baseMessage+ message );
    }

}
