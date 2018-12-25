package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SelectTheme extends BaseScreen {
	TextButton returnMenu;
	TextButton newTheme1,newTheme2;
	TextButton[] themeButtons;
	static Texture targetTexture = new Texture("grid.png");
//	BaseActor frame1,frame2;
	static int targetNumber = 1;
	public SelectTheme(BaseGame game) {
		super(game);
	}

	@Override
	public void create() {
//		frame1 = new BaseActor();
//		frame1.setTexture(new Texture("frame.jpg"));
//		
//		frame2 = new BaseActor();
//		frame2.setTexture(new Texture("frame.jpg"));
		
		

		themeButtons = new TextButton[2];
		newTheme1 = new TextButton(targetNumber == 1 ? "REGULAR": "", game.skin, "buttonTheme3");
		newTheme2 = new TextButton(targetNumber == 2 ? "SPACE": "", game.skin, "buttonTheme4");
//		newTheme3 = new TextButton(targetNumber == 3 ? "CHOSEN": "", game.skin, "buttonTheme3");
//		newTheme4 = new TextButton(targetNumber == 4 ? "CHOSEN": "", game.skin, "buttonTheme4");
		themeButtons[0] = newTheme1;
		themeButtons[1] = newTheme2;
		
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
				newTheme1.setText("REGULAR");
				targetTexture = game.skin.get("themeTexture1", Texture.class);
				targetNumber = 1;
				uiTable.setBackground(game.skin.getDrawable("regular_theme_sel"));
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
				newTheme2.setText("SPACE");
				targetTexture = game.skin.get("themeTexture2", Texture.class);
				targetNumber = 2;
				uiTable.setBackground(game.skin.getDrawable("space_backgroundSP"));
			}
		});
		
//		newTheme3.addListener(new InputListener()
//		{
//			public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
//				game.skin.get("buton", Sound.class).play();
//				// Clean the texts
//				for (int i = 0; i < themeButtons.length; i++) {
//					themeButtons[i].setText("");
//				}
//				return true;
//			}
//			public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
//				newTheme3.setText("CHOSEN");
//				targetTexture = game.skin.get("themeTexture3", Texture.class);
//				targetNumber = 3;
//			}
//		});
//		
//		newTheme4.addListener(new InputListener()
//		{
//			public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
//				game.skin.get("buton", Sound.class).play();
//				// Clean the texts
//				for (int i = 0; i < themeButtons.length; i++) {
//					themeButtons[i].setText("");
//				}
//				return true;
//			}
//			public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
//				newTheme4.setText("CHOSEN");
//				targetTexture = game.skin.get("themeTexture4", Texture.class);
//				targetNumber = 4;
//			}
//		});


//		uiTable.setBackground(game.skin.getDrawable("background_blurred"));
		if(SelectTheme.targetNumber == 2)
			uiTable.setBackground(game.skin.getDrawable("space_backgroundSP"));
		else
			uiTable.setBackground(game.skin.getDrawable("regular_theme_sel"));
		
//		uiStage.addActor(frame1);
//		uiStage.addActor(frame2);
		uiStage.addActor(newTheme1);
		uiStage.addActor(newTheme2);
//		uiStage.addActor(newTheme3);
//		uiStage.addActor(newTheme4);

		uiTable.add(returnMenu);

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
	
//		frame1.setWidth(250);
//		frame1.setHeight(180);
//		frame1.setPosition(150,250);
//		
//		frame2.setWidth(250);
//		frame2.setHeight(180);
//		frame2.setPosition(200,100);
		
		
		int cbx = 70;
		themeButtons[0].setWidth(3 * cbx);
		themeButtons[0].setHeight(2*cbx);
		themeButtons[0].setPosition(200,300);
		
		themeButtons[1].setWidth(3 * cbx);
		themeButtons[1].setHeight(2*cbx);
		themeButtons[1].setPosition(200,100);


		returnMenu.setPosition(30, 0);
		returnMenu.setWidth(180);

	}

}
