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
	static Texture targetTexture=new Texture("ambulance.png");
	static int targetNumber=1;
	public SelectCar(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		createStyles();
		carButtons=new TextButton[4];
		newcar1=new TextButton(targetNumber==1?"CHOOSEN":"", game.skin,"buttonCarStyle1");
		newcar2=new TextButton(targetNumber==2?"CHOOSEN":"", game.skin,"buttonCarStyle2");
		newcar3=new TextButton(targetNumber==3?"CHOOSEN":"", game.skin,"buttonCarStyle3");
		newcar4=new TextButton(targetNumber==4?"CHOOSEN":"", game.skin,"buttonCarStyle4");
		newcar5=new TextButton(targetNumber==5?"CHOOSEN":"", game.skin,"buttonCarStyle5");
		newcar6=new TextButton(targetNumber==6?"CHOOSEN":"", game.skin,"buttonCarStyle6");
		newcar7=new TextButton(targetNumber==7?"CHOOSEN":"", game.skin,"buttonCarStyle7");
		newcar8=new TextButton(targetNumber==8?"CHOOSEN":"", game.skin,"buttonCarStyle8");
		carButtons[0]=newcar1;
		carButtons[1]=newcar2;
		carButtons[2]=newcar3;
		carButtons[3]=newcar8;
		/*carButtons[4]=newcar5;
		carButtons[5]=newcar6;
		carButtons[6]=newcar7;
		carButtons[7]=newcar8;*/
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
				newcar1.setText("CHOOSEN");
				targetTexture=game.skin.get("car1Texture",Texture.class);
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
				newcar2.setText("CHOOSEN");
				targetTexture=game.skin.get("car2Texture",Texture.class);
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
				newcar3.setText("CHOOSEN");
				targetTexture=game.skin.get("car3Texture",Texture.class);
				targetNumber=3;
			}
			});
		/*
		newcar4.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar4.setText("CHOOSEN");
			}
			});
		
		newcar5.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar5.setText("CHOOSEN");
			}
			});
		
		newcar6.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar6.setText("CHOOSEN");
			}
			});
		
		newcar7.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				game.skin.get("buton",Sound.class).play();
				//First clean
				for(int i=0;i<carButtons.length;i++)
					carButtons[i].setText("");
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				newcar7.setText("CHOOSEN");
			}
			});*/
		
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
				newcar8.setText("CHOOSEN");
				targetTexture=game.skin.get("car8Texture",Texture.class);
				targetNumber=8;
			}
			});
		
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiStage.addActor(newcar1);
		uiStage.addActor(newcar2);
		uiStage.addActor(newcar3);/*
		uiStage.addActor(newcar4);
		uiStage.addActor(newcar5);
		uiStage.addActor(newcar6);
		uiStage.addActor(newcar7);*/
		uiStage.addActor(newcar8);
		
		uiTable.add(returnMenu);
		

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<carButtons.length;i++){
			int cbx=90;
			carButtons[i].setWidth(cbx);
			carButtons[i].setHeight(cbx);
			carButtons[i].setPosition((cbx)*2*(i%(carButtons.length/2)), 3*VIEW_HEIGHT/4-(cbx+10)*(i/(carButtons.length/2)));
		}
		returnMenu.setPosition(30, 0);
		returnMenu.setWidth(180);

	}
	public void createStyles(){
		
	}

}
