package com.mygdx.game;

import java.util.ArrayList;

public class Trainer {
	int[][] begin;
	Card[] cards;
	MCTSNode root,player;
	Board[] boards;
	public Trainer(int[][] begin,Card[] cards,ArrayList<Car> cars,Board[] boards) {
		this.begin=begin;
		this.cards=cards;
		this.boards=boards;
		root=new MCTSNode(begin, null, 2, 0,0,cars,cards,boards);
		
		
	}
	public void train(){
		int targetNumOfEpisodes=1000;
		for(int i=0;i<targetNumOfEpisodes;i++){
			MCTSNode temp=root.selection();
			while(temp!=null)
				if(temp.allPossibleChildren.size()==0)
					temp=temp.selection();
				else {
					temp=temp.expand();
					break;
				}
			temp=temp.rollOut();
			temp.backpropagation();
			
		}
		player=root;
	}
	public MCTSNode play(MCTSNode action){
		MCTSNode node=null;
		for(MCTSNode n:player.children)
			if(n.equals(action)){
				node=n;
				break;
			}
		MCTSNode answer=node.selectPlay();
		player=answer;
		return answer;
	}

}
