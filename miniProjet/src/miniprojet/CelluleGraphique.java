package miniprojet;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * La classe CelluleGraphique représente une cellule de la grille avec un affichage graphique.
 * Les cellules allumées ("X") sont jaunes, et les cellules éteintes ("O") sont grises.
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
        // Appeler le comportement par défaut du bouton
        super.paintComponent(g);

        // Définir la couleur de fond selon l'état de la cellule
        if (celluleLumineuseAssociee.getEtat()) {
            g.setColor(Color.YELLOW); // "X" => Allumée (jaune)
        } else {
            g.setColor(Color.GRAY); // "O" => Éteinte (gris)
        }

        // Remplir le fond du bouton
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dessiner la bordure
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Ajouter le texte centré ("X" ou "O")
        g.setColor(Color.BLACK);
    }
}


