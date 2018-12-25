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
	ArrayList<Car> cars;
	Card[] cards;
	Board[] boards;
	public MCTSNode(int[][] table,MCTSNode parent,int c_square,int playerNo,int card_index,ArrayList<Car> cars,Card[] cards,Board[] boards) {
		this.table=copyArray(table);
		children=new ArrayList<MCTSNode>();
		wins=0;
		simulations=0;
		allPossibleChildren=getAllPossibleChildren();
		this.parent=parent;
		this.c_square=c_square;
		this.playerNo=playerNo;
		this.card_index=card_index;
		this.cars=createCopy(cars);
		this.cards=copyCards(cards);
		this.boards=boards;
	}
	private int[][] copyArray(int[][] arr){
		int[][] copyArray=new int[arr.length][arr[0].length];
		for(int i=0;i<arr.length;i++)
			for(int j=0;j<arr[0].length;j++)
				copyArray[i][j]=arr[i][j];
		return copyArray;
	}
	private boolean isArraysSame(int[][] arr1,int[][] arr2){
		for(int i=0;i<arr1.length;i++)
			for(int j=0;j<arr1[0].length;j++)
				if(arr1[i][j]!=arr2[i][j])
					return false;
		return true;
	}
	private ArrayList<MCTSNode> getAllPossibleChildren() {
		// the creation algorithm creates all the possible states from this state by using
		// the card_index to see all the possible scenarios.
		// c_square is as same as the parent's c_square. It is constant for all MCTSNode objects.
		// playerNo of the children will be updated as 1-this.playerNo to change the player 0 to 1 or 1 to 0.
		ArrayList<Car> copyCars=createCopy(cars);
		Board[] copyBoards=createCopyBoards(boards);
		ArrayList<MCTSNode> possibleActions=new ArrayList<MCTSNode>();
		if(cards[card_index].slice)
			for(int k=0;k<copyCars.size();k++){
				for(int i=0;i<table.length;i++)
					for(int j=0;j<table[0].length;j++){
						int[][] table1=copyArray(table);
						if(copyCars.get(k).isMovePossible(i, j, table1, k)){
							copyCars.get(k).setPosition(j, j, table1, k, 0, 0, 0, 0, copyCars);
							MCTSNode temp=new MCTSNode(table1, this, c_square, 1-playerNo, (card_index+1)%cards.length,cars, cards,boards);
							possibleActions.add(temp);
						}
					}
		}
		else{
			if(cards[card_index].moveAmount>=1)
				for(int i=0;i<cars.size();i++)
					for(int[] arr:cars.get(i).getPossibleActions(table, i, 1)){
						int[][] table1=copyArray(table);
						copyCars.get(i).setPosition(arr[0], arr[1], table1, i, 0, 0, 0, 0, copyCars);
						MCTSNode temp=new MCTSNode(table1, this, c_square, 1-playerNo, (card_index+1)%cards.length,cars, cards,boards);
						possibleActions.add(temp);
					}
			
			if(cards[card_index].moveAmount>=2){
				for(int i=0;i<cars.size();i++)
					for(MCTSNode node:possibleActions){
						int[][] table1=node.table;
						for(int[] arr:cars.get(i).getPossibleActions(table1, i, 1)){
							int[][] table2=copyArray(table1);
							copyCars.get(i).setPosition(arr[0], arr[1], table2, i, 0, 0, 0, 0, copyCars);
							if(isArraysSame(table1, table2))
								continue;
							MCTSNode temp=new MCTSNode(table2, this, c_square, 1-playerNo, (card_index+1)%cards.length,cars, cards,boards);
							possibleActions.add(temp);
					}
				}
				
			}
			
			if(cards[card_index].moveAmount>=3){
				for(int i=0;i<cars.size();i++)
					for(MCTSNode node:possibleActions){
						int[][] table2=node.table;
						for(int[] arr:cars.get(i).getPossibleActions(table2, i, 1)){
							int[][] table3=copyArray(table2);
							copyCars.get(i).setPosition(arr[0], arr[1], table3, i, 0, 0, 0, 0, copyCars);
							if(isArraysSame(table3, table2))
								continue;
							MCTSNode temp=new MCTSNode(table3, this, c_square, 1-playerNo, (card_index+1)%cards.length,cars, cards,boards);
							possibleActions.add(temp);
					}
				}
			}
			
		}
		ArrayList<MCTSNode> newPossibleActions=new ArrayList<MCTSNode>();
	
		if(cards[card_index].shift){
			for(MCTSNode node:possibleActions){
				MCTSNode newNode=copy(node);
				for(int i=0;i<node.table.length;i++)
					for(Board b:node.boards)
						if(b.dragBoard(b.x, i, newNode.table, 0, 0, 0, 0, copyCars))
							newPossibleActions.add(copy(newNode));
			}
		
		}
		
		
		
		
		
		
		// when children are created.
		// MCTSNode objects will be inserted to the allPossibleChildren list and child objects' parent set to this
				for(MCTSNode node:cards[card_index].shift?newPossibleActions:possibleActions){
					node.parent=this;
					allPossibleChildren.add(node);
				}
					
		
		return possibleActions;
	}
	private Board[] createCopyBoards(Board[] boards2) {
		// TODO Auto-generated method stub
		
		Board[] boards=new Board[boards2.length];
		for(int i=0;i<boards.length;i++)
			boards[i]=new Board(boards2[i].x, boards2[i].y, boards2[i].width, boards2[i].height, boards2[i].leftX, boards2[i].leftY, boards2[i].id);
		return boards;
	}
	public boolean isLoss(){
		boolean loss=(allPossibleChildren.size()+children.size()==0);
		return loss;
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
		if(numOfRemainingChildren==0)
			return null;
		int random=MathUtils.random(numOfRemainingChildren-1);
		children.add(allPossibleChildren.get(random));
		MCTSNode mnode=allPossibleChildren.get(random);
		allPossibleChildren.remove(random);
		return mnode;
		
		
	}
	public MCTSNode rollOut(){	//return which player wins
		MCTSNode temp=expand();
		if(temp==null)
			return this ;
		while(!temp.isLoss())
			temp=temp.expand();
		return temp;
		
	}
	public void backpropagation(){
		wins++;
		simulations++;
			if(this.parent!=null){
				this.parent.simulations++;
				if(this.parent.parent!=null)
					this.parent.parent.backpropagation();
			}
		
	}
	
	public double getUCT(){
		if(simulations>0)
			return (double)wins/simulations+Math.sqrt(c_square*Math.log(parent.simulations)/simulations);
		return 0;
	}
	public MCTSNode selectPlay(){
		int index=0;
		double max=0;
		for(int i=1;i<children.size();i++)
			if(children.get(i).getUCT()>max){
				max=children.get(i).getUCT();
				index=i;
			}
		if(children.size()>0)
			return children.get(index);
		return expand();
	}
	public boolean equals(Object o) { 
		  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof MCTSNode)) { 
            return false; 
        } 
          
        // typecast o to Node so that we can compare data members  
        MCTSNode other = (MCTSNode) o; 
          
        // Compare the data members and return accordingly  
        for(int i=0;i<other.table.length;i++)
			for(int j=0;j<other.table[0].length;j++)
				if(other.table[i][j]!=table[i][j])
					return false;
		return true;
        
    }
	public ArrayList<Car> createCopy(ArrayList<Car> cars){
		ArrayList<Car> copy=new ArrayList<Car>();
		for(Car c:cars){
			Car c_=new Car(c.direction, c.x, c.y, c.width, c.height, c.id);
			copy.add(c_);
		}
		return copy;
	}
	private Card[] copyCards(Card[] cards){
		Card[] c=new Card[cards.length];
		for(int i=0;i<c.length;i++)
			c[i]=new Card(cards[i].moveAmount,cards[i].shift,cards[i].slice);
		return c;
	}
	private MCTSNode copy(MCTSNode n){
		MCTSNode node=new MCTSNode(copyArray(n.table), n.parent, n.c_square, n.playerNo, n.card_index, createCopy(n.cars), copyCards(n.cards), createCopyBoards(n.boards));
		return node;
	}
	
	
	
	
}
