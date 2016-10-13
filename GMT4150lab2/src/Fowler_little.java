import java.util.ArrayList;
import java.util.List;

public class Fowler_little {
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;
	private final Point[][] grid;

	public Fowler_little(Point[][] grid) {
		this.grid = grid;
	}

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
				System.out.print(p.getZ() + " ");
			}
			System.out.println("\n");
		}
		System.out.println("*************");
	}

	public static void displayCycle(String[][] matrix, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("*************");
	}

	// TODO compter le nombre de cycles
	public int countCycle(String[][] elevationProfile) {
		List<String> list = new ArrayList<>();
		int n = 0;
		list.add(elevationProfile[0][0]);
		list.add(elevationProfile[0][1]);
		list.add(elevationProfile[0][2]);
		list.add(elevationProfile[1][2]);
		list.add(elevationProfile[2][2]);
		list.add(elevationProfile[2][1]);
		list.add(elevationProfile[2][0]);
		list.add(elevationProfile[1][0]);
		list.add(elevationProfile[0][0]);

		String current = list.get(0);

		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).equals(list.get(i + 1))) {
				// next
			} else if (list.get(i + 1).equals(current)) {
				n++;
			}
		}
		System.out.println("+++++++++++++++++++++++++++++++" + n);
		return n;
	}

	public void setProfile(Point[][] matrix3) {
		String[][] elevationProfile = cycle(matrix3);
		displayCycle(elevationProfile, 3);
		int nSommet = 0;
		int nCreux = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// On ne compte pas le centre
				if (i == 1 && j == 1) {
					// Nothing
				} else {
					if (elevationProfile[i][j].equals("+")) {
						nSommet++;
					} else {
						nCreux++;
					}
				}
			}

		}
		if (nSommet == 8) {
			System.out.println("C'est un creux !! ");
		} else if (nCreux == 8) {
			System.out.println("C'est un sommet !");
		} else {
			System.out.println("Cherchons !");
			int nCycle = countCycle(elevationProfile);
			if (nCycle >= 2 && nCycle <= 4) {
				System.out.println("C'est une passe !");
			} else {
				System.out.println("ben je sais pas");
			}
		}
	}

	private double egality(Point[][] matrix3, int i, int j, double elevCenter) {
		double diff = 0;
		int posX = matrix3[i][j].getPosX();
		int posY = matrix3[i][j].getPosY();
		// coin haut gauche
		if (i == 0 && j == 0) {
			if (posX > 0) {
				if (posY > 0) {
					diff = elevCenter - grid[posX - 1][posY - 1].getZ();
				} else {
					diff = elevCenter - grid[posX - 1][posY].getZ();
				}
			} else {
				diff = elevCenter - grid[posX][posY - 1].getZ();
			}

		} else if (i == 0 && j == 1) {
			if (posX > 0) {
				diff = elevCenter - grid[posX - 1][posY].getZ();
			} else {
				diff = 1;// FAUX
			}
			// coin haut droite
		} else if (i == 0 && j == 2) {
			diff = elevCenter - grid[posX + 1][posY + 1].getZ();
		} else if (i == 1 && j == 0) {
			if (posY > 0) {
				diff = elevCenter - grid[posX][posY - 1].getZ();
			} else {
				diff = 1; // FAUX
			}
			// Coin bas gauche
		} else if (i == 2 && j == 0) {
			if (posY > 0) {
				if (posX < NB_ROW - 1) {
					diff = elevCenter - grid[posX + 1][posY - 1].getZ();
				} else {
					diff = elevCenter - grid[posX][posY - 1].getZ();
				}
			} else {
				diff = elevCenter - grid[posX + 1][posY].getZ();
			}
		} else if (i == 2 && j == 1) {
			if (posX < NB_ROW - 1) {
				diff = elevCenter - grid[posX + 1][posY].getZ();
			} else {
				diff = 1; // FAUX
			}
			// Coin bas droit
		} else if (i == 2 && j == 2) {
			if (posY < NB_COL - 1) {
				diff = elevCenter - grid[posX + 1][posY + 1].getZ();
			} else {
				diff = elevCenter - grid[posX + 1][posY].getZ();
			}
		} else if (i == 1 && j == 2) {
			if (posY < NB_COL - 1) {
				diff = elevCenter - grid[posX][posY + 1].getZ();
			} else {
				diff = 1;// FAUX
			}
		}
		return diff;

	}

	public String[][] cycle(Point[][] matrix3) {
		String[][] elevationProfile = new String[3][3];
		double elevCenter = matrix3[1][1].getZ();
		elevationProfile[1][1] = "s";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					// Nothing
				} else {
					// Différence entre l'élévation du point central et
					// l'élévation
					// de chaque point coté

					double diff = elevCenter - matrix3[i][j].getZ();
					while (elevationProfile[i][j] == null || elevationProfile[i][j].equals("=")) {
						if (diff < 0) {
							elevationProfile[i][j] = "+";
						} else if (diff > 0) {
							elevationProfile[i][j] = "-";
						} else {
							elevationProfile[i][j] = "=";
							diff = egality(matrix3, i, j, elevCenter);

						}
					}

				}
			}
		}
		return elevationProfile;
	}

	public void matrix3() {
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
				setProfile(matrix3);

			}
		}
	}

	public Point[][] selectPoints() {
		/// TODO
		matrix3();
		return null;
	}

}
