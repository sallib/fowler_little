import java.util.Objects;

public class Point {

	private final int id; // TODO A supprimer -> sert pour test
	private final double x;
	private final double y;
	private final double z;
	private Type type;
	private int posX; // Sauvegarde la position X sur la matrice originale
	private int posY;// Sauvegarde la position X sur la matrice originale

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

	public Type getType() {
		return this.type;
	}

	public void setType(Type t) {
		Objects.requireNonNull(t);
		this.type = t;
	}

	@Override
	public String toString() {
		return id + ";"+ x  + ";" + y + ";" + z + ";" + type;
	}
}
