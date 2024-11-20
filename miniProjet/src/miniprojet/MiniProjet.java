/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniprojet;

/**
 *
 * @author ariste ethan
 */
public class MiniProjet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Créez une partie avec une grille de taille 3x3 (par exemple)
        Partie partie = new Partie(4); // Le paramètre 3 définit la taille de la grille (3x3)
        
        // Lancez la partie
        partie.lancerPartie(); // Lancer la méthode qui démarre le jeu
    }
    
}
