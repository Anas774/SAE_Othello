import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Joueur_VS_Joueur {

    public static final int LIGNES = 8;
    public static final int COLONNES = 8;
    public static char[][] plateau = new char[LIGNES][COLONNES];
    public static char joueur = 'B';

    public static void boucleDeJeu() {

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

    public static void placerJeton() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                if (plateau[i][j] == ' ') {
                    plateau[i][j] = joueur;
                }
            }
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

    public static void main(String[] args) {
        initialiserPlateau();

        afficherPlateau();
    }

}
