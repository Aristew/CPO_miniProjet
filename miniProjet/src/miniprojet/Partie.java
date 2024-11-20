/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

import java.util.Scanner;

/**
 *
 * @author ethan
 */
public class Partie {
    private GrilleDeCellules grille;  // La grille du jeu
    private int nombreDeCoups;       // Compteur des coups joués

    // Constructeur
    public Partie(int taille) {
        this.grille = new GrilleDeCellules(taille, taille);
        this.nombreDeCoups = 0;

        // Mélanger la grille pour démarrer avec un état aléatoire
        grille.melangerMatriceAleatoirement(taille * 2);
    }

    // Méthode pour lancer une partie
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

            // Demander le type de coup à jouer
            System.out.println("\nChoisissez une action :");
            System.out.println("1. Inverser une ligne");
            System.out.println("2. Inverser une colonne");
            System.out.println("3. Inverser la diagonale principale");
            System.out.println("4. Inverser la diagonale secondaire");
            System.out.print("Entrez votre choix (1-4) : ");
            int choix = scanner.nextInt();

            // Effectuer l'action en fonction du choix
            switch (choix) {
                case 1:
                    System.out.print("Entrez le numéro de la ligne (1-" + (grille.matriceCellules.length) + ") : ");
                    int ligne = scanner.nextInt()-1;
                    grille.activerLigneDeCellules(ligne);
                    break;
                case 2:
                    System.out.print("Entrez le numéro de la colonne (1-" + (grille.matriceCellules[0].length) + ") : ");
                    int colonne = scanner.nextInt()-1;
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

            // Incrémenter le compteur de coups
            nombreDeCoups++;
        }

        // Victoire
        System.out.println("Félicitations ! Vous avez éteint toutes les lumières.");
        System.out.println("Nombre total de coups joués : " + nombreDeCoups);
        scanner.close();
    }

    // Méthode pour vérifier si toutes les cellules de la grille sont éteintes
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
