import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class Joueur_VS_Joueur {

    public static final int LIGNES = 8;
    public static final int COLONNES = 8;
    public static char[][] plateau = new char[LIGNES][COLONNES];
    public static char joueur = 'B';

    public static void boucleDeJeuJoueurVSJoueur() {

        initialiserPlateau();

        Scanner scanner = new Scanner(System.in);

        boolean enCours = true;

        while (enCours) {

            afficherPlateau();

            System.out.println("\nJoueur " + joueur + ", choisissez une ligne (1-7): ");
            System.out.println("\nJoueur " + joueur + ", choisissez une colonne (1-7): ");

            int ligne, colonne;

            do {
                ligne = scanner.nextInt() - 1;
                colonne = scanner.nextInt() - 1;

                if (ligne < 0 || ligne > LIGNES) {
                    System.out.println("Veuillez choisir une ligne entre 1 et 7 : ");
                }
                else if (colonne < 0 || colonne >= COLONNES) {
                    System.out.println("Veuillez choisir une colonne entre 1 et 7 : ");
                }
            } while (ligne < 0 || ligne >= LIGNES || colonne < 0 || colonne >= COLONNES);

            placerJeton(ligne,colonne);


            /*
               Le joueur 'B' gagne


            if () {

            }

            */

            /*
               Le joueur 'B' gagne

            else if () {

            }

            */

            /*  Plateau remplie

            else if (plateauRemplie()) {
                afficherPlateau();
                System.out.println("Au des joueur a gagné");
                enCours = false;
            }

            */

            /*
                Aucun retournement possible

            else if () {

            }

            */

            joueur = (joueur == 'N') ? 'B' : 'N';

        }

    }

    public static void initialiserPlateau() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                plateau[i][j] = ' ';
            }
        }
        QuatrePion();
    }

    public static void afficherPlateau() {
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < LIGNES; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < COLONNES; j++) {
                if (plateau[i][j] == 'B') {
                    System.out.print("\u001B[37m\u25CF\u001B[0m ");
                }
                else if (plateau[i][j] == 'N') {
                    System.out.print("\u001B[30m\u25CF\u001B[0m ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.print(i+1);
            System.out.println();
        }
        System.out.println("  1 2 3 4 5 6 7 8");
    }

    public static void placerJeton(int ligne, int colonne) {
        if (plateau[ligne][colonne] == ' ') {
            plateau[ligne][colonne] = joueur;
        }
    }

    public static void QuatrePion() {
        int milieuLignes = plateau.length / 2;
        int milieuColonnes = plateau[0].length / 2;

        plateau[milieuLignes - 1][milieuColonnes - 1] = 'B';
        plateau[milieuLignes - 1][milieuColonnes] = 'N';
        plateau[milieuLignes][milieuColonnes - 1] = 'N';
        plateau[milieuLignes][milieuColonnes] = 'B';
    }

    public static boolean plateauRemplie() {
        for (int j = 0; j < COLONNES; j++) {
            if (plateau[0][j] == ' ') {
                return false;
            }
        }
        return true;
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

    public static void main(String[] args) {
        initialiserPlateau();

        afficherPlateau();
    }

}
