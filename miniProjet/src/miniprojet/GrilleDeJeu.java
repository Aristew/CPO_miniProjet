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
 * pour manipuler l'état des cellules de manière individuelle ou en groupe.
 */
public class GrilleDeJeu {
    public CelluleLumineuse[][] matriceCellules; // Matrice de cellules lumineuses
    private int nbLignes; // Nombre de lignes de la grille
    private int nbColonnes; // Nombre de colonnes de la grille
    // Constructeur
    public GrilleDeJeu(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.matriceCellules = new CelluleLumineuse[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new CelluleLumineuse();
            }
        }
    }
    // Getter pour nbLignes
    public int getNbLignes() {
        return this.nbLignes;
    }
    // Getter pour nbColonnes
    public int getNbColonnes() {
        return this.nbColonnes;
    }
    // Méthode pour éteindre toutes les cellules de la grille
    public void eteindreToutesLesCellules() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j].eteindreCellule();
            }
        }
    }
    // Méthode pour activer aléatoirement une ligne, une colonne ou une diagonale
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
    // Méthode pour mélanger la grille aléatoirement sur nbTours
    public void melangerMatriceAleatoirement(int nbTours) {
        eteindreToutesLesCellules(); // Commence avec toutes les cellules éteintes
        for (int i = 0; i < nbTours; i++) {
            activerLigneColonneOuDiagonaleAleatoire();
        }
    }
    // Méthode pour activer une ligne entière
    public void activerLigneDeCellules(int idLigne) {
        if (idLigne >= 0 && idLigne < nbLignes) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[idLigne][j].activerCellule();
            }
        }
    }
    // Méthode pour activer une colonne entière
    public void activerColonneDeCellules(int idColonne) {
        if (idColonne >= 0 && idColonne < nbColonnes) {
            for (int i = 0; i < nbLignes; i++) {
                matriceCellules[i][idColonne].activerCellule();
            }
        }
    }
    // Méthode pour activer la diagonale descendante
    public void activerDiagonaleDescendante() {
        int taille = Math.min(nbLignes, nbColonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[i][i].activerCellule();
        }
    }
    // Méthode pour activer la diagonale montante
    public void activerDiagonaleMontante() {
        int taille = Math.min(nbLignes, nbColonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[nbLignes - 1 - i][i].activerCellule();
        }
    }
    // Méthode pour vérifier si toutes les cellules sont éteintes
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
    // Redéfinition de la méthode toString pour représenter visuellement la grille
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
