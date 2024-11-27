/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

import java.util.Scanner;

/**
 *
 * @author ethan ariste
 */
/**
 * La classe Partie gère une session de jeu du type "Light Off", où le but est d'éteindre toutes
 * les lumières d'une grille en inversant leur état (allumée/éteinte) via diverses actions.
 */
/**
 * La classe Partie gère une session de jeu du type "Light Off", où le but est d'éteindre toutes
 * les lumières d'une grille en inversant leur état (allumée/éteinte) via diverses actions.
 */
public class Partie {
    /**
     * La grille de cellules lumineuses utilisée pour le jeu.
     */
    private GrilleDeCellules grille;

    /**
     * Le nombre de coups joués depuis le début de la partie.
     */
    private int nombreDeCoups;

    /**
     * Constructeur de la classe Partie.
     * Initialise une partie avec une grille carrée de taille spécifiée et mélange
     * la grille pour commencer avec un état aléatoire.
     *
     * @param taille la taille de la grille (nombre de lignes et de colonnes)
     */
    public Partie(int taille) {
        this.grille = new GrilleDeCellules(taille, taille);
        this.nombreDeCoups = 0;

        // Mélange la grille pour obtenir un état initial aléatoire
        grille.melangerMatriceAleatoirement(taille * 2);
    }

    /**
     * Démarre une session de jeu où le joueur interagit avec la console
     * pour inverser l'état des cellules jusqu'à ce que la grille soit entièrement éteinte.
     */
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu Light Off !");
        System.out.println("Votre objectif : éteindre toutes les lumières de la grille.");
        System.out.println("Grille initiale :");
        System.out.println(grille);

        // Boucle principale du jeu
        while (!estGrilleEteinte()) {
            System.out.println("Grille actuelle :");
            System.out.println(grille);

            // Menu des actions disponibles
            System.out.println("\nChoisissez une action :");
            System.out.println("1. Inverser une ligne");
            System.out.println("2. Inverser une colonne");
            System.out.println("3. Inverser la diagonale principale");
            System.out.println("4. Inverser la diagonale secondaire");
            System.out.print("Entrez votre choix (1-4) : ");
            int choix = scanner.nextInt();

            // Traitement de l'action choisie
            switch (choix) {
                case 1:
                    System.out.print("Entrez le numéro de la ligne (1-" + grille.matriceCellules.length + ") : ");
                    int ligne = scanner.nextInt() - 1;
                    grille.activerLigneDeCellules(ligne);
                    break;
                case 2:
                    System.out.print("Entrez le numéro de la colonne (1-" + grille.matriceCellules[0].length + ") : ");
                    int colonne = scanner.nextInt() - 1;
                    grille.activerColonneDeCellules(colonne);
                    break;
                case 3:
                    grille.activerDiagonaleDescendante();
                    break;
                case 4:
                    grille.activerDiagonaleMontante();
                    break;
                default:
                    System.out.println("Choix invalide. Essayez à nouveau.");
                    continue;
            }

            // Incrémentation du compteur de coups
            nombreDeCoups++;
        }

        // Message de victoire
        System.out.println("Félicitations ! Vous avez éteint toutes les lumières.");
        System.out.println("Nombre total de coups joués : " + nombreDeCoups);
        scanner.close();
    }

    /**
     * Vérifie si toutes les cellules de la grille sont éteintes.
     *
     * @return true si toutes les cellules sont éteintes, false sinon
     */
    private boolean estGrilleEteinte() {
        for (int i = 0; i < grille.matriceCellules.length; i++) {
            for (int j = 0; j < grille.matriceCellules[i].length; j++) {
                if (grille.matriceCellules[i][j].estAllumee()) {
                    return false;
                }
            }
        }
        return true;
    }
}
