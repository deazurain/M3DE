package m3de.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public abstract class Game {

	private long lastDelta;
	
	private int FPS;
	private int FPSCount;
	private long lastFPS;
	
	
	public Game() {
		
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} 
		catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		initialize();
		
		lastDelta = getTimeInMiliseconds();
		lastFPS = getTimeInMiliseconds();
		
		while (!Display.isCloseRequested()) {
			
			updateFPS();
			
			Display.setTitle("FPS: " + FPS);
			
			update(getDelta());
			
			render();
			
			Display.update();
			
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private int getDelta() {
		long now = getTimeInMiliseconds();
		int d = (int)(now - lastDelta);
		lastDelta = now;
		return d;
	}
	
	private void updateFPS() {
		long now = getTimeInMiliseconds();
		if(now - lastFPS < 1000) {
			FPSCount++;
		}
		else {
			FPS = FPSCount;
			FPSCount = 1;
			lastFPS = now;
		}
	}
	
	public int getFPS() {
		return FPS;
	}
	
	private long getTimeInMiliseconds() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
		
	public abstract void initialize();
	public abstract void update(int ms);
	public abstract void render();
	
}
