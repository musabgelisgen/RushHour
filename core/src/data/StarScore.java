package com.mygdx.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StarScore implements Serializable {
	
	private static final long serialVersionUID = -6029482710483724958L;
	private ArrayList<Integer> scores;
	
	public StarScore() {
		scores = new ArrayList<>();
	}
	
	public void addScore (int score) {
		scores.add(score);
	}
	
	public void updateScore (int level, int score) {
		scores.set(level, score);
	}
	
	public ArrayList<Integer> getScores(){
		return scores;
	}
}
