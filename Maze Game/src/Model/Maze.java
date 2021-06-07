package Model;

/*
 * Maze class, responsible for storing default mazes and all the key locations and coordinates inside these mazes.
 */
public class Maze {
	
	private boolean _view;
	private boolean _view2;
	private int[] origin = {-1, -1};
	private int[][] origins = {{-1, -1}, {-1, -1}};
	private boolean [][] _curMaze = 
			
		{
			{false, false, false, false, false,  false, false, false, false,  true,  false},
			{false, true,  true,  true,  false,  true,  true,  true,  false,  true,  false},
			{false, true,  false, true,  true,   true,  false, true,  true,   true,  false},
			{false, true,  true,  true,  false,  true,  true,  true,  false,  true,  false},
			{false, true,  false, true,  true,   true,  false, true,  true,   true,  false},
			{false, true,  true,  true,  false,  true,  true,  true,  false,  true,  false},
			{false, true,  false, true,  true,   true,  false, true,  true,   true,  false},
			{false, true,  true,  true,  false,  true,  true,  true,  false,  true,  false},
			{false, true,  false, true,  true,   true,  false, true,  true,   true,  false},
			{false, true,  true,  true,  false,  true,  true,  true,  false,  true,  false},
			{false, true,  false, false, false,  false, false, false, false,  false, false}
		};
	private int[] _playerStartingLocation = {1, 1};
	private int[] _minotaurStartingLocation = {1, 1};
	private int[] _secretDoor = {1,1};
	private int[] _door = {1,1};
	private int[] _egg = {1,1};
	private int[] _key = {1,1};
	private int[] _Skey = {1,1};
	private int[] _artifact = {1,1};
	private int[] _harpyStartingLocation = {1,1};
	private int[] _swordLocation = {1, 1};
	private int[] _exitLocation = {1, 1};
	private int[] _torchLocation = {1, 1};
	private int[] _mapLocation = {1, 1};
	private int[][] _hiddenLocation = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
	private int[] _cyclopsStartingLocation = {1, 1};
	private int[][] _patrolCyclopsLocation = {{1, 1},{1, 1},{1, 1},{1, 1}};
	private int[][] _patrolMinotaurLocation = {{1, 1},{1, 1},{1, 1},{1, 1}};
	private int[][] _patrolHarpyLocation = {{1, 1},{1, 1},{1, 1},{1, 1}};
	private boolean [][][] _mazeList = 
		{
				{
					{false,false,false,false,false,false,false,false,false,false,true,false},
					{false,true,true,true,false,true,true,true,false,true,true,false},
					{false,true,false,true,true,true,false,true,true,true,false,false},
					{false,true,true,true,false,true,true,true,false,true,true,false},
					{false,true,false,true,true,true,false,true,true,true,false,false},
					{false,true,true,true,false,true,true,true,false,true,true,false},
					{false,true,false,true,true,true,false,true,true,true,false,false},
					{false,true,true,true,false,true,true,true,false,true,true,false},
					{false,true,false,true,true,true,false,true,true,true,false,false},
					{false,true,true,true,false,true,true,true,false,true,true,false},
					{false,true,false,true,true,true,false,true,true,true,false,false},
					{false,true,false,false,false,false,false,false,false,false,false,false},
					
				},
				{
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false},
					{false,false,true,true,true,true,false,true,false,true,true,false,false,true,true,true,true,true,false,true,false,false},
					{false,false,true,true,true,true,false,true,false,true,true,false,false,true,true,true,true,true,false,false,false,false},
					{false,false,true,true,true,true,false,true,false,true,true,true,false,false,true,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,false,true,true,true,false,false,true,true,true,true,true,true,true,false},
					{false,true,false,false,false,true,true,true,false,true,true,true,true,false,false,true,true,false,false,true,true,false},
					{false,true,true,true,true,true,true,true,false,true,true,true,true,false,false,true,true,true,false,true,true,false},
					{false,false,false,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false},
					{false,false,false,false,false,false,true,true,false,true,true,true,true,true,true,true,true,true,false,true,false,true},
					{false,false,false,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,false},
					{false,false,false,true,false,true,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,false},
					{false,false,false,true,false,true,true,true,false,true,true,true,true,false,false,true,true,true,false,true,true,false},
					{false,false,false,true,false,true,true,true,false,true,true,true,true,false,false,true,true,true,false,true,true,false},
					{false,true,true,true,false,true,true,true,false,true,true,true,false,false,true,true,true,true,false,true,true,false},
					{false,true,true,true,false,true,true,true,true,true,true,true,false,false,true,true,true,false,false,true,true,false},
					{false,false,false,true,false,true,true,true,false,true,true,false,false,true,true,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,false,true,true,false,false,true,true,false,false,true,true,false,false,false},
					{false,true,false,false,false,true,true,true,false,true,true,true,true,true,true,false,false,false,false,false,false,false},
					{false,true,false,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false},
					{false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
				},
				{
					{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
					{false,false,false,false,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false},
					{false,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,true,true,true,false,false,false,false},
					{false,false,false,false,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,true,false,false,false,false},
					{false,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,false,true,true,true,true,false,true,true,true,true,true,true,true,true,true,false,false,false,false,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false},
					{false,true,true,false,true,true,true,true,false,true,true,true,true,false,true,true,false,true,true,true,true,true,true,false,false,false,false,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,true,true,true,true,false,true,true,false,true,true,true,true,false},
					{false,true,true,true,true,true,false,true,true,true,true,true,true,false,false,true,false,true,true,true,true,true,true,false,true,true,true,true,true,true,true,false},
					{false,false,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,true,true,true,true,false},
					{false,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true,true,false},
					{false,true,true,true,true,false,false,true,true,true,true,true,true,false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false},
					{false,true,true,true,false,true,true,false,true,true,true,true,true,false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false},
					{false,true,true,false,true,true,true,true,false,true,true,true,true,false,false,false,false,true,true,true,true,true,true,true,false,false,true,true,true,true,true,false},
					{false,true,false,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,false,true,true,true,true,true,false,true,true,true,true,true,true,false},
					{false,false,true,true,true,true,true,true,true,true,false,true,true,false,true,true,true,false,true,true,false,true,true,true,true,false,true,true,true,true,true,false},
					{false,true,false,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,true,true,true,false,true,true,false,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,false,true,true,true,true,false,true,true,false,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false},
					{false,true,true,true,false,true,true,false,true,true,true,true,true,false,true,true,true,false,true,true,false,true,true,true,false,true,true,true,true,true,true,false},
					{false,true,true,true,true,false,false,true,true,true,true,true,true,false,true,true,true,true,true,false,true,true,true,true,false,false,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,true,false,false,true,true,true,true,true,true,true,true,true,true,true,true,false},
					{false,true,false,false,false,true,true,false,false,false,true,true,false,false,true,true,true,true,true,false,true,true,true,true,false,false,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false,true,true,true,false,true,true,true,true,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,false,true,true,true,true,false,true,true,true,false},
					{false,false,false,false,false,false,false,false,false,false,true,true,true,false,true,true,true,true,true,false,true,true,false,true,true,true,true,false,true,true,true,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,true,true,true,true,true,true,false,true,true,true,true,false},
					{false,true,true,false,false,false,false,false,false,false,false,true,true,false,true,true,true,false,true,true,true,true,true,true,false,false,true,true,false,false,false,false},
					{false,true,true,false,false,false,false,false,false,false,false,true,true,false,true,true,true,false,true,true,true,true,true,true,true,true,true,true,false,false,false,false},
					{false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,true,true,true,true,true,true,false,false,false,false},
					{false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
				}
		};
	private int[][] _playerStartingLocations = {{11,1},{20,1},{31,1}};
	private int[][] _minotaurStartingLocations = {{1,10},{9,19},{26,15}};
	private int[][] _secretDoors = {origin, {3,19},{3,2}};
	private int[][] _doors = {origin, {9,20},{27,15}};
	private int[][] _eggs = {origin, {9,12},{25,25}};
	private int[][] _keys = {origin, {4,2},{17,6}};
	private int[][] _Skeys = {origin, {18,14},{27,30}};
	private int[][] _artifacts = {origin,{2,19},{2,2}};
	private int[][] _harpyStartingLocations = {origin,origin,{24,26}};
	private int[][] _swordLocations = {{5,6},{3,3},{18,18}};
	private int[][] _exitLocations = {{0,10},{9,21},{31,15}};
	private int[][] _torchLocations = {origin,{10,3},{17,4}};
	private int[][] _mapLocations = {origin,{20,20},{3,23}};
	private int[][][] _hiddenLocations = 
		{
				origins,
				{{2,2},{2,3},{3,2},{2,7},{3,7},{4,7},{4,11},{6,12},{7,17},{8,3},{8,4},{10,17},{13,12},{14, 17},{15,11},{16,3},{17, 10},{17,17},{17,18}},
				{{1,5},{3,4},{3,8},{3,22},{4,3},{4,22},{4,23},{5,5},{5,10},{7,2},{7,7},{7,14},{7,15},{8,14},{8,15},{8,24},{8,25},{9,5},{9,24},{9,25},{11,8},{11,9},{12,13},{16,4},{16,6},{16,25},{17,5},{17,7},{17,24},{18,4},{18,6},{18,19},{18,25},{19,19},{19,18},{19,24},{20,25},{23,1},{23,5},{23,6},{23,10},{23,11},{25,24},{26,24},{26,25}}
		};
	private int[][] _cyclopsStartingLocations = {origin,{8,9},{17,2}};
	private int[][][] _patrolCyclopsLocations = 
		{
				origins,
				{{8,9},{8,16},{10,16},{10,9}},
				{{17,2},{20,5},{20,6},{17,9},{14,6},{14,5}}
		};
	private int[][][] _patrolMinotaurLocations = 
		{
				origins,
				{{9,19},{5,19},{5,16},{1,16},{1,12},{1,5},{4,3},{5,6},{7,7},{15,7},{15,9},{13,11},{11,12},{11,16},{16,15},{16,19},{12,19}},
				{{26,15},{25,20},{29,21},{29,26},{26,29},{22,28},{17,27},{11,27},{5,27},{7,20},{4,15},{4,8},{7,5},{9,10},{11,12},{14,10},{18,11},{21,10},{18,12},{11,12},{11,18},{11,27},{13,26},{14,22},{15,17},{16,17},{17,15},{20,15},{22,16}}
		};
	private int[][][] _patrolHarpyLocations = 
		{
				origins,
				origins,
				{{24,26},{12,29},{4,18},{13,8},{24,14}}
		};
	
	/*
	 * Set the maze to selected maze given an input that is an int in the form of a string.
	 */
	public Maze(String mazeNum) { setCurMaze(Integer.parseInt(mazeNum)); }
	
	/*
	 * Return the current maze.
	 */
	public boolean[][] getCurMaze() { return _curMaze; }
	
	/*
	 * Sets the current maze to the selected maze (1, 2, or 3) with all the aspects of that maze.
	 */
	public void setCurMaze(int mazeNum) { 
		_curMaze = _mazeList[mazeNum - 1]; 
		_playerStartingLocation = _playerStartingLocations[mazeNum - 1]; 
		_minotaurStartingLocation = _minotaurStartingLocations[mazeNum - 1]; 
		_swordLocation = _swordLocations[mazeNum - 1]; 
		_exitLocation = _exitLocations[mazeNum - 1]; 
		_torchLocation = _torchLocations[mazeNum - 1];
		_mapLocation = _mapLocations[mazeNum - 1]; 
		_hiddenLocation = _hiddenLocations[mazeNum - 1];
		_cyclopsStartingLocation = _cyclopsStartingLocations[mazeNum - 1];
		_patrolCyclopsLocation = _patrolCyclopsLocations[mazeNum - 1];
		_patrolMinotaurLocation = _patrolMinotaurLocations[mazeNum - 1];
		_harpyStartingLocation = _harpyStartingLocations[mazeNum - 1];
		_patrolHarpyLocation = _patrolHarpyLocations[mazeNum - 1];
		_egg = _eggs[mazeNum - 1];
		_key = _keys[mazeNum - 1];
		_Skey = _Skeys[mazeNum - 1];
		_door = _doors[mazeNum - 1];
		_secretDoor = _secretDoors[mazeNum - 1];
		_artifact = _artifacts[mazeNum - 1];
		_view = true;
		_view2 = true;
			
		if(mazeNum == 1) {
			_view = false;
		}
		if(mazeNum == 1 || mazeNum == 2) {
			_view2 = false;
		}
	}
	
	/*
	 * Returns the coordinates of all hidden locations in the current maze.
	 */
	public int[][] getHiddenLocation() { return _hiddenLocation; }
	
	/*
	 * Returns the starting location for the cyclops of the current maze.
	 */
	public int[] getCyclopsStartingLocation() { return _cyclopsStartingLocation; }
	
	/*
	 * Returns the check points coordinates for the pathing of the cyclops of the current maze
	 * while he is not chasing the  player. 
	 */
	public int[][] getPatrolCyclopsLocation() { return _patrolCyclopsLocation; }
	
	/*
	 * Returns the check points coordinates for the  pathing of the minotaur of the current maze
	 * while he is not chasing.
	 */
	public int[][] getPatrolMinotaurLocation() { return _patrolMinotaurLocation; }
	
	/*
	 * Returns the starting coordinate for the player in the current maze. 
	 */
	public int[] getPlayerStart() { return _playerStartingLocation; }
	
	/*
	 * Returns the starting coordinate of the minotau in the current maze.
	 */
	public int[] getMinotaurStart() { return _minotaurStartingLocation; }
	
	/*
	 * Returns the coordinate of the exit for he current maze.
	 */
	public int[] getExit() { return _exitLocation; }
	
	/*
	 * Returns the coordinate of the sword for he current maze.
	 */
	public int[] getSword() { return _swordLocation; }
	
	/*
	 * Returns the coordinate of the torch for he current maze.
	 */
	public int[] getTorch() { return _torchLocation; }
	
	/*
	 * Returns the coordinate of the map for he current maze.
	 */
	public int[] getMap() { return _mapLocation; }
	
	/*
	 * Return true if the  current maze is made to be played with 
	 * limited visibility and objects complementing this mode.
	 */
	public boolean hasView()  { return _view; }
	
	/*
	 * Returns the number of check points in the pathing of the minotaur for the current maze.
	 */
	public int getMaxMinotaur() { return _patrolMinotaurLocation.length; }
	
	/*
	 * Returns the number of check points in the pathing  of the cyclops for the  current  maze.
	 */
	public int getMaxCyclops() { return _patrolCyclopsLocation.length; }
	
	/*
	 * Return the location of the egg in the current maze.
	 */
	public int[] getEgg() { return _egg; }
	
	/*
	 * Return the location of the artifact in the current maze.
	 */
	public int[] getArtifact() { return _artifact; }
	
	/*
	 * Return the location for the door in the current maze.
	 */
	public int[] getDoor() { return _door; }
	
	/*
	 * Return the location of the secret door in the current maze.
	 */
	public int[] getSDoor() { return _secretDoor; }
	
	/*
	 * Return the location of the key in the current maze.
	 */
	public int[] getKey() { return _key; }
	
	/*
	 * Return the location of the secret key in the current maze.
	 */
	public int[] getSKey() { return _Skey; }
	
	/*
	 * Returns true if the maze had a further reduced vision than the normal reduced vision.
	 */
	public boolean hasView2() { return _view2; }
	
	/*
	 * Returns the number of check points in the pathing for the harpy of the current maze.
	 */
	public int getMaxHarpy() { return _patrolHarpyLocation.length; }
	
	/*
	 * Returns the starting  location for the harpy of the current maze.
	 */
	public int[] getHarpyStart() { return _harpyStartingLocation; }
	
	/*
	 * Return the check points coordinates for the patrol of the harpy of the current maze.
	 */
	public int[][] getPatrolHarpyLocation() { return _patrolHarpyLocation; }
}
