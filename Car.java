package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Car extends BaseActor {
	int direction;//0 if horizotal 1 if vertical
	int x,y;
	int width,height;
	int firstTouchX,firstTouchY;
	int id;
	int xx,yy;
	protected boolean pressed=false;
	Sound buton;
	boolean play;
	boolean move;
	int lastMoveAmountX,lastMoveAmountY;
			
	public Car(int direction,int m,int n,int width,int height,int id) {
		
		super();
		buton=Gdx.audio.newSound(Gdx.files.internal("win.ogg"));
		this.direction=direction;
		this.x=m;
		this.y=n;
		this.width=width;
		this.height=height;
		this.id=id;
		xx=x;
		yy=y;
		play=true;
		move=true;
	}
	
	
	public boolean setPosition(int x, int y, int[][] game,int num,int w1,int h1,int a,int b) {
		
		if(x<0)
			x=0;
		if(y<0)
			y=0;
		
		int xx=x;
		int yy=y;
		
		
		if(x>game.length-width)
			x=game.length-width;
		
		if(y>game[0].length-height)
			y=game[0].length-height;
		if(direction==0)
			y=this.y;
		else
			x=this.x;
		boolean put=true;
		for(int i=game.length-(Math.min(y,this.y)+height);i<game.length-Math.min(y,this.y);i++)
			for(int j=Math.min(x,this.x);j<Math.max(x,this.x)+this.width;j++)
				if(game[i][j]!=0 && game[i][j]!=(num+1) ){
					put=false;
					break;
				}
		
		
		
		if(put){
			this.xx=xx;
			this.yy=yy;
			for(int i=game.length-(this.y+height);i<game.length-this.y;i++)
				for(int j=this.x;j<this.x+this.width;j++)
					game[i][j]=0;
			for(int i=game.length-(y+height);i<game.length-y;i++)
				for(int j=x;j<x+this.width;j++)
					game[i][j]=(num+1);
			
			lastMoveAmountX=Math.abs(game.length-(this.y+height)-game.length-(y+height));
			lastMoveAmountY=Math.abs(this.x-x);
			
			this.x=x;
			this.y=y;
			if(id==1){
				if(xx==6 && play){
					buton.play();
					for(Car carx:Levels.cars){
					  carx.move=false;
					  carx.play=false;
					}
				}
				setPosition(xx*w1+a, y*h1+b);
				
			}
			else
				setPosition(x*w1+a, y*h1+b);
			
		}
		return put;
		
	}
	public int getXX(int w1,int a){
		return x*w1+a;
	}
	public int getYY(int h1,int b){
		return y*h1+b;
	}
	@Override
	public void act(float dt) {
		// TODO Auto-generated method stub
		if(move)
			super.act(dt);
	}
}
