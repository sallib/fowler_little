import java.util.List;

public class Grid {
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;
	
	/**
	 * Création de la grille de points initiale
	 * 
	 * @param points
	 * @return
	 */
	public static Point[][] createInitialGrid(List<Point> points) {
		Point[][] grid = new Point[NB_COL][NB_ROW];
		int index = 0; // index de points

		for (int j = 0; j < NB_COL; j++) { // Parcours des colonnes de
											// gauche à droite
			for (int i = NB_ROW - 1; i >= 0; i--) {// Parcours des lignes de bas
													// en haut
				Point tmp = points.get(index);
				tmp.setPosX(i);
				tmp.setPosY(j);
				grid[i][j] = tmp;
				index++;
			}
		}
		return grid;
	}
}
