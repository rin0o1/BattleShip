package com.battleship.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class cBattleShip extends Game {
	static public Skin gameSkin;
	public void create () {
		String path= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Desktop\\Tests\\skin\\comic-ui.json";
		gameSkin = new Skin(Gdx.files.internal(path));
	//	this.setScreen(new cStartView(this));
		this.setScreen(new cGameView(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
	}
}

/*public class Test extends ApplicationAdapter {
	private Stage stage;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());

		Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));

		Image image1 = new Image(texture);
		image1.setPosition(Gdx.graphics.getWidth()/3-image1.getWidth()/2,Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
		stage.addActor(image1);

		Image image2 = new Image(texture);
		image2.setPosition(Gdx.graphics.getWidth()*2/3-image2.getWidth()/2,Gdx.graphics.getHeight()*2/3-image2.getHeight()/2);
		image2.setOrigin(image2.getWidth()/2,image2.getHeight()/2);
		image2.rotateBy(45);
		stage.addActor(image2);

		Image image3 = new Image(texture);
		image3.setSize(texture.getWidth()/2,texture.getHeight()/2);
		image3.setPosition(Gdx.graphics.getWidth()/3-image3.getWidth()/2,Gdx.graphics.getHeight()/3-image3.getHeight());
		stage.addActor(image3);

		texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		TextureRegion textureRegion = new TextureRegion(texture);
		textureRegion.setRegion(0,0,texture.getWidth()*8,texture.getHeight()*4);
		Image image4 = new Image(textureRegion);
		image4.setSize(200,100);
		image4.setPosition(Gdx.graphics.getWidth()*2/3-image4.getWidth()/2,Gdx.graphics.getHeight()/3-image4.getHeight());
		stage.addActor(image4);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();

	}*/
