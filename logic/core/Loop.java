package logic.core;

import org.lwjgl.opengl.Display;

import automata.main.DisplayManager;
import automata.main.Main;
import automata.main.rendering.Render;

public class Loop {
		
	private static boolean first = true;
	
	public static void loop() {
		while(Main.isRunning) {
			if (DisplayManager.isClosed()) Main.isRunning = false;
			if (!first) Render.render();
			DisplayManager.updateScreen();
			Display.sync(Main.FPS);
			first = false;
		}
		DisplayManager.destroy();
		System.exit(0);
	}
}
