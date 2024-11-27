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
    private GrilleDeJeu grille; // Instance de la grille de jeu
    private int nbCoups; // Compteur de coups joués

    // Constructeur .
    public Partie() {
        this.grille = new GrilleDeJeu(7, 7); // Exemple : une grille de 7x7
        this.nbCoups = 0; // Initialisation du compteur de coups à zéro
    }

    // Méthode pour initialiser la partie
    public void initialiserPartie() {
        grille.melangerMatriceAleatoirement(10); // Mélanger la grille avec 10 tours (modifiable)
        System.out.println("La partie est initialisée !");
        System.out.println(grille); // Affiche l'état initial de la grille
    }

    // Méthode pour lancer la partie
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu LightOff !");
        System.out.println("Votre objectif est d'éteindre toutes les cellules lumineuses.");
        System.out.println(grille); // Affiche l'état initial de la grille

        // Boucle principale du jeu
        while (!grille.cellulesToutesEteintes()) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1: Activer une ligne | 2: Activer une colonne | 3: Activer une diagonale descendante | 4: Activer une diagonale montante");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> {
                    System.out.print("Entrez le numéro de la ligne (0 à " + (grille.getNbLignes() - 1) + "): ");
                    int ligne = scanner.nextInt();
                    if (ligne<=(grille.getNbLignes() - 1) && ligne>0) {
                        grille.activerLigneDeCellules(ligne);
                    } else {
                        System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                }
                case 2 -> {
                    System.out.print("Entrez le numéro de la colonne (0 à " + (grille.getNbColonnes() - 1) + "): ");
                    int colonne = scanner.nextInt();
                    if (colonne<=(grille.getNbColonnes() - 1) && colonne>0) {
                        grille.activerColonneDeCellules(colonne);
                    } else {
                        System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                }
                case 3 -> grille.activerDiagonaleDescendante();
                case 4 -> grille.activerDiagonaleMontante();
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }

            nbCoups++; // Incrémenter le compteur de coups
            System.out.println("Grille mise à jour :");
            System.out.println(grille); // Afficher la grille mise à jour
            System.out.println("Nombre de coups joués : " + nbCoups);
        }

        System.out.println("Félicitations, vous avez éteint toutes les cellules !");
        System.out.println("Partie terminée en " + nbCoups + " coups.");
        scanner.close(); // Fermer le scanner pour libérer les ressources
    }
}
