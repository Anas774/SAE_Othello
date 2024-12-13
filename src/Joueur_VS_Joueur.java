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






}
