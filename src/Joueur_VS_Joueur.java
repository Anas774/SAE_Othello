import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Joueur_VS_Joueur {

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

    public static void afficherPlateau() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                if (plateau[i][j] == 'B') {
                    System.out.print(plateau[i][j]);
                }
                else if (plateau[i][j] == 'N') {
                    System.out.print(plateau[i][j]);
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void placerJeton() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                if (plateau[i][j] == ' ') {
                    plateau[i][j] = joueur;
                }
            }
        }
    }

    public static boolean peutPrendrePion(char[][] plateau, int x, int y, char joueur) {

        if (plateau[x][y] != ' ') {
            return false; // La case doit être vide pour jouer
        }

        char adversaire = (joueur == 'N') ? 'B' : 'N';

        // Directions : haut, bas, gauche, droite, et diagonales
        int[] directionsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionsY = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            int i = x + directionsX[d];
            int j = y + directionsY[d];
            boolean trouveAdversaire = false;

            // Parcourir dans une direction donnée
            while (estDansLePlateau(i, j) && plateau[i][j] == adversaire) {
                trouveAdversaire = true;
                i += directionsX[d];
                j += directionsY[d];
            }

            // Si un pion de notre couleur est trouvé après des adversaires
            if (trouveAdversaire && estDansLePlateau(i, j) && plateau[i][j] == joueur) {
                return true;
            }
        }

        return false;
    }

    // Vérifier si une position est dans le plateau
    public static boolean estDansLePlateau(int x, int y) {
        return x >= 0 && x < LIGNES && y >= 0 && y < COLONNES;
    }





}
