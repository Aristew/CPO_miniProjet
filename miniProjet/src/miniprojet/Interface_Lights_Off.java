/*                    
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniprojet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Classe Interface_Lights_Off - Fenêtre principale du jeu Lights Off.
 * Cette classe gère l'interface graphique et les interactions utilisateur.
 * 
 * @author ethan ariste
 */
public class Interface_Lights_Off extends javax.swing.JFrame {
    GrilleDeJeu grille;
    int nbCoups;
    int maxCoups; // Limite du nombre de coups en fonction du niveau  
    boolean premierLancement = true;
    /**
     * Méthode pour demander le niveau de difficulté au joueur via une boîte de dialogue.
     * 
     * @return La taille de la grille en fonction du niveau choisi (5, 7 ou 9).
     */
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

    /**
     * Constructeur de la classe Interface_Lights_Off.
     * Initialise l'interface graphique et démarre une première partie.
     */
    public Interface_Lights_Off() {
    initComponents();
    jLabel1.setText("Nombre de coups : "+nbCoups);
    // Initialisation par défaut avant le choix du niveau
    int tailleParDefaut = 5; // Par défaut 5x5
    this.grille = new GrilleDeJeu(tailleParDefaut, tailleParDefaut);
    this.nbCoups = 0;
    this.premierLancement = true; // Définir que c'est le premier lancement

    // Crée une grille visuelle par défaut
    jPanel1.setLayout(new java.awt.GridLayout(tailleParDefaut, 1));
    jPanel4.setLayout(new java.awt.GridLayout(1, tailleParDefaut));
    jPanel2.setLayout(new java.awt.GridLayout(tailleParDefaut, tailleParDefaut));
    
    for (int i = 0; i < tailleParDefaut; i++) {
        JButton button = new JButton("" + i);
        jPanel4.add(button);
    }

    for (int j = 0; j < tailleParDefaut; j++) {
        JButton button = new JButton("" + j);
        jPanel1.add(button);
    }

    for (int i = 0; i < tailleParDefaut; i++) {
        for (int j = 0; j < tailleParDefaut; j++) {
            CelluleGraphique button = new CelluleGraphique(grille.matriceCellules[i][j], 600 / tailleParDefaut, 600 / tailleParDefaut);
            jPanel2.add(button);
        }
    }

    // Lancement de la partie après construction
    initialiserPartie(false);

    // Ajuste la taille de la fenêtre
    this.setSize(new java.awt.Dimension(800, 800));
}
   
    
/**
     * Initialise ou relance une partie.
     * 
     * @param isRelance Indique si c'est une relance (true) ou un premier démarrage (false).
     */
    public void initialiserPartie(boolean isRelance) {
    if (!isRelance) {
        // Affiche un message de bienvenue seulement si ce n'est pas une relance
        JOptionPane.showMessageDialog(this, 
                "Bienvenue dans Lights Off !\n\n" + 
                "Règles du jeu :\n" +
                "- Toutes les cellules doivent être éteintes pour gagner.\n" +
                "- Vous pouvez cliquer sur une ligne, une colonne, ou une diagonale pour modifier l'état des cellules.\n" +
                "- Essayez de résoudre le puzzle en un minimum de coups, sans dépasser la limite !", 
                "Bienvenue", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Demander le niveau de difficulté à chaque initialisation
    int tailleGrille = demanderNiveauDifficulte();

    // Mettre à jour la grille en recréant les composants
    grille = new GrilleDeJeu(tailleGrille, tailleGrille);

    // Vider les panels et recréer les boutons
    jPanel1.removeAll();
    jPanel4.removeAll();
    jPanel2.removeAll();
    
    jPanel1.setLayout(new java.awt.GridLayout(tailleGrille, 1)); // Lignes
    jPanel4.setLayout(new java.awt.GridLayout(1, tailleGrille)); // Colonnes
    jPanel2.setLayout(new java.awt.GridLayout(tailleGrille, tailleGrille)); // Grille

    // Ajouter les boutons pour les colonnes
    for (int i = 0; i < tailleGrille; i++) {
        JButton button = new JButton("Col " + (i + 1));
        jPanel4.add(button);

        final int colonne = i; // capture de la colonne correspondante
        button.addActionListener(evt -> {
            grille.activerColonneDeCellules(colonne);
            nbCoups++;
            rafraichirGrille();
            verifierConditionVictoire();
            verifierLimiteCoups();
        });
    }

    // Ajouter les boutons pour les lignes
    for (int j = 0; j < tailleGrille; j++) {
        JButton button = new JButton("Ligne " + (j + 1));
        jPanel1.add(button);

        final int ligne = j; // capture de la ligne correspondante
        button.addActionListener(evt -> {
            grille.activerLigneDeCellules(ligne);
            nbCoups++;
            rafraichirGrille();
            verifierConditionVictoire();
            verifierLimiteCoups();
        });
    }

    // Ajouter les cellules graphiques
    for (int i = 0; i < tailleGrille; i++) {
        for (int j = 0; j < tailleGrille; j++) {
            int tailleBouton = 600 / tailleGrille; // Ajuster la taille des boutons
            CelluleGraphique button = new CelluleGraphique(grille.matriceCellules[i][j], tailleBouton, tailleBouton);
            jPanel2.add(button);
        }
    }

    // Mélanger la matrice selon le niveau
    switch (tailleGrille) {
        case 5: // Facile
            grille.melangerMatriceAleatoirement(10);
            break;
        case 7: // Moyen
            grille.melangerMatriceAleatoirement(15);
            break;
        case 9: // Difficile
            grille.melangerMatriceAleatoirement(10);
            break;
        default:
            grille.melangerMatriceAleatoirement(10); // Par défaut
    }

    nbCoups = 0; // Réinitialise le compteur de coups
    rafraichirGrille(); // Réactualise l'affichage
    jPanel1.revalidate(); // Revalide les composants graphiques
    jPanel4.revalidate();
    jPanel2.revalidate();
}

/**
     * Vérifie si la limite de coups a été atteinte.
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
            initialiserPartie(true);
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
        jLabel1.setText("Nombre de coups : "+nbCoups);

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
                     initialiserPartie(true);
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

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

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
