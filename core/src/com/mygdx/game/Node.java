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
	Car moved;
	int lastX,lastY,targetX,targetY;
	int carnumber;
	public Node(){
		moved=null;
		parent=null;
		fCost=0;
		gCost=0;
		hCost=0;
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
		int cost=(table[0].length-(cars.get(targetCar()).fake_x));
		if(cars.get(targetCar()).direction==0){
			for(int i=cars.get(targetCar()).fake_x+cars.get(targetCar()).width;i<table[0].length;i++){
				if(table[table.length-1-cars.get(targetCar()).fake_y][i]!=0)
					cost++;
			}
			return cost;
		}
		return 0;
	}
	public int targetCar(){
		int i=0;
		for(;i<cars.size();i++){
			if(cars.get(i).id==1)
				return i;
			
		}
		return 0;
	}
	public void createChilderen(){
		for(int i=0;i<cars.size();i++){
			for(int k=0;k<table.length+1;k++)
				for(int j=0;j<table[0].length+1;j++){
					int[][] table=copyTable();
					if(cars.get(i).fakeSetPosition(j, k, table, i, Levels.w1, Levels.h1, Levels.a, Levels.b,false)){
						Node n=new Node();
						n.cars=cars;
						n.parent=this;
						n.gCost=this.gCost+Math.abs(cars.get(i).lastMoveAmountX)*(1-cars.get(i).direction)+Math.abs(cars.get(i).lastMoveAmountY)*(cars.get(i).direction);
						//improve n.gCost 
						n.table=table;
						n.hCost=n.getHCost();
						n.fCost=n.gCost+n.hCost;
						n.moved=cars.get(i);
						n.lastX=cars.get(i).fake_lastMoveAmountX;
						n.lastY=cars.get(i).fake_lastMoveAmountY;
						n.carnumber=i;
						n.targetX=j;
						n.targetY=k;
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
	
	public boolean equals(Object o) { 
		  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Node)) { 
            return false; 
        } 
          
        // typecast o to Node so that we can compare data members  
        Node other = (Node) o; 
          
        // Compare the data members and return accordingly  
        for(int i=0;i<other.table.length;i++)
			for(int j=0;j<other.table[0].length;j++)
				if(other.table[i][j]!=table[i][j])
					return false;
		return true;
        
    }
	
}



