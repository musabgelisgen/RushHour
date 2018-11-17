package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Singleplayer extends BaseScreen {
	TextButton level1,level2,level3,level4,level5,level6,level7,level8,returnMenu;
	Label lev1,lev2,lev3,lev4,lev5,lev6,lev7,lev8;
	Sound buton;
	public Singleplayer(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		//create the objects needed
		buton=Gdx.audio.newSound(Gdx.files.internal("button.ogg"));
		lev1=new Label("Level1", game.skin,"uiLabelStyle");
		lev2=new Label("Level2", game.skin,"uiLabelStyle");
		lev3=new Label("Level3", game.skin,"uiLabelStyle");
		lev4=new Label("Level4", game.skin,"uiLabelStyle");
		lev5=new Label("Level5", game.skin,"uiLabelStyle");
		lev6=new Label("Level6", game.skin,"uiLabelStyle");
		lev7=new Label("Level7", game.skin,"uiLabelStyle");
		lev8=new Label("Level8", game.skin,"uiLabelStyle");
		TextButtonStyle buttonStyle1=game.skin.get("font",TextButtonStyle.class);
		TextButtonStyle buttonStyle2=new TextButtonStyle();
		TextButtonStyle buttonStyle3=new TextButtonStyle();
		BitmapFont font=new BitmapFont();
		buttonStyle2.font=font;
		buttonStyle3.font=font;
		Texture upButton1=new Texture("level1.jpeg");
		Texture overButton1=new Texture("level1over.jpeg");
		Texture locked=new Texture("lockedlevel1.jpeg");
		Texture returnM=new Texture("arrow.png");
		Texture overarrow=new Texture("overarrow.png");
		game.skin.add("overarrow", new NinePatch(overarrow,5,5,5,5));
		game.skin.add("returnM", new NinePatch(returnM,5,5,5,5));
		game.skin.add("upButton1", new NinePatch(upButton1,5,5,5,5));
		game.skin.add("overButton1", new NinePatch(overButton1,5,5,5,5));
		game.skin.add("locked", new NinePatch(locked,5,5,5,5));
		buttonStyle1.up=game.skin.getDrawable("upButton1");
		buttonStyle1.over=game.skin.getDrawable("overButton1");
		buttonStyle1.downFontColor=Color.PINK;
		buttonStyle2.up=game.skin.getDrawable("locked");
		buttonStyle2.over=game.skin.getDrawable("locked");
		buttonStyle2.downFontColor=Color.PINK;
		buttonStyle3.up=game.skin.getDrawable("returnM");
		buttonStyle3.over=game.skin.getDrawable("overarrow");
		
		game.skin.add("buttonStyle1", buttonStyle1);
		game.skin.add("buttonStyle2", buttonStyle2);
		game.skin.add("buttonStyle3", buttonStyle3);
		
		level1=new TextButton("", game.skin, "buttonStyle1");
		level2=new TextButton("", game.skin, "buttonStyle2");
		level3=new TextButton("", game.skin, "buttonStyle2");
		level4=new TextButton("", game.skin, "buttonStyle2");
		level5=new TextButton("", game.skin, "buttonStyle2");
		level6=new TextButton("", game.skin, "buttonStyle2");
		level7=new TextButton("", game.skin, "buttonStyle2");
		level8=new TextButton("", game.skin, "buttonStyle2");
		returnMenu=new TextButton("Main Menu", game.skin, "buttonStyle3");
		
		//create the eventlisteners
		level1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level1(game));
			}
			});
		
		returnMenu.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new MainMenu(game));
			}
			});
		
		
		//replace the objects on the screen
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(level1).pad(10);
		uiTable.add(level2).pad(10);
		uiTable.add(level3).pad(10);
		uiTable.add(level4).pad(10);
		
		uiTable.row();
		
		uiTable.add(lev1);
		uiTable.add(lev2);
		uiTable.add(lev3);
		uiTable.add(lev4);
		
		uiTable.row();
		
		uiTable.add(level5).pad(10);
		uiTable.add(level6).pad(10);
		uiTable.add(level7).pad(10);
		uiTable.add(level8).pad(10);
		
		uiTable.row();
		
		uiTable.add(lev5);
		uiTable.add(lev6);
		uiTable.add(lev7);
		uiTable.add(lev8);
		
		uiTable.row();
		
		uiTable.add(returnMenu);
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

}
