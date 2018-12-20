package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Car extends BaseActor {
	int direction;//0 if horizotal 1 if vertical
	int x,y,fake_x,fake_y;
	int width,height;
	int firstTouchX,firstTouchY;
	int id;
	int xx,yy,fake_xx,fake_yy;
	protected boolean pressed=false;
	Sound buton;
	boolean play;
	boolean move;
	int lastMoveAmountX,lastMoveAmountY,fake_lastMoveAmountX,fake_lastMoveAmountY;
			
	public Car(int direction,int m,int n,int width,int height,int id) {
		
		super();
		buton=Gdx.audio.newSound(Gdx.files.internal("win.ogg"));
		this.direction=direction;
		this.x=m;
		this.y=n;
		fake_x=m;
		fake_y=n;
		this.width=width;
		this.height=height;
		this.id=id;
		xx=x;
		yy=y;
		play=true;
		move=true;
	}
public boolean fakeSetPosition(int x, int y, int[][] game,int num,int w1,int h1,int a,int b,boolean set) {
		if(x==fake_x && y== fake_y)
			return false;
		int[][] arr=new int[game.length][game[0].length];
		for(int i=0;i<game.length;i++)
			for(int j=0;j<game[0].length;j++)
				arr[i][j]=game[i][j];
		if(x<0)
			return false;
		if(y<0)
			return false;
		if(x>game.length)
			return false;
		int xx=x;
		int yy=y;
		
		
		if(x>game.length-width)
			return false;
		
		if(y>game[0].length-height)
			return false;
		if(direction==0)
			if(y!=this.fake_y)
				return false;
		if(direction==1)
			if(x!=this.fake_x)
				return false;
		boolean put=true;
		//find the target because fake_x and fake_y doesn't work
		
		boolean devam=true;
		
		for(int i=game.length-1 ;devam && i>-1;i--)
			for(int j=0;j<game[0].length;j++)
				if(game[i][j]==(num+1)){
					devam=false;
					this.fake_x=j;
					this.fake_y=game.length-i-1;
					break;
				}
					
		
		
		//////////////////////
		int hedefx=x,hedefy=y,hedeffakex=this.fake_x,hedeffakey=this.fake_y;
		for(int i=game.length-(Math.max(y,this.fake_y)+height);i<game.length-Math.min(y,this.fake_y);i++)
			for(int j=Math.min(x,this.fake_x);j<Math.max(x,this.fake_x)+this.width;j++)
				if(game[i][j]!=0 && game[i][j]!=(num+1) ){
					return false;
				}
		
		int[][] hedef2={{1, 2, 7, 7, 7, 10},
						{1, 2, 4, 0, 0, 10},
						{0, 0, 4, 0, 3, 3},
						{0, 0, 0, 0, 8, 11},
						{0, 5, 5, 0, 8, 11},
						{6, 6, 9, 9, 8, 0}};
		
		int[][] hedef1={{1, 2, 7, 7, 7, 10},
						{1, 2, 4, 0, 0, 10},
						{3, 3, 4, 0, 0, 0},
						{0, 0, 0, 0, 8, 11},
						{0, 5, 5, 0, 8, 11},
						{6, 6, 9, 9, 8, 0}};
		boolean bulundu1=true;
		for(int i=0;i<game.length && bulundu1;i++)
			for(int j=0;j<game[0].length;j++)
				if(game[i][j]!=hedef1[i][j]){
					bulundu1=false;
					break;
				}
		this.fake_x=x;
		this.fake_y=y;
		if(put){
			this.fake_xx=xx;
			this.fake_yy=yy;
			for(int i=0;i<game.length;i++)
				for(int j=0;j<game[0].length;j++)
					if(game[i][j]==(num+1))
						game[i][j]=0;
			for(int i=game.length-(y+height);i<game.length-y;i++)
				for(int j=x;j<x+this.width;j++)
					game[i][j]=(num+1);
			
			//fake_lastMoveAmountY=y-this.fake_y;
			//fake_lastMoveAmountX=x-this.fake_x;
			
			if(id==1){
				if(xx==6 && play){
					buton.play();
					for(Car carx:Levels.cars){
					  carx.move=false;
					  carx.play=false;
					}
				}
				if(set)
				setPosition(xx*w1+a, y*h1+b);
				
			}
			else
				if(set)
				setPosition(x*w1+a, y*h1+b);
			
		}
		boolean bulundu2=true;
		for(int i=0;i<game.length && bulundu2;i++)
			for(int j=0;j<game[0].length;j++)
				if(game[i][j]!=hedef2[i][j]){
					bulundu2=false;
					break;
				}	
		if(bulundu2 && bulundu1)
			System.out.println("x:"+hedefx+" y:"+hedefy+" fakex:"+hedeffakex+" fakey:"+hedeffakey+" width:"+width+" height:"+height);
		
		return true;
		
		
	}
	
	
	public boolean setPosition(int x, int y, int[][] game,int num,int w1,int h1,int a,int b,boolean set) {
		int[][] arr=new int[game.length][game[0].length];
		for(int i=0;i<game.length;i++)
			for(int j=0;j<game[0].length;j++)
				arr[i][j]=game[i][j];
		boolean movebool=false;
		
		if(x<0)
			x=0;
		if(y<0)
			y=0;
		if(x>game.length)
			x=game[0].length;
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
		for(int i=game.length-(Math.max(y,this.y)+height);i<game.length-Math.min(y,this.y);i++)
			for(int j=Math.min(x,this.x);j<Math.max(x,this.x)+this.width;j++)
				if(game[i][j]!=0 && game[i][j]!=(num+1) ){
					return false;
					//put=false;
					//break;
				}
		
		
		
		if(put){
			
			for(int i=0;i<game.length;i++)
				for(int j=0;j<game[0].length;j++)
					if(game[i][j]==(num+1))
						game[i][j]=0;
			for(int i=game.length-(y+height);i<game.length-y;i++)
				for(int j=x;j<x+this.width;j++)
					game[i][j]=(num+1);
			
			lastMoveAmountY=Math.abs(this.y-y);
			lastMoveAmountX=Math.abs(this.x-x);
			
			this.x=x;
			this.y=y;
			if(id==1){
				if(this.xx<xx)
					movebool=true;
				if(xx==6 && play){
					buton.play();
					for(Car carx:Levels.cars){
					  carx.move=false;
					  carx.play=false;
					}
				}
				if(set)
				setPosition(xx*w1+a, y*h1+b);
				
			}
			else
				if(set)
				setPosition(x*w1+a, y*h1+b);
			this.xx=xx;
			this.yy=yy;
			
		}
		for(int i=0;i<game.length;i++)
			for(int j=0;j<game[0].length;j++)
				if(arr[i][j]!=game[i][j])
					return put;
					
		return movebool;
		
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
