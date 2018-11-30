package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	int[][] table;
	Node parent;
	ArrayList<Node> nb;
	ArrayList<Car> cars;
	int fCost;
	int gCost;
	int hCost;
	public Node(){
		parent=null;
		fCost=0;
		gCost=0;
	    nb=new ArrayList<Node>();
	}
	public Node(int[][] table,ArrayList<Car> cars,Node parent,int gCost,int hCost){
		this.parent=parent;
		this.table=table;
		this.gCost=gCost;
		this.hCost=hCost;
		fCost=gCost+hCost;
		this.cars=cars;
		nb=new ArrayList<Node>();
	}
	public String toString() {
		String s="";
    	for(int i=0;i<table.length;i++){
    		s+=Arrays.toString(table[i]);
    		s+="\n";
    	}
    	return s;
}
	public int getHCost(){
		int cost=0;
		if(cars.get(targetCar(cars)).direction==0){
			for(int i=cars.get(targetCar(cars)).x+cars.get(targetCar(cars)).width;i<=table[0].length;i++){
				if(table[table.length-1-cars.get(targetCar(cars)).y][i]!=0)
					cost++;
			}
			return cost;
		}
		return 0;
	}
	public int targetCar(ArrayList<Car> cars){
		int i=0;
		for(;i<cars.size();i++){
			if(cars.get(i).id==1)
				return i;
			
		}
		return 0;
	}
	public void createChilderen(){
		for(int i=0;i<cars.size();i++){
			int[][] table=copyTable();
			if(cars.get(i).direction==0){
				for(int j=0;j<table[0].length;j++)
					if(cars.get(i).setPosition(j, cars.get(j).y, table, i, Levels.w1, Levels.h1, Levels.a, Levels.b)){
						Node n=new Node();
						n.cars=cars;
						n.parent=this;
						n.nb=new ArrayList<Node>();
						n.gCost=this.gCost+cars.get(i).lastMoveAmountX*(1-cars.get(i).direction)+cars.get(i).lastMoveAmountY*(cars.get(i).direction);
						n.table=table;
						n.hCost=n.getHCost();
						n.fCost=n.gCost+n.hCost;
						nb.add(n);
					}
				
			}
			else{
				for(int j=0;j<table.length;j++)
					if(cars.get(i).setPosition(cars.get(j).x, j, table, i, Levels.w1, Levels.h1, Levels.a, Levels.b)){
						Node n=new Node();
						n.cars=cars;
						n.parent=this;
						n.gCost=this.gCost+cars.get(i).lastMoveAmountX*(1-cars.get(i).direction)+cars.get(i).lastMoveAmountY*(cars.get(i).direction);
						n.table=table;
						n.hCost=n.getHCost();
						n.fCost=n.gCost+n.hCost;
						nb.add(n);
					}
			}
		}
	}
	public int[][] copyTable(){
		int[][] copy=new int[this.table.length][this.table[0].length];
		for(int i=0;i<copy.length;i++)
			for(int j=0;j<copy[0].length;j++)
				copy[i][j]=this.table[i][j];
		return copy;
	}
}



