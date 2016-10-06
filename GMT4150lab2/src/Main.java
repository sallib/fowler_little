import java.io.IOException;
import java.util.List;

public class Main {

	private final static String FILE_NAME = "Grille_Lab2.csv";
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;

	public static Point[][] createGrid(List<Point> points) {
		Point[][] grid = new Point[NB_COL][NB_ROW];
		int index = 0; // index de points

		for (int j = 0; j < NB_COL; j++) { // Parcours des colonnes de
											// gauche à droite
			for (int i = NB_ROW - 1; i >= 0; i--) {// Parcours des lignes de bas
													// en haut
				grid[i][j] = points.get(index);
				index++;
			}
		}
		return grid;
	}

	// Main de test
	public static void main(String[] args) throws IOException {

		List<Point> list = CsvFileReader.readFile(FILE_NAME);

		Point[][] grid = createGrid(list);
		for (int i = 0; i < NB_ROW; i++) {
			for (int j = 0; j < NB_COL; j++) {
				Point p = grid[i][j];
				System.out.print(p.getId() + " ");
			}
			System.out.println("\n");
		}
	}

}
