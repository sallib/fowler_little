import java.util.ArrayList;
import java.util.List;

public class Fowler_little {
	private final static int NB_ROW = 7;
	private final static int NB_COL = 7;
	private final Point[][] grid;

	public Fowler_little(Point[][] grid) {
		this.grid = grid;
	}

	public Point[][] getGrid() {
		return grid;
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
				System.out.print(p);
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

	/**
	 * Compte le nombre de cycle
	 * @param elevationProfile
	 * @return nombre de cycle
	 */
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
		return n;
	}

	/**
	 * Attribut un profil chaque point (Sommet / Creux / Passe / Indefini)
	 * 
	 * @param matrix3
	 */
	public void setProfile(Point[][] matrix3) {
		// Création du profil d'élévation (+ ou -)
		String[][] elevationProfile = cycle(matrix3);
		displayCycle(elevationProfile, 3); // TODO : a supprimer
		int nSommet = 0;
		int nCreux = 0;
		int nEgal = 0;
		// Compte le nombre de + et le nombre de -
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					// Nothing : on ne compte pas le centre
				} else {
					if (elevationProfile[i][j].equals("+")) {
						nSommet++;
					} else if (elevationProfile[i][j].equals("-")) {
						nCreux++;
					} else {
						nEgal++;
					}
				}
			}

		}
		// On récupère les coordonnées de la matrice initiale
		int posX = matrix3[1][1].getPosX();
		int posY = matrix3[1][1].getPosY();

		// Uniquement cas où l'égalité est en bordure de la matrice
		if (nEgal > 0) {
			grid[posX][posY].setType(Type.INDEFINI);
		} else if (nSommet == 8) {
			grid[posX][posY].setType(Type.CREUX);
		} else if (nCreux == 8) {
			grid[posX][posY].setType(Type.SOMMET);
		} else {
			// On compte le nb de cycle pour savoir si c'est une passe
			int nCycle = countCycle(elevationProfile);
			if (nCycle >= 2 && nCycle <= 4) {
				grid[posX][posY].setType(Type.PASSE);
			} else {
				grid[posX][posY].setType(Type.INDEFINI);
			}
		}
	}

	/**
	 * Traite le cas ou le point central a une élévationn égale au point de coté
	 * Dans ce cas on regarde les points autours
	 * 
	 * @param matrix3
	 * @param i
	 * @param j
	 * @param elevCenter
	 * @return La différence d'élévation
	 */
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
				diff = -9999;
			}
			// coin haut droite
		} else if (i == 0 && j == 2) {
			if (posX < NB_ROW - 1) {
				if (posY < NB_COL - 1) {
					diff = elevCenter - grid[posX + 1][posY + 1].getZ();
				} else {
					diff = elevCenter - grid[posX + 1][posY].getZ();
				}
			} else {
				diff = elevCenter - grid[posX][posY + 1].getZ();
			}
		} else if (i == 1 && j == 0) {
			if (posY > 0) {
				diff = elevCenter - grid[posX][posY - 1].getZ();
			} else {
				diff = -9999;
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
				diff = -9999;
			}
			// Coin bas droit
		} else if (i == 2 && j == 2) {
			if (posY < NB_COL - 1) {
				if (posX < NB_ROW - 1) {
					diff = elevCenter - grid[posX + 1][posY + 1].getZ();
				} else {
					diff = elevCenter - grid[posX][posY + 1].getZ();
				}
			} else {
				diff = elevCenter - grid[posX + 1][posY].getZ();
			}
		} else if (i == 1 && j == 2) {
			if (posY < NB_COL - 1) {
				diff = elevCenter - grid[posX][posY + 1].getZ();
			} else {
				diff = -9999;
			}
		}
		return diff;

	}

	/**
	 * Création d'une matrice avec le profil d'élévation par rapport au point
	 * central
	 * 
	 * @param matrix3
	 * @return matrice avec le profile d'élévation (+ et -)
	 */
	public String[][] cycle(Point[][] matrix3) {
		String[][] elevationProfile = new String[3][3];
		double elevCenter = matrix3[1][1].getZ();
		elevationProfile[1][1] = "s";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					// Nothing : on ne passe pas le centre
				} else {
					// Différence entre l'élévation du point central et
					// l'élévation
					// de chaque point coté

					double diff = elevCenter - matrix3[i][j].getZ();
					// Analyse des points voisins : on boucle jusqu'à déterminer
					// + ou -
					while (elevationProfile[i][j] == null) {
						//Cas d'égalité en bordure de matrice
						if (diff == -9999){
							elevationProfile[i][j] = "?";
						}
						else if (diff < 0) {
							elevationProfile[i][j] = "+";
						} else if (diff > 0) {
							elevationProfile[i][j] = "-";
						} else {
							// Cas d'égalité
							diff = egality(matrix3, i, j, elevCenter);

						}
					}

				}
			}
		}
		return elevationProfile;
	}

	/**
	 * Création des matrices 3x3 (fenêtre glissante) On
	 */
	public void matrix3() {
		Point[][] matrix3 = new Point[3][3];
		for (int row = 0; row < NB_ROW - 2; row++) {
			for (int col = 0; col < NB_COL - 2; col++) {
				int a = 0;
				int b = 0;
				for (int i = row; i < row + 3; i++) {
					for (int j = col; j < col + 3; j++) {
						matrix3[a][b] = grid[i][j];
						b++;
					}
					b = 0;
					a++;
				}
				// Attribution des profile type (Sommet / Creux / Passe /
				// Indefini)
				setProfile(matrix3);

			}
		}
	}

	/**
	 * Analyse la matrice terrain selon une grille glissante de 2x2.
	 * 
	 * @return information de Crête ou de Talweg ou d'indéfinie.
	 * @param grid
	 *            le terrain
	 */
	private void matrix2() {
		// parcours du grid terrain entier.
		for (int row = 1; row < NB_ROW - 1; row++) { // de row = 1 à row = 5
														// /////// etape A
			for (int col = 1; col < NB_COL - 1; col++) { // de col = 1 à col = 5
															// /////// etape A
				// pour chaque cellule analysable générer une analyse de 4
				// fenêtres de 2x2
				// une cellule analysable doit être une passe ou indéfini.
				if (grid[row][col].getType() == Type.PASSE || grid[row][col].getType() == Type.INDEFINI) {
					Point current = grid[row][col];
					// parcourir current row-1 à row+1 et col-1 à col+1 =
					// fenêtre 2x2 == parcours de la fenêtre 3x3 à décomposer en
					// fenêtre 2x2
					Point[][][] fenetres2x2 = new Point[4][2][2];
					decomposeFenetre2x2(row, col, fenetres2x2); /////// etape
																/////// C
					// pour chacune des 4 fenêtres :
					analyseFenetre2x2(fenetres2x2, current); /////// etape D
				}
				// si jamais le plus haut : crête
				// si jamais le plus bas : talweg
				// IsNotCrete == TRUE && IsNotTalweg == TRUE ? status =
				// indefinie : status = crete || talweg
			}
			// TODO : Traitements à faire !!
		}

	}

	/**
	 * Méthode d'analyse des 4 fenêtres pour identifier les crêtes et talweg.
	 * Modifie la valeur Type des points en fonction du résultat {TALWEG,
	 * CRETE,INDEFINI}
	 * 
	 * @param fenetres2x2
	 * @param current
	 */
	private void analyseFenetre2x2(Point[][][] fenetres2x2, Point current) { /////// etape
																				/////// D
		Point max = fenetres2x2[0][0][0];// définition du min et max au point
											// haut gauche pour démarrer le
											// traitement.
		Point min = fenetres2x2[0][0][0];
		// Pour chaque fenêtre 2x2, current est-il > ou < à la valeur testée.
		// max MAX ? rien : max = current
		// min MIN ? rien : min = current
		for (Point[][] fenetre : fenetres2x2) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (fenetre[i][j].getZ() > max.getZ()) {
						max = fenetre[i][j];
					} else if (fenetre[i][j].getZ() < min.getZ()) {
						min = fenetre[i][j];
					}
				}
			}
		}
		changeCurrentStatus(current, max, min);

	}

	/**
	 * Méthode de mise à jour du statut du point courrant.
	 * 
	 * @param current
	 *            point analysé par les fenêtres 2x2
	 * @param max
	 *            point le plus élevé retenu des 4 fenêtres 2x2
	 * @param min
	 *            point le plus bas retenu des 4 fenêtres 2x2
	 */
	private void changeCurrentStatus(Point current, Point max, Point min) {
		boolean isNotRidge = false;
		boolean isNotThalweg = false;
		if (current.getId() == max.getId()) { // si "jamais le plus haut", peut
												// être une talweg.
			isNotThalweg = true;
		}
		if (current.getId() == min.getId()) {// si "jamais le plus bas", peut
												// être une crête.
			isNotRidge = true;
		}
		if (isNotRidge && isNotThalweg) { // si current est les deux, on
											// l'élimine car le point est
											// quelconque
			current.setType(Type.INDEFINI);
		} else if (isNotRidge) {
			current.setType(Type.TALWEG);
		} else if (isNotThalweg) {
			current.setType(Type.CRETE);
		} else {
			current.setType(Type.PASSE);// si current est aucun des deux: reste
										// à passe.
		}

	}

	/**
	 * @param row
	 *            identifiant colonne de la cellule courante
	 * @param col
	 *            identifiant ligne de la cellule courante
	 * @param grid
	 *            matrice terrain
	 * @param fenetres2x2
	 *            paramètre complété par la méthode
	 */
	private void decomposeFenetre2x2(int row, int col, Point[][][] fenetres2x2) { /// etape
																					/// C
		int a = 0; // identifiant colonne de matrice3x3
		int b = 0; // identifiant ligne de matrice3x3
		for (int i = row - 1; i < row + 2; i++) { /////// etape B
			for (int j = col - 1; j < col + 2; j++) { /////// etape B
				switch (a) {
				case 0:
					switch (b) {
					case 0:
						fenetres2x2[0][a][b] = grid[i][j];
						break;
					case 1:
						fenetres2x2[0][a][b] = grid[i][j];
						fenetres2x2[2][a][b - 1] = grid[i][j];
						break;
					case 2:
						fenetres2x2[2][a][b - 1] = grid[i][j];
						break;
					default: // never happens
						break;
					}
					break;
				case 1:
					switch (b) {
					case 0:
						fenetres2x2[0][a][b] = grid[i][j];
						fenetres2x2[1][a - 1][b] = grid[i][j];
						break;
					case 1:
						fenetres2x2[0][a][b] = grid[i][j];
						fenetres2x2[1][a - 1][b] = grid[i][j];
						fenetres2x2[2][a][b - 1] = grid[i][j];
						fenetres2x2[3][a - 1][b - 1] = grid[i][j];
						break;
					case 2:
						fenetres2x2[2][a][b - 1] = grid[i][j];
						fenetres2x2[3][a - 1][b - 1] = grid[i][j];
						break;
					default: // never happens
						break;
					}
					break;
				case 2:
					switch (b) {
					case 0:
						fenetres2x2[1][a - 1][b] = grid[i][j];
						break;
					case 1:
						fenetres2x2[1][a - 1][b] = grid[i][j];
						fenetres2x2[3][a - 1][b - 1] = grid[i][j];
						break;
					case 2:
						fenetres2x2[3][a - 1][b - 1] = grid[i][j];
						break;
					default: // never happens
						break;
					}
					break;
				default:// never happens
					break;
				}
				b++;
			}
			b = 0;
			a++;
		}
	}

	public Point[][] selectPoints() {
		/// TODO
		matrix3();
		matrix2();
		return grid;
	}

}
