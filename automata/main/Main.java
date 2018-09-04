package automata.main;

import automata.main.simulation.Life;
import logic.core.Loop;

public class Main {
	
	public static final int WIDTH  = 1000;
	public static final int HEIGHT = 1000;
	public static final int SIZE   = 20;
	public static final int FPS    = 20;
	public static final String TITLE = "Game Of Life";
	
	public static boolean isRunning = true;
	
	public Main() {
		DisplayManager.create(WIDTH, HEIGHT, TITLE);
		Life.createCells(false);
		Loop.loop();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main main = new Main();
	}
}