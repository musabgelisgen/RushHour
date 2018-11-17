package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Level1 extends BaseScreen {
	int m,n;
	ShapeRenderer sr;
	int w,h;
	Car car1,car2,car3,car4,car5,car6,car7;
	ArrayList<Car> cars;
	public Level1(BaseGame game) {
		super(game);
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		cars=new ArrayList<Car>();
		m=6;
		n=6;
		w=VIEW_WIDTH/2;
		h=VIEW_HEIGHT/2;
		sr=new ShapeRenderer();
		car1=new Car(0,3,5,2,1);
		car2=new Car(0,0,4,3,1);
		car3=new Car(0,0,2,2,1);
		car4=new Car(1,2,1,1,2);
		car5=new Car(1,3,2,1,2);
		car6=new Car(1,5,1,1,2);
		car7=new Car(0,2,0,2,1);
		
		
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
		
		for(final Car carx:cars){
		
		carx.addListener(new InputListener(){
			
			
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
						carx.firstTouchX=x+carx.getX();
						carx.firstTouchY=y+carx.getY();
						
					
				 carx.pressed=true;
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						carx.pressed=false;
					}
		 });
		
		}
		
		uiTable.setBackground(game.skin.getDrawable("background"));
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
		
		for(Car x:cars){
			x.setWidth(w/m*x.width);
			x.setHeight(h/n*x.height);
			if(x.getX()<VIEW_WIDTH/2-w/2)
				x.setPosition(VIEW_WIDTH/2-w/2, x.getY());
			if(x.getX()+x.getWidth()>VIEW_WIDTH/2+w/2){
				x.setPosition(VIEW_WIDTH/2+w/2-x.getWidth(),x.getY());
			}
			
			if(x.getY()<VIEW_HEIGHT/2-h/2)
				x.setPosition(x.getX(),VIEW_HEIGHT/2-h/2);
			if(x.getY()+x.getHeight()>VIEW_HEIGHT/2+h/2){
				x.setPosition(x.getX(), VIEW_HEIGHT/2+h/2-x.getHeight());
			}
			
			if(x.direction==0){
				x.setPosition(x.getX(), VIEW_HEIGHT/2-h/2+h*x.y/n);
			}
			else{
				x.setPosition(VIEW_WIDTH/2-w/2+x.x*w/m, x.getY());
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
		/////////////////
		sr.begin(ShapeType.Line);
		sr.setColor(Color.BLACK);
		for(int i=0;i<m+1;i++)
			sr.line(VIEW_WIDTH/2-w/2, VIEW_HEIGHT/2-h/2+h*i/m, VIEW_WIDTH/2+w/2, VIEW_HEIGHT/2-h/2+h*i/m);
		for(int i=0;i<n+1;i++)
			sr.line(VIEW_WIDTH/2-w/2+w*i/m, VIEW_HEIGHT/2-h/2, VIEW_WIDTH/2-w/2+w*i/m, VIEW_HEIGHT/2+h/2);
		sr.end();
		mainStage.draw();
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		boolean nonintersect=true;
		for(Car carx:cars){
			int a=VIEW_WIDTH/2-w/2;
			int b=VIEW_HEIGHT/2-h/2;
			int sc=VIEW_HEIGHT-screenY;
			int w1=w/m;
			int h1=h/n;
			int a1=screenX-a;
			a1/=w1;
			int a2=(int) (carx.firstTouchX-a);
			a2/=w1;
			int b1=sc-b;
			b1/=h1;
			int b2=(int) (carx.firstTouchY-b);
			b2/=h1;
			float oldx=carx.getX();
			float oldy=carx.getX();
			Rectangle r0=new Rectangle(w1*a1+a,carx.getY(), carx.getWidth(), carx.getHeight());
			Rectangle r1=new Rectangle(carx.getX(),h1*b1+b, carx.getWidth(), carx.getHeight());
			for(Car cary:cars)
				if(carx!=cary){
					if(carx.direction==0){
						if(r0.overlaps(cary.getRectangleBoundary())){
							nonintersect=false;
							System.out.println("sdaf");
							break;
						}
					}
					else{
						if(r1.overlaps(cary.getRectangleBoundary())){
							nonintersect=false;
							break;
						}
					}
				}
			
			if(carx.pressed && nonintersect){
				if(carx.direction==0){
					carx.setPosition(w1*a1+a,carx.getY());
				}
				else{
					carx.setPosition(carx.getX(),h1*b1+b);
				}
			}
		}
		return true;
	}
	
	
	
}
