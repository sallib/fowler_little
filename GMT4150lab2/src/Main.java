import java.io.IOException;
import java.util.List;

public class Main {

	private final static String FILE_NAME = "Grille_Lab2.csv";
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;

	/**
	 * Affichage des ID des points d'une matrice ==> Pour les tests !
	 * @param matrix
	 * @param size
	 */
	public static void displayMatrix(Point[][] matrix, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Point p = matrix[i][j];
				System.out.print(p.getId() + " ");
			}
			System.out.println("\n");
		}
		System.out.println("*************");
	}

	

	// Main de test
	public static void main(String[] args) throws IOException {

		// Lecture du CSV et récupération d'une liste de Points
		List<Point> list = CsvFileReader.readFile(FILE_NAME);

		// Transforme la liste de point en grille de point
		Point[][] grid = Grid.createInitialGrid(list);

		// Affiche les ID des points de la grille ==> OK
		//displayMatrix(grid, NB_COL);

		//Fichier de résultat
		Fowler_little fl = new Fowler_little(grid);
		
		Point[][] finalGrid = fl.selectPoints();
		
		
		

	}

}
