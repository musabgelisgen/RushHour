package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import data.SerializationUtil;
import data.StarScore;

public class SerializationTest {

	public static void main(String[] args) {
		StarScore starScore = new StarScore(); 
		starScore.addScore(1);
		starScore.addScore(2);
		
		String fileFolder = ""; 
		String scoreDir = ""; //location for StarScore object in file system
		String os = System.getProperty("os.name").toUpperCase();
		
		if (os.contains("WIN")) {
			fileFolder = System.getenv("APPDATA") + "\\" + "RushHour";
			scoreDir =  fileFolder + "\\scores.ser";
		}

		else if (os.contains("MAC")) {
			fileFolder = System.getProperty("user.home") + "/Library/Application Support" + "/RushHour";
			scoreDir = fileFolder + "/scores.ser";
		}

		File directory = new File(fileFolder);
		if (directory.exists() == false) {
			directory.mkdir();
		}
		

		try { //serialization here
			SerializationUtil.serialize(starScore, scoreDir);
		} catch (IOException e1) {
			System.out.println("Score Serialization Failed");
		}
		

		StarScore starScore2 = null;
		try { //deserialization here
			starScore2 = (StarScore) SerializationUtil.deserialize(scoreDir);	
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> scores = starScore2.getScores();
		System.out.println(scores); //should print 1,2
	}

}
