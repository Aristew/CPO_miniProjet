/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

/**
 *
 * @author ethan ariste
 */
/**
 * La classe CelluleLumineuse représente une cellule qui peut être allumée ou éteinte.
 * Son état est défini par un booléen (true pour allumée, false pour éteinte).
 */
public class CelluleLumineuse {
    // Attribut : état de la cellule (true = allumée, false = éteinte)
    private boolean etat;

    // Constructeur par défaut : initialise la cellule à "éteinte"
    public CelluleLumineuse() {
        this.etat = false;
    }

    // Méthode pour activer la cellule (inverser son état)
    public void activerCellule() {
        this.etat = !this.etat;
    }

    // Méthode pour éteindre la cellule
    public void eteindreCellule() {
        this.etat = false;
    }

    // Méthode pour vérifier si la cellule est éteinte
    public boolean estEteint() {
        return !this.etat;
    }

    // Méthode pour obtenir l'état de la cellule
    public boolean getEtat() {
        return this.etat;
    }

    // Redéfinition de la méthode toString pour représenter l'état de la cellule
    @Override
    public String toString() {
        return etat ? "X" : "O"; // "X" pour allumée, "O" pour éteinte
    }
}
