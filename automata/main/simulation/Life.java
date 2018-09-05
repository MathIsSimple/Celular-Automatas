package automata.main.simulation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import automata.main.DisplayManager;
import automata.main.rendering.Render;

public class Life {
	public static final int SIZE   = 20;
	public static final int WIDTH  = 1000;
	public static final int HEIGHT = 500;
	
	public static int[][] cells   = new int[WIDTH / SIZE][HEIGHT / SIZE];
	public static int[][] nextGen = new int[WIDTH / SIZE][HEIGHT / SIZE];
	
	public static boolean canRun = false;
	
	public static void start() {
		DisplayManager.create(WIDTH, HEIGHT, "Game Of Life");
		createCells(false);
	}
	
	public static void createCells(boolean randomly) {
		for (int i = 0; i < cells.length; i ++) {
			for (int j = 0; j < cells[i].length; j ++) {
				int value = 0;
				if (randomly) value = Math.round(logic.utils.Math.randomNumber(0, 1));
				cells[i][j] = value;
			}
		}
	}
	
	public static void render() {
		for (int i = 0; i < cells.length; i ++) {
			for (int j = 0; j < cells[i].length; j ++) {
				switch (cells[i][j]) {
					case 0:
						GL11.glColor3f(1.0f, 1.0f, 1.0f);
						break;
					case 1:
						GL11.glColor3f(0.0f, 0.0f, 0.0f);
						break;
				}
				Render.renderQuad(i * SIZE, j * SIZE, SIZE, SIZE);
			}
		}
	}
	
	public static void live() {
		if (canRun) {
			for (int i = 0; i < cells.length; i ++) {
				for (int j = 0; j < cells[i].length; j ++) {
					int neighbors  = 0;
					for (int x = i-1; x < i + 2; x ++) {
						for (int y = j-1; y < j + 2; y ++) {
							if (x < 0 || x > cells.length-1) continue;
							if (y < 0 || y > cells[i].length-1) continue;
							if (i == x && j == y) continue;
							if (cells[x][y] == 1) neighbors ++;
						}
					}
					int status = cells[i][j];
					int nextStatus = status;
					switch (status) {
						case 1:
							if (neighbors < 2 || neighbors > 3) nextStatus = 0;
							break;
						case 0:
							if (neighbors == 3) nextStatus = 1;
							break;
					}
					nextGen[i][j] = nextStatus;
				}
			}
			
			cells = nextGen;
			nextGen = new int[WIDTH / SIZE][HEIGHT / SIZE];
		} else {
			if (Mouse.isButtonDown(0)) {
				int x = (int) Math.floor(Mouse.getX() / SIZE);
				int y = (int) Math.floor(logic.utils.Math.map(Mouse.getY(), 0, HEIGHT, HEIGHT, 0) / SIZE);
				if (cells[x][y] == 0) {cells[x][y] = 1;}
				else                  {cells[x][y] = 0;}
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			} else if (Mouse.isButtonDown(2)) {
				createCells(true);
				canRun = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) canRun = true;
			
		}
	}
}
