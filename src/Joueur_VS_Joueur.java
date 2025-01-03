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

            System.out.println();

            afficherPlateau();

            if (aucunCoupPossible()) {
                System.out.println();
                System.out.println("Aucun coup n'est possible pour Noir et/ou Blanc !");
                enCours = false;
            }

            if (!peutJouer(joueur)) {
                System.out.println();
                System.out.println("Le joueur " + joueur + " ne peut pas jouer et passe son tour !");
                joueur = (joueur == 'B') ? 'N' : 'B';

            } else {

                int ligne, colonne;

                do {
                    System.out.println("\nJoueur " + joueur + ", choisissez une ligne (1-8): ");
                    ligne = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.println("\nJoueur " + joueur + ", choisissez une colonne (1-8): ");
                    colonne = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (ligne < 0 || ligne >= LIGNES) {
                        System.out.println("\nVeuillez choisir une ligne entre 1 et 8 !\n");
                    }
                    else if (colonne < 0 || colonne >= COLONNES) {
                        System.out.println("\nVeuillez choisir une colonne entre 1 et 8 !\n");
                    }

                } while (!peutPrendre(plateau, ligne, colonne, joueur));

                placerJeton(ligne, colonne);

                retournerPions(plateau, ligne, colonne, joueur);

                if (plateauRemplie()) {
                    afficherPlateau();
                    System.out.println();
                    System.out.println("La grille est remplie ! ");
                    enCours = false;
                }

                joueur = (joueur == 'B') ? 'N' : 'B';

            }
        }

        calculerGagnant();
    }

    public static void initialiserPlateau() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                plateau[i][j] = ' ';
            }
        }
        plateau[3][3] = 'B';
        plateau[3][4] = 'N';
        plateau[4][3] = 'N';
        plateau[4][4] = 'B';
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
            System.out.println("\nCase injouable !");
            return false;
        }

        char adversaire = (joueur == 'B') ? 'N' : 'B';
        boolean coupValide = false;

        int[] directionsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionsY = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            if (parcourirDirection(plateau, x, y, directionsX[d], directionsY[d], joueur, adversaire, false)) {
                coupValide = true;
            }
        }

        return coupValide;
    }

    public static void retournerPions(char[][] plateau, int x, int y, char joueur) {
        char adversaire = (joueur == 'B') ? 'N' : 'B';

        int[] directionsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionsY = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            parcourirDirection(plateau, x, y, directionsX[d], directionsY[d], joueur, adversaire, true);
        }
    }

    public static boolean parcourirDirection(char[][] plateau, int x, int y, int dirX, int dirY, char joueur, char adversaire, boolean retournerPions) {
        int i = x + dirX;
        int j = y + dirY;
        boolean trouveAdversaire = false;

        while (estDansLePlateau(i, j) && plateau[i][j] == adversaire) {
            trouveAdversaire = true;
            i += dirX;
            j += dirY;
        }

        if (trouveAdversaire && estDansLePlateau(i, j) && plateau[i][j] == joueur) {

            if (retournerPions) {
                i = x + dirX;
                j = y + dirY;

                while (plateau[i][j] == adversaire) {
                    plateau[i][j] = joueur;
                    i += dirX;
                    j += dirY;
                }
            }
            return true;
        }

        return false;
    }

    public static boolean aucunCoupPossible() {
        for (int x = 0; x < LIGNES; x++) {
            for (int y = 0; y < COLONNES; y++) {
                if (plateau[x][y] == ' ') {
                    if (peutPrendre(plateau, x, y, 'N') || peutPrendre(plateau, x, y, 'B')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean peutJouer(char joueur) {
        for (int x = 0; x < LIGNES; x++) {
            for (int y = 0; y < COLONNES; y++) {
                if (plateau[x][y] == ' ' && peutPrendre(plateau, x, y, joueur)) {
                    return true;
                }
            }
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

    public static void calculerGagnant() {
        int scoreBlanc = compteurBlanc(plateau);
        int scoreNoir = compteurNoir(plateau);
        int casesVides = compteurVide(plateau);

        System.out.println("\nScore actuel :");
        System.out.println();
        System.out.println("Blanc : " + scoreBlanc);
        System.out.println("Noir : " + scoreNoir);
        System.out.println("Cases vides : " + casesVides);

        if (scoreBlanc > scoreNoir) {
            scoreBlanc += casesVides;
        }
        else if (scoreNoir > scoreBlanc) {
            scoreNoir += casesVides;
        }

        System.out.println("\nScore final :");
        System.out.println();
        System.out.println("Blanc : " + scoreBlanc);
        System.out.println("Noir : " + scoreNoir);

        if (scoreBlanc > scoreNoir) {
            System.out.println();
            System.out.println("Le joueur Blanc a gagné !");
        }
        else if (scoreNoir > scoreBlanc) {
            System.out.println();
            System.out.println("Le joueur Noir a gagné !");
        }
        else {
            System.out.println();
            System.out.println("Match nul !");
        }
    }


}
