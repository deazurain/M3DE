package m3de.math;

public class Vector {

	public float x;
	public float y;
	public float z;
	
	public Vector() {
		this(0, 0, 0);
	}
	
	public Vector(Vector v) {
		set(v.x, v.y, v.z);
	}
	
	public Vector(float x, float y, float z) {
		set(x, y, z);
	}
	
	public Vector set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public Vector invert() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}
	
	public Vector add(float dx, float dy, float dz) {
		x += dx;
		y += dy;
		z += dz;
		return this;
	}
	
	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
	
	public Vector addScaled(Vector v, float scalar) {
		x += v.x * scalar;
		y += v.y * scalar;
		z += v.z * scalar;
		return this;
	}
	
	public Vector subtract(float dx, float dy, float dz) {
		x -= dx;
		y -= dy;
		z -= dz;
		return this;
	}
	
	public Vector subtract(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}
	
	public Vector subtractScaled(Vector v, float scalar) {
		x -= v.x * scalar;
		y -= v.y * scalar;
		z -= v.z * scalar;
		return this;
	}
	
	public Vector scale(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	public Vector scale(float sx, float sy, float sz) {
		x *= sx;
		y *= sy;
		z *= sz;
		return this;
	}
	
	public Vector scale(Vector scalars) {
		x *= scalars.x;
		y *= scalars.y;
		z *= scalars.z;
		return this;
	}
	
	/**
	 * Returns the length, also called magnitude, of the vector. It is calculated using
	 * the Pythagorean theorem. This involves taking the root which is not required in
	 * some cases. Thats why there is also a cheaper 'lengthSquared()' method available. 
	 * @return The length of this vector. 
	 */
	public float length() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Returns the squared length, also called squared magnitude, of this vector. It is 
	 * calculated using the Pythagorean theorem. This operation is can be used in place of
	 * the 'length()' method to improve performance.  
	 * @return The squared length of this vector. 
	 */
	public float squaredLength() {
		return x*x + y*y + z*z;
	}
	
	/**
	 * Tries to make sure that the length of this vector becomes 1. This is impossible
	 * when the length is 0. Attempting to normalize a vector with length 0 has no effect. 
	 * @return The vector itself. 
	 */
	public Vector normalize() {
		float l = length();
		if(l != 0) {
			scale(1/l);
		}
		return this;
	}
	
	/**
	 * Calculates the difference vector between two points that are represented as vectors. 
	 * @param from The origin of the difference vector
	 * @param to The end point of the difference vector
	 * @return The difference vector. 
	 */
	public static Vector difference(Vector from, Vector to) {
		return new Vector(
				to.x - from.x,
				to.y - from.y,
				to.z - from.z);
	}
	
	/**
	 * Returns the component product of two vectors. In effect, the
	 * components are multiplied by each other. 
	 * @param a 
	 * @param b 
	 * @return The component product.
	 */
	public static Vector scale(Vector a, Vector b) {
		return new Vector(
				a.x*b.x,
				a.y*b.y,
				a.z*b.z);
	}
	
	/**
	 * Returns the scalar product of two vectors. A more common name for this
	 * operation is 'dot product'. It is calculated by taking the sum of the 
	 * product of the components. 
	 * @param a
	 * @param b
	 * @return The scalar product. 
	 */
	public static float dot(Vector a, Vector b) {
		return a.x*b.x + a.y*b.y + a.z*b.z;
	}
	
	/**
	 * Returns the vector product of two vectors. A more common name for this
	 * operation is 'cross product'. It's calculation is fairly complex in respect
	 * to the component- and dot product operations. 
	 * @param a
	 * @param b
	 * @return The vector product. 
	 */
	public static Vector cross(Vector a, Vector b) {
		return new Vector(
				a.y*b.z - a.z*b.y,
				a.z*b.x - a.x*b.z,
				a.x*b.y - a.y*b.x);
	}
	

}
