package Model;

import Controller.JOP;
import Sound.SoundEffects;
import View.Images;
import View.StringMap;

/*
 * World class, reads the input from the user and transforms it into actions inside the
 * game by updating he status of all variables inside the game.
 */
public class World{
	
	Images image = new Images();
	Player _player;
	Minotaur _minotaur;
	Maze _maze;
	StringMap _StringMap;
	Cyclops _cyclops;
	Harpy _harpy;
	SoundEffects sound2 = new SoundEffects();
	
	public final int _minotaurVision = 5;
	private boolean _isPlaying = true;
	private int _patrolStateMinotaur = 1;
	private int _patrolStateCyclops = 1;
	private int _patrolStateHarpy = 1;
	private final int _cyclopsVision = 2;
	private final int _harpyVison = 3;
	private int _maxHarpy;
	private int _maxMinotaur;
	private int _maxCyclops;
	
	/*
	 * Creates the world and initializes all variables that operate inside the world.
	 */
	public World(String worldNum) throws Exception {
		_maze = new Maze(worldNum);
		_harpy = new Harpy(_maze.getHarpyStart()[0], _maze.getHarpyStart()[1]);
		_minotaur = new Minotaur(_maze.getMinotaurStart()[0], _maze.getMinotaurStart()[1]);
		_cyclops = new Cyclops(_maze.getCyclopsStartingLocation()[0], _maze.getCyclopsStartingLocation()[1]);
		_player = new Player(_maze.getPlayerStart()[0], _maze.getPlayerStart()[1]);
		_StringMap = new StringMap(_maze, _player, _minotaur, _cyclops, _harpy);
		_maxMinotaur = _maze.getMaxMinotaur();
		_maxCyclops = _maze.getMaxCyclops();
		_maxHarpy = _maze.getMaxHarpy();
		_patrolStateMinotaur = 1;
		_patrolStateCyclops = 1;
		_patrolStateHarpy = 1;
		update();
	}
	
	/*
	 * Updates the condition of the variables inside the world,
	 *  like if anything was killed or if the player wins or loses.
	 */
	public void update() throws Exception {

		_isPlaying = true;
		
		while(_isPlaying) {
			
			isDeath();
			
			victory();
			
			playerMove();
			
			isDeath();
			
			victory();
			
			findItem();
			
			hiddingPlayer();
			
			moveMinotaur();
			
			isDeath();
			
			victory();
			
			moveCyclops();
			
			isDeath();
			
			victory();
			
			moveHarpy();
			
			isDeath();
			
			victory();
		}
	}
	
	/*
	 * Reads the move of the player and move the player on the maze.
	 */
	public void playerMove() throws Exception {
		String map = _StringMap.generateMap();
		String msg = "((W A S D) to move, (Q) to stand, (1, 2, or 3) to change map) What is you move move?";
		
		boolean on = true;
		
		while(on) {
			String move = JOP.in(map + msg);
			if(move == null) {
				//on = true;
				System.exit(0);
			}
			else if(move.equalsIgnoreCase("getTorch();")) {
				_player.giveTorch();
			}
			else if(move.equalsIgnoreCase("getMap();")) {
				_player.giveMap();
			}
			else if(move.equalsIgnoreCase("getSword();")) {
				_player.giveSword();
			}
			else if(move.equalsIgnoreCase("win();")) {
				win();
			}
			else if(move.equalsIgnoreCase("1") || move.equalsIgnoreCase("2") || move.equalsIgnoreCase("3")) {
				showSteps();
				new World(move);
			}
			else if(move.equalsIgnoreCase("Exit")) {
				showSteps();
				System.exit(0);
			}
			else if(move.equalsIgnoreCase("skip();")) {
				on = false;
			}
			else if(move.equalsIgnoreCase("q") && _player.hidden()) {
				on = false;
				Player._steps++;
			}
			else if(getPlayerMove(move)) {
				on = false;
				Player._steps++;
			}
		}
	}
	
	/*
	 * Captures in which direction the player wants to move.
	 */
	private boolean getPlayerMove(String s) {

		if (s.equalsIgnoreCase("W")) {
			if ((_player.getRow() - 1) >= 0 && _maze.getCurMaze()[_player.getRow() - 1][_player.getCol()]) {
				_player.setPosition(_player.getRow() - 1, _player.getCol());
				return true;
			} else {
				return false;
			}
		}
		if (s.equalsIgnoreCase("S")) {
			if ((_player.getRow() + 1) < _maze.getCurMaze().length && _maze.getCurMaze()[_player.getRow() + 1][_player.getCol()]) {
				_player.setPosition(_player.getRow() + 1, _player.getCol());
				return true;
			} else {
				return false;
			}
		}
		if (s.equalsIgnoreCase("D")) {
			if ((_player.getCol() + 1) < _maze.getCurMaze()[0].length && _maze.getCurMaze()[_player.getRow()][_player.getCol() + 1]) {
				_player.setPosition(_player.getRow(), _player.getCol() + 1);
				return true;
			} else {
				return false;
			}
		}
		if (s.equalsIgnoreCase("A")) {
			if ((_player.getCol() - 1) >= 0 && _maze.getCurMaze()[_player.getRow()][_player.getCol() - 1]) {
				_player.setPosition(_player.getRow(), _player.getCol() - 1);
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
	
	/*
	 * Moves the harpy on the patrol path or on chasing the player. 
	 */
	public void moveHarpy() throws Exception {
		
		if(_maze.hasView2()) {
			
			int rDist = _player.getRow() - _harpy.getRow();
			int cDist = _player.getCol() - _harpy.getCol();
			int r = _harpy.getRow();
			int c = _harpy.getCol();
			
			if(_patrolStateHarpy == _maxHarpy) {
				_patrolStateHarpy = 0;
			}
			
			if(_maze.getPatrolHarpyLocation()[_patrolStateHarpy][0] == _harpy.getRow() && _maze.getPatrolHarpyLocation()[_patrolStateHarpy][1] == _harpy.getCol()) {
				_patrolStateHarpy++;
			}
			
			if(_patrolStateHarpy == _maxHarpy) {
				_patrolStateHarpy = 0;
			}
			
			int r2 = _harpy.getRow();
			int c2 = _harpy.getCol();
			int rDist2 = _maze.getPatrolHarpyLocation()[_patrolStateHarpy][0] - _harpy.getRow();
			int cDist2 = _maze.getPatrolHarpyLocation()[_patrolStateHarpy][1] - _harpy.getCol();
			
			if(_harpyVison >= Math.sqrt(Math.pow(rDist, 2) + Math.pow(cDist, 2)) && !_player.hidden()) {
				
				if(!_harpy.isChase() && _harpy.isAlive()) {
					SoundPlusImage("Monster Roar sound effect (deleted).wav", "unnamed.png", "The Harpy is Chasing You!!!!", "Harpy Chase");
				}
				
				_harpy.startChase();
				
				if(rDist < 0) {
					_harpy.setPosition(r - 1, c);
				}
				if(rDist > 0) {
					_harpy.setPosition(r + 1, c);
				}
				if(cDist > 0) {
					_harpy.setPosition(r, c + 1);
				}
				if(cDist < 0) {
					_harpy.setPosition(r, c - 1);
				}
				
			}
			else {
				
				if(_harpy.isChase() && _harpy.isAlive()) {
					SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav","hqdefault.png", "The Harpy Stopped Chasing You...", "Harpy Stopped");
				}
				
				_harpy.stopChase();
				
				if(rDist2 < 0) {
					_harpy.setPosition(r2 - 1, c2);
				}
				if(rDist2 > 0) {
					_harpy.setPosition(r2 + 1, c2);
				}
				if(cDist2 > 0) {
					_harpy.setPosition(r2, c2 + 1);
				}
				if(cDist2 < 0) {
					_harpy.setPosition(r2, c2 - 1);
				}
				
			}
			
			if(_patrolStateHarpy == _maxHarpy) {
				_patrolStateHarpy = 0;
			}
			
			if(_maze.getPatrolHarpyLocation()[_patrolStateHarpy][0] == _harpy.getRow() && _maze.getPatrolHarpyLocation()[_patrolStateHarpy][1] == _harpy.getCol()) {
				_patrolStateHarpy++;
			}
			
			if(_patrolStateHarpy == _maxHarpy) {
				_patrolStateHarpy = 0;
			}
			
			r2 = _harpy.getRow();
			c2 = _harpy.getCol();
			rDist2 = _maze.getPatrolHarpyLocation()[_patrolStateHarpy][0] - _harpy.getRow();
			cDist2 = _maze.getPatrolHarpyLocation()[_patrolStateHarpy][1] - _harpy.getCol();
			
			if(_harpyVison >= Math.sqrt(Math.pow(rDist, 2) + Math.pow(cDist, 2)) && !_player.hidden()) {
				
				if(!_harpy.isChase() && _harpy.isAlive()) {
					SoundPlusImage("Monster Roar sound effect (deleted).wav", "unnamed.png", "The Harpy is Chasing You!!!!", "Harpy Chase");
				}
				
				_harpy.startChase();
				
			}
			else {
				
				if(_harpy.isChase() && _harpy.isAlive()) {
					SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav","hqdefault.png", "The Harpy Stopped Chasing You...", "Harpy Stopped");
				}
				
				_harpy.stopChase();
				
				if(rDist2 < 0) {
					_harpy.setPosition(r2 - 1, c2);
				}
				if(rDist2 > 0) {
					_harpy.setPosition(r2 + 1, c2);
				}
				if(cDist2 > 0) {
					_harpy.setPosition(r2, c2 + 1);
				}
				if(cDist2 < 0) {
					_harpy.setPosition(r2, c2 - 1);
				}
				
			}
			
			if(_patrolStateHarpy == _maxHarpy) {
				_patrolStateHarpy = 0;
			}
			
			if(_maze.getPatrolHarpyLocation()[_patrolStateHarpy][0] == _harpy.getRow() && _maze.getPatrolHarpyLocation()[_patrolStateHarpy][1] == _harpy.getCol()) {
				_patrolStateHarpy++;
			}
			
			if(_patrolStateHarpy == _maxHarpy) {
				_patrolStateHarpy = 0;
			}
			
			r2 = _harpy.getRow();
			c2 = _harpy.getCol();
			rDist2 = _maze.getPatrolHarpyLocation()[_patrolStateHarpy][0] - _harpy.getRow();
			cDist2 = _maze.getPatrolHarpyLocation()[_patrolStateHarpy][1] - _harpy.getCol();
			
			if(_harpyVison >= Math.sqrt(Math.pow(rDist, 2) + Math.pow(cDist, 2)) && !_player.hidden()) {
				
				if(!_harpy.isChase() && _harpy.isAlive()) {
					SoundPlusImage("Monster Roar sound effect (deleted).wav", "unnamed.png", "The Harpy is Chasing You!!!!", "Harpy Chase");
				}
				
				_harpy.startChase();
				
			}
			else {
				
				if(_harpy.isChase() && _harpy.isAlive()) {
					SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav","hqdefault.png", "The Harpy Stopped Chasing You...", "Harpy Stopped");
				}
				
				_harpy.stopChase();
				
				if(rDist2 < 0) {
					_harpy.setPosition(r2 - 1, c2);
				}
				if(rDist2 > 0) {
					_harpy.setPosition(r2 + 1, c2);
				}
				if(cDist2 > 0) {
					_harpy.setPosition(r2, c2 + 1);
				}
				if(cDist2 < 0) {
					_harpy.setPosition(r2, c2 - 1);
				}
				
			}
			
		}
	}
	
	/*
	 * Moves the minotaur on its patrol path or while chasing the player.
	 */
	public void moveMinotaur() throws Exception {
		
		int rDist = _player.getRow() - _minotaur.getRow();
		int cDist = _player.getCol() - _minotaur.getCol();
		int r = _minotaur.getRow();
		int c = _minotaur.getCol();
		
		if(_maze.hasView()) {
			if(_patrolStateMinotaur == _maxMinotaur) {
				_patrolStateMinotaur = 0;
			}
			if(_maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][0] == _minotaur.getRow() && _maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][1] == _minotaur.getCol()) {
				_patrolStateMinotaur++;
			}
			if(_patrolStateMinotaur == _maxMinotaur) {
				_patrolStateMinotaur = 0;
			}
			int r2 = _minotaur.getRow();
			int c2 = _minotaur.getCol();
			int rDist2 = _maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][0] - _minotaur.getRow();
			int cDist2 = _maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][1] - _minotaur.getCol();
			
			if(_minotaurVision >= Math.sqrt(Math.pow(rDist, 2) + Math.pow(cDist, 2)) && !_player.hidden()) {
				
				if(!_minotaur.isChase() && _minotaur.isAlive()) {
					SoundPlusImage("Monster Roar sound effect (deleted).wav", "670cc926a19ec3d8bd77ac0954b59abb.png", "The Minotaur is Chasing You!!!!", "Minotaur Chase");
				}
				
				_minotaur.startChase();
				
				if(rDist < 0 && _maze.getCurMaze()[r - 1][c]) {
					_minotaur.setPosition(r - 1, c);
				}
				if(rDist > 0 && _maze.getCurMaze()[r +1][c]) {
					_minotaur.setPosition(r + 1, c);
				}
				if(cDist > 0 && _maze.getCurMaze()[r][c + 1]) {
					_minotaur.setPosition(r, c + 1);
				}
				if(cDist < 0 && _maze.getCurMaze()[r][c - 1]) {
					_minotaur.setPosition(r, c - 1);
				}
				
			}
			else {
				
				if(_minotaur.isChase() && _minotaur.isAlive()) {
					SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav", "minotaur-23e9e710-7437-49de-8e60-cc09dd446f3-resize-750.png", "The Minotaur Stopped Chasing You...", "Minotaur Stopped Chase");
				}
				
				_minotaur.stopChase();
				
				if(rDist2 < 0 && _maze.getCurMaze()[r2 - 1][c2]) {
					_minotaur.setPosition(r2 - 1, c2);
				}
				if(rDist2 > 0 && _maze.getCurMaze()[r2 + 1][c2]) {
					_minotaur.setPosition(r2 + 1, c2);
				}
				if(cDist2 > 0 && _maze.getCurMaze()[r2][c2 + 1]) {
					_minotaur.setPosition(r2, c2 + 1);
				}
				if(cDist2 < 0 && _maze.getCurMaze()[r2][c2 - 1]) {
					_minotaur.setPosition(r2, c2 - 1);
				}
				
			}
			
			if(_patrolStateMinotaur == _maxMinotaur) {
				_patrolStateMinotaur = 0;

			}
			if(_maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][0] == _minotaur.getRow() && _maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][1] == _minotaur.getCol()) {
				_patrolStateMinotaur++;
			}
			if(_patrolStateMinotaur == _maxMinotaur) {
				_patrolStateMinotaur = 0;
			}
			r2 = _minotaur.getRow();
			c2 = _minotaur.getCol();
			rDist2 = _maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][0] - _minotaur.getRow();
			cDist2 = _maze.getPatrolMinotaurLocation()[_patrolStateMinotaur][1] - _minotaur.getCol();
			
			if(_minotaurVision >= Math.sqrt(Math.pow(rDist, 2) + Math.pow(cDist, 2)) && !_player.hidden()) {
				
				if(!_minotaur.isChase() && _minotaur.isAlive()) {
					SoundPlusImage("Monster Roar sound effect (deleted).wav", "670cc926a19ec3d8bd77ac0954b59abb.png", "The Minotaur is Chasing You!!!!", "Minotaur Chase");
				}
				
				_minotaur.startChase();
				
			}
			else {
				
				if(_minotaur.isChase() && _minotaur.isAlive()) {
					SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav", "minotaur-23e9e710-7437-49de-8e60-cc09dd446f3-resize-750.png", "The Minotaur Stopped Chasing You...", "Minotaur Stopped Chase");
				}
				
				_minotaur.stopChase();
				
				if(rDist2 < 0 && _maze.getCurMaze()[r2 - 1][c2]) {
					_minotaur.setPosition(r2 - 1, c2);
				}
				if(rDist2 > 0 && _maze.getCurMaze()[r2 + 1][c2]) {
					_minotaur.setPosition(r2 + 1, c2);
				}
				if(cDist2 > 0 && _maze.getCurMaze()[r2][c2 + 1]) {
					_minotaur.setPosition(r2, c2 + 1);
				}
				if(cDist2 < 0 && _maze.getCurMaze()[r2][c2 - 1]) {
					_minotaur.setPosition(r2, c2 - 1);
				}
			}
		}
		else {
			if(rDist < 0 && _maze.getCurMaze()[r - 1][c]) {
				_minotaur.setPosition(r - 1, c);
			}
			if(rDist > 0 && _maze.getCurMaze()[r +1][c]) {
				_minotaur.setPosition(r + 1, c);
			}
			if(cDist > 0 && _maze.getCurMaze()[r][c + 1]) {
				_minotaur.setPosition(r, c + 1);
			}
			if(cDist < 0 && _maze.getCurMaze()[r][c - 1]) {
				_minotaur.setPosition(r, c - 1);
			}
		}
	}
	
	/*
	 * Moves the cyclops on its patrol path or while chasing the player.
	 */
	public void moveCyclops() throws Exception {
		if(_maze.hasView()) {
			int rDist = _player.getRow() - _cyclops.getRow();
			int cDist = _player.getCol() - _cyclops.getCol();
			int r = _cyclops.getRow();
			int c = _cyclops.getCol();
			
			if(_patrolStateCyclops == _maxCyclops) {
				_patrolStateCyclops = 0;
			}
			
			if(_maze.getPatrolCyclopsLocation()[_patrolStateCyclops][0] == _cyclops.getRow() && _maze.getPatrolCyclopsLocation()[_patrolStateCyclops][1] == _cyclops.getCol()) {
				_patrolStateCyclops++;
			}
			
			if(_patrolStateCyclops == _maxCyclops) {
				_patrolStateCyclops = 0;
			}
			int r2 = _cyclops.getRow();
			int c2 = _cyclops.getCol();
			int rDist2 = _maze.getPatrolCyclopsLocation()[_patrolStateCyclops][0] - _cyclops.getRow();
			int cDist2 = _maze.getPatrolCyclopsLocation()[_patrolStateCyclops][1] - _cyclops.getCol();
			
			if(_cyclopsVision >= Math.sqrt(Math.pow(rDist, 2) + Math.pow(cDist, 2)) && !_player.hidden()) {
				
				if(!_cyclops.isChase() && _cyclops.isAlive()) {
					SoundPlusImage("Monster Roar sound effect (deleted).wav", "63032.png", "The Cyclops is Chasing You!!!!", "Cyclops Chase");
				}
				
				_cyclops.startChase();
				
				if(rDist < 0 && _maze.getCurMaze()[r - 1][c]) {
					_cyclops.setPosition(r - 1, c);
				}
				if(rDist > 0 && _maze.getCurMaze()[r +1][c]) {
					_cyclops.setPosition(r + 1, c);
				}
				if(cDist > 0 && _maze.getCurMaze()[r][c + 1]) {
					_cyclops.setPosition(r, c + 1);
				}
				if(cDist < 0 && _maze.getCurMaze()[r][c - 1]) {
					_cyclops.setPosition(r, c - 1);
				}
				
			}
			else {
				
				if(_cyclops.isChase() && _cyclops.isAlive()) {
					SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav", "Beast-cyclop-001.png", "The Cyclops Stopped Chasing You...", "Cyclops Stopped Chase");
				}
				
				_cyclops.stopChase();
				
				if(rDist2 < 0 && _maze.getCurMaze()[r2 - 1][c2]) {
					_cyclops.setPosition(r2 - 1, c2);
				}
				if(rDist2 > 0 && _maze.getCurMaze()[r2 + 1][c2]) {
					_cyclops.setPosition(r2 + 1, c2);
				}
				if(cDist2 > 0 && _maze.getCurMaze()[r2][c2 + 1]) {
					_cyclops.setPosition(r2, c2 + 1);
				}
				if(cDist2 < 0 && _maze.getCurMaze()[r2][c2 - 1]) {
					_cyclops.setPosition(r2, c2 - 1);
				}
				
			}
		}
	}
	
	/*
	 * Hides the player if it is on a hidden space.
	 */
	public boolean hiddingPlayer() throws Exception {
		if(_maze.hasView()) {
			for(int r = 0; r < _maze.getHiddenLocation().length; r++) {
					if(_player.getRow() == _maze.getHiddenLocation()[r][0] && _player.getCol() == _maze.getHiddenLocation()[r][1]) {
						if(!_player.hidden()) {
							SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav", "istockphoto-1224329398-170667a.png", "You are Hidden...", "Player Hidden");
						}
						_player.hide();
						return true;
				}
			}
			if(_player.hidden()) {
				SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav", "Ares_The_Spartan_God.png", "You are not Hidden Anymore...", "Player Out Of Hidden");
			}
			_player.unhide(); 
			return false;
		}
		return false;
	}
	
	/*
	 * Checks to see if the player has won.
	 */
	public void victory() throws Exception {
		if(_player.getRow() == _maze.getExit()[0] && _player.getCol() == _maze.getExit()[1]) {
			_isPlaying = false;
			SoundPlusImage("Epic Victory Sound Effect (128 kbps) (online-audio-converter.com).wav", "dc9df6480f414110ccde98b6af74bd8a.png", "You Win! You found the exit!!!", "Exit Victory");
			showSteps();
			keepPlaying();
		}
	}
	
	/*
	 * Checks to see if the player has died.
	 */
	public void isDeath() throws Exception {
		if(_minotaur.getRow() == _player.getRow() && _minotaur.getCol() == _player.getCol() && !_player.hasSword()) {
			_player.kill();
			SoundPlusImage("Screamer sound FnaF3 Springtrap (128 kbps) (online-audio-converter.com).wav", "Minotaure_GOW_III.png", "You Lost! You were too weak of mind and body...", "Minotaur Killed You");
			_isPlaying = false;
			showSteps();
			keepPlaying();
		}
		if(_minotaur.getRow() == _player.getRow() && _minotaur.getCol() == _player.getCol() && _player.hasSword() && !_player.hasKilledMinotaur()) {
			_minotaur.kill();
			_player.killedMinotaur();
			_player.giveMhead();
			SoundPlusImage("Epic Victory Sound Effect (128 kbps) (online-audio-converter.com).wav", "51d245a15ed0b55bf5b6e100a353089d (1).png", "You killed the Minotaur! You cut his horns off!!!", "Killed Minotaur");
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav","Thesutaur.png","Horns Acquired! You keep the horns as a trophy!!!", "Minotaur's Horns");
		}
		if(_cyclops.getRow() == _player.getRow() && _cyclops.getCol() == _player.getCol() && !_player.hasSword()) {
			_player.kill();
			SoundPlusImage("Screamer sound FnaF3 Springtrap (128 kbps) (online-audio-converter.com).wav", "032757ceef20c9a45e18d6456c4cd4d5.png", "You Lost! You were too weak of mind and body...", "Cyclops Killed You");
			_isPlaying = false;
			showSteps();
			keepPlaying();
		}
		if(_cyclops.getRow() == _player.getRow() && _cyclops.getCol() == _player.getCol() && _player.hasSword() && !_player.hasKilledCyclops()) {
			_player.killedCyclops();
			_cyclops.kill();
			_player.giveChead();
			SoundPlusImage("Epic Victory Sound Effect (128 kbps) (online-audio-converter.com).wav", "824947-bigthumbnail.png", "You killed the Cyclops! You ripped his eye off!!!", "Killed Cyclops");
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav", "kratosvscyclops.png","Eye Acquired! You keep the eye of the cyclops as a trophy!!!","Cyclops' Eye");
		}
		if(_harpy.getRow() == _player.getRow() && _harpy.getCol() == _player.getCol() && !_player.hasSword()) {
			_player.kill();
			SoundPlusImage("Screamer sound FnaF3 Springtrap (128 kbps) (online-audio-converter.com).wav", "3062-ravenous-harpy-mobile.png", "You Lost! You were too weak of mind and body...", "Harpy Killed You");
			_isPlaying = false;
			showSteps();
			keepPlaying();
		}
		if(_harpy.getRow() == _player.getRow() && _harpy.getCol() == _player.getCol() && _player.hasSword() && !_player.hasKilledHarpy()) {
			_player.killedHarpy();
			_harpy.kill();
			_player.giveAhead();
			SoundPlusImage("Epic Victory Sound Effect (128 kbps) (online-audio-converter.com).wav", "witcher-3-wild-hunt-wolf-wall-sculpture-igni-vs-harpies-save-game-of-witcher-3-wild-hunt-wolf-wall-sculpture.png", "You killed the Harpy! You cut her head off!!!", "Killed Harpy");
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav", "aca049419aa1e1ea984b13a0fa5654d5.png", "Head Acquired! You keep her head as a trophy!!!","Harpy's Head");
		}
	}
	
	/*
	 * Checks to see if the player has found any iteams and gives the found item to the player.
	 */
	public void findItem() throws Exception {
		if(_player.getRow() == _maze.getSword()[0] && _player.getCol() == _maze.getSword()[1]  && !_player.hasSword()) {
			_player.giveSword();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav", "Sinner_Sword_Detail_Pile_Of_Bones.png", "Sowrd added!! Kill That Minotaur (Space to Continue)", "Sword Found");
		}
		if(_player.getRow() == _maze.getTorch()[0] && _player.getCol() == _maze.getTorch()[1] && _maze.hasView()&& !_player.hasTorch()) {
			_player.giveTorch();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav", "6e0437df9985cbed58c8eb5fe5064ed6.png", "Torch added!! Vision Increased (Space to Continue)", "Torch Found"); 
		}
		if(_player.getRow() == _maze.getMap()[0] && _player.getCol() == _maze.getMap()[1] && _maze.hasView()&& !_player.hasMap()) {
			_player.giveMap();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav", "5127556-ancient-map-scroll.png", "Map added!! Knowledge guides your way (Space to Continue)", "Map Found");
		}
		if(_player.getRow() == _maze.getEgg()[0] && _player.getCol() == _maze.getEgg()[1] && _maze.hasView() && !_player.hasEgg()) {
			_player.giveEgg();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav","e90793f22620ea401f0f934bc9896664.png","Golden Dragon Egg Found! Worth a lot if you make it out alive!!!", "Golden Dragon Egg");
		}
		if(_player.getRow() == _maze.getKey()[0] && _player.getCol() == _maze.getKey()[1] && _maze.hasView() && !_player.hasKey()) {
			_player.giveKey();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav","key-847830_960_720.png","You found a key for the exit!!!", "Normal Key");
			_maze.getCurMaze()[_maze.getDoor()[0]][_maze.getDoor()[1]] = true;
		}
		if(_player.getRow() == _maze.getSKey()[0] && _player.getCol() == _maze.getSKey()[1] && _maze.hasView() && !_player.hasSKey()) {
			_player.giveSKey();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav","1Uwk3A-T-wBqQ4U3pzBUuQ.png","You found a weird key, what could it be for?", "Secret Key (Hint ?)");
			_maze.getCurMaze()[_maze.getSDoor()[0]][_maze.getSDoor()[1]] = true;
		}
		if(_player.getRow() == _maze.getArtifact()[0] && _player.getCol() == _maze.getArtifact()[1] && !_player.hasArtifact() && _maze.hasView()) {
			_player.giveArtifact();
			SoundPlusImage("The Legend of Zelda Chest Opening and Getting Item Sound (128 kbps) (online-audio-converter.com).wav","tpjfs7ne76v31.png","You found a collection of strange artifact!\nThey all transmite a powerful aura\nSure they are worth a lot if you survive this...", "Secret Artifacts");
		}
		if(((_player.getRow() == _maze.getDoor()[0] + 1 && _player.getCol() == _maze.getDoor()[1]) || (_player.getRow() == _maze.getDoor()[0] -1 && _player.getCol() == _maze.getDoor()[1]) || (_player.getRow() == _maze.getDoor()[0] && _player.getCol() == _maze.getDoor()[1] + 1) || (_player.getRow() == _maze.getDoor()[0] && _player.getCol() == _maze.getDoor()[1] - 1))&& !_player.hasKey() && _maze.hasView()) {
			SoundPlusImage("Heartbeat sound (128 kbps) (online-audio-converter.com).wav","download.png","The door seems to be closed. Maybe there is a key around?", "Closed Door");
		}
	}
	
	/*
	 * Displays the winning ending of the current maze level.
	 */
	public void win() throws Exception {
		_isPlaying = false;
		JOP.msg("You Win!");
		showSteps();
		keepPlaying();
	}
	
	/*
	 * Shows how many steps it took the player to complete the maze.
	 */
	public void showSteps() {
		if(_player.isAlive()) {
			if(_player.hasEgg()) {
				Player._points += 100;
			}
			if(_player.hasChead()) {
				Player._points += 150;
			}
			if(_player.hasMhead()) {
				Player._points += 150;
			}
			if(_player.hasAhead()) {
				Player._points += 150;
			}
			if(_player.hasArtifact()) {
				Player._points += 500;
			}
			if(_player.isAlive()) {
				Player._points += 200;
			}
		}
		if(_maze.hasView()) {
			JOP.msg("Steps of this run: " + Player._steps + "\n" + "Points of this run: " + Player._points + "\n" + "Total Score (Points - Steps): " + (Player._points - Player._steps));
		}
		else {
			JOP.msg("Steps of this run: " + Player._steps);
		}
	}
	
	/*
	 * Displays a specific secondary sound plus an image for specific events of the game.
	 */
	public void SoundPlusImage(String s, String x, String y, String z) throws Exception {
		SoundEffects.clip1.stop();
		sound2.Run2(s);
		image.display(x, y, z);
		SoundEffects.clip2.stop();
		SoundEffects.clip1.start();
	}
	
	/*
	 * Determines if the player wishes to keep playing or if it wants to change levels, or if it wants to leave.
	 */
	public void keepPlaying() throws Exception {
		String s = JOP.in("Want to keep playing? ((1, 2 or 3) for new game) or (exit) to quit)");
		if(s == null || s.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
		else if(s.equalsIgnoreCase("1") || s.equalsIgnoreCase("2") || s.equalsIgnoreCase("3")) {
			new World(s);
		}
		else {
			new World("1");
		}
	}
	
}
