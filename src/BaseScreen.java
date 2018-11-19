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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen implements Screen, InputProcessor {
	protected BaseGame game;
	protected static Stage mainStage,uiStage;
	public final int VIEW_WIDTH=640;
	public final int VIEW_HEIGHT=480;
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
		BitmapFont font=new BitmapFont();
		buttonStyle1.font=font;
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
