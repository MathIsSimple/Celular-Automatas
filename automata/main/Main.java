package automata.main;

import automata.main.simulation.Life;
import automata.main.simulation.Maze;
import automata.main.simulation.Wire;
import logic.core.Loop;

public class Main {
	
	public static final int FPS    = 150;
	public static final String TITLE = "Game Of Life";
	public static final String GAME  = "Maze";
	
	public static boolean isRunning = true;
	
	public Main() {
		switch(GAME) {
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
		Loop.loop();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main main = new Main();
	}
}