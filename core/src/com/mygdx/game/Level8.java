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

public class Level8 extends Levels {
	

	public Level8(BaseGame game) {
		super(game);
		levelno=8;
	}

	
	public void createCars(ArrayList<Car> list){
		Car car1=new Car(0,1,5,3,1,0);
		Car car2=new Car(1,0,3,1,2,0);
		Car car3=new Car(0,4,4,2,1,0);
		Car car4=new Car(0,2,3,2,1,1);
		Car car5=new Car(1,4,1,1,3,0);
		Car car6=new Car(0,1,2,3,1,0);
		Car car7=new Car(0,0,1,2,1,0);
		Car car8=new Car(1,2,0,1,2,0);
		Car car9=new Car(0,3,0,3,1,0);
		
		car1.setTexture(new Texture("chevrolet.png"));
		
		car1.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car1.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car1.y/number_of_height_tiles);
		
		car2.setTexture(new Texture("chevrolet.png"));
		car2.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car2.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car2.y/number_of_height_tiles);
		
		car3.setTexture(new Texture("ferrari.png"));
		car3.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car3.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car3.y/number_of_height_tiles);
		
		car4.setTexture(new Texture("ferrari.png"));
		car4.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car4.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car4.y/number_of_height_tiles);
				
		car5.setTexture(new Texture("jeep.png"));
		car5.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car5.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car5.y/number_of_height_tiles);
				
		car6.setTexture(new Texture("ambulance.png"));
		car6.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car6.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car6.y/number_of_height_tiles);
				
		car7.setTexture(new Texture("dashcam.png"));
		car7.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car7.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car7.y/number_of_height_tiles);
		
		car8.setTexture(new Texture("dashcam.png"));
		car8.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car8.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car8.y/number_of_height_tiles);
	
		car9.setTexture(new Texture("car10.png"));
		car9.setPosition(VIEW_WIDTH/2-half_of_view_width/2+car9.x*half_of_view_width/number_of_width_tiles, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*car9.y/number_of_height_tiles);
		
		
		list.add(car1);
		list.add(car2);
		list.add(car3);
		list.add(car4);
		list.add(car5);
		list.add(car6);
		list.add(car7);
		list.add(car8);
		list.add(car9);
		
		mainStage.addActor(car1);
		mainStage.addActor(car2);
		mainStage.addActor(car3);
		mainStage.addActor(car4);
		mainStage.addActor(car5);
		mainStage.addActor(car6);
		mainStage.addActor(car7);
		mainStage.addActor(car8);
		mainStage.addActor(car9);
		
		startX=car4.getX();
		startY=car4.getY();
	}
}
	
	
	

