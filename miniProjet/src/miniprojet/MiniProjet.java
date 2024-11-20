/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniprojet;

/**
 *
 * @author arist
 */
public class MiniProjet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Méthode principale pour tester la classe 
        CelluleLumineuse cellule = new CelluleLumineuse();
        System.out.println("Etat initial : " + cellule); // Doit afficher "0"

        cellule.allumer();
        System.out.println("Apres allumage : " + cellule); // Doit afficher "1"

        cellule.inverserEtat();
        System.out.println("Apres inversion : " + cellule); // Doit afficher "0"

        cellule.eteindre();
        System.out.println("Apres extinction : " + cellule); // Doit afficher "0"
        GrilleDeCellules grille = new GrilleDeCellules(4, 5); // Grille 4x5
        // test activation d'une ligne 
        System.out.println("Grille initiale :");
        System.out.println(grille);grille.activerLigneDeCellules(2);
        System.out.println("Après activation de la ligne 2 :");
        System.out.println(grille);
        
         // Test activerColonneDeCellules
        grille.activerColonneDeCellules(3);
        System.out.println("Après activation de la colonne 3 :");
        System.out.println(grille);

        // Test activerDiagonaleDescendante
        GrilleDeCellules grille4 = new GrilleDeCellules(4, 5);
        grille4.activerDiagonaleDescendante();
        System.out.println("Après activation de la diagonale descendante :");
        System.out.println(grille4);

        // Test activerDiagonaleMontante
        GrilleDeCellules grille5 = new GrilleDeCellules(4, 5);
        grille5.activerDiagonaleMontante();
        System.out.println("Après activation de la diagonale montante :");
        System.out.println(grille5);
        System.out.println("On désactive la diagonale montante :");
        grille5.activerDiagonaleMontante();
        System.out.println(grille5);

        
        // Test melangerMatriceAleatoirement
        GrilleDeCellules grille6 = new GrilleDeCellules(4, 5); // On teste sur une nouvelle grille
        System.out.println("Nouvelle grille vierge : ");
        System.out.println(grille6);
        grille6.melangerMatriceAleatoirement(4);
        System.out.println("Après mélange aléatoire (4 tours) :");
        System.out.println(grille6);
        
        
    }
    
}
