package com.mygdx.game;

import java.util.Arrays;

public class Board extends BaseActor {
	boolean empty=false;
	int width,height;
	int[][] table;
	int x,y;	//arrayda asol üst köþesinin asýl array'deki indexleri
	boolean pressed;
	int firstTouchX,firstTouchY,lastX,lastY;
	int leftX,leftY;
	public Board(int x,int y,int width,int height,int leftX,int leftY) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		table=new int[height][width];
		pressed=false;
		this.leftX=leftX;
		this.leftY=leftY;
	}
	
	public void updateBoard(int[][] board){
		boolean emp=true;
		for(int i=y;i<y+height;i++)
			for(int j=x;j<x+width;j++){
				
				table[i-y][j-x]=board[i][j];
				if(board[i][j]!=0)
					emp=false;
			}
		empty=emp;
	}
	public void updatePosition(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void dragBoard(int a,int b,int[][] gameTable,int mapx,int mapy,int mapWidth,int mapHeight){
		if(b<0)
			return;
		if(b>2*gameTable.length/3)
			return;
		updateBoard(gameTable);
		for(int i=0;i<table.length;i++)
			System.out.println(Arrays.toString(table[i]));
		System.out.println(empty);
		System.out.println("\n\n");
		if(!empty)
			return;
		if(y==b)
			return;
		for(int i=y;i<y+height;i++)
			for(int j=x;j<x+width;j++)
				gameTable[i][j]=-1;
		for(int i=b;i<b+height;i++)
			for(int j=a;j<a+width;j++){
				table[i-b][j-a]=0;
				gameTable[i][j]=0;
			}
		
		
		updatePosition(a, b);
		leftY=mapy+2*mapHeight/3-b*mapHeight/gameTable.length;
		setPosition(leftX,leftY);
		System.out.println("deðiþti");
		
		
	}

}
