/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author arist
 */
public class CelluleGraphique extends JButton{
    CelluleLumineuse celluleLumineuseAssociee;
    int largeur;
    int longueur;

    public CelluleGraphique(CelluleLumineuse celluleLumineuseAssociee, int largeur, int longueur) {
        this.celluleLumineuseAssociee = celluleLumineuseAssociee;
        this.largeur = largeur;
        this.longueur = longueur;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.setText(celluleLumineuseAssociee.toString());
    }
    
}
