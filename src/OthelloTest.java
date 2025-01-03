import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OthelloTest {
    @BeforeEach
    void setUp() {
        Joueur_VS_Joueur.initialiserPlateau();
    }

    @Test
    void testInitialisationPlateau() {
        // Vérifie que les 4 pions initiaux sont bien placés
        assertEquals('B', Joueur_VS_Joueur.plateau[3][3], "Le pion en (4,4) devrait être Blanc");
        assertEquals('N', Joueur_VS_Joueur.plateau[3][4], "Le pion en (4,5) devrait être Noir");
        assertEquals('N', Joueur_VS_Joueur.plateau[4][3], "Le pion en (5,4) devrait être Noir");
        assertEquals('B', Joueur_VS_Joueur.plateau[4][4], "Le pion en (5,5) devrait être Blanc");
    }

    @Test
    void testPeutPrendre() {
        // Vérifie si un coup est valide pour Blanc
        assertTrue(Joueur_VS_Joueur.peutPrendre(Joueur_VS_Joueur.plateau, 2, 4, 'B'), "Le coup (3,5) devrait être valide pour Blanc");
        assertFalse(Joueur_VS_Joueur.peutPrendre(Joueur_VS_Joueur.plateau, 0, 0, 'B'), "Le coup (1,1) ne devrait pas être valide pour Blanc");
    }

    @Test
    void testPeutJouer() {
        // Vérifie que Blanc et Noir peuvent jouer sur le plateau initial
        assertTrue(Joueur_VS_Joueur.peutJouer('B'), "Blanc devrait pouvoir jouer avec l'état initial");
        assertTrue(Joueur_VS_Joueur.peutJouer('N'), "Noir devrait pouvoir jouer avec l'état initial");

        // Simule un plateau rempli où aucun coup n'est possible
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Joueur_VS_Joueur.plateau[i][j] = 'B';
            }
        }
        assertFalse(Joueur_VS_Joueur.peutJouer('B'), "Blanc ne devrait pas pouvoir jouer sur un plateau plein");
        assertFalse(Joueur_VS_Joueur.peutJouer('N'), "Noir ne devrait pas pouvoir jouer sur un plateau plein");
    }

    @Test
    void testAucunCoupPossible() {
        // Vérifie que la méthode détecte quand aucun coup n'est possible
        assertFalse(Joueur_VS_Joueur.aucunCoupPossible(), "Au début du jeu, il devrait y avoir des coups possibles");

        // Simule un plateau rempli où aucun coup n'est possible
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Joueur_VS_Joueur.plateau[i][j] = 'B';
            }
        }
        assertTrue(Joueur_VS_Joueur.aucunCoupPossible(), "Avec un plateau plein, aucun coup ne devrait être possible");
    }

    @Test
    void testPlateauRemplie() {
        // Simule un plateau rempli
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Joueur_VS_Joueur.plateau[i][j] = 'B';
            }
        }
        assertTrue(Joueur_VS_Joueur.plateauRemplie(), "Le plateau n'est pas rempli");
    }

}
