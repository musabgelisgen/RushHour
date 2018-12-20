package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ASTAR {
	static int numberOfMovesNeeded;
	static int numberOfMovesDone;
	public static ArrayList<Node> Astar(Node begin,ArrayList<Car> cars){
		Comparator<Node> sorter = new Comparator<Node>() {
			@Override
			public int compare(Node arg0, Node arg1) {
				if(arg0.fCost > arg1.fCost) {
					return 1;
				}
				if(arg0.fCost < arg1.fCost) {
					return -1;
				}
				return 0;
			}
		};
		ArrayList<Node> path=new ArrayList<Node>();
		ArrayList<Node> openset=new ArrayList<Node>();
		ArrayList<Node> closedset=new ArrayList<Node>();
		Node current;
		openset.add(begin);
		int ii=0;
		while(openset.size()>0){
			Collections.sort(openset, sorter);
			current=openset.get(0);
			int[][] table=current.copyTable(current.table);
			int target=current.targetCar();
			Car t=cars.get(target);
			int total=0; 
			for(int i=t.fake_x+t.width;i<current.table[0].length;i++)
				total+=current.table[current.table.length-1-t.fake_y][i];
			if(isFinished(target+1,current.table, t.width,t.y)){
				System.out.println("OVER");
				while(current.parent!=null){
					path.add(0, current);
					current=current.parent;
					
				}
				path.add(0, current);
				return path;
			}
			openset.remove(current);
			closedset.add(current);
			 
			current.createChilderen();
			
			for(Node n:current.nb){
				
				
				
				if(closedset.contains(n)){
					continue;
					
				}
				if(openset.contains(n)){
					Node tar=null;
					for(int i=0;i<openset.size();i++)
						if(openset.get(i).equals(n)){
							if(n.gCost<openset.get(i).gCost){
								/*openset.get(i).gCost=n.gCost;
								openset.get(i).hCost=n.hCost;
								openset.get(i).parent=current;
								openset.get(i).lastX=n.lastX;
								openset.get(i).lastY=n.lastY;
								openset.get(i).nb=null;*/
								openset.remove(i);
								openset.add(n);
								//openset.get(i).moved=n.moved;
							}
						}
					
				}
				else{
					openset.add(n);
				}
			}
			
		}
		
		
		return path;
	}
	public static int getdistance(int[][] arr1,int[][] arr2){
		int i=0,j=0;
		int a=0,b=0;
		exit:
		for(;i<arr1.length;i++)
			for(;j<arr1[0].length;j++)
				if(arr1[i][j]!=arr2[i][j])
					break exit;
		int t=arr1[i][j];
		if(t==0)
			t=arr2[i][j];
		int[][] arr=(t==0)?arr1:arr2;
		exit1:
		for(a=arr.length-1;a>-1;a--)
			for(b=0;b<arr[0].length;b++)
				if(arr[a][b]==t)
					break exit1;
		return (Math.abs(a-i)+Math.abs(b-j));
		
	}
	public static int getDistToExit(int carnumber,int[][] arr){
		int i=arr.length-1,j=0;
		for(;i>-1;i--)
			for(;j<arr[0].length;j++)
				if(arr[i][j]==carnumber)
					return arr[0].length-j;
		return 0;
	}
	public static boolean isFinished(int num,int[][]arr,int width,int i){
			i=arr.length-i-1;
			for(int j=0;j<arr[0].length;j++){
				if(arr[i][j]==num){
					for(int k=j+width;k<arr[0].length;k++)
						if(arr[i][k]!=0)
							return false;
				}
			}
		return true;
	}
	

}
