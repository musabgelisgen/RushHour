package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SelectTheme extends BaseScreen {
	TextButton returnMenu;
	TextButton newTheme1,newTheme2,newTheme3,newTheme4;
	TextButton[] themeButtons;
	static Texture targetTexture = new Texture("grid.png");
	static int targetNumber = 1;
	public SelectTheme(BaseGame game) {
		super(game);
	}

	@Override
	public void create() {

		themeButtons = new TextButton[4];
		newTheme1 = new TextButton(targetNumber == 1 ? "CHOSEN": "", game.skin, "buttonTheme1");
		newTheme2 = new TextButton(targetNumber == 2 ? "CHOSEN": "", game.skin, "buttonTheme2");
		newTheme3 = new TextButton(targetNumber == 3 ? "CHOSEN": "", game.skin, "buttonTheme3");
		newTheme4 = new TextButton(targetNumber == 4 ? "CHOSEN": "", game.skin, "buttonTheme4");
		themeButtons[0] = newTheme1;
		themeButtons[1] = newTheme2;
		themeButtons[2] = newTheme3;
		themeButtons[3] = newTheme4;
		
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
		
		newTheme1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
				game.skin.get("buton", Sound.class).play();
				// Clean the texts
				for (int i = 0; i < themeButtons.length; i++) {
					themeButtons[i].setText("");
				}
				return true;
			}
			public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
				newTheme1.setText("CHOSEN");
				targetTexture = game.skin.get("themeTexture1", Texture.class);
				targetNumber = 1;
			}
		});
		
		newTheme2.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
				game.skin.get("buton", Sound.class).play();
				for (int i = 0; i < themeButtons.length; i++) {
					themeButtons[i].setText("");
				}
				return true;
			}
			public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
				newTheme2.setText("CHOSEN");
				targetTexture = game.skin.get("themeTexture2", Texture.class);
				targetNumber = 2;
			}
		});
		
		newTheme3.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
				game.skin.get("buton", Sound.class).play();
				// Clean the texts
				for (int i = 0; i < themeButtons.length; i++) {
					themeButtons[i].setText("");
				}
				return true;
			}
			public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
				newTheme3.setText("CHOSEN");
				targetTexture = game.skin.get("themeTexture3", Texture.class);
				targetNumber = 3;
			}
		});
		
		newTheme4.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
				game.skin.get("buton", Sound.class).play();
				// Clean the texts
				for (int i = 0; i < themeButtons.length; i++) {
					themeButtons[i].setText("");
				}
				return true;
			}
			public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
				newTheme4.setText("CHOSEN");
				targetTexture = game.skin.get("themeTexture4", Texture.class);
				targetNumber = 4;
			}
		});


		uiTable.setBackground(game.skin.getDrawable("background_blurred"));

		uiStage.addActor(newTheme1);
		uiStage.addActor(newTheme2);
		uiStage.addActor(newTheme3);
		uiStage.addActor(newTheme4);

		uiTable.add(returnMenu);

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

		for(int i=0;i<themeButtons.length;i++){
			int cbx = 70;
			themeButtons[i].setWidth(2 * cbx);
			themeButtons[i].setHeight(cbx);
			themeButtons[i].setPosition((cbx)*3*(i%(themeButtons.length/2))+VIEW_WIDTH/4 - 20, 3*VIEW_HEIGHT/4-(cbx+10)*2*(i/(themeButtons.length/2)));
		}

		returnMenu.setPosition(30, 0);
		returnMenu.setWidth(180);

	}

}
