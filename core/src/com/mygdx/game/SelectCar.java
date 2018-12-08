package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class SelectCar extends BaseScreen {
	TextButton returnMenu;
	TextButton newcar1,newcar2,newcar3,newcar4,newcar5,newcar6,newcar7,newcar8;
	public SelectCar(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		createStyles();
		
		
		returnMenu=new TextButton("Back", game.skin, "buttonStyle3");
		returnMenu.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new MainMenu(game));
			}
			});
		uiTable.setBackground(game.skin.getDrawable("selectcar"));
		uiTable.add(returnMenu);
		

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
		
		returnMenu.setPosition(30, 0);
		returnMenu.setWidth(180);

	}
	public void createStyles(){
		
	}

}
