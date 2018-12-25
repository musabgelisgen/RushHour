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
	TextButton[] carButtons;
	static Texture targetTexture=new Texture("pickup_horiz.png");
	static Texture targetTexture2= new Texture("Space_escape_1.png");
	static int targetNumber=1;
	public SelectCar(BaseGame game) {
		super(game);
	}

	@Override
	public void create() {
		createStyles();
		carButtons=new TextButton[4];
		newcar1=new TextButton(targetNumber==1?"CHOSEN":"", game.skin,"buttonCarStyle1");
		newcar2=new TextButton(targetNumber==2?"CHOSEN":"", game.skin,"buttonCarStyle2");
		newcar3=new TextButton(targetNumber==3?"CHOSEN":"", game.skin,"buttonCarStyle3");
		newcar4=new TextButton(targetNumber==4?"CHOSEN":"", game.skin,"buttonCarStyle4");
		newcar5=new TextButton(targetNumber==5?"CHOSEN":"", game.skin,"buttonCarStyle5");
		newcar6=new TextButton(targetNumber==6?"CHOSEN":"", game.skin,"buttonCarStyle6");
		newcar7=new TextButton(targetNumber==7?"CHOSEN":"", game.skin,"buttonCarStyle7");
		newcar8=new TextButton(targetNumber==8?"CHOSEN":"", game.skin,"buttonCarStyle8");
		if(SelectTheme.targetNumber == 2) {
			newcar1=new TextButton(targetNumber==1?"CHOSEN":"", game.skin,"buttonCarStyle9");
			newcar2=new TextButton(targetNumber==2?"CHOSEN":"", game.skin,"buttonCarStyle10");
			newcar3=new TextButton(targetNumber==3?"CHOSEN":"", game.skin,"buttonCarStyle11");
			newcar8=new TextButton(targetNumber==8?"CHOSEN":"", game.skin,"buttonCarStyle12");
		}
		carButtons[0]=newcar1;
		carButtons[1]=newcar2;
		carButtons[2]=newcar3;
		carButtons[3]=newcar8;
		
		targetTexture=game.skin.get("car1Texture",Texture.class);
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
		newcar1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");

				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar1.setText("CHOSEN");
				targetTexture=game.skin.get("car1Texture",Texture.class);
//				if(SelectTheme.targetNumber == 2) 
				targetTexture2=game.skin.get("car9Texture",Texture.class);
				targetNumber=1;
			}
		});

		newcar2.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar2.setText("CHOSEN");
				targetTexture=game.skin.get("car2Texture",Texture.class);
//				if(SelectTheme.targetNumber == 2) 
				targetTexture2=game.skin.get("car10Texture",Texture.class);
				targetNumber=2;
			}
		});

		newcar3.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar3.setText("CHOSEN");
				targetTexture=game.skin.get("car3Texture",Texture.class);
//				if(SelectTheme.targetNumber == 2) 
				targetTexture2=game.skin.get("car11Texture",Texture.class);
				targetNumber=3;
			}
		});

		newcar8.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar8.setText("CHOSEN");
				targetTexture=game.skin.get("car8Texture",Texture.class);
//				if(SelectTheme.targetNumber == 2) 
				targetTexture2=game.skin.get("car12Texture",Texture.class);
				targetNumber=8;
			}
		});

		if(SelectTheme.targetNumber == 2)
			uiTable.setBackground(game.skin.getDrawable("space_backgroundSP"));
		else
			uiTable.setBackground(game.skin.getDrawable("car_backgroundSP"));
		uiStage.addActor(newcar1);
		uiStage.addActor(newcar2);
		uiStage.addActor(newcar3);
		uiStage.addActor(newcar8);

		uiTable.add(returnMenu);


	}

	@Override
	public void update(float dt) {
		for(int i=0;i<carButtons.length;i++){
			int cbx=70;
			carButtons[i].setWidth(2 * cbx);
			carButtons[i].setHeight(cbx);
			carButtons[i].setPosition((cbx)*3*(i%(carButtons.length/2))+VIEW_WIDTH/4 - 20, 3*VIEW_HEIGHT/4-(cbx+10)*2*(i/(carButtons.length/2)));
		}
		returnMenu.setPosition(30, 0);
		returnMenu.setWidth(180);

	}
	public void createStyles(){

	}

}
