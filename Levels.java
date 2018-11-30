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
	Sound carVoice;
	int m,n;
	ShapeRenderer sr;
	int w,h;
	TextButton returnMenu;
	static ArrayList<Car> cars;
	int[][] gameTable;
	static int a,b,w1,h1;
	boolean win=false;
	float startX,startY;
	Sound buton;
	int pass;
	BaseActor winner;
	public Levels(BaseGame game) {
		super(game);
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		winner=new BaseActor();
		winner.setTexture(new Texture("youwin1.png"));
		pass=Singleplayer.passed;
		carVoice=Gdx.audio.newSound(Gdx.files.internal("car.ogg"));
		returnMenu=new TextButton("Back", game.skin, "buttonStyle3");
		game.skin.add("returnMenu", returnMenu);
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
		createCars(cars);
		
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
						carVoice.play();
						
					
				 carx.pressed=true;
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						carx.pressed=false;
						carVoice.stop();
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
					Singleplayer.passed=pass+1;
				}
			}
			});
		
		}
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);
		mainStage.addActor(winner);
		winner.setVisible(false);
		
	}
	public void createCars(ArrayList<Car> list){
		Car car1=new Car(0,3,5,2,1,0);
		Car car2=new Car(0,0,4,2,1,0);
		Car car3=new Car(0,0,2,2,1,1);
		Car car4=new Car(1,2,1,1,2,0);
		Car car5=new Car(1,3,2,1,2,0);
		Car car6=new Car(1,5,1,1,2,0);
		Car car7=new Car(0,2,0,2,1,0);
		
		car1.setTexture(new Texture("ferrari.png"));
		car1.setPosition(VIEW_WIDTH/2-w/2+car1.x*w/m, VIEW_HEIGHT/2-h/2+h*car1.y/n);
		
		car2.setTexture(new Texture("bugatti.png"));
		car2.setPosition(VIEW_WIDTH/2-w/2+car2.x*w/m, VIEW_HEIGHT/2-h/2+h*car2.y/n);
		
		car3.setTexture(new Texture("ambulance.png"));
		car3.setPosition(VIEW_WIDTH/2-w/2+car3.x*w/m, VIEW_HEIGHT/2-h/2+h*car3.y/n);
		
		car4.setTexture(new Texture("chevrolet.png"));
		car4.setPosition(VIEW_WIDTH/2-w/2+car4.x*w/m, VIEW_HEIGHT/2-h/2+h*car4.y/n);
				
		car5.setTexture(new Texture("jeep.png"));
		car5.setPosition(VIEW_WIDTH/2-w/2+car5.x*w/m, VIEW_HEIGHT/2-h/2+h*car5.y/n);
				
		car6.setTexture(new Texture("dashcam.png"));
		car6.setPosition(VIEW_WIDTH/2-w/2+car6.x*w/m, VIEW_HEIGHT/2-h/2+h*car6.y/n);
				
		car7.setTexture(new Texture("mercedes.png"));
		car7.setPosition(VIEW_WIDTH/2-w/2+car7.x*w/m, VIEW_HEIGHT/2-h/2+h*car7.y/n);
		
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

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
		for(Car x:cars){
			x.setWidth(w/m*x.width);
			x.setHeight(h/n*x.height);
			if(x.xx==m && x.id==1){
				win=true;
			}
			
		if(win){
			winner.setVisible(true);
			winner.setPosition(VIEW_WIDTH/2-100, VIEW_HEIGHT/2-100);
			winner.setWidth(200);
			winner.setHeight(200);
			pause();
		}
			 
			
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
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.YELLOW);
		for(Car x:cars)
			if(x.id==1){
				sr.rect(VIEW_WIDTH/2+w/2, x.getY(), 2*w/m, h/n);
			}
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
		returnMenu.setPosition(0,0);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.YELLOW);
		for(Car x:cars)
			if(x.id==1)
				sr.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
		sr.end();
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
				
			if(cars.get(i).pressed && cars.get(i).move){
					
					cars.get(i).setPosition(a1,b1,gameTable,i,w1,h1,a,b);
			}
		
		
	}
		
		return true;
	}
}
	
	
	

