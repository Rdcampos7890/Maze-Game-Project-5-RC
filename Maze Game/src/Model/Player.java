package Model;

/*
 * PLayeer class, creates a player that takes input from the user  to move.
 */
public class Player {

	private int _row, _col;
	private boolean _isAlive;
	private boolean _sword;
	public static int _steps;
	private boolean _torch;
	private boolean _map;
	private boolean _hidden;
	private boolean _killMinotaur;
	private boolean _killCyclops;
	private boolean _killHarpy;
	private boolean _egg;
	private boolean _Chead;
	private boolean _Mhead;
	private boolean _Skey;
	private boolean _key;
	public static int _points;
	private boolean _artifact;
	private boolean _Ahead;
	
	/*
	 * Constructs a player object and initializes all variables for a player.
	 */
	public Player(int row, int col) {
		_artifact = false;
		_row = row;
		_col = col;
		_isAlive = true;
		_sword = false;
		_steps = 0;
		_points = 0;
		_torch = false;
		_map = false;
		_killMinotaur = false;
		_killCyclops = false;
		_egg = false;
		_Chead = false;
		_Mhead = false;
		_Skey = false;
		_key = false;
		_Ahead = false;
		_killHarpy = false;
	}
	
	/*
	 * Return the current row of the player.
	 */
	public int getRow() { return _row; }
	
	/*
	 * Return the current column of the player.
	 */
	public int getCol() { return _col; }
	
	/*
	 * Sets the row and column of the player to the ones of the parameter.
	 */
	public void setPosition(int r, int c) { _row = r; _col = c; }
	
	/*
	 * Return true if player is alive.
	 */
	public boolean isAlive() { return _isAlive; }
	
	/*
	 * Kill player.
	 */
	public void kill() { _isAlive = false; }
	
	/*
	 * Revive player.
	 */
	public void revive() { _isAlive = true; }
	
	/*
	 * Returns true if player has a sword.
	 */
	public boolean hasSword() { return _sword; }
	
	/*
	 * Gives player a sword.
	 */
	public void giveSword() { _sword = true; }
	
	/*
	 * Removes the sword from the player.
	 */
	public void takeSowrd() { _sword = false; }
	
	/*
	 * Gives the player a torch.
	 */
	public void giveTorch() { _torch = true; }
	
	/*
	 * Gives the player a map.
	 */
	public void giveMap() { _map = true; }
	
	/*
	 * Return true if the player has a torch.
	 */
	public boolean hasTorch() { return _torch; }
	
	/*
	 * Return true if the player has a map.
	 */
	public boolean hasMap() { return _map; }
	
	/*
	 * Removes the torch form the player.
	 */
	public void takeTorch() { _torch = false; }
	
	/*
	 * Removes the map from the player.
	 */
	public void takeMap() { _map = false; }

	/*
	 * Return true if the player is hidden.
	 */
	public boolean hidden() { return _hidden; }
	
	/*
	 * Hides player.
	 */
	public void hide() { _hidden = true; }
	
	/*
	 * Reveales player.
	 */
	public void unhide() { _hidden = false; }
	
	/*
	 * States that the player had killed the minotaur.
	 */
	public void killedMinotaur() { _killMinotaur = true; }
	
	/*
	 * States that the player had killed the cyclops.
	 */
	public void killedCyclops() { _killCyclops = true; }
	
	/*
	 * Return true if the player has killed the cyclops.
	 */
	public boolean hasKilledCyclops() { return _killCyclops; }
	
	/*
	 * Return true if the player has killed the minotaur.
	 */
	public boolean hasKilledMinotaur() { return _killMinotaur; }
	
	/*
	 * Return true if the player has a killed the harpy.
	 */
	public boolean hasKilledHarpy() { return _killHarpy; }
	
	/*
	 * States that the player has killed the harpy.
	 */
	public void killedHarpy() { _killHarpy = true; }
	
	/*
	 * Return true if the player has an egg.
	 */
	public boolean hasEgg() { return _egg; }
	
	/*
	 * Gives an egg to the player.
	 */
	public void giveEgg() { _egg = true; }
	
	/*
	 * Removes the egg from the player.
	 */
	public void takeEgg() { _egg = false; }
	
	/*
	 * Return true if the player has a cyclops head.
	 */
	public boolean hasChead() { return _Chead; }
	
	/*
	 * Gives the cyclops head to the player.
	 */
	public void giveChead() { _Chead = true; }
	
	/*
	 * Removes the cyclops head from the player.
	 */
	public void takeChead() { _Chead = false; }
	
	/*
	 * Return true if the player has a minotaur head.
	 */
	public boolean hasMhead() { return _Mhead; }
	
	/*
	 * Gives the minotaur head to the player.
	 */
	public void giveMhead() { _Mhead = true; }
	
	/*
	 * Removes the minotaur head from the player.
	 */
	public void takeMhead() { _Mhead = false; }
	
	/*
	 * Return true if the player has a harpy head.
	 */
	public boolean hasAhead() { return _Ahead; }
	
	/*
	 * Gives harpy head to the player.
	 */
	public void giveAhead() { _Ahead = true; }
	
	/*
	 * Removes harpy head from player.
	 */
	public void takeAhead() { _Ahead = false; }
	
	/*
	 * Return true if the player has the key.
	 */
	public boolean hasKey() { return _key; }
	
	/*
	 * Gives key to the player.
	 */
	public void giveKey() { _key = true; }
	
	/*
	 * Removes key from the player.
	 */
	public void takeKey() { _key = false; }
	
	/*
	 * Return true if the player has the secret key.
	 */
	public boolean hasSKey() { return _Skey; }
	
	/*
	 * Gives secret key to the player.
	 */
	public void giveSKey() { _Skey = true; }
	
	/*
	 * Removes secret key from the player.
	 */
	public void takeSKey() { _Skey = false; }
	
	/*
	 * Returns true if the player had the artifact.
	 */
	public boolean hasArtifact() { return _artifact; }
	
	/*
	 * Gives artifact to the player.
	 */
	public  void giveArtifact() { _artifact = true; }
	
	/*
	 * Removes the artifact from the player.
	 */
	public void takeArtifact() { _artifact = false; }

	/*
	 * Return true if the player has killed the harpy.
	 */
	public boolean haskillHarpy() { return _killHarpy; }
	
}
