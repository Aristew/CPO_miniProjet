/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniprojet;

/**
 *
 * @author ariste ethan
 */
/**
 * La classe MiniProjet contient le point d'entrée principal de l'application.
 * Elle initialise une partie de jeu avec une grille et démarre l'interaction avec le joueur.
 */
public class MiniProjet {

    
    /**
     * Méthode principale (point d'entrée du programme).
     * Elle crée une instance de la classe Partie avec une grille carrée et lance la partie.
     *
     * @param args les arguments de la ligne de commande (non utilisés dans ce programme)
     */
    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.initialiserPartie();
        partie.lancerPartie();
    }
}
