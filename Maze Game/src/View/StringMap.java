package View;

import Model.Cyclops;
import Model.Harpy;
import Model.Maze;
import Model.Minotaur;
import Model.Player;

/*
 * StringMap class, responsible for updating the visual map that the user sees and updating
 * it depending on how the world reads the input of the user.
 */
public class StringMap {
	
	private final String _key = "K";
	private final String _Skey = "?";
	private final String _egg = "E";
	private final String _artifact = "?";
	private final String _door = "D";
	private final String _har = "A";
	private final String _SDoor = "?";
	private final String _wall = "|||";
	private final String _path = "   ";
	private final String _ply =  "P";
	private final String _min =  "M";
	private final String _exit = "X";
	private final String _sword ="S";
	private final String _torch = "T";
	private final String _map = "W";
	private final String _space = "     ";
	private final String _cyc = "C";
	private final String _hid = "H";
	private Maze _maze;
	private Player _player;
	private Minotaur _minotaur;
	private Cyclops _cyclops;
	private Harpy _harpy;
	
	
	/*
	 * Creates the map for the  maze, getting information from the player, monsters, and maze itself.
	 */
	public StringMap(Maze m, Player p, Minotaur t, Cyclops c, Harpy h) {
		_harpy = h;
		_maze = m;
		_player = p;
		_minotaur = t;
		_cyclops = c;
	}
	
	/*
	 * Generates the map and updates it as the elements of the map move, appear, and disappear.
	 */
	public String generateMap() {
		String map = "";
		if(!_maze.hasView() || _player.hasMap()) {
			for(int r = 0; r < _maze.getCurMaze().length; r++) {
				for(int c = 0; c < _maze.getCurMaze()[0].length; c++) {
					if(_player.getRow() == r && _player.getCol() == c) {
						map += _ply + _space;
					}
					else if(_minotaur.getRow() == r && _minotaur.getCol() == c && _minotaur.isAlive()) {
						map += _min + _space;
					}
					else if(_cyclops.getRow() == r && _cyclops.getCol() == c && _cyclops.isAlive() && _maze.hasView()) {
						map += _cyc + _space;
					}
					else if(_harpy.getRow() == r && _harpy.getCol() == c && _harpy.isAlive() && _maze.hasView2()) {
						map += _har + _space;
					}
					else if(_maze.getSword()[0] == r && _maze.getSword()[1] == c && !_player.hasSword()) {
						map += _sword + _space;
					}
					else if(_maze.getTorch()[0] == r && _maze.getTorch()[1] == c && !_player.hasTorch() && _maze.hasView()) {
						map += _torch + _space;
					}
					else if(_maze.getMap()[0] == r && _maze.getMap()[1] == c && !_player.hasMap() && _maze.hasView()) {
						map += _map + _space;
					}
					else if(_maze.getDoor()[0] == r && _maze.getDoor()[1] == c && _maze.hasView()) {
						map += _door + _space;
					}
					else if(_maze.getSDoor()[0] == r && _maze.getSDoor()[1] == c && _maze.hasView()) {
						map += _SDoor + _space;
					}
					else if(_maze.getArtifact()[0] == r && _maze.getArtifact()[1] == c && _maze.hasView() && !_player.hasArtifact()) {
						map += _artifact + _space;
					}
					else if(_maze.getSKey()[0] == r && _maze.getSKey()[1] == c && _maze.hasView() && !_player.hasSKey()) {
						map += _Skey + _space;
					}
					else if(_maze.getKey()[0] == r && _maze.getKey()[1] == c && _maze.hasView() && !_player.hasKey()) {
						map += _key + _space;
					}
					else if(_maze.getEgg()[0] == r && _maze.getEgg()[1] == c && _maze.hasView() && !_player.hasEgg()) {
						map += _egg + _space;
					}
					else if(_maze.getExit()[0] == r && _maze.getExit()[1] == c) {
						map += _exit + _space;
					}
					else if(hiddingPlace(r, c)) {
						map += _hid + _space;
					}
					else if(_maze.getCurMaze()[r][c]) {
						map += _path + _space;
					}
					else {
						map += _wall + _space;
					}
				}
				map += "\n";
			}
			map += "\n";
		}
		else if(_maze.hasView() && !_player.hasTorch() && !_player.hasMap()) {
			int r1 = _player.getRow();
			int c1 = _player.getCol();
			int r2 = r1 - 2;
			if(r2 < 0) {
				r2 = 0;
			}
			int c2 = c1 - 2;
			if(c2 < 0) {
				c2 = 0;
			}
			int r3 = r1 + 2;
			if(r3 > _maze.getCurMaze().length - 1) {
				r3 = _maze.getCurMaze().length - 1;
			}
			int c3 = c1 +2;
			if(c3 > _maze.getCurMaze()[0].length - 1) {
				c3 = _maze.getCurMaze()[0].length - 1;
			}
			for(int r = r2; r <= r3; r++) {
				for(int c = c2; c <= c3; c++) {
					if(_player.getRow() == r && _player.getCol() == c) {
						map += _ply + _space;
					}
					else if(_minotaur.getRow() == r && _minotaur.getCol() == c && _minotaur.isAlive()) {
						map += _min + _space;
					}
					else if(_cyclops.getRow() == r && _cyclops.getCol() == c && _cyclops.isAlive() && _maze.hasView()) {
						map += _cyc + _space;
					}
					else if(_harpy.getRow() == r && _harpy.getCol() == c && _harpy.isAlive() && _maze.hasView2()) {
						map += _har + _space;
					}
					else if(_maze.getSword()[0] == r && _maze.getSword()[1] == c && !_player.hasSword()) {
						map += _sword + _space;
					}
					else if(_maze.getTorch()[0] == r && _maze.getTorch()[1] == c && !_player.hasTorch() && _maze.hasView()) {
						map += _torch + _space;
					}
					else if(_maze.getMap()[0] == r && _maze.getMap()[1] == c && !_player.hasMap() && _maze.hasView()) {
						map += _map + _space;
					}
					else if(_maze.getDoor()[0] == r && _maze.getDoor()[1] == c && _maze.hasView()) {
						map += _door + _space;
					}
					else if(_maze.getSDoor()[0] == r && _maze.getSDoor()[1] == c && _maze.hasView()) {
						map += _SDoor + _space;
					}
					else if(_maze.getArtifact()[0] == r && _maze.getArtifact()[1] == c && _maze.hasView() && !_player.hasArtifact()) {
						map += _artifact + _space;
					}
					else if(_maze.getSKey()[0] == r && _maze.getSKey()[1] == c && _maze.hasView() && !_player.hasSKey()) {
						map += _Skey + _space;
					}
					else if(_maze.getKey()[0] == r && _maze.getKey()[1] == c && _maze.hasView() && !_player.hasKey()) {
						map += _key + _space;
					}
					else if(_maze.getEgg()[0] == r && _maze.getEgg()[1] == c && _maze.hasView() && !_player.hasEgg()) {
						map += _egg + _space;
					}
					else if(_maze.getExit()[0] == r && _maze.getExit()[1] == c) {
						map += _exit + _space;
					}
					else if(hiddingPlace(r, c)) {
						map += _hid + _space;
					}
					else if(_maze.getCurMaze()[r][c]) {
						map += _path + _space;
					}
					else {
						map += _wall + _space;
					}
				}
				map += "\n";
			}
			map += "\n";
		}
		else if(_maze.hasView() && !_player.hasMap() && _player.hasTorch()) {
			int r1 = _player.getRow();
			int c1 = _player.getCol();
			int r2 = r1 - 3;
			if(r2 < 0) {
				r2 = 0;
			}
			int c2 = c1 - 3;
			if(c2 < 0) {
				c2 = 0;
			}
			int r3 = r1 + 3;
			if(r3 > _maze.getCurMaze().length - 1) {
				r3 = _maze.getCurMaze().length - 1;
			}
			int c3 = c1 +3;
			if(c3 > _maze.getCurMaze()[0].length - 1) {
				c3 = _maze.getCurMaze()[0].length - 1;
			}
			for(int r = r2; r <= r3; r++) {
				for(int c = c2; c <= c3; c++) {
					if(_player.getRow() == r && _player.getCol() == c) {
						map += _ply + _space;
					}
					else if(_minotaur.getRow() == r && _minotaur.getCol() == c && _minotaur.isAlive()) {
						map += _min + _space;
					}
					else if(_cyclops.getRow() == r && _cyclops.getCol() == c && _cyclops.isAlive() && _maze.hasView()) {
						map += _cyc + _space;
					}
					else if(_harpy.getRow() == r && _harpy.getCol() == c && _harpy.isAlive() && _maze.hasView2()) {
						map += _har + _space;
					}
					else if(_maze.getSword()[0] == r && _maze.getSword()[1] == c && !_player.hasSword()) {
						map += _sword + _space;
					}
					else if(_maze.getTorch()[0] == r && _maze.getTorch()[1] == c && !_player.hasTorch() && _maze.hasView()) {
						map += _torch + _space;
					}
					else if(_maze.getMap()[0] == r && _maze.getMap()[1] == c && !_player.hasMap() && _maze.hasView()) {
						map += _map + _space;
					}
					else if(_maze.getDoor()[0] == r && _maze.getDoor()[1] == c && _maze.hasView()) {
						map += _door + _space;
					}
					else if(_maze.getSDoor()[0] == r && _maze.getSDoor()[1] == c && _maze.hasView()) {
						map += _SDoor + _space;
					}
					else if(_maze.getArtifact()[0] == r && _maze.getArtifact()[1] == c && _maze.hasView() && !_player.hasArtifact()) {
						map += _artifact + _space;
					}
					else if(_maze.getSKey()[0] == r && _maze.getSKey()[1] == c && _maze.hasView() && !_player.hasSKey()) {
						map += _Skey + _space;
					}
					else if(_maze.getKey()[0] == r && _maze.getKey()[1] == c && _maze.hasView() && !_player.hasKey()) {
						map += _key + _space;
					}
					else if(_maze.getEgg()[0] == r && _maze.getEgg()[1] == c && _maze.hasView() && !_player.hasEgg()) {
						map += _egg + _space;
					}
					else if(_maze.getExit()[0] == r && _maze.getExit()[1] == c) {
						map += _exit + _space;
					}
					else if(hiddingPlace(r, c)) {
						map += _hid + _space;
					}
					else if(_maze.getCurMaze()[r][c]) {
						map += _path + _space;
					}
					else {
						map += _wall + _space;
					}
				}
				map += "\n";
			}
			map += "\n";
		}
		else {
			for(int r = 0; r < _maze.getCurMaze().length; r++) {
				for(int c = 0; c < _maze.getCurMaze()[0].length; c++) {
					if(_player.getRow() == r && _player.getCol() == c) {
						map += _ply + _space;
					}
					else if(_minotaur.getRow() == r && _minotaur.getCol() == c && _minotaur.isAlive()) {
						map += _min + _space;
					}
					else if(_cyclops.getRow() == r && _cyclops.getCol() == c && _cyclops.isAlive() && _maze.hasView()) {
						map += _cyc + _space;
					}
					else if(_harpy.getRow() == r && _harpy.getCol() == c && _harpy.isAlive() && _maze.hasView2()) {
						map += _har + _space;
					}
					else if(_maze.getSword()[0] == r && _maze.getSword()[1] == c && !_player.hasSword()) {
						map += _sword + _space;
					}
					else if(_maze.getTorch()[0] == r && _maze.getTorch()[1] == c && !_player.hasTorch() && _maze.hasView()) {
						map += _torch + _space;
					}
					else if(_maze.getMap()[0] == r && _maze.getMap()[1] == c && !_player.hasMap() && _maze.hasView()) {
						map += _map + _space;
					}
					else if(_maze.getDoor()[0] == r && _maze.getDoor()[1] == c && _maze.hasView()) {
						map += _door + _space;
					}
					else if(_maze.getSDoor()[0] == r && _maze.getSDoor()[1] == c && _maze.hasView()) {
						map += _SDoor + _space;
					}
					else if(_maze.getArtifact()[0] == r && _maze.getArtifact()[1] == c && _maze.hasView() && !_player.hasArtifact()) {
						map += _artifact + _space;
					}
					else if(_maze.getSKey()[0] == r && _maze.getSKey()[1] == c && _maze.hasView() && !_player.hasSKey()) {
						map += _Skey + _space;
					}
					else if(_maze.getKey()[0] == r && _maze.getKey()[1] == c && _maze.hasView() && !_player.hasKey()) {
						map += _key + _space;
					}
					else if(_maze.getEgg()[0] == r && _maze.getEgg()[1] == c && _maze.hasView() && !_player.hasEgg()) {
						map += _egg + _space;
					}
					else if(_maze.getExit()[0] == r && _maze.getExit()[1] == c) {
						map += _exit + _space;
					}
					else if(hiddingPlace(r, c)) {
						map += _hid + _space;
					}
					else if(_maze.getCurMaze()[r][c]) {
						map += _path + _space;
					}
					else {
						map += _wall + _space;
					}
				}
				map += "\n";
			}
			map += "\n";
		}
		return map;
	}
	
	/*
	 * Detects which places are hiding places where the player can hide and not be seen by the monsters.
	 */
	public boolean hiddingPlace(int r, int c) {
		for(int i = 0; i < _maze.getHiddenLocation().length; i++) {
			if(_maze.getHiddenLocation()[i][0] == r && _maze.getHiddenLocation()[i][1] == c) {
				return true;
			}
		}
		return false;
	}
}
