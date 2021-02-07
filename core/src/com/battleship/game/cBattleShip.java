package com.battleship.game;

import io.socket.client.IO;
import io.socket.client.Socket;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class cBattleShip extends Game {
	static public Skin gameSkin;

	private Socket socket;
	public void create ()
	{
		String path= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Desktop\\Tests\\skin\\comic-ui.json";
		gameSkin = new Skin(Gdx.files.internal(path));
		connectSocket();
		Gdx.graphics.setWindowedMode(1000,800);
		this.setScreen(new cStartView(this));
	//this.setScreen(new cGameView(this));
	}

	private void connectSocket(){
		try {
			socket= IO.socket("http://localhost:8081");
			socket.connect();
			System.out.println("System connected");
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public void render () {
		super.render();
	}

	public void dispose () {
	}
}

//radar_01

//WeaponBattleshipStandardGun.png

