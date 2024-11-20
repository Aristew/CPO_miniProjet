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

    }
    
}
