package 
Main;



//Grammar 

//Null Pointer Exception

//Credit Message and Grading Message and Warning Message 

//Clean

//Add More Mazes?

import Controller.JOP;
import Model.World;
import Sound.SoundEffects;

/*
 * MazeGame class, runs main method.
 */
public class MazeGame {
	
	public static SoundEffects sound = new SoundEffects();
	
	/*
	 * Main method, display the instructions on how to play the game and the objectives
	 * as well as  all the information needed to understand the game, apart from starting the music of the game. 
	 * It also starts the game by calling on the constructor of the World class. 
	 */
	public static void main(String[] args) throws Exception {
		
		JOP.msg("(Only for graders) \nAdded Stuff: \nThere is a point system based on getting the horns of the minotaur, the head of the harpy, the golden dragon egg, the eye of the cyclops, and/or the secret artifact. \nThere is the addition of a cyclops and a harpy. \nThere is a patrol sequence for all monsters plus a chase state in which you get chase if you are in range. \nThere are 3 maps which are keyed to the numbers 1, 2, or 3. \nThere are hidden locations where the player desapiers from the chase range (doesn't mean that the monsters won't check randomly) \nThere is a key and a secret key, the key is for the door of the exit and the secret key is for the door to the artifact. \nThere is a stand option by pressing q if the player is in a hidden area. \nThe vision for the player is limited and will increase by one if the player gets the item (torch) or to complete view if they get the item (map). \nThere is the chocie for gaming or terror music with the addition of sound effects for different scenes annd images for the corersponding scenes. \nThere is a sword item that allows the killing of the mosnters, kiling them will drops their corresponding throphy for points. \nAt any time the plaayer can write (exit) to exit the game. \nSteps taken during the map and points earn together with a total score (points - steps) will show at the end of each run of a map. \nFinally, there are also commands for testing whcih are basically calling (get) and teh name of teh object you want as if it was a method. \nList of commands: getSword();, getMap();, getTorch();, win();, and skip(); \nThey work as their name suggest they do. \n Recommended map to test the game are maps 1 and 3, 1 for simplificity of the game working and 3 to see all adds on.");
		
	    JOP.msg("<html><font face='Monospace' size='20' color='green'>The Labyrinth Of The Minotaur");
		JOP.msg("Movement (W A S D) or Press (Q) to stand (Only on hidden spots) \nPlayer (P) = You \nMinotaur (M) = Enemy \nCyclops (C) = Enemy \nHarpy (A) = Enemy \nExit (X) = Scape \nTorch (T) = More Vision \nMap (W) = Full Vision \nSword (S) = Sword To Kill Minotaur/Cyclops/Harpy \nGolden Dragon Egg (E) = Points \nKey (K) = Way to Unlock Exit Door \nDoor (D) = Block of Exit, A Key is Needed \nHidding Location (H) = Place to Hide (Does not mean enemies can't go by some and check them) \nGoal: Scape throught the exit \nChange map by entering (1, 2, or 3) at any time \nMap 1 is the only map that doesn't have limited vision \nYou will be able to see how many steps you took at the end of each round together with your points and you total score \nTo exit just write (exit) at any time");
		JOP.msg("Monster Explanation: \nMinotaur: \nPatrol movement of 2 squares pr turn. \nChase movement of 1 square per turn. \nChase range of 5 squares. \nDrops (Horn of the Minotaur) \nCyclops: \nPatrol and chase movement of 1 square per turn. \nChase range of 2 squares. \nDrops (Eye of Cyclops) \nHarpy: \nPatrol movement of 3 squares per trun. \nChase movement of 1 sqaure per turn. \nChase range of 3 squares. \nDrop (Head of Harpy)");
		JOP.msg("Warning: \nThere is blood and other stuff that could eventually scare a 5 year old,\n so if you get scare with things that would only scare a 5 years old please don't play. \nAlso, epilepsy warning, don' play if you got epilepsy.");
		
		
	    String s1 = JOP.in("Do you want gaming music (gaming) or terror music (terror). Different answer will display terror music.");
	    
	    if(s1 == null || s1.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
	    else if(s1.equalsIgnoreCase("gaming")) {
			sound.Run("Different Heaven - Nekozilla [NCS Release] (128 kbps) (online-audio-converter.com).wav");
	    }
		else {
			sound.Run("MELANCHOLIA Music Box _Sad, creepy song_ (128 kbps) (online-audio-converter.com).wav");
		}
	    
		String s = JOP.in("Pick you starting maze (1, 2, 3)");
		
		if(s.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
		
		if(!s.equals("1") && !s.equals("2") && !s.equals("3")) {
			s = "1";
		}
		
		new World(s);
	}
}
