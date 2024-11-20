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

public class GrilleDeCellules {
    private CelluleLumineuse[][] matriceCellules;
    private int lignes;
    private int colonnes;

    public GrilleDeCellules(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.matriceCellules = new CelluleLumineuse[lignes][colonnes];
        
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                matriceCellules[i][j] = new CelluleLumineuse();
            }
        }
    }

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

    // Méthode pour activer/éteindre une ligne entière
    public void activerLigneDeCellules(int idLigne) {
        if (idLigne >= 0 && idLigne < lignes) {
            for (int j = 0; j < colonnes; j++) {
                matriceCellules[idLigne][j].inverserEtat();
            }
        }
    }

    // Méthode pour activer/éteindre une colonne entière
    public void activerColonneDeCellules(int idColonne) {
        if (idColonne >= 0 && idColonne < colonnes) {
            for (int i = 0; i < lignes; i++) {
                matriceCellules[i][idColonne].inverserEtat();
            }
        }
    }

    // Méthode pour activer/éteindre la diagonale descendante (de haut gauche à bas droite)
    public void activerDiagonaleDescendante() {
        int taille = Math.min(lignes, colonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[i][i].inverserEtat();
        }
    }

    // Méthode pour activer la diagonale montante (de bas gauche à haut droit)
    public void activerDiagonaleMontante() {
        int taille = Math.min(lignes, colonnes);
        for (int i = 0; i < taille; i++) {
            matriceCellules[lignes - 1 - i][i].inverserEtat();
        }
    }

    // Méthode pour activer une ligne, colonne ou diagonale aléatoirement
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

    // Méthode pour mélanger la grille aléatoirement un nombre donné de tours
    public void melangerMatriceAleatoirement(int nbTours) {
        for (int i = 0; i < nbTours; i++) {
            activerLigneColonneOuDiagonaleAleatoire();
        }
    }
}

