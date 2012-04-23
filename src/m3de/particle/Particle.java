package m3de.particle;

import m3de.math.Vector;

public class Particle {
	
	public static final float DEFAULT_DAMPING = 0.98f;
	public static final float DEFAULT_INVERTED_MASS = 1f;
	
	private Vector position;
	private Vector velocity;
	private Vector acceleration;
	private Vector accumulatedForces;
	
	private float damping;

	private float invertedMass;
	
	public Particle() {
		this(0, 0, 0);
	}
	
	public Particle(float x, float y, float z) {
		this(x, y, z, DEFAULT_INVERTED_MASS);
	}
	
	public Particle(float x, float y, float z, float invertedMass) {
		this(x, y, z, invertedMass, DEFAULT_DAMPING);
	}
	
	public Particle(float x, float y, float z, float invertedMass, float damping) {
		this.position = new Vector(x, y, z);
		this.velocity = new Vector();
		this.acceleration = new Vector();
		this.accumulatedForces = new Vector();
		this.invertedMass = invertedMass;
		this.damping = damping;
	}

	public Particle applyForce(Vector force) {
		accumulatedForces.add(force);
		return this;
	}
	
	public Particle clearForces() {
		accumulatedForces.set(0, 0, 0);
		return this;
	}
	
	public Particle integrate(int ms) {
		// nothing should happen when no time has passed
		if(ms <= 0) return this;
		
		// calculate acceleration caused by forces
		Vector x = new Vector(acceleration);
		x.addScaled(accumulatedForces, invertedMass);
		
		// update linear velocity from the acceleration
		velocity.addScaled(x, ms);
		
		// impose drag
		velocity.scale(damping);//(float) Math.pow(damping, ms));

		// update linear position
		// p' = p + v*t + (a*t*t)/2
		position.addScaled(velocity, ms);
		//position.addScaled(acceleration, ms * ms / 2); // high burst extra accuracy
		
		return this;
	}

	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getZ() {
		return position.z;
	}
}
