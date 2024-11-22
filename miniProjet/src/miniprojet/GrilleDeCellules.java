/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

/**
 *
 * @author ethan ariste
 * Classe représentant une grille de cellules lumineuses.
 */
import java.util.Random;

/**
 * La classe GrilleDeCellules représente une grille de cellules lumineuses.
 * Chaque cellule peut être allumée ou éteinte, et la grille offre des opérations
 * pour manipuler l'état des cellules de manière individuelle ou en groupe.
 */
public class GrilleDeCellules {
    /**
     * Matrice de cellules lumineuses représentant la grille.
     */
    CelluleLumineuse[][] matriceCellules;

    /**
     * Nombre de lignes dans la grille.
     */
    private int lignes;

    /**
     * Nombre de colonnes dans la grille.
     */
    private int colonnes;

    /**
     * Constructeur de la classe GrilleDeCellules.
     * Initialise une grille avec un certain nombre de lignes et colonnes, 
     * toutes les cellules étant éteintes par défaut.
     *
     * @param lignes   le nombre de lignes dans la grille
     * @param colonnes le nombre de colonnes dans la grille
     */
    public GrilleDeCellules(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.matriceCellules = new CelluleLumineuse[lignes][colonnes];
        
        // Initialisation des cellules
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                matriceCellules[i][j] = new CelluleLumineuse();
            }
        }
    }

    /**
     * Retourne une représentation textuelle de la grille.
     * Les cellules sont affichées sous forme d'une matrice où "1" représente une cellule allumée
     * et "0" une cellule éteinte.
     *
     * @return la représentation de la grille sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder();
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                affichage.append(matriceCellules[i][j].toString()).append(" ");
            }
            affichage.append("\n");
        }
        return affichage.toString();
    }

    /**
     * Inverse l'état de toutes les cellules d'une ligne donnée.
     *
     * @param idLigne l'indice de la ligne à inverser (commençant à 0)
     */
    public void activerLigneDeCellules(int idLigne) {
        if (idLigne >= 0 && idLigne < lignes) {
            for (int j = 0; j < colonnes; j++) {
                matriceCellules[idLigne][j].inverserEtat();
            }
        }
    }

    /**
     * Inverse l'état de toutes les cellules d'une colonne donnée.
     *
     * @param idColonne l'indice de la colonne à inverser (commençant à 0)
     */
    public void activerColonneDeCellules(int idColonne) {
        if (idColonne >= 0 && idColonne < colonnes) {
            for (int i = 0; i < lignes; i++) {
                matriceCellules[i][idColonne].inverserEtat();
            }
        }
    }

    /**
     * Inverse l'état des cellules situées sur la diagonale descendante
     * (de la cellule en haut à gauche à celle en bas à droite).
     */
    public void activerDiagonaleDescendante() {
        int taille = Math.min(lignes, colonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[i][i].inverserEtat();
        }
    }

    /**
     * Inverse l'état des cellules situées sur la diagonale montante
     * (de la cellule en bas à gauche à celle en haut à droite).
     */
    public void activerDiagonaleMontante() {
        int taille = Math.min(lignes, colonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[lignes - 1 - i][i].inverserEtat();
        }
    }

    /**
     * Active aléatoirement une ligne, une colonne ou une diagonale.
     */
    public void activerLigneColonneOuDiagonaleAleatoire() {
        Random rand = new Random();
        int choix = rand.nextInt(4); // 0: ligne, 1: colonne, 2: diagonale descendante, 3: diagonale montante

        if (choix == 0) {
            activerLigneDeCellules(rand.nextInt(lignes));
        } else if (choix == 1) {
            activerColonneDeCellules(rand.nextInt(colonnes));
        } else if (choix == 2) {
            activerDiagonaleDescendante();
        } else if (choix == 3) {
            activerDiagonaleMontante();
        }
    }

    /**
     * Mélange la grille en activant des lignes, colonnes ou diagonales aléatoires
     * un nombre donné de fois.
     *
     * @param nbTours le nombre d'opérations aléatoires à effectuer
     */
    public void melangerMatriceAleatoirement(int nbTours) {
        for (int i = 0; i < nbTours; i++) {
            activerLigneColonneOuDiagonaleAleatoire();
        }
    }
}

