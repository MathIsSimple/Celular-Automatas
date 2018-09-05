package automata.main;

import automata.main.simulation.Life;
import automata.main.simulation.Maze;
import automata.main.simulation.Wire;
import logic.core.Loop;

public class Main {
	
	public static final int FPS    = 60;
	public static final String TITLE = "Game Of Life";
	public static final String[] GAMES = new String[] {"Life", "Wire", "Maze"};
	public static String Game = "";
	
	public static boolean isRunning = true;
	
	public Main() {
		start("Maze");
		Loop.loop();
	}
	
	public static void start(String game) {
		switch(game) {
			case "Life": 
				Life.start();
				break;
			case "Wire":
				Wire.start();
				break;
			case "Maze":
				Maze.start();
				break;
		}
		Game = game;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main main = new Main();
	}
}