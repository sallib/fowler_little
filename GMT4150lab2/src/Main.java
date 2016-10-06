import java.io.IOException;
import java.util.List;

public class Main {

	private final static String FILE_NAME = "Grille_Lab2.csv";
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;



	// Main de test
	public static void main(String[] args) throws IOException {

		//Lecture du CSV et récupération d'une liste de Points
		List<Point> list = CsvFileReader.readFile(FILE_NAME);

		//Transforme la liste de point en grille de point 
		Point[][] grid = Grid.createInitialGrid(list);
		
		//Affiche les ID des points de la grille
		for (int i = 0; i < NB_ROW; i++) {
			for (int j = 0; j < NB_COL; j++) {
				Point p = grid[i][j];
				System.out.print(p.getId() + " ");
			}
			System.out.println("\n");
		}
	}

}
