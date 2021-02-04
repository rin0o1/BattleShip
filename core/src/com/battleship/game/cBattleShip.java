package com.battleship.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class cBattleShip extends Game {
	static public Skin gameSkin;
	public void create ()
	{
		String path= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Desktop\\Tests\\skin\\comic-ui.json";
		gameSkin = new Skin(Gdx.files.internal(path));

		Gdx.graphics.setWindowedMode(1000,800);
		this.setScreen(new cStartView(this));
	//this.setScreen(new cGameView(this));
	}
	public void render () {
		super.render();
	}

	public void dispose () {
	}
}

