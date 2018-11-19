package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Level6 extends Levels {
	
	int m,n;
	ShapeRenderer sr;
	int w,h;
	Car car1,car2,car3,car4,car5,car6,car7;
	TextButton returnMenu;
	ArrayList<Car> cars;
	int[][] gameTable;
	int a,b,w1,h1;
	boolean win=false;
	float startX,startY;
	Sound buton;
	public Level6(BaseGame game) {
		super(game);
		
	}

	
}
	
	
	

