import java.util.Scanner;

public class Menu {

    public static void MenuOthello() throws InterruptedException {
        Scanner scanner  = new Scanner(System.in);

        int choix = 0;

        System.out.println("\nBUT DU JEU :");
        System.out.println("\nAvoir plus de pions de sa couleur que l’adversaire à la fin de la partie. Celle-ci s’achève lorsque aucun des deux joueurs ne peut plus jouer de coup légal. " + ("\n") +
                "Cela intervient généralement lorsque les 64 cases sont occupées.");

        System.out.println(("\n") + "1. Jouer contre un joueur");
        System.out.println("2. Jouer contre l'ordinateur ");

        do {
            System.out.print("\nChoisissez votre mode de jeu (Tapez 1 ou 2) :\t");
            while (!scanner.hasNextInt()) {
                System.out.print("\nEntrée invalide. Veuillez entrer un nombre (1 ou 2) : ");
                scanner.next();
            }
            choix = scanner.nextInt();
        } while (choix < 1 || choix > 2);

        switch (choix) {
            case 1:
                Joueur_VS_Joueur.boucleDeJeuJoueurVSJoueur();
                break;

            case 2:
                Joueur_VS_IA.boucleDeJeuJoueurVSOrdi();
                break;
        }
    }
}
