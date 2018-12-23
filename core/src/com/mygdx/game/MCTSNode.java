package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.math.MathUtils;

public class MCTSNode {
	int[][] table;
	int wins,simulations;
	ArrayList<MCTSNode> children;
	ArrayList<MCTSNode> allPossibleChildren;
	MCTSNode parent;
	int c_square;
	int playerNo;
	int card_index;
	public MCTSNode(int[][] table,MCTSNode parent,int c_square,int playerNo,int card_index) {
		this.table=table;
		children=new ArrayList<MCTSNode>();
		wins=0;
		simulations=0;
		allPossibleChildren=getAllPossibleChildren();
		this.parent=parent;
		this.c_square=c_square;
		this.playerNo=playerNo;
		this.card_index=card_index;
	}
	private ArrayList<MCTSNode> getAllPossibleChildren() {
		//kulu is going to implement the method.
		// the creation algorithm creates all the possible states from this state by using
		// the card_index to see all the possible scenarios.
		// card_index of children will be incremented and should be taken module of the cards length
		// when children are created.
		// MCTSNode objects will be inserted to the allPossibleChildren list.
		// child objects' parent set to this
		// c_square is as same as the parent's c_square. It is constant for all MCTSNode objects.
		// playerNo of the children will be updated as 1-this.playerNo to change the player 0 to 1 or 1 to 0.
		return null;
	}
	
	public MCTSNode selection(){
		if(allPossibleChildren.size()>0)
			return null;
		int index=0;
		for(int i=1;i<children.size();i++)
			if(children.get(i).getUCT()>children.get(index).getUCT())
				index=i;
		return children.get(index);
	}
	
	public MCTSNode expand(){
		int numOfRemainingChildren=allPossibleChildren.size();
		int random=MathUtils.random(numOfRemainingChildren-1);
		children.add(allPossibleChildren.get(random));
		MCTSNode mnode=allPossibleChildren.get(random);
		allPossibleChildren.remove(random);
		return mnode;
		
		
	}
	public MCTSNode rollOut(){
		return expand();
	}
	
	public double getUCT(){
		return (double)wins/simulations+Math.sqrt(c_square*Math.log(parent.simulations)/simulations);
	}
	
	
	
	
}
