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

public class Level1 extends Levels {
	
	
	public Level1(BaseGame game) {
		super(game);
		levelno=1;
		
	}
	
	public void createCars(ArrayList<Car> list){
		Car car1=new Car(0,3,5,2,1,0);
		Car car2=new Car(0,0,4,2,1,0);
		Car car3=new Car(0,0,2,2,1,1);
		Car car4=new Car(1,2,1,1,2,0);
		Car car5=new Car(1,3,2,1,2,0);
		Car car6=new Car(1,5,1,1,2,0);
		Car car7=new Car(0,2,0,2,1,0);
		
		car1.setTexture(new Texture("obs_car_horiz.png"));
		car1.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car1.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car1.y/height_tiles);
		
		car2.setTexture(new Texture("obs_car_horiz.png"));
		car2.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car2.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car2.y/height_tiles);
		
		car3.setTexture(new Texture("ambulance.png"));
		car3.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car3.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car3.y/height_tiles);
		
		car4.setTexture(new Texture("obs_car_vert.png"));
		car4.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car4.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car4.y/height_tiles);
				
		car5.setTexture(new Texture("obs_car_vert.png"));
		car5.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car5.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car5.y/height_tiles);
				
		car6.setTexture(new Texture("obs_car_vert.png"));
		car6.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car6.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car6.y/height_tiles);
				
		car7.setTexture(new Texture("obs_car_horiz.png"));
		car7.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car7.x*half_of_view_width/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car7.y/height_tiles);
		
		list.add(car1);
		list.add(car2);
		list.add(car3);
		list.add(car4);
		list.add(car5);
		list.add(car6);
		list.add(car7);
		
		mainStage.addActor(car1);
		mainStage.addActor(car2);
		mainStage.addActor(car3);
		mainStage.addActor(car4);
		mainStage.addActor(car5);
		mainStage.addActor(car6);
		mainStage.addActor(car7);
		
		startX=car3.getX();
		startY=car3.getY();
	}

	
}
	
	
	

