package com.mygdx.game;

public class Car extends BaseActor {
	int direction;//0 if horizotal 1 if vertical
	int x,y;
	int width,height;
	float firstTouchX,firstTouchY;
	protected boolean pressed=false;
	public Car(int direction,int m,int n,int width,int height) {
		super();
		this.direction=direction;
		this.x=m;
		this.y=n;
		this.width=width;
		this.height=height;
	}
}
