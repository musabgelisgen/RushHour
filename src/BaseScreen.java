package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen implements Screen, InputProcessor {
	protected BaseGame game;
	protected static Stage mainStage,uiStage;
	public final int VIEW_WIDTH=640;
	public final int VIEW_HEIGHT=480;
	private boolean paused;
	protected Table uiTable;
	@Override
	
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
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
		create();
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
