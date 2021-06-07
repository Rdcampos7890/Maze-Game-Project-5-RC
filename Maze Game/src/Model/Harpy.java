package Model;

/*
 * Harpy class, creates a harpy for the maze.
 */
public class Harpy {
	
	private int _row, _col;
	private boolean _isAlive;
	private boolean _chase;
	
	/*
	 * Creates a harpy object and initializes the variables of the object.
	 */
	public Harpy(int row, int col) {
		_row = row;
		_col = col;
		_isAlive = true;
		_chase = false;
	}
	
	/*
	 * Return the row of the harpy.
	 */
	public int getRow() { return _row; }
	
	/*
	 * Returns the column of the harpy.
	 */
	public int getCol() { return _col; }
	
	/*
	 * Sets the position of the harpy to the given row and column. 
	 */
	public void setPosition(int r, int c) { _row = r; _col = c;}
	
	/*
	 * Returns true if the  harpy is alive.
	 */
	public boolean isAlive() { return _isAlive; }
	
	/*
	 * Kills the harpy.
	 */
	public void kill() { _isAlive = false; }
	
	/*
	 * Revives the harpy.
	 */
	public void revive() { _isAlive = true; }	
	
	/*
	 * Returns true if  the harpy is chasing the player.
	 */
	public boolean isChase() { return _chase; }
	
	/*
	 * Makes the  harpy start chasing the player.
	 */
	public void startChase() { _chase = true; }
	
	/*
	 * Makes the harpy stop chasing the player.
	 */
	public void stopChase() { _chase = false; }
}
