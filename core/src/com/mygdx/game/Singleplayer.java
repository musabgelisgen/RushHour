package com.mygdx.game;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import data.SerializationUtil;
import data.StarScore;

public class Singleplayer extends BaseScreen {
	TextButton level1,level2,level3,level4,level5,level6,level7,level8,returnMenu;
	Label lev1,lev2,lev3,lev4,lev5,lev6,lev7,lev8;
	Sound buton,winSound;
	boolean[] enables;
	static int passed=7;
	TextButton[] levels;
	StarScore scores = null;

	public Singleplayer(BaseGame game) {
		super(game);
		
	}

	@Override
	public void create() {
		//create the objects needed
		
		winSound=Gdx.audio.newSound(Gdx.files.internal("win.ogg"));
		game.skin.add("winSound", winSound);
		enables=new boolean[8];
		for(int i=0;i<enables.length;i++)
			if(i>passed)
				enables[i]=false;
			else
				enables[i]=true;
		levels=new TextButton[8];		
		buton=Gdx.audio.newSound(Gdx.files.internal("button.ogg"));
		game.skin.add("buton", buton,Sound.class);
		TextButtonStyle aa=game.skin.get("buttonStyle1", TextButtonStyle.class);
		lev1=new Label("Level1", game.skin,"uiLabelStyle");
		lev2=new Label("Level2", game.skin,"uiLabelStyle");
		lev3=new Label("Level3", game.skin,"uiLabelStyle");
		lev4=new Label("Level4", game.skin,"uiLabelStyle");
		lev5=new Label("Level5", game.skin,"uiLabelStyle");
		lev6=new Label("Level6", game.skin,"uiLabelStyle");
		lev7=new Label("Level7", game.skin,"uiLabelStyle");
		lev8=new Label("Level8", game.skin,"uiLabelStyle");
		
		
		level1=new TextButton("", game.skin, "buttonStyle1");
		levels[0]=level1;
		level2=new TextButton("", game.skin, "buttonStyle1");
		levels[1]=level2;
		level3=new TextButton("", game.skin, "buttonStyle1");
		levels[2]=level3;
		level4=new TextButton("", game.skin, "buttonStyle1");
		levels[3]=level4;
		level5=new TextButton("", game.skin, "buttonStyle1");
		levels[4]=level5;
		level6=new TextButton("", game.skin, "buttonStyle1");
		levels[5]=level6;
		level7=new TextButton("", game.skin, "buttonStyle1");
		levels[6]=level7;
		level8=new TextButton("", game.skin, "buttonStyle1");
		levels[7]=level8;
		
		Texture threeStar = new Texture("three_stars.png");
		Texture twoStar = new Texture("two_stars.png");
		Texture oneStar = new Texture("one_stars.png");
		Texture noStar = new Texture("no_stars.png");
		
		String fileFolder = ""; 
		String scoreDir = ""; //location for StarScore object in file system
		String os = System.getProperty("os.name").toUpperCase();
		
		if (os.contains("WIN")) {
			fileFolder = System.getenv("APPDATA") + "\\" + "RushHour";
			scoreDir =  fileFolder + "\\scores.ser";
		}

		else if (os.contains("MAC")) {
			fileFolder = System.getProperty("user.home") + "/Library/Application Support" + "/RushHour";
			scoreDir = fileFolder + "/scores.ser";
		}
		
		File directory = new File(fileFolder);
		if (directory.exists() == false) {
			directory.mkdir();
			
			scores = new StarScore();
			for(int i = 0; i < 8; i++) {
				scores.addScore(0);
			}
			try { //serialization here
				SerializationUtil.serialize(scores, scoreDir);
			} catch (IOException e1) {
				System.out.println("Score Serialization Failed");
			}
		}
		else {
			try { //deserialization here
				scores = (StarScore) SerializationUtil.deserialize(scoreDir);

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}		
		
		for(int i=0;i<8;i++){
			levels[i].setTouchable(enables[i]?Touchable.enabled:Touchable.disabled);
			levels[i].setStyle(i>passed?game.skin.get("buttonStyle2", TextButtonStyle.class):game.skin.get("buttonStyle1", TextButtonStyle.class));
		}
		
		
		returnMenu=new TextButton("Main Menu", game.skin, "buttonStyle3");
		
		//create the eventlisteners
		
		addListeners();
		

		//replace the objects on the screen
		Texture backgroundSP=new Texture("background_blurred.jpg");
		game.skin.add("background_blurred", backgroundSP);
		uiTable.setBackground(game.skin.getDrawable("background_blurred"));
		

		for(int i = 0; i < 4; i++) {
			if(scores.getScores().get(i) == 0)
				uiTable.add(new Image(noStar)).pad(10);
			else if(scores.getScores().get(i) == 1)
				uiTable.add(new Image(oneStar)).pad(10);
			else if(scores.getScores().get(i) == 2)
				uiTable.add(new Image(twoStar)).pad(10);
			else
				uiTable.add(new Image(threeStar)).pad(10);
		}

		uiTable.row();
		
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
		
		for(int i = 4; i < 8; i++) {
			if(scores.getScores().get(i) == 0)
				uiTable.add(new Image(noStar)).pad(10);
			else if(scores.getScores().get(i) == 1)
				uiTable.add(new Image(oneStar)).pad(10);
			else if(scores.getScores().get(i) == 2)
				uiTable.add(new Image(twoStar)).pad(10);
			else
				uiTable.add(new Image(threeStar)).pad(10);
		}
		
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
		returnMenu.setPosition(0, 0);
		
	}
	public void addListeners(){
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
		
		level2.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level2(game));
			}
			});
		
		level3.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level3(game));
			}
			});
		
		level4.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level4(game));
			}
			});
		
		level5.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level5(game));
			}
			});
		
		level6.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level6(game));
			}
			});
		
		level7.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level7(game));
			}
			});
		
		level8.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Level8(game));
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
	}

}