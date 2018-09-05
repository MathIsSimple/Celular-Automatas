package automata.main.simulation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import automata.main.DisplayManager;
import automata.main.rendering.Render;

public class Wire {
	public static final int SIZE   = 20;
	public static final int WIDTH  = 1000;
	public static final int HEIGHT = 750;
	
	public static int[][] cells   = new int[WIDTH / SIZE][HEIGHT / SIZE];
	public static int[][] nextGen = new int[WIDTH / SIZE][HEIGHT / SIZE];
	
	public static boolean canRun = false;
	
	public static void start() {
		DisplayManager.create(WIDTH, HEIGHT, "Wire World");
		createCells();
	}
	
	private static void createCells() {
		for (int i = 0; i < cells.length; i ++) {
			for (int j = 0; j < cells[i].length; j ++) {
				int value = 0;
				cells[i][j] = value;
			}
		}
	}
	
	public static void render() {
		for (int i = 0; i < cells.length; i ++) {
			for (int j = 0; j < cells[i].length; j ++) {
				boolean render = true;
				switch (cells[i][j]) {
					case 0:
						render = false;
						break;
					case 1:
						GL11.glColor3f(0.0f, 0.0f, 1.0f);
						break;
					case 2:
						GL11.glColor3f(1.0f, 0.0f, 0.0f);
						break;
					case 3:
						GL11.glColor3f(1.0f, 1.0f, 0.0f);
						break;
				}
				if (render) Render.renderQuad(i * SIZE, j * SIZE, SIZE, SIZE);
			}
		}
	}
	
	public static void live() {
		if (canRun) {
			for (int i = 0; i < cells.length; i ++) {
				for (int j = 0; j < cells[i].length; j ++) {
					switch(cells[i][j]) {
						case 1:
							nextGen[i][j] = 2;
							break;
						case 2:
							nextGen[i][j] = 3;
							break;
						case 3:
							int headNeighbors  = 0;
							for (int x = i-1; x < i + 2; x ++) {
								for (int y = j-1; y < j + 2; y ++) {
									if (x < 0 || x > cells.length-1) continue;
									if (y < 0 || y > cells[i].length-1) continue;
									if (i == x && j == y) continue;
									if (cells[x][y] == 1) headNeighbors ++;
								}
							}
							nextGen[i][j] = 3;
							if (headNeighbors == 1 || headNeighbors == 2) nextGen[i][j] = 1;
							break;
					}
				}
			}
			
			cells = nextGen;
			nextGen = new int[WIDTH / SIZE][HEIGHT / SIZE];
		} else {
			if (Mouse.isButtonDown(0)) {
				int x = (int) Math.floor(Mouse.getX() / SIZE);
				int y = (int) Math.floor(logic.utils.Math.map(Mouse.getY(), 0, HEIGHT, HEIGHT, 0) / SIZE);
				if (cells[x][y] == 3) {cells[x][y] = 0;}
				else                  {cells[x][y] ++;}
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			} else if (Mouse.isButtonDown(1)) canRun = true;
		}
	}
	
}
