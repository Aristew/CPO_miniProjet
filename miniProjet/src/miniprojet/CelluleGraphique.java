package miniprojet;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * La classe CelluleGraphique représente une cellule de la grille avec un affichage graphique.
 * Les cellules allumées ("X") sont jaunes, et les cellules éteintes ("O") sont grises.
 * @author ariste ethan
 */
public class CelluleGraphique extends JButton {
    CelluleLumineuse celluleLumineuseAssociee;
    int largeur;
    int longueur;

    /**
     * Constructeur de la classe CelluleGraphique.
     *
     * @param celluleLumineuseAssociee la cellule lumineuse associée à cette cellule graphique.
     * @param largeur largeur du bouton.
     * @param longueur longueur du bouton.
     */
    public CelluleGraphique(CelluleLumineuse celluleLumineuseAssociee, int largeur, int longueur) {
        this.celluleLumineuseAssociee = celluleLumineuseAssociee;
        this.largeur = largeur;
        this.longueur = longueur;
    }

    /**
     * Redéfinit la méthode paintComponent pour personnaliser l'affichage graphique.
     *
     * @param g l'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (celluleLumineuseAssociee.getEtat()) {
            g.setColor(Color.YELLOW); // "X" : Allumée (jaune)
        } else {
            g.setColor(Color.GRAY); // "O" : Éteinte (gris)
        }

        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.setColor(Color.BLACK);
        
    }
}

