import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComprendreSwingExo extends JPanel {

    final private JLabel    nomTxt = new JLabel("Nom : ");
    final private JTextField   nom = new JTextField(25);
    final private JButton       bA = new JButton("A");
    final private JButton       bB = new JButton("B");
    final private JButton       bC = new JButton("C");
    final private JButton       bQ = new JButton("Q");

    public ComprendreSwingExo() {
        super();


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets  = new Insets(2, 4, 2, 4);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0; this.add(bA, gbc);
        gbc.gridx = 1;                this.add(bB, gbc);
        gbc.gridx = 2;                this.add(bC, gbc);


        nomTxt.setHorizontalAlignment(JLabel.RIGHT);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; this.add(nomTxt, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; this.add(nom, gbc);


        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        JPanel panneauQ = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panneauQ.add(bQ);
        this.add(panneauQ, gbc);

        this.bQ.addActionListener(new ActionQuitter());

        this.bC.addActionListener(new ActionEffacer());

        ActionTrace trace = new ActionTrace();
        bA.addMouseListener(trace);
        bB.addMouseListener(trace);
    }

    public class ActionQuitter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFrame fenetre = (JFrame) SwingUtilities.getWindowAncestor(ComprendreSwingExo.this);
            if (fenetre != null) {
                fenetre.dispose();   
            }
        }
    }


    public class ActionEffacer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            nom.setText("");
        }
    }


    public class ActionTrace extends MouseAdapter {

        @Override 
        public void mouseClicked(MouseEvent ev) {
            System.out.println("Appui sur " + ((JButton) ev.getSource()).getText());
        }

        @Override
        public void mouseEntered(MouseEvent ev) {
            JButton source = (JButton) ev.getSource();
            System.out.println("Entrée dans " + source.getText());
        }

        @Override
        public void mouseExited(MouseEvent ev) {
            JButton source = (JButton) ev.getSource();
            System.out.println("Sortie de " + source.getText());
        }
    }

    public static JFrame newJFrame(String titre) {
        JFrame fenetre = new JFrame(titre);
        ComprendreSwingExo comprendre = new ComprendreSwingExo();
        fenetre.getContentPane().add(comprendre);
        fenetre.pack();
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return fenetre;
    }

    public static void exemple1() {
        JFrame frame1 = newJFrame("Fenêtre1");
        JFrame frame2 = newJFrame("Fenêtre2");
        frame2.setLocation(300, 100);
        frame1.setVisible(true);
        frame2.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(ComprendreSwingExo::exemple1);
        System.out.println("Fin du programme principal !");
    }
}