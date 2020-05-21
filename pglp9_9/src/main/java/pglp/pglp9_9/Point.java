package pglp.pglp9_9;

public class Point {

	private int x;
	private int y;

	static Point PointFactory(int X, int Y) {

		return new Point(X, Y);
	}

	public int getX() {
		return x;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public Point() {
		// TODO Auto-generated constructor stub
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move(int x2, int y2) {
		this.x += x2;
		this.y += y2;

	}

}
