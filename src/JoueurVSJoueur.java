public class JoueurVSJoueur {

    public static final int LIGNES = 8;
    public static final int COLONNES = 8;
    public static char[][] plateau = new char[LIGNES][COLONNES];
    public static char joueur = 'B';

    public static void initialiserPlateau() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                plateau[i][j] = ' ';
            }
        }
    }

    public static void afficherGrille() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {

                if (plateau[i][j] == 'B') {
                    System.out.print(joueur);
                }
                else if (plateau[i][j] == 'N') {
                    System.out.print(joueur);
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }



}
