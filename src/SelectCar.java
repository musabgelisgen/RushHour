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
		newcar1=new TextButton("", game.skin, "selectbuttonStyle1");
		newcar2=new TextButton("", game.skin, "selectbuttonStyle2");
		newcar3=new TextButton("", game.skin, "selectbuttonStyle3");
		newcar4=new TextButton("", game.skin, "selectbuttonStyle4");
		newcar5=new TextButton("", game.skin, "selectbuttonStyle5");
		newcar6=new TextButton("", game.skin, "selectbuttonStyle6");
		newcar7=new TextButton("", game.skin, "selectbuttonStyle7");
		newcar8=new TextButton("", game.skin, "selectbuttonStyle8");
		
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
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(newcar1);
		uiTable.add(newcar2);
		uiTable.add(newcar3);
		uiTable.add(newcar4);
		
		uiTable.row();
		
		uiTable.add(newcar5);
		uiTable.add(newcar6);
		uiTable.add(newcar7);
		uiTable.add(newcar8);
		uiTable.add(returnMenu);
		

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		newcar1.setWidth(100);
		newcar1.setHeight(100);
		
		newcar2.setWidth(100);
		newcar2.setHeight(100);
		
		newcar3.setWidth(100);
		newcar3.setHeight(100);
	
		newcar4.setWidth(100);
		newcar4.setHeight(100);
		
		newcar5.setWidth(100);
		newcar5.setHeight(100);
		
		newcar6.setWidth(100);
		newcar6.setHeight(100);
		
		newcar7.setWidth(100);
		newcar7.setHeight(100);
		
		newcar8.setWidth(100);
		newcar8.setHeight(100);
		
		returnMenu.setPosition(0, 0);

	}
	public void createStyles(){
		TextButtonStyle selectbuttonStyle1=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle2=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle3=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle4=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle5=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle6=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle7=new TextButtonStyle();
		TextButtonStyle selectbuttonStyle8=new TextButtonStyle();
		
		BitmapFont font=new BitmapFont();
		
		selectbuttonStyle1.font=font;
		selectbuttonStyle2.font=font;
		selectbuttonStyle3.font=font;
		selectbuttonStyle4.font=font;
		selectbuttonStyle5.font=font;
		selectbuttonStyle6.font=font;
		selectbuttonStyle7.font=font;
		selectbuttonStyle8.font=font;
		
		Texture im1=new Texture("newcar1.jpg");
		Texture im2=new Texture("newcar2.jpg");
		Texture im3=new Texture("newcar3.jpg");
		Texture im4=new Texture("newcar4.jpg");
		Texture im5=new Texture("newcar5.png");
		Texture im6=new Texture("newcar6.jpg");
		Texture im7=new Texture("newcar7.jpg");
		Texture im8=new Texture("newcar8.jpg");
		
		game.skin.add("im1", new NinePatch(im1,5,5,5,5));
		game.skin.add("im2", new NinePatch(im2,5,5,5,5));
		game.skin.add("im3", new NinePatch(im3,5,5,5,5));
		game.skin.add("im4", new NinePatch(im4,5,5,5,5));
		game.skin.add("im5", new NinePatch(im5,5,5,5,5));
		game.skin.add("im6", new NinePatch(im6,5,5,5,5));
		game.skin.add("im7", new NinePatch(im7,5,5,5,5));
		game.skin.add("im8", new NinePatch(im8,5,5,5,5));
	
		selectbuttonStyle1.up=game.skin.getDrawable("im1");
		selectbuttonStyle2.up=game.skin.getDrawable("im2");
		selectbuttonStyle3.up=game.skin.getDrawable("im3");
		selectbuttonStyle4.up=game.skin.getDrawable("im4");
		selectbuttonStyle5.up=game.skin.getDrawable("im5");
		selectbuttonStyle6.up=game.skin.getDrawable("im6");
		selectbuttonStyle7.up=game.skin.getDrawable("im7");
		selectbuttonStyle8.up=game.skin.getDrawable("im8");
		
		game.skin.add("selectbuttonStyle1", selectbuttonStyle1);
		game.skin.add("selectbuttonStyle2", selectbuttonStyle2);
		game.skin.add("selectbuttonStyle3", selectbuttonStyle3);
		game.skin.add("selectbuttonStyle4", selectbuttonStyle4);
		game.skin.add("selectbuttonStyle5", selectbuttonStyle5);
		game.skin.add("selectbuttonStyle6", selectbuttonStyle6);
		game.skin.add("selectbuttonStyle7", selectbuttonStyle7);
		game.skin.add("selectbuttonStyle8", selectbuttonStyle8);
	}

}
