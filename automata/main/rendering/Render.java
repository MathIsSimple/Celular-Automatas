package automata.main.rendering;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import automata.main.Main;
import automata.main.simulation.Life;
import automata.main.simulation.Maze;
import automata.main.simulation.Wire;
import logic.utils.Vector;

public class Render {
	
	private static Vector camera = new Vector(0, 0);
	private static Vector zoom   = new Vector(1, 1);
	
	public static void prepareOPENGL(int width, int height) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void renderQuad(int x, int y, int w, int h) {
		GL11.glVertex2f(x, y + h);
		GL11.glVertex2f(x + w, y + h);
		GL11.glVertex2f(x + w, y);
		GL11.glVertex2f(x, y);
	}
		
	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glPushMatrix();
		GL11.glTranslated(Mouse.getX(), Mouse.getY(), 0);
		GL11.glScaled(zoom.x, zoom.y, 1);
		GL11.glTranslated(-Mouse.getX(), -Mouse.getY(), -0);
		GL11.glTranslatef(camera.x, camera.y * zoom.y, 0);
		GL11.glBegin(GL11.GL_QUADS);
		switch(Main.Game) {
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
		
		switch (Main.Game) {
			case "Maze":
				GL11.glLineWidth(1);
				break;
		}

		GL11.glBegin(GL11.GL_LINES);
		switch (Main.Game) {
			case "Maze":
				Maze.render(false);
				break;
		}
		GL11.glEnd();
		GL11.glPopMatrix();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) camera.x += 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) camera.x -= 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) camera.y += 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) camera.y -= 1;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
		
		if (Mouse.isButtonDown(1)) {Mouse.setGrabbed(true);}
		
		if (Mouse.isGrabbed()) {
			camera.x -= Mouse.getDX();
			camera.y += Mouse.getDY();
		}

		int mouseDWheel = Mouse.getDWheel();
		
		if (mouseDWheel > 0) {
			zoom.x *= 1.01f; 
			zoom.y *= 1.01f;
		} 
		
		if (mouseDWheel < 0) {
			zoom.x /= 1.01f;
			zoom.y /= 1.01f;
		}		
	}
}
