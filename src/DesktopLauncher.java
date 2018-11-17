package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.RushHourGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		RushHourGame game=new RushHourGame();
		LwjglApplication launcher=new LwjglApplication(game);
	}
}
