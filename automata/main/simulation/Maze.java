package automata.main.simulation;

import java.util.ArrayList;
import java.util.Stack;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import automata.main.DisplayManager;
import automata.main.rendering.Render;
import logic.utils.Vector;

public class Maze {
	private static final int SIZE   = 10;
	private static final int WIDTH  = 600;
	private static final int HEIGHT = 600;
	
	private static int[][][] maze = new int[WIDTH / SIZE][HEIGHT / SIZE][4];
	
	private static Stack<Vector> stack = new Stack<Vector>();
	private static ArrayList<Vector> visited = new ArrayList<Vector>();
	private static Vector currentPosition = new Vector(0, 0);
	
	private static boolean finished = false;
	private static boolean started = false;
	
	private static int time = 0;
	
	public static void start() {
		DisplayManager.create(WIDTH, HEIGHT, "Maze Generator");
		initMaze();
	}
	
	private static void initMaze() {
		for (int i = 0; i < maze.length; i ++) {
			for (int j = 0; j < maze[i].length; j ++) {
				for (int k = 0; k < maze[i][j].length; k ++) {
					maze[i][j][k] = 1;
				}
			}
		}
		stack.push(currentPosition);
		visited.add(currentPosition);
	}
	
	public static void live() {
		if (started) {
			if (Mouse.isButtonDown(0)) {
				started = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (visited.size() != maze.length * maze[0].length) {
				int i = (int) currentPosition.x;
				int j = (int) currentPosition.y;
				ArrayList<Vector> neighbours = new ArrayList<Vector>();
				Vector[] possibleNeighbours = {
						new Vector(i, j - 1), new Vector(i, j + 1), 
						new Vector(i - 1, j), new Vector(i + 1, j)
				};
				for (Vector pn : possibleNeighbours) {
					int x = (int) pn.x;
					int y = (int) pn.y;
					if (x < 0 || x > maze.length-1) continue;
					if (y < 0 || y > maze[i].length-1) continue;
					boolean canBeAdded = true;
					for (Vector v : visited) {
						if (v.x == x && v.y == y) {
							canBeAdded = false;
						}
					}
					if (canBeAdded) neighbours.add(new Vector(x, y));
				}
				if (neighbours.size() > 0) {
					Vector randomNeighbour = neighbours.get(Math.round(logic.utils.Math.randomNumber(0, neighbours.size()-1)));
					stack.push(randomNeighbour);
					visited.add(new Vector(randomNeighbour.x, randomNeighbour.y, time));
					
					int rnx = (int) randomNeighbour.x;
					int rny = (int) randomNeighbour.y;
					
					int cpx = (int) currentPosition.x;
					int cpy = (int) currentPosition.y;
					
					if (randomNeighbour.x > currentPosition.x) {
						maze[cpx][cpy][1] = 0;
						maze[rnx][rny][3] = 0;
					} else if (randomNeighbour.x < currentPosition.x) {
						maze[cpx][cpy][3] = 0;
						maze[rnx][rny][1] = 0;
					} else if (randomNeighbour.y > currentPosition.y) {
						maze[cpx][cpy][2] = 0;
						maze[rnx][rny][0] = 0;
					} else if (randomNeighbour.y < currentPosition.y) {
						maze[cpx][cpy][0] = 0;
						maze[rnx][rny][2] = 0;
					}
					
					currentPosition = randomNeighbour;
				} else {
					currentPosition = stack.pop();
				}
				time ++;
			} else {finished = true;}
		} else if (Mouse.isButtonDown(0) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			started = true;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void render(boolean quads) {
		if (quads) {
			for (int i = 0; i < maze.length; i ++) {
				for (int j = 0; j < maze[i].length; j ++) {
					int x = i * SIZE;
					int y = j * SIZE;
					int w = 1;
					GL11.glColor3f(1.0f, 1.0f, 1.0f);
					switch (maze[i][j][0]) {
						case 1:
							Render.renderQuad(x, y, SIZE, w);
							break;
					}
					switch (maze[i][j][1]) {
						case 1:
							Render.renderQuad(x + SIZE - w, y, w, SIZE);
							break;
					}
					switch (maze[i][j][2]) {
						case 1:
							Render.renderQuad(x, y + SIZE - w, SIZE, w);
							break;
					}
					switch (maze[i][j][3]) {
						case 1:
							Render.renderQuad(x, y, w, SIZE);
							break;
					}
				}
			}
			if (!finished) {
				GL11.glColor3f(0.0f, 0.0f, 1.0f);
				Render.renderQuad((int) currentPosition.x * SIZE, (int) currentPosition.y * SIZE, SIZE, SIZE);
			}
		} else if(!finished) {
			int lx = (int) visited.get(0).x;
			int ly = (int) visited.get(0).y;
			for (Vector v : visited) {
				int x = (int) (v.x * SIZE);
				int y = (int) (v.y * SIZE);
				if ((lx == x || ly == y) && (Math.abs(lx - x) == SIZE || Math.abs(ly - y) == SIZE)) {
					float r = logic.utils.Math.map(v.z, 0, time, 0, 1);
					float g = logic.utils.Math.map(time - v.z, 0, time, 0, 1);
					GL11.glColor3f(r, g, 1.0f);
					GL11.glVertex2f(lx + SIZE / 2, ly + SIZE / 2);
					GL11.glVertex2f(x + SIZE / 2, y + SIZE / 2);
				}
				lx = x;
				ly = y;
			}
		}
	}
}
