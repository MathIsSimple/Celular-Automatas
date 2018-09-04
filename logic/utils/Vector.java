package logic.utils;

public class Vector {
	public float x, y;
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector v2) {
		x += v2.x;
		y += v2.y;
	}
	
	public void sub(Vector v2) {
		x -= v2.x;
		y -= v2.y;
	}
	
	public void mult(Vector v2) {
		x *= v2.x;
		y *= v2.y;
	}
	
	public void mult(float n) {
		x *= n;
		y *= n;
	}
	
	public void div(Vector v2) {
		x /= v2.x;
		y /= v2.y;
	}
	
	public void div(float n) {
		x /= n;
		y /= n;
	}
	
}
