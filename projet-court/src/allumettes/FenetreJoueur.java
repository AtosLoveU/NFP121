package allumettes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FlowLayout;

public class FenetreJoueur extends JFrame {

    private final Object verrou = new Object();
    private int choix = 0;       // >0 : nombre d'allumettes voulu
                                  // -1  : le joueur veut tricher

    private int nbATricher = 0;  // lu depuis le champ texte

    private final JLabel labelCompteur;
    private final JButton bouton1;
    private final JButton bouton2;
    private final JButton bouton3;
    private final JTextField champTricher;

    public FenetreJoueur(String nomJoueur) {
        super(nomJoueur + " ?");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        labelCompteur = new JLabel("", SwingConstants.CENTER);
        labelCompteur.setFont(new Font("SansSerif", Font.BOLD, 48));
        add(labelCompteur, BorderLayout.CENTER);

        JPanel panneauBoutons = new JPanel(new FlowLayout());
        bouton1 = new JButton("1");
        bouton2 = new JButton("2");
        bouton3 = new JButton("3");
        panneauBoutons.add(bouton1);
        panneauBoutons.add(bouton2);
        panneauBoutons.add(bouton3);
        add(panneauBoutons, BorderLayout.SOUTH);

        JPanel panneauTricher = new JPanel(new FlowLayout());
        JButton boutonTricher = new JButton("tricher");
        champTricher = new JTextField("1", 3);
        panneauTricher.add(boutonTricher);
        panneauTricher.add(champTricher);
        add(panneauTricher, BorderLayout.NORTH);

        bouton1.addActionListener(e -> signalerChoix(1));
        bouton2.addActionListener(e -> signalerChoix(2));
        bouton3.addActionListener(e -> signalerChoix(3));
        boutonTricher.addActionListener(e -> signalerTricher());

        pack();
        setLocationRelativeTo(null);
    }

    /** Appelé par StrategieSwing (thread main) avant de montrer la fenêtre. */
    public void mettreAJour(int nbAllumettes) {
        labelCompteur.setText(String.valueOf(nbAllumettes));
        bouton1.setEnabled(nbAllumettes >= 1);
        bouton2.setEnabled(nbAllumettes >= 2);
        bouton3.setEnabled(nbAllumettes >= 3);
    }

    /** Bloque le thread main jusqu'au choix du joueur.
     * @return le nombre d'allumettes choisi, ou -1 pour triche */
    public int attendreChoix() {
        synchronized (verrou) {
            while (choix == 0) {
                try {
                    verrou.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            int resultat = choix;
            choix = 0;   // réinitialiser pour le prochain tour
            return resultat;
        }
    }

    public int getNbATricher() {
        return nbATricher;
    }

    // Appelé par les ActionListeners (thread Swing)
    private void signalerChoix(int valeur) {
        synchronized (verrou) {
            choix = valeur;
            verrou.notify();
        }
    }

    private void signalerTricher() {
        try {
            nbATricher = Integer.parseInt(champTricher.getText().trim());
        } catch (NumberFormatException e) {
            nbATricher = 1;
        }
        synchronized (verrou) {
            choix = -1;
            verrou.notify();
        }
    }
}