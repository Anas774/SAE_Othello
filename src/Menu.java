import java.util.Scanner;

public class Menu {

    public static void MenuOthello() {
        Scanner scanner  = new Scanner(System.in);

        int choix = 0;

        System.out.println("BUT DU JEU :");
        System.out.println("Avoir plus de pions de sa couleur que l’adversaire à la fin de la partie. Celle-ci s’achève lorsque aucun des deux joueurs ne peut plus jouer de coup légal. " + ("\n") +
                "Cela intervient généralement lorsque les 64 cases sont occupées.");

        System.out.println(("\n") + "1. Jouer contre un joueur");
        System.out.println("2. Jouer contre l'ordinateur ");

        System.out.print(("\n") + "Choisissez votre mode de jeu (Tapez 1 ou 2) :" + ("\t"));
        choix = scanner.nextInt();

        switch (choix) {
            case 1:
                Joueur_VS_Joueur.boucleDeJeuJoueurVSJoueur();
                break;

            case 2:
                // Pour joueur avec une IA
                break;
        }
    }
}
