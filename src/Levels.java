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

public class Levels extends BaseScreen {
	
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
	public Levels(BaseGame game) {
		super(game);
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		returnMenu=new TextButton("Main Menu", game.skin, "buttonStyle3");
		buton=game.skin.get("buton", Sound.class);
		win=false;
		cars=new ArrayList<Car>();
		m=6;
		n=6;
		gameTable=new int[n][m];
		w=VIEW_WIDTH/2;
		h=VIEW_HEIGHT/2;
		w1=w/m;
		h1=h/n;
		a=VIEW_WIDTH/2-w/2;
		b=VIEW_HEIGHT/2-h/2;
		sr=new ShapeRenderer();
		createCars();

		mainStage.addActor(car1);
		mainStage.addActor(car2);
		mainStage.addActor(car3);
		mainStage.addActor(car4);
		mainStage.addActor(car5);
		mainStage.addActor(car6);
		mainStage.addActor(car7);
		
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		cars.add(car6);
		cars.add(car7);
		
		for(int i=0;i<cars.size();i++){
			for(int j=n-cars.get(i).y-cars.get(i).height;j<n-cars.get(i).y;j++)
				for(int k=cars.get(i).x;k<cars.get(i).x+cars.get(i).width;k++){
					gameTable[j][k]=i+1;
				}
		}
		
		for(final Car carx:cars){
		
		carx.addListener(new InputListener(){
			
			
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
						carx.firstTouchX=(int) (x/(w/m));
						carx.firstTouchY=(int) (y/(h/n));
					
				 carx.pressed=true;
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						carx.pressed=false;
					}
		 });
		
		returnMenu.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new Singleplayer(game));
				if(win){
					for(Car carx:cars)
						carx.buton.stop();
					Singleplayer.passed=1;
				}
			}
			});
		
		}
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);
		startX=car3.getX();
		startY=car3.getY();
	}
	public void createCars(){
		car1=new Car(0,3,5,2,1,1);
		car2=new Car(0,0,4,3,1,2);
		car3=new Car(0,0,2,2,1,3);
		car4=new Car(1,2,1,1,2,4);
		car5=new Car(1,3,2,1,2,5);
		car6=new Car(1,5,1,1,2,6);
		car7=new Car(0,2,0,2,1,7);
		
		car1.setTexture(new Texture("car1.png"));
		car1.setPosition(VIEW_WIDTH/2-w/2+car1.x*w/m, VIEW_HEIGHT/2-h/2+h*car1.y/n);
		
		car2.setTexture(new Texture("car2.png"));
		car2.setPosition(VIEW_WIDTH/2-w/2+car2.x*w/m, VIEW_HEIGHT/2-h/2+h*car2.y/n);
		
		car3.setTexture(new Texture("car3.png"));
		car3.setPosition(VIEW_WIDTH/2-w/2+car3.x*w/m, VIEW_HEIGHT/2-h/2+h*car3.y/n);
		
		car4.setTexture(new Texture("car4.png"));
		car4.setPosition(VIEW_WIDTH/2-w/2+car4.x*w/m, VIEW_HEIGHT/2-h/2+h*car4.y/n);
				
		car5.setTexture(new Texture("car5.png"));
		car5.setPosition(VIEW_WIDTH/2-w/2+car5.x*w/m, VIEW_HEIGHT/2-h/2+h*car5.y/n);
				
		car6.setTexture(new Texture("car6.png"));
		car6.setPosition(VIEW_WIDTH/2-w/2+car6.x*w/m, VIEW_HEIGHT/2-h/2+h*car6.y/n);
				
		car7.setTexture(new Texture("car7.png"));
		car7.setPosition(VIEW_WIDTH/2-w/2+car7.x*w/m, VIEW_HEIGHT/2-h/2+h*car7.y/n);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
		
		for(Car x:cars){
			x.setWidth(w/m*x.width);
			x.setHeight(h/n*x.height);
			if(x.xx>=m)
				win=true;
			
		
			 
			
		}

			
		}
		
		
		

	
	
	@Override
	public void render(float dt) {
		//board
		super.render(dt);
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.GRAY);
		sr.rect(VIEW_WIDTH/2-w/2, VIEW_HEIGHT/2-h/2, w, h);
		sr.end();
		/////////////////
		sr.begin(ShapeType.Line);
		sr.setColor(Color.BLACK);
		for(int i=0;i<m+1;i++)
			sr.line(VIEW_WIDTH/2-w/2, VIEW_HEIGHT/2-h/2+h*i/m, VIEW_WIDTH/2+w/2, VIEW_HEIGHT/2-h/2+h*i/m);
		for(int i=0;i<n+1;i++)
			sr.line(VIEW_WIDTH/2-w/2+w*i/m, VIEW_HEIGHT/2-h/2, VIEW_WIDTH/2-w/2+w*i/m, VIEW_HEIGHT/2+h/2);
		sr.end();
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.CYAN);
		sr.rect(startX-w/m,startY,w/m,h/n);
		sr.end();
		mainStage.draw();
		returnMenu.setPosition(0, 0);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// upperleft is (0,0)			
		for(int i=0;i<cars.size();i++){
			int a=VIEW_WIDTH/2-w/2;
			int b=VIEW_HEIGHT/2-h/2;
			//(a,b) is the lowerleft corner
			int sc=VIEW_HEIGHT-screenY;
			int w1=w/m;
			int h1=h/n;
			int a1=screenX-a;
			a1/=w1;
			int b1=sc-b;
			b1/=h1;
				
			if(cars.get(i).pressed){
					
					cars.get(i).setPosition(a1,b1,gameTable,i,w1,h1,a,b);
			}
		
		
	}
		
		return true;
	}
}
	
	
	

