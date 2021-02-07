package com.battleship.game;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class cMessageOutput {

    private static TextButton messageBoxText;
    private static String baseMessage;

    public  cMessageOutput(cTableSection section , float startWidth, float startHeight)
    {
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

    public TextButton getMessageBoxText(){
        return messageBoxText;
    }

    public  static void showMessage(String message){
        messageBoxText.setText(baseMessage+ message );
    }


}
