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

public class Level5 extends Levels {
	

	public Level5(BaseGame game) {
		super(game);
		levelno=5;
	}
	
	public void createCars(ArrayList<Car> list){
		Car car1=new Car(1,0,4,1,2,0);//
		Car car2=new Car(1,0,0,1,3,0);//
		Car car3=new Car(1,2,4,1,2,0);//
		Car car4=new Car(0,0,3,2,1,1);//
		Car car5=new Car(0,3,5,3,1,0);//
		Car car6=new Car(0,4,4,2,1,0);//
		Car car7=new Car(1,4,2,1,2,0);//
		Car car8=new Car(1,5,2,1,2,0);//
		Car car9=new Car(1,5,0,1,2,0);//
		
		
		car1.setTexture(new Texture("obs_car_vert.png"));
		car1.setPosition(VIEW_WIDTH/2-w/2+car1.x*w/m, VIEW_HEIGHT/2-h/2+h*car1.y/n);
		
		car2.setTexture(new Texture("truck_vert.png"));
		car2.setPosition(VIEW_WIDTH/2-w/2+car2.x*w/m, VIEW_HEIGHT/2-h/2+h*car2.y/n);
		
		car3.setTexture(new Texture("obs_car_vert.png"));
		car3.setPosition(VIEW_WIDTH/2-w/2+car3.x*w/m, VIEW_HEIGHT/2-h/2+h*car3.y/n);
		
		car4.setTexture(new Texture("ferrari.png"));
		car4.setPosition(VIEW_WIDTH/2-w/2+car4.x*w/m, VIEW_HEIGHT/2-h/2+h*car4.y/n);
				
		car5.setTexture(new Texture("truck_horiz.png"));
		car5.setPosition(VIEW_WIDTH/2-w/2+car5.x*w/m, VIEW_HEIGHT/2-h/2+h*car5.y/n);
				
		car6.setTexture(new Texture("obs_car_horiz.png"));
		car6.setPosition(VIEW_WIDTH/2-w/2+car6.x*w/m, VIEW_HEIGHT/2-h/2+h*car6.y/n);
				
		car7.setTexture(new Texture("obs_car_vert.png"));
		car7.setPosition(VIEW_WIDTH/2-w/2+car7.x*w/m, VIEW_HEIGHT/2-h/2+h*car7.y/n);
		
		car8.setTexture(new Texture("obs_car_vert.png"));
		car8.setPosition(VIEW_WIDTH/2-w/2+car8.x*w/m, VIEW_HEIGHT/2-h/2+h*car8.y/n);
	
		car9.setTexture(new Texture("obs_car_vert.png"));
		car9.setPosition(VIEW_WIDTH/2-w/2+car9.x*w/m, VIEW_HEIGHT/2-h/2+h*car9.y/n);
				
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
	
	
	

