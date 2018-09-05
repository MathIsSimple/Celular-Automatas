package automata.main.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import automata.main.Main;
import automata.main.simulation.Life;
import automata.main.simulation.Maze;
import automata.main.simulation.Wire;

public class Render {
	
	public static void prepareOPENGL(int width, int height) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	public static void renderQuad(int x, int y, int w, int h) {
		GL11.glVertex2f(x, y + h);
		GL11.glVertex2f(x + w, y + h);
		GL11.glVertex2f(x + w, y);
		GL11.glVertex2f(x, y);
	}
		
	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glBegin(GL11.GL_QUADS);
		switch(Main.GAME) {
			case "Life": 
				Life.render();
				Life.live();
				break;
			case "Wire":
				Wire.render();
				Wire.live();
				break;
			case "Maze":
				Maze.render(true);
				Maze.live();
				break;
		}
		GL11.glEnd();
		
		switch (Main.GAME) {
			case "Maze":
				GL11.glLineWidth(2);
				break;
		}

		GL11.glBegin(GL11.GL_LINES);
		switch (Main.GAME) {
			case "Maze":
				Maze.render(false);
				break;
		}
		GL11.glEnd();
	}
}
