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
 * La classe GrilleDeJeu représente une grille de cellules lumineuses.
 * Chaque cellule peut être allumée ou éteinte, et la grille offre des opérations
 * pour manipuler l'état des cellules individuellement ou en groupe.
 * 
 * @author Ethan Ariste
 */
public class GrilleDeJeu {
    /**
     * Matrice à deux dimensions de cellules lumineuses.
     */
    private CelluleLumineuse[][] matriceCellules;

    /**
     * Nombre de lignes dans la grille.
     */
    private int nbLignes;

    /**
     * Nombre de colonnes dans la grille.
     */
    private int nbColonnes;

    /**
     * Constructeur de la classe.
     * Initialise la grille avec un nombre spécifié de lignes et de colonnes.
     * 
     * @param nbLignes   nombre de lignes de la grille.
     * @param nbColonnes nombre de colonnes de la grille.
     */
    public GrilleDeJeu(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.matriceCellules = new CelluleLumineuse[nbLignes][nbColonnes];

        // Initialisation de chaque cellule de la matrice.
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new CelluleLumineuse();
            }
        }
    }

    /**
     * Retourne le nombre de lignes de la grille.
     * 
     * @return le nombre de lignes.
     */
    public int getNbLignes() {
        return this.nbLignes;
    }

    /**
     * Retourne le nombre de colonnes de la grille.
     * 
     * @return le nombre de colonnes.
     */
    public int getNbColonnes() {
        return this.nbColonnes;
    }

    /**
     * Éteint toutes les cellules de la grille.
     */
    public void eteindreToutesLesCellules() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j].eteindreCellule();
            }
        }
    }

    /**
     * Active aléatoirement une ligne, une colonne ou une diagonale dans la grille.
     */
    public void activerLigneColonneOuDiagonaleAleatoire() {
        Random rand = new Random();
        int choix = rand.nextInt(4); // 0: ligne, 1: colonne, 2: diagonale descendante, 3: diagonale montante
        switch (choix) {
            case 0 -> activerLigneDeCellules(rand.nextInt(nbLignes));
            case 1 -> activerColonneDeCellules(rand.nextInt(nbColonnes));
            case 2 -> activerDiagonaleDescendante();
            case 3 -> activerDiagonaleMontante();
        }
    }

    /**
     * Mélange la grille en activant aléatoirement des lignes, colonnes ou diagonales 
     * un nombre spécifié de fois.
     * 
     * @param nbTours le nombre de tours de mélange.
     */
    public void melangerMatriceAleatoirement(int nbTours) {
        eteindreToutesLesCellules(); // Commence avec toutes les cellules éteintes
        for (int i = 0; i < nbTours; i++) {
            activerLigneColonneOuDiagonaleAleatoire();
        }
    }

    /**
     * Active toutes les cellules d'une ligne spécifiée.
     * 
     * @param idLigne l'index de la ligne à activer.
     */
    public void activerLigneDeCellules(int idLigne) {
        if (idLigne >= 0 && idLigne < nbLignes) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[idLigne][j].activerCellule();
            }
        }
    }

    /**
     * Active toutes les cellules d'une colonne spécifiée.
     * 
     * @param idColonne l'index de la colonne à activer.
     */
    public void activerColonneDeCellules(int idColonne) {
        if (idColonne >= 0 && idColonne < nbColonnes) {
            for (int i = 0; i < nbLignes; i++) {
                matriceCellules[i][idColonne].activerCellule();
            }
        }
    }

    /**
     * Active la diagonale descendante de la grille.
     */
    public void activerDiagonaleDescendante() {
        int taille = Math.min(nbLignes, nbColonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[i][i].activerCellule();
        }
    }

    /**
     * Active la diagonale montante de la grille.
     */
    public void activerDiagonaleMontante() {
        int taille = Math.min(nbLignes, nbColonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[nbLignes - 1 - i][i].activerCellule();
        }
    }

    /**
     * Vérifie si toutes les cellules de la grille sont éteintes.
     * 
     * @return true si toutes les cellules sont éteintes, false sinon.
     */
    public boolean cellulesToutesEteintes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].estEteint()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Redéfinit la méthode toString pour afficher visuellement la grille.
     * 
     * @return une représentation textuelle de la grille.
     */
    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder();
        // Affichage des indices de colonnes
        affichage.append("   ");
        for (int j = 0; j < nbColonnes; j++) {
            affichage.append("| ").append(j).append(" ");
        }
        affichage.append("|\n").append("   ").append("-".repeat(nbColonnes * 4)).append("\n");
        // Affichage des cellules avec les indices de lignes
        for (int i = 0; i < nbLignes; i++) {
            affichage.append(i).append(" | "); // Indice de ligne
            for (int j = 0; j < nbColonnes; j++) {
                affichage.append(matriceCellules[i][j].toString()).append(" | ");
            }
            affichage.append("\n").append("   ").append("-".repeat(nbColonnes * 4)).append("\n");
        }
        return affichage.toString();
    }
}
