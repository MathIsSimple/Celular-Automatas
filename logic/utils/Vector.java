package logic.utils;

public class Vector {
	public float x, y, z;
	
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Vector v2) {
		x += v2.x;
		y += v2.y;
		z += v2.z;
	}
	
	public void sub(Vector v2) {
		x -= v2.x;
		y -= v2.y;
		z -= v2.z;
	}
	
	public void mult(Vector v2) {
		x *= v2.x;
		y *= v2.y;
		z *= v2.z;
	}
	
	public void mult(float n) {
		x *= n;
		y *= n;
		z *= n;
	}
	
	public void div(Vector v2) {
		x /= v2.x;
		y /= v2.y;
		z /= v2.z;
	}
	
	public void div(float n) {
		x /= n;
		y /= n;
		z /= n;
	}
	
}
