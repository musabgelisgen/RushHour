package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ComputerVsHuman extends BaseScreen {
	TextButton returnMenu;
	public ComputerVsHuman(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		returnMenu=new TextButton("Back", game.skin, "buttonStyle3");
		returnMenu.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				MainMenu.start=false;
				game.skin.get("buton",Sound.class).play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new MultiPlayer(game));
			}
			});
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		returnMenu.setPosition(0, 0);
	}

}
