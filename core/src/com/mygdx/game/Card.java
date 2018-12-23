package com.mygdx.game;

public class Card extends BaseActor {
	int moveAmount;	//it shows how many units we can move totally
	boolean shift;	//it shows whether we can shift the board
	boolean slice;	//it shows whether we can slice the car however we want
	public Card(int moveAmount,boolean shift,boolean slice) {
		this.moveAmount=moveAmount;
		this.shift=shift;
		this.slice=slice;
	}

}
