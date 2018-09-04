package automata.main.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import automata.main.simulation.Life;

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
			Life.render();
		GL11.glEnd();
		Life.live();
	}
}
