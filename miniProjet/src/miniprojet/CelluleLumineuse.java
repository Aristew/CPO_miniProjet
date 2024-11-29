/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

/**
 * La classe CelluleLumineuse représente une cellule qui peut être allumée ou éteinte.
 * Son état est défini par un booléen : true pour allumée, false pour éteinte.
 * Elle fournit des méthodes pour manipuler et interroger son état.
 * 
 * @author Ethan Ariste
 */
public class CelluleLumineuse {
    /**
     * L'état de la cellule lumineuse : true si allumée, false si éteinte.
     */
    private boolean etat;

    /**
     * Constructeur par défaut.
     * Initialise l'état de la cellule à éteinte (false).
     */
    public CelluleLumineuse() {
        this.etat = false;
    }

    /**
     * Active la cellule lumineuse en inversant son état.
     * Si elle est éteinte, elle s'allume ; si elle est allumée, elle s'éteint.
     */
    public void activerCellule() {
        this.etat = !this.etat;
    }

    /**
     * Éteint la cellule lumineuse.
     * Si la cellule est déjà éteinte, cette méthode n'a aucun effet.
     */
    public void eteindreCellule() {
        this.etat = false;
    }

    /**
     * Vérifie si la cellule lumineuse est éteinte.
     * 
     * @return true si la cellule est éteinte, false si elle est allumée.
     */
    public boolean estEteint() {
        return !this.etat;
    }

    /**
     * Renvoie l'état actuel de la cellule lumineuse.
     * 
     * @return true si la cellule est allumée, false si elle est éteinte.
     */
    public boolean getEtat() {
        return this.etat;
    }

    /**
     * Redéfinit la méthode toString pour fournir une représentation visuelle de la cellule.
     * 
     * @return "X" si la cellule est allumée, "O" si elle est éteinte.
     */
    @Override
    public String toString() {
        return etat ? "X" : "O";
    }
}
