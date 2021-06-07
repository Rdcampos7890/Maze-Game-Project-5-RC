package Model;

/*
 * Cyclops class, creates a cyclops for the maze.
 */
public class Cyclops {

	private int _row, _col;
	private boolean _isAlive;
	private boolean _chase;
	
	/*
	 * Creates a cyclops and initializes all its variables.
	 */
	public Cyclops(int row, int col) {
		_row = row;
		_col = col;
		_isAlive = true;
		_chase = false;
	}
	
	/*
	 * Returns the row in which the cyvlops is.
	 */
	public int getRow() { return _row; }
	
	/*
	 * Returns the column in which the cyclops is.
	 */
	public int getCol() { return _col; }
	
	/*
	 * Sets the row and column of the cyclops to the given row and column.
	 */
	public void setPosition(int r, int c) { _row = r; _col = c;}
	
	/*
	 * Returns true if the cyclops is still alive.
	 */
	public boolean isAlive() { return _isAlive; }
	
	/*
	 * Kills the cyclops.
	 */
	public void kill() { _isAlive = false; }
	
	/*
	 * Revives the cyclops.
	 */
	public void revive() { _isAlive = true; }	
	
	/*
	 * Returns true if the cyclops is chasing the player, false otherwise.
	 */
	public boolean isChase() { return _chase; }
	
	/*
	 * Makes the cyclops start chasing the player.
	 */
	public void startChase() { _chase = true; }
	
	/*
	 * Makes the cyclops stop chasing the player.
	 */
	public void stopChase() { _chase = false; }
}
