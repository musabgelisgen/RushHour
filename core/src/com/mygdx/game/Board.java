package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board extends BaseActor {
	boolean shiftable;
	int width,height;
	int[][] table;
	int x,y;	//arrayda asol üst köþesinin asýl array'deki indexleri
	boolean pressed;
	int firstTouchX,firstTouchY,lastX,lastY;
	int leftX,leftY;
	int id;
	private boolean shift;
	public Board(int x,int y,int width,int height,int leftX,int leftY,int id) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		table=new int[height][width];
		pressed=false;
		this.leftX=leftX;
		this.leftY=leftY;
		shiftable=false;
		this.id=id;
	}
	
	public void updateBoard(int[][] board){
		for(int i=y;i<y+height;i++)
			for(int j=x;j<x+width;j++){
				table[i-y][j-x]=board[i][j];
			}
		boolean shiftable=true;
		for(int j=0;j<board.length;j++)
			if((board[j][4]>0 && board[j][5]==board[j][4] && (id==1 || id==2))||(board[j][8]>0 && board[j][9]==board[j][8] && (id==3|| id==2))){
				shiftable=false;
				break;
			}
		System.out.println(board[6][8]+" , "+board[6][9]);
		shift=shiftable;
		
	}
	public void updatePosition(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void dragBoard(int a,int b,int[][] gameTable,int mapx,int mapy,int mapWidth,int mapHeight,ArrayList<Car> cars){
		boolean[] set=new boolean[cars.size()];
		for(int i=0;i<set.length;i++)
			set[i]=false;
		if(b<0)
			return;
		if(b>2*gameTable.length/3)
			return;
		updateBoard(gameTable);
		if(!shift)
			return;
//		for(int i=gameTable.length-1;i>-1;i--)
//				System.out.println(Arrays.toString(gameTable[i]));
//		System.out.println("\n\n");
		if(y==b)
			return;
		for(int i=y;i<y+height;i++)
			for(int j=x;j<x+width;j++)
				gameTable[i][j]=-1;
		for(int i=b;i<b+height;i++)
			for(int j=a;j<a+width;j++){	
				gameTable[i][j]=table[i-b][j-a];
			}
		updatePosition(a, b);
		updateBoard(gameTable);
		
		leftY=mapy+2*mapHeight/3-b*mapHeight/gameTable.length;
		setPosition(leftX,leftY);
		for(int i=gameTable.length-1;i>-1;i--)
			for(int j=0;j<gameTable[0].length;j++)
				if(gameTable[i][j]>0)
				if(!set[gameTable[i][j]-1]){
					cars.get(gameTable[i][j]-1).x=j;
					cars.get(gameTable[i][j]-1).y=gameTable.length-1-i;
					Car car=cars.get(gameTable[i][j]-1);
					//cars.get(gameTable[i][j]-1).setPosition(mapx+cars.get(gameTable[i][j]-1).x*mapWidth/gameTable[0].length,mapy+(gameTable.length-1-cars.get(gameTable[i][j]-1).y)*mapHeight/gameTable.length);
					car.setPosition(mapx+car.x*mapWidth/gameTable[0].length, mapy+mapHeight*car.y/gameTable.length);
					set[gameTable[i][j]-1]=true;
				}
//		for(Car car:cars)
//			car.setPosition(mapx+car.x*mapWidth/gameTable[0].length, mapy+mapy*car.y/gameTable.length);
		
	}

}
