package automata.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import automata.main.rendering.Render;

public class DisplayManager {
	public static boolean isClosed() {
		return Display.isCloseRequested();
	}
	
	public static void updateScreen() {
		Display.update();
	}
	
	public static void destroy() {
		Display.destroy();
	}
	
	public static void create(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setFullscreen(false);
			Display.setResizable(false);
			Display.setTitle(title);
			Display.create();
			
			Render.prepareOPENGL(width, height);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
}
