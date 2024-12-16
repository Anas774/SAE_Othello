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


            /*              Le joueur 'B' gagne

            if () {

            }

            */

            /*              Le joueur 'N' à gagné

            else if () {

            }

            */

            /*              Le plateau est rempli

            else if () {

            }

            */

            /*             Aucun retournement possible

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

    public static boolean grilleRemplie() {
        for (int j = 0; j < COLONNES; j++) {
            if (plateau[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initialiserPlateau();

        afficherPlateau();
    }

}
