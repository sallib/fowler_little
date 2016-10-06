
public class Point {

	private final int id;
	private final double x;
	private final double y;
	private final double z;
	private final Type type;

	public Point(int id, double x, double y, double z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = Type.INDEFINI;
	}

	public int getId(){
		return this.id;
	}
	@Override
	public String toString() {
		return "Point [id=" + id + "x=" + x + ", y=" + y + ", z=" + z + ", type=" + type + "]";
	}
}
