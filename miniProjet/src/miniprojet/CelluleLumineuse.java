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
    /** 
     * État de la cellule : true si allumée, false si éteinte.
     */
    boolean etat; 
        
    /**
     * Constructeur par défaut de la classe CelluleLumineuse.
     * Initialise la cellule à l'état éteint.
     */
    public CelluleLumineuse() {
        this.etat = false; // Par défaut, la cellule est éteinte
    }
    
    /**
     * Allume la cellule en définissant son état sur true.
     */
    public void allumer() {
        this.etat = true;
    }

    /**
     * Éteint la cellule en définissant son état sur false.
     */
    public void eteindre() {
        this.etat = false;
    }
    
    /**
     * Vérifie si la cellule est allumée.
     *
     * @return true si la cellule est allumée, false sinon.
     */
    public boolean estAllumee() {
        return this.etat;
    }
    
    /**
     * Inverse l'état actuel de la cellule :
     * si elle est allumée, elle devient éteinte, et inversement.
     */
    public void inverserEtat() {
        this.etat = !this.etat;
    }
    
    /**
     * Retourne une représentation textuelle de l'état de la cellule.
     *
     * @return "1" si la cellule est allumée, "0" si elle est éteinte.
     */
    @Override
    public String toString() {
        return etat ? "1" : "0";
    }
}
