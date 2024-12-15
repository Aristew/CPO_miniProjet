/*                    
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniprojet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author ethan
 */
public class Interface_Lights_Off extends javax.swing.JFrame {
    GrilleDeJeu grille;
    int nbCoups;
    int maxCoups; // Limite du nombre de coups en fonction du niveau
    int nbLignes;  // Ajoutez cette variable
    int nbColonnes;  // Ajoutez cette variable
    
    public int demanderNiveauDifficulte() {
        // Options disponibles
        String[] options = {"Facile (5x5, max 50 coups)", "Moyen (7x7, max 20 coups)", "Difficile (9x9, max 10 coups)"};
        
        // Boîte de dialogue pour demander le niveau de difficulté
        int choix = JOptionPane.showOptionDialog(
                this, // Parent Component
                "Choisissez un niveau de difficulté :", // Message
                "Niveau de Difficulté", // Titre
                JOptionPane.DEFAULT_OPTION, // Type d'options
                JOptionPane.QUESTION_MESSAGE, // Icône
                null, // Icône personnalisée (null = icône par défaut)
                options, // Options
                options[0] // Option par défaut
        );

        // Renvoie le niveau choisi et la taille correspondante
        switch (choix) {
            case 0:
                maxCoups = 50; // Facile
                return 5; // Taille 5x5
            case 1:
                maxCoups = 20; // Moyen
                return 7; // Taille 7x7
            case 2:
                maxCoups = 10; // Difficile
                return 9; // Taille 9x9
            default:
                maxCoups = 20; // Niveau moyen par défaut
                return 7; // Taille 7x7 par défaut
        }
    }

    public Interface_Lights_Off() {
        initComponents();
        nbLignes = demanderNiveauDifficulte(); // Demander uniquement le niveau, taille incluse
    nbColonnes = nbLignes;  // La grille est carrée, donc nbColonnes = nbLignes
    jPanel1.setLayout(new java.awt.GridLayout(nbLignes, 1)); // Ajuste le layout des boutons de lignes
    jPanel4.setLayout(new java.awt.GridLayout(1, nbColonnes));
    this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
    this.nbCoups = 0;
    jPanel2.setLayout(new java.awt.GridLayout(nbLignes, nbColonnes));
       
        // Ajouter les boutons pour les colonnes
        for (int i = 0; i < nbColonnes; i++) {
            JButton button = new JButton("" + i);
            jPanel4.add(button);

            final int colonne = i; // capture de la colonne correspondante
            button.addActionListener(evt -> {
                grille.activerColonneDeCellules(colonne);
                nbCoups++;
                rafraichirGrille();
                verifierConditionVictoire();
                verifierLimiteCoups(); // Vérification ajoutée ici

            });
        }

        // Ajouter les boutons pour les lignes
        for (int j = 0; j < nbLignes; j++) {
            JButton button = new JButton("" + j);
            jPanel1.add(button);

            final int ligne = j; // capture de la ligne correspondante
            button.addActionListener(evt -> {
                grille.activerLigneDeCellules(ligne);
                nbCoups++;
                rafraichirGrille();
                verifierConditionVictoire();
                verifierLimiteCoups(); // Vérification ajoutée ici

            });
        }

        // Ajouter les cellules graphiques
    for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            int tailleBouton = 600 / nbLignes; // Ajuster en fonction de l'espace disponible
            CelluleGraphique button = new CelluleGraphique(grille.matriceCellules[i][j], tailleBouton, tailleBouton);
            jPanel2.add(button);
        }
    }
    
        initialiserPartie();
        this.setSize(new java.awt.Dimension(800, 800)); // Ajuster la taille totale si nécessaire
    }
   
    
public void initialiserPartie() {
    grille.eteindreToutesLesCellules();
    
    switch (nbLignes) {
        case 5: // Facile (5x5)
            grille.melangerMatriceAleatoirement(5); // Moins de mélange pour faciliter
            break;
        case 7: // Moyen (7x7)
            grille.melangerMatriceAleatoirement(15);
            break;
        case 9: // Difficile (9x9)
            grille.melangerMatriceAleatoirement(10);
            break;
        default:
            // Cas par défaut, au cas où
            grille.melangerMatriceAleatoirement(15);
            break;
    }
    
    nbCoups = 0;
    rafraichirGrille();
}

/**
 * Vérifie si le nombre de coups a dépassé la limite autorisée.
 */
public void verifierLimiteCoups() {
    if (nbCoups >= maxCoups) {
        JOptionPane.showMessageDialog(this,
                "Dommage, vous avez dépassé la limite de " + maxCoups + " coups.",
                "Défaite",
                JOptionPane.INFORMATION_MESSAGE);

        // Proposer de relancer ou quitter
        String[] options = {"Relancer Partie", "Quitter"};
        int choix = JOptionPane.showOptionDialog(
                this,
                "Voulez-vous relancer une partie ?",
                "Fin de Partie",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choix == 0) {
            initialiserPartie();
        } else {
            this.dispose();
            System.exit(0);
        }
    }
}

    /**
     * Met à jour l'affichage graphique des cellules.
     */
    public void rafraichirGrille() {
        jPanel2.repaint();
    }

    /**
    * Vérifie si la condition de victoire est remplie.
    */
    public void verifierConditionVictoire() {
       if (grille.cellulesToutesEteintes()) {
           JOptionPane.showMessageDialog(this,
                   "Bravo, vous avez gagné en " + nbCoups + " coups !",
                   "Victoire",
                   JOptionPane.INFORMATION_MESSAGE);
                String[] a = {"Relancer Partie", "Quitter "};
    
                    // Boîte de dialogue pour quitter ou relancer
                    int b = JOptionPane.showOptionDialog(
                this, // Parent Component
                "Relancer une partie", // Message
                "Jeux Light Off", // Titre
                JOptionPane.DEFAULT_OPTION, // Type d'options
                JOptionPane.QUESTION_MESSAGE, // Icône
                null, // Icône personnalisée (null = icône par défaut)
                a,
                a[0]); // Option par défaut
                if (b == 0) { 
                     initialiserPartie();
                }else {
                    this.dispose(); // Ferme la fenêtre actuelle
                    System.exit(0); // Termine le programme
                        
                } 
       }
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new java.awt.GridLayout(1, 7));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.GridLayout(7, 1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new java.awt.GridLayout(7, 7));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setText("Diagonale Montante ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setText("Diagonale Descendante ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        grille.activerDiagonaleDescendante();
        nbCoups++;
        rafraichirGrille();
        verifierConditionVictoire();
        verifierLimiteCoups(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //ne devrait pas exister 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        grille.activerDiagonaleMontante();
        nbCoups++;
        rafraichirGrille();
        verifierConditionVictoire();
        verifierLimiteCoups(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface_Lights_Off.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface_Lights_Off.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface_Lights_Off.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface_Lights_Off.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface_Lights_Off().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
