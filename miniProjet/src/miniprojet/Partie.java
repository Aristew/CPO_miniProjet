package miniprojet;


import java.util.Scanner;

/**
 * La classe Partie gère une session de jeu "Light Off", où le joueur doit éteindre
 * toutes les lumières d'une grille en inversant leur état via différentes actions.
 * 
 * @author Ethan Ariste
 */
public class Partie {
    /**
     * Instance de la grille de jeu.
     */
    private GrilleDeJeu grille;

    /**
     * Compteur du nombre de coups joués par le joueur.
     */
    private int nbCoups;

    /**
     * Constructeur par défaut.
     * Initialise une nouvelle partie avec une grille de 7x7 cellules et un compteur de coups à zéro.
     */
    public Partie() {
        this.grille = new GrilleDeJeu(7, 7); // Exemple : une grille de 7x7
        this.nbCoups = 0;
    }

    /**
     * Initialise la partie en mélangeant la grille.
     * Par défaut, la grille est mélangée avec 10 activations aléatoires.
     */
    public void initialiserPartie() {
        grille.melangerMatriceAleatoirement(10); // Mélanger la grille avec 10 tours (modifiable)
        System.out.println("La partie est initialisee !");
        System.out.println(grille); // Affiche l'état initial de la grille
    }

    /**
     * Lance le jeu "Light Off".
     * Le joueur interagit avec la grille via le terminal en choisissant d'activer
     * des lignes, colonnes ou diagonales jusqu'à ce que toutes les cellules soient éteintes.
     */
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu LightOff !");
        System.out.println("Votre objectif est d'eteindre toutes les cellules lumineuses.");
        System.out.println(grille); // Affiche l'état initial de la grille

        // Boucle principale du jeu
        while (!grille.cellulesToutesEteintes()) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1: Activer une ligne | 2: Activer une colonne | 3: Activer une diagonale descendante | 4: Activer une diagonale montante");
            int choix = scanner.nextInt();

            // Traitement des choix du joueur
            switch (choix) {
                case 1 -> {
                    System.out.print("Entrez le numero de la ligne (0 à " + (grille.getNbLignes() - 1) + "): ");
                    int ligne = scanner.nextInt();
                    if (ligne >= 0 && ligne < grille.getNbLignes()) {
                        grille.activerLigneDeCellules(ligne);
                    } else {
                        System.out.println("Choix invalide. Veuillez reessayer.");
                    }
                }
                case 2 -> {
                    System.out.print("Entrez le numero de la colonne (0 à " + (grille.getNbColonnes() - 1) + "): ");
                    int colonne = scanner.nextInt();
                    if (colonne >= 0 && colonne < grille.getNbColonnes()) {
                        grille.activerColonneDeCellules(colonne);
                    } else {
                        System.out.println("Choix invalide. Veuillez reessayer.");
                    }
                }
                case 3 -> grille.activerDiagonaleDescendante();
                case 4 -> grille.activerDiagonaleMontante();
                default -> System.out.println("Choix invalide. Veuillez reessayer.");
            }

            nbCoups++; // Incrémenter le compteur de coups
            System.out.println("Grille mise à jour :");
            System.out.println(grille); // Afficher la grille mise à jour
            System.out.println("Nombre de coups joues : " + nbCoups);
        }

        // Fin de la partie
        System.out.println("Felicitations, vous avez eteint toutes les cellules !");
        System.out.println("Partie terminee en " + nbCoups + " coups.");
        scanner.close(); // Fermer le scanner pour libérer les ressources
    }
}
