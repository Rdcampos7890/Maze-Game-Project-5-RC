package Model;

/*
 * Minotauir class, creates a minotaur for the maze.
 */
public class Minotaur {

	private int _row, _col;
	private boolean _isAlive;
	private boolean _chase;
	
	/*
	 * Creates a minotaur object and initializes its variables.
	 */
	public Minotaur(int row, int col) {
		_row = row;
		_col = col;
		_isAlive = true;
		_chase = false;
	}
	
	/*
	 * Returns the current row in which the minotaur is.
	 */
	public int getRow() { return _row; }
	
	/*
	 * Returns the current column in which the minotaur is.
	 */
	public int getCol() { return _col; }
	
	/*
	 * Sets the row and column of the minotaur to the ones given in the parameter.
	 */
	public void setPosition(int r, int c) { _row = r; _col = c;}
	
	/*
	 * Returns true if the minotaur is alive.
	 */
	public boolean isAlive() { return _isAlive; }
	
	/*
	 * Kills the minotaur.
	 */
	public void kill() { _isAlive = false; }
	
	/*
	 * Revives the minotaur.
	 */
	public void revive() { _isAlive = true; }	
	
	/*
	 * Returns true if the minotaur is chasing the player.
	 */
	public boolean isChase() { return _chase; }
	
	/*
	 * Makes the minotaur start chasing the player.
	 */
	public void startChase() { _chase = true; }
	
	/*
	 * Makes the minotaur stop chasing the player.
	 */
	public void stopChase() { _chase = false; }
}
