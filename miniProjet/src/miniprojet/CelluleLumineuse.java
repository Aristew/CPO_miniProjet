/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

/**
 *
 * @author ethan ariste
 */
public class CelluleLumineuse {
    boolean etat; // true si allumée, false si éteinte
        
    public CelluleLumineuse() {
        this.etat = false; // Par défaut, la cellule est éteinte
    }
    
    // Méthode pour allumer la cellule
    public void allumer() {
        this.etat = true;
    }

    // Méthode pour éteindre la cellule
    public void eteindre() {
        this.etat = false;
    }
    
    // Méthode pour vérifier si la cellule est allumée
    public boolean estAllumee() {
        return this.etat;
    }
    
    // Méthode pour inverser l'état de la cellule
    public void inverserEtat() {
        this.etat = !this.etat;
    }
    
    // Méthode pour afficher l'état de la cellule (utile pour la console)
    @Override
    public String toString() {
        return etat ? "1" : "0"; // "1" pour allumée, "0" pour éteinte
    }
}
