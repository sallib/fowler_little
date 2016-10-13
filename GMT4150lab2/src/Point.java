
public class Point {

	private final int id; // TODO A supprimer -> sert pour test
	private final double x;
	private final double y;
	private final double z;
	private final Type type;
	private int posX;
	private int posY;

	public Point(int id, double x, double y, double z) {
		this.id = id; // TODO A supprimer -> sert pour test
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = Type.INDEFINI;
	}

	public int getId() {
		return this.id;
	}

	public double getZ() {
		return this.z;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z + ", type=" + type + "]";
	}
}
