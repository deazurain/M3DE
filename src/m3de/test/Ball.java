package m3de.test;

import java.util.Random;

import m3de.math.Vector;
import m3de.particle.Particle;

import org.lwjgl.opengl.GL11;

public class Ball {
	private Particle particle;
	private float red;
	private float green;
	private float blue;
	private float width;
	private float height;
	
	public Ball() {
		Random r = new Random();
		
		width = 3 + r.nextFloat() * 13;
		height = 3 + r.nextFloat() * 13;
		
		float mass = (width * height)/9;
		
		particle = new Particle(0, 0, 0, 1/mass);
		
		red = r.nextFloat();
		green = r.nextFloat();
		blue = r.nextFloat();
	}
	
	public void jolt() {
		Random r = new Random();
		Vector v = new Vector(r.nextFloat() - 0.5f, r.nextFloat() - 0.5f, 0);
		v.normalize();
		v.scale(0.1f);
		particle.applyForce(v);
	}
	
	public void update(int ms) {
		
		float x = particle.getX();
		float y = particle.getY();
		
		Vector f = new Vector(0f, -0.001f, 0f);
		
		float l = 50, r = 750;
		float t = 550, b = 50;
		if(x < l) f.x += 0.002f;
		else if(x > r) f.x -= 0.002f;
		if(y < b) f.y += 0.002f;
		else if(y > t) f.y -= 0.002f;
		
		particle.applyForce(f);
		
		particle.integrate(ms);

		particle.clearForces();

	}
	
	public void render() {
		
		float x = particle.getX();
		float y = particle.getY();
		
		float l = x - width/2;
		float r = x + width/2;
		float t = y + height/2;
		float b = y - height/2;
		
		GL11.glColor3f(red, green, blue);
		
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(l,b);
		    GL11.glVertex2f(r,b);
		    GL11.glVertex2f(r,t);
		    GL11.glVertex2f(l,t);
		GL11.glEnd();
	}
	
	public String toString() {
		return "<Ball x:" + particle.getX() + " y:" + particle.getY() + ">";
	}

	public void explosionAt(float mx, float my, float range, float power) {
		float x = particle.getX();
		float y = particle.getY();
		
		float dx = x - mx;
		float dy = y - my;
		
		if(dx == 0) dx += 0.000001f;
		if(dy == 0) dy += 0.000001f;
		
		float d = dx*dx + dy*dy;
		
		if(d < range * range) {
			Vector push = new Vector(dx, dy, 0);
			push.normalize();
			push.scale(power*(1 - d/range/range));
			particle.applyForce(push);
		}
	}

	public void suctionAt(float mx, float my, float range, float power) {
		float x = particle.getX();
		float y = particle.getY();
		
		float dx = mx - x;
		float dy = my - y;
		
		if(dx == 0) dx += 0.000001f;
		if(dy == 0) dy += 0.000001f;
		
		float d = dx*dx + dy*dy;
		
		if(d < range * range) {
			Vector push = new Vector(dx, dy, 0);
			push.normalize();
			push.scale(power*(1 - d/range/range));
			particle.applyForce(push);
		}
	}
}
