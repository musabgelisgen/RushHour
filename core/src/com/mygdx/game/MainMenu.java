package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenu extends BaseScreen {
	TextButton singleplayer,multiplayer;
	TextButton select_theme,select_car,credits;
	Sound buton;
	 Music instrumenatal;
	BitmapFont newfont;
	static boolean start=true;
	static boolean carSelected=false;
	public MainMenu(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		instrumenatal=Gdx.audio.newMusic(Gdx.files.internal("main.ogg"));
			instrumenatal.play();
		
		game.skin.add("music", instrumenatal);
		BitmapFont font=new BitmapFont();
		game.skin.add("uiFont", font);
		LabelStyle style=new LabelStyle(font, Color.YELLOW);
		game.skin.add("uiLabelStyle", style);
		TextButtonStyle buttonStyle=new TextButtonStyle();
		buttonStyle.font=font;
		buttonStyle.fontColor=Color.RED;
		game.skin.add("font", buttonStyle);
		Texture upButton=new Texture("buttonUp.png");
		game.skin.add("upButton", new NinePatch(upButton,5,5,5,5));
		buttonStyle.up=game.skin.getDrawable("upButton");
		buttonStyle.overFontColor=Color.BLUE;
		buttonStyle.downFontColor=Color.PINK;
		game.skin.add("buttonStyle", buttonStyle);
		Texture background=new Texture("background.jpg");
		game.skin.add("background", background);
		
		Texture backgroundSP=new Texture("background_blurred.jpg");
		game.skin.add("background_blurred", backgroundSP);
		
		Texture selectcar=new Texture("selectCar.PNG");
		game.skin.add("selectcar", selectcar);
		
		Texture selecttheme=new Texture("selecttheme.jpeg");
		game.skin.add("selecttheme", selecttheme);
		
		Texture credit=new Texture("credits.jpeg");
		game.skin.add("credits", credit);
		
		buton=Gdx.audio.newSound(Gdx.files.internal("button.ogg"));
		game.skin.add("buton", buton);
		select_theme=new TextButton("SELECT THEME", game.skin, "buttonStyle");
		
		credits=new TextButton("CREDITS", game.skin, "buttonStyle");
		credits.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0f,0f,0,8f),0.5f))));
		
		
		select_theme.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0f,0f,0,8f),0.5f))));
		singleplayer=new TextButton("SINGLEPLAYER", game.skin,"buttonStyle");
		singleplayer.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0f,0f,0,8f),0.5f))));
		
	    multiplayer=new TextButton("MULTIPLAYER", game.skin,"buttonStyle");
	    multiplayer.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
	    
	    select_car=new TextButton("SELECT CAR", game.skin,"buttonStyle");
	    select_car.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add(singleplayer).padLeft(450).width(150).height(50);
		uiTable.row();
		uiTable.row();
		
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add(multiplayer).right().width(150).height(50).padTop(10);
		uiTable.row();
		uiTable.row();
		
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add(select_theme).right().width(150).height(50).padTop(10);
		uiTable.row();
		uiTable.row();
		
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add(select_car).right().width(150).height(50).padTop(10);
		uiTable.row();
		uiTable.row();
		
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add();
		uiTable.add(credits).right().width(150).height(50).padTop(10);
		uiTable.row();
		addListeners();
		addStyles();
		

	}
	
	public void addListeners(){
		singleplayer.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
		
			
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				buton.play(0.2f);
				instrumenatal.stop();
				game.setScreen(new Singleplayer(game));
			}
			});
		
		credits.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
		
			
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				buton.play(0.2f);
				instrumenatal.stop();
				game.setScreen(new Credits(game));
			}
			});
		
		
		select_theme.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				return true;
			}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					instrumenatal.stop();
					buton.play(0.2f);
					game.setScreen(new SelectTheme(game));
				
					
				}
				});

		select_car.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				instrumenatal.stop();
				buton.play(0.2f);
				game.setScreen(new SelectCar(game));
				carSelected=true;
			}
			});
		
		multiplayer.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				return true;
			}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					instrumenatal.stop();
					buton.play(0.2f);
					game.setScreen(new MultiPlayer(game));
					
				}
				});

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

}
