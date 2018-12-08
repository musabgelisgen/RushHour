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
			int[][] table=current.copyTable();
			int target=current.targetCar();
			Car t=cars.get(target);
			int total=0;
			for(int i=t.x+t.width;i<current.table[0].length;i++)
				total+=current.table[current.table.length-1-t.y][i];
			if(total==0){
				while(current.parent!=null){
					path.add(0, current);
					numberOfMovesNeeded+=Math.abs(current.lastX-current.parent.lastX)+Math.abs(current.lastY-current.parent.lastY);
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
								openset.get(i).gCost=n.gCost;
								openset.get(i).parent=current;
								openset.get(i).lastX=n.lastX;
								openset.get(i).lastY=n.lastY;
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
	

}
