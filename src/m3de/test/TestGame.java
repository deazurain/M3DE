package m3de.test;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class TestGame extends Game {
	
	private Ball balls[];

	@Override
	public void initialize() {
	
		System.out.println("Initializing TestGame");
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		balls = new Ball[10000];
		
		for(int i = 0; i < balls.length; i++) {
			balls[i] = new Ball();
		}
	}

	@Override
	public void update(int ms) {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			for(Ball b : balls) {
				b.jolt();
			}
		}
		
		if(Mouse.isButtonDown(0)) {
			float mx = Mouse.getX();
			float my = Mouse.getY();
			for(Ball b : balls) {
				b.explosionAt(mx, my, 200f, 0.1f);
			}
		}
		
		if(Mouse.isButtonDown(1)) {
			float mx = Mouse.getX();
			float my = Mouse.getY();
			for(Ball b : balls) {
				b.suctionAt(mx, my, 400f, 0.05f);
			}
		}
		
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
			    System.out.println("A Key Pressed");
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
			    System.out.println("A Key Released");
		        }
		    }
		}
		
		for(Ball b : balls) {
			b.update(ms);
		}
		
	}

	@Override
	public void render() {
		
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		
		
		
		
		// draw colored quad
		GL11.glColor3f(0f, 0.4f, 0f);
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(50,550);
		    GL11.glVertex2f(750,550);
		    GL11.glVertex2f(750,50);
		    GL11.glVertex2f(50,50);
		GL11.glEnd();
		
		for(Ball b : balls) {
			b.render();
		}
		
		
	}


}
