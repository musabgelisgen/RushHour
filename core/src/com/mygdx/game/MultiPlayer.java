package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MultiPlayer extends BaseScreen {
	TextButton returnMenu,com_vs_hum,hum_vs_hum;
	public MultiPlayer(BaseGame game) {
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
				game.setScreen(new MainMenu(game));
			}
			});
		
		com_vs_hum=new TextButton("Computer vs Human", game.skin, "buttonStyle");
		com_vs_hum.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new ComputerVsHuman(game));
			}
			});
		
		hum_vs_hum=new TextButton("Human vs Human", game.skin, "buttonStyle");
		hum_vs_hum.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
		
		com_vs_hum.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
		hum_vs_hum.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new HumanVsHuman(game));
			}
			});
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);
		uiTable.row();
		uiTable.add(com_vs_hum).padLeft(300).width(150).height(50).padBottom(20);
		uiTable.row();
		uiTable.add(hum_vs_hum).padLeft(300).width(150).height(50);
		

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		returnMenu.setPosition(0, 0);

	}

}
