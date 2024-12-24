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

            int ligne, colonne;

            do {
                System.out.println("\nJoueur " + joueur + ", choisissez une ligne (1-8): ");
                ligne = scanner.nextInt() - 1;
                scanner.nextLine();

                System.out.println("\nJoueur " + joueur + ", choisissez une colonne (1-8): ");
                colonne = scanner.nextInt() - 1;
                scanner.nextLine();

                if (ligne < 0 || ligne >= LIGNES) {
                    System.out.println("Veuillez choisir une ligne entre 1 et 8 : ");
                }

                else if (colonne < 0 || colonne >= COLONNES) {
                    System.out.print("Veuillez choisir une colonne entre 1 et 8 : ");
                }

            } while (!peutPrendre(plateau,ligne,colonne,joueur));

            placerJeton(ligne,colonne);

            if (plateauRemplie()) {
                afficherPlateau();
                System.out.println("La grille est remplie");
                enCours = false;
            }


            /*
                Aucun retournement possible

            else if () {

            }

            */

            joueur = (joueur == 'N') ? 'B' : 'N';

        }

        if (compteurBlanc(plateau) > compteurNoir(plateau)) {
            afficherPlateau();
            System.out.println("Le joueur " + joueur + " a gagné gg");
        }

        else if (compteurBlanc(plateau) < compteurNoir(plateau)) {
            afficherPlateau();
            System.out.println("Le joueur " + joueur + " a gagné gg");
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
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                if (plateau[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean peutPrendre(char[][] plateau, int x, int y, char joueur) {
        if (plateau[x][y] != ' ') {

            System.out.println();

            System.out.println("Case injouable ! ");
            return false;
        }

        char adversaire = (joueur == 'N') ? 'B' : 'N';
        boolean coupValide = false;

        int[] directionsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionsY = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            if (parcourirDirection(plateau, x, y, directionsX[d], directionsY[d], joueur, adversaire, false)) {
                coupValide = true;
            }
        }

        if (coupValide) {
            for (int d = 0; d < 8; d++) {
                parcourirDirection(plateau, x, y, directionsX[d], directionsY[d], joueur, adversaire, true);
            }
        }

        return coupValide;
    }


    public static boolean parcourirDirection(char[][] plateau, int x, int y, int dirX, int dirY, char joueur, char adversaire, boolean retournerPions) {
        int i = x + dirX;
        int j = y + dirY;
        boolean trouveAdversaire = false;

        while (estDansLePlateau(i, j) && plateau[i][j] == adversaire) {
            trouveAdversaire = true;
            if (retournerPions) {
                plateau[i][j] = joueur;
            }
            i += dirX;
            j += dirY;
        }

        if (trouveAdversaire && estDansLePlateau(i, j) && plateau[i][j] == joueur) {
            return true;
        }

        return false;
    }

    public static boolean estDansLePlateau(int x, int y) {
        return x >= 0 && x < LIGNES && y >= 0 && y < COLONNES;
    }

    public static int compteurBlanc(char[][] tab) {
        int cpt = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == 'B') {
                    cpt++;
                }
            }
        }
        return cpt;
    }

    public static int compteurNoir(char[][] tab) {
        int cpt = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == 'N') {
                    cpt++;
                }
            }
        }
        return cpt;
    }

    public static int compteurVide(char[][] tab) {
        int cpt = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == ' ') {
                    cpt++;
                }
            }
        }
        return cpt;
    }

    public static void main(String[] args) {
        initialiserPlateau();

        afficherPlateau();
    }

}
