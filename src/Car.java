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
	}
	
	
	public void setPosition(int x, int y, int[][] game,int num,int w1,int h1,int a,int b) {
		int xx=x;
		int yy=y;
		this.xx=x;
		this.yy=y;
		if(x<0)
			x=0;
		if(x>game.length-width)
			x=game.length-width;
		if(y<0)
			y=0;
		if(y>game[0].length-height)
			y=game[0].length-height;
		if(direction==0)
			y=this.y;
		else
			x=this.x;
		boolean put=true;
		for(int i=game.length-(y+height);i<game.length-y;i++)
			for(int j=x;j<x+this.width;j++)
				if(game[i][j]!=0 && game[i][j]!=(num+1) ){
					put=false;
					break;
				}
		if(put){
			for(int i=game.length-(this.y+height);i<game.length-this.y;i++)
				for(int j=this.x;j<this.x+this.width;j++)
					game[i][j]=0;
			for(int i=game.length-(y+height);i<game.length-y;i++)
				for(int j=x;j<x+this.width;j++)
					game[i][j]=(num+1);
			
			this.x=x;
			this.y=y;
			if(id==1){
				setPosition(xx*w1+a, y*h1+b);
				if(xx>=6 && play){
					buton.play();
					play=false;
				}
			}
			else
				setPosition(x*w1+a, y*h1+b);
			
		}
		
		
	}
	public int getXX(int w1,int a){
		return x*w1+a;
	}
	public int getYY(int h1,int b){
		return y*h1+b;
	}
}
