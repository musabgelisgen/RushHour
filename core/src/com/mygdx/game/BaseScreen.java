package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen implements Screen, InputProcessor {
	protected BaseGame game;
	protected static Stage mainStage,uiStage;
	public int VIEW_WIDTH=640;
	public int VIEW_HEIGHT=480;
	private boolean paused;
	protected Table uiTable;
	Sound buton;
	Music instrumental;
	@Override

	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addStyles(){
		buton=Gdx.audio.newSound(Gdx.files.internal("button.ogg"));
		instrumental=Gdx.audio.newMusic(Gdx.files.internal("main.ogg"));
		game.skin.add("buton", buton,Sound.class);
		game.skin.add("instrumental", buton,Music.class);
		TextButtonStyle buttonStyle1=new TextButtonStyle();
		TextButtonStyle buttonStyle2=new TextButtonStyle();
		TextButtonStyle buttonStyle3=new TextButtonStyle();

		BitmapFont font=new BitmapFont(Gdx.files.internal("newfont.fnt"));//normally is was BitmapFont font=new BitmapFont();

		buttonStyle1.font=font;
		buttonStyle2.font=font;
		buttonStyle3.font=font;
		Texture upButton1=new Texture("level1.png");
		Texture overButton1=new Texture("level1over.png");
		Texture locked=new Texture("lock.png");
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

		TextButtonStyle buttonCarStyle1=new TextButtonStyle();
		TextButtonStyle buttonCarStyle2=new TextButtonStyle();
		TextButtonStyle buttonCarStyle3=new TextButtonStyle();
		TextButtonStyle buttonCarStyle4=new TextButtonStyle();
		TextButtonStyle buttonCarStyle5=new TextButtonStyle();
		TextButtonStyle buttonCarStyle6=new TextButtonStyle();
		TextButtonStyle buttonCarStyle7=new TextButtonStyle();
		TextButtonStyle buttonCarStyle8=new TextButtonStyle();
		TextButtonStyle buttonCarStyle9=new TextButtonStyle();
		TextButtonStyle buttonCarStyle10=new TextButtonStyle();
		TextButtonStyle buttonCarStyle11=new TextButtonStyle();
		TextButtonStyle buttonCarStyle12=new TextButtonStyle();

		Texture car1=new Texture("pickup_horiz.png");
		Texture car2=new Texture("police_horiz.png");
		Texture car3=new Texture("taxi_horiz.png");
		Texture car4=new Texture("newcar1.jpg");
		Texture car5=new Texture("newcar4.jpg");
		Texture car6=new Texture("newcar5.png");
		Texture car7=new Texture("police.png");
		Texture car8=new Texture("ambulance_horiz.png");
		Texture car9=new Texture("Space_escape_1.png");
		Texture car10=new Texture("Space_escape_2.png");
		Texture car11=new Texture("Space_escape_3.png");
		Texture car12=new Texture("Space_escape_4.png");
		

		game.skin.add("car1Texture", car1);
		game.skin.add("car2Texture", car2);
		game.skin.add("car3Texture", car3);
		game.skin.add("car4Texture", car4);
		game.skin.add("car5Texture", car5);
		game.skin.add("car6Texture", car6);
		game.skin.add("car7Texture", car7);
		game.skin.add("car8Texture", car8);
		game.skin.add("car9Texture", car9);
		game.skin.add("car10Texture", car10);
		game.skin.add("car11Texture", car11);
		game.skin.add("car12Texture", car12);

		game.skin.add("car1", new NinePatch(car1,5,5,5,5));
		game.skin.add("car2", new NinePatch(car2,5,5,5,5));
		game.skin.add("car3", new NinePatch(car3,5,5,5,5));
		game.skin.add("car4", new NinePatch(car4,5,5,5,5));
		game.skin.add("car5", new NinePatch(car5,5,5,5,5));
		game.skin.add("car6", new NinePatch(car6,5,5,5,5));
		game.skin.add("car7", new NinePatch(car7,5,5,5,5));
		game.skin.add("car8", new NinePatch(car8,5,5,5,5));
		game.skin.add("car9", new NinePatch(car9,5,5,5,5));
		game.skin.add("car10", new NinePatch(car10,5,5,5,5));
		game.skin.add("car11", new NinePatch(car11,5,5,5,5));
		game.skin.add("car12", new NinePatch(car12,5,5,5,5));

		buttonCarStyle1.up=game.skin.getDrawable("car1");
		buttonCarStyle2.up=game.skin.getDrawable("car2");
		buttonCarStyle3.up=game.skin.getDrawable("car3");
		buttonCarStyle4.up=game.skin.getDrawable("car4");
		buttonCarStyle5.up=game.skin.getDrawable("car5");
		buttonCarStyle6.up=game.skin.getDrawable("car6");
		buttonCarStyle7.up=game.skin.getDrawable("car7");
		buttonCarStyle8.up=game.skin.getDrawable("car8");
		buttonCarStyle9.up=game.skin.getDrawable("car9");
		buttonCarStyle10.up=game.skin.getDrawable("car10");
		buttonCarStyle11.up=game.skin.getDrawable("car11");
		buttonCarStyle12.up=game.skin.getDrawable("car12");

		buttonCarStyle1.font=font;
		buttonCarStyle2.font=font;
		buttonCarStyle3.font=font;
		buttonCarStyle4.font=font;
		buttonCarStyle5.font=font;
		buttonCarStyle6.font=font;
		buttonCarStyle7.font=font;
		buttonCarStyle8.font=font;
		buttonCarStyle9.font=font;
		buttonCarStyle10.font=font;
		buttonCarStyle11.font=font;
		buttonCarStyle12.font=font;

		game.skin.add("buttonCarStyle1", buttonCarStyle1);
		game.skin.add("buttonCarStyle2", buttonCarStyle2);
		game.skin.add("buttonCarStyle3", buttonCarStyle3);
		game.skin.add("buttonCarStyle4", buttonCarStyle4);
		game.skin.add("buttonCarStyle5", buttonCarStyle5);
		game.skin.add("buttonCarStyle6", buttonCarStyle6);
		game.skin.add("buttonCarStyle7", buttonCarStyle7);
		game.skin.add("buttonCarStyle8", buttonCarStyle8);
		game.skin.add("buttonCarStyle9", buttonCarStyle9);
		game.skin.add("buttonCarStyle10", buttonCarStyle10);
		game.skin.add("buttonCarStyle11", buttonCarStyle11);
		game.skin.add("buttonCarStyle12", buttonCarStyle12);
		

		// Themes
		TextButtonStyle buttonTheme1 = new TextButtonStyle();
		TextButtonStyle buttonTheme2 = new TextButtonStyle();
		TextButtonStyle buttonTheme3 = new TextButtonStyle();
		TextButtonStyle buttonTheme4 = new TextButtonStyle();
		
		Texture theme1 = new Texture("grid.png");
		Texture theme2 = new Texture("space.gif");
		Texture theme3 = new Texture("Regular_Frame.png");
		Texture theme4 = new Texture("Space_Frame.png");
		
		game.skin.add("themeTexture1", theme1);
		game.skin.add("themeTexture2", theme2);
		game.skin.add("themeTexture3", theme3);
		game.skin.add("themeTexture4", theme4);
		
		game.skin.add("theme1", new NinePatch(theme1, 5, 5, 5, 5));
		game.skin.add("theme2", new NinePatch(theme2, 5, 5, 5, 5));
		game.skin.add("theme3", new NinePatch(theme3, 5, 5, 5, 5));
		game.skin.add("theme4", new NinePatch(theme4, 5, 5, 5, 5));
		
		buttonTheme1.up = game.skin.getDrawable("theme1");
		buttonTheme2.up = game.skin.getDrawable("theme2");
		buttonTheme3.up = game.skin.getDrawable("theme3");
		buttonTheme4.up = game.skin.getDrawable("theme4");
		
		buttonTheme1.font = font;
		buttonTheme2.font = font;
		buttonTheme3.font = font;
		buttonTheme4.font = font;
		
		game.skin.add("buttonTheme1", buttonTheme1);
		game.skin.add("buttonTheme2", buttonTheme2);
		game.skin.add("buttonTheme3", buttonTheme3);
		game.skin.add("buttonTheme4", buttonTheme4);
		
		// car themes
		Texture obs_car_vert_Theme1 = new Texture("obs_car_vert.png");
		Texture obs_car_horiz_Theme1 = new Texture("obs_car_horiz.png");
		Texture truck_vert_Theme1 = new Texture("truck_vert.png");
		Texture truck_horiz_Theme1 = new Texture("truck_horiz.png");
		Texture obs_car_vert_Theme2 = new Texture("Space_obs_vert.png");
		Texture obs_car_horiz_Theme2 = new Texture("Space_obs_horiz.png");
		Texture truck_vert_Theme2 = new Texture("Space_truck_vert.png");
		Texture truck_horiz_Theme2 = new Texture("Space_truck_horiz.png");
		
		game.skin.add("obs_car_vert_Theme1", obs_car_vert_Theme1);
		game.skin.add("obs_car_horiz_Theme1", obs_car_horiz_Theme1);
		game.skin.add("truck_vert_Theme1", truck_vert_Theme1);
		game.skin.add("truck_horiz_Theme1", truck_horiz_Theme1);
		game.skin.add("obs_car_vert_Theme2", obs_car_vert_Theme2);
		game.skin.add("obs_car_horiz_Theme2", obs_car_horiz_Theme2);
		game.skin.add("truck_vert_Theme2", truck_vert_Theme2);
		game.skin.add("truck_horiz_Theme2", truck_horiz_Theme2);
		
		LabelStyle style = new LabelStyle(font, Color.WHITE);
		game.skin.add("label_font", style);
	}

	public BaseScreen(BaseGame game) {
		this.game = game;
		mainStage=new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));
		uiStage=new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));
		uiTable=new Table();
		uiTable.setFillParent(true);
		uiStage.addActor(uiTable);
		paused=false;
		InputMultiplexer im=new InputMultiplexer(this,uiStage,mainStage);
		Gdx.input.setInputProcessor(im);
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false,800,400);
		create();
		addStyles();
	}

	public abstract void create();
	public abstract void update(float dt);
	public void render(float dt){

		uiStage.act(dt);
		if(!paused){
			mainStage.act(dt);
			update(dt);
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainStage.draw();
		uiStage.draw();

	}
	public boolean isPaused(){
		return paused;
	}
	public void setPaused(boolean b){
		paused=b;
	}
	public void togglePaused(){
		paused=!paused;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		mainStage.getViewport().update(width, height,true);
		uiStage.getViewport().update(width, height,true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
