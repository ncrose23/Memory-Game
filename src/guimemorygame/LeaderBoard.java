package guimemorygame;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class LeaderBoard {
	//array list variable to hold file lines
	private ArrayList<String> currentLb;
	//the current LeaderBoard.txt file
	private File lb = new File("LeaderBoard.txt");
	//filewriter to overwrite board when it's update
	public LeaderBoard() {
		//Storing each line in a string array
		currentLb = new ArrayList<String>();
		
		//added to fix error where sc wouldn't be initialized if the file wasn't found
		Scanner sc = null;
		
		try {
			sc = new Scanner(lb);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(sc.hasNextLine()) {
			currentLb.add(sc.nextLine());
		}
	}
	
	
	
	//method to update the leader board with the current score and name of the user
	public void updateLeaderBoard(int turnCount, String curDif, String name) {
		//checking to see where the high score should be placed based on the current difficulty mode 
		int difNum = 0;
		if (curDif.equals("EASY MODE")) {
			difNum = 1;
		} else if(curDif.equals("MEDIUM MODE")) {
			difNum = 2;
		} else if (curDif.equals("HARD MODE")){
			difNum=3;
		}
		int currentBest = -99999;
		for (int i = 0; i<=currentLb.get(difNum).length(); i++) {
			if (isNumeric(currentLb.get(difNum).substring(i))) {
				currentBest = Integer.parseInt(currentLb.get(difNum).substring(i));
				i=9999;
			}
			
			//if (isNumeric(currentLb.get(difNum).substring(i,i+1))) 
			//	currentBest = Integer.parseInt(currentLb.get(difNum).substring(i,i+1));
		}
		if (turnCount<currentBest||currentBest==-99999) {
			currentLb.set(difNum,curDif+": "+name+", "+turnCount);
		}
		//overwrite file method i learned from codespeedy.com
		//trying to overwrite leaderboard
		Scanner leaderBoard = null;
		try {
			leaderBoard = new Scanner(lb);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String content = "";
        for (String j: currentLb) {
        	content+=j+"\n";
        }
        //System.out.println(content);
        leaderBoard.close();
        try {
            FileWriter writer = new FileWriter(lb, false);
            writer.write(content);
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		
		
	//method to check if something is numeric
		private static boolean isNumeric(String str) {
			if (str == null) {
			    return false;
			 }
			 try {
			    int i = Integer.parseInt(str);
			 } catch (NumberFormatException nfe) {
			    return false;
			 }
			   	return true;
		}
	
	
}