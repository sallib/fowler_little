import java.util.List;

public class Fowler_little {
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;

	/**
	 * Affichage des ID des points d'une matrice ==> Pour les tests !
	 * 
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

	// TODO
	public static int countCycle() {
		return 0;
	}

	public static void matrix3(Point[][] grid) {
		// Matrice 3x3
		// Divise la grille de points "grid" en matrices 3x3 (fenêtre glissante)
		// TODO :
		// comparer les points coté avec le centre de la matrice (SIGNE + ou -)
		// Déterminer si c'est un SOMMET, CREUX ou INDEFINI
		// Si INDEFINI => compter le nombre de cycle
		// Si Nombre de cycle est compris entre 2 et 4 => PASSE
		Point[][] matrix3 = new Point[3][3];
		for (int row = 0; row < NB_ROW - 2; row++) {
			for (int col = 0; col < NB_COL - 2; col++) {
				int a = 0;
				int b = 0;
				System.out.println("**" + row + " " + col);
				for (int i = row; i < row + 3; i++) {
					for (int j = col; j < col + 3; j++) {
						matrix3[a][b] = grid[i][j];
						b++;
					}
					b = 0;
					a++;
				}
				displayMatrix(matrix3, 3);
				// TODO : Traitements à faire !!
			}
		}
	}

	public static Point[][] selectPoints(Point[][] grid) {
		/// TODO
		matrix3(grid);
		return null;
	}

}
