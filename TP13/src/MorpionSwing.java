import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

/** Programmation d'un jeu de Morpion avec une interface graphique Swing.
  *
  * REMARQUE : Dans cette solution, le patron MVC n'a pas été appliqué !
  * On a un modèle (?), une vue et un contrôleur qui sont fortement liés.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.4 $
  */

public class MorpionSwing {

	// les images à utiliser en fonction de l'état du jeu.
	private static final Map<ModeleMorpion.Etat, ImageIcon> images
		= new HashMap<ModeleMorpion.Etat, ImageIcon>();
	static {
		images.put(ModeleMorpion.Etat.VIDE,  new ImageIcon("../blanc.jpg"));
		images.put(ModeleMorpion.Etat.CROIX, new ImageIcon("../croix.jpg"));
		images.put(ModeleMorpion.Etat.ROND,  new ImageIcon("../rond.jpg"));
	}

// Choix de réalisation :
// ----------------------
//
//  Les attributs correspondant à la structure fixe de l'IHM sont définis
//	« final static » pour montrer que leur valeur ne pourra pas changer au
//	cours de l'exécution.  Ils sont donc initialisés sans attendre
//  l'exécution du constructeur !

	private ModeleMorpion modele;	// le modèle du jeu de Morpion

//  Les éléments de la vue (IHM)
//  ----------------------------

	/** Fenêtre principale */
	private JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[ModeleMorpion.TAILLE][ModeleMorpion.TAILLE];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();
	
	private final JMenuBar menu = new JMenuBar();


// Le constructeur
// ---------------

	/** Construire le jeu de morpion */
	public MorpionSwing() {
		this(new ModeleMorpionSimple());
	}

	/** Construire le jeu de morpion */
	public MorpionSwing(ModeleMorpion modele) {
		// Initialiser le modèle
		this.modele = modele;

		// Créer les cases du Morpion
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel();
				this.cases[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			}
		}

		// Initialiser le jeu
		this.recommencer();

		// Construire la vue (présentation)
		//	Définir la fenêtre principale
		this.fenetre = new JFrame("Morpion");
		this.fenetre.setLocation(100, 200);

		Container contenu = this.fenetre.getContentPane();

		JPanel panneauGrille = new JPanel(new GridLayout(ModeleMorpion.TAILLE, ModeleMorpion.TAILLE));
		for (int j = 0; j < ModeleMorpion.TAILLE; j++) {
			for (int i = 0; i < ModeleMorpion.TAILLE; i++) {
				panneauGrille.add(this.cases[i][j]);
			}
		}
		contenu.add(panneauGrille, BorderLayout.CENTER);

		JPanel panneauCommandes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panneauCommandes.add(this.boutonNouvellePartie);
		panneauCommandes.add(new JLabel("Joueur : "));
		panneauCommandes.add(this.joueur);
		panneauCommandes.add(this.boutonQuitter);
		panneauCommandes.add(this.menu);
		contenu.add(panneauCommandes, BorderLayout.SOUTH);

		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.quitter();
				System.exit(0);
			}
		});

		this.boutonNouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recommencer();
			}
		});

		for (int i = 0; i < ModeleMorpion.TAILLE; i++) {
			for (int j = 0; j < ModeleMorpion.TAILLE; j++) {
				final int col = i;
				final int lig = j;
				this.cases[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						jouerCase(col, lig);
					}
				});
			}
		}

		// afficher la fenêtre
		this.fenetre.pack();			// redimmensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
	}


// Quelques réactions aux interactions de l'utilisateur
// ----------------------------------------------------

	/** Recommencer une nouvelle partie. */
	public void recommencer() {
		this.modele.recommencer();

		// Vider les cases
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j].setIcon(images.get(this.modele.getValeur(i, j)));
				this.cases[i][j].revalidate();
				this.cases[i][j].repaint();
			}
		}

		// Mettre à jour le joueur
		this.joueur.setIcon(images.get(modele.getJoueur()));
		this.joueur.revalidate();
		this.joueur.repaint();
	}

	/** Jouer dans la case (col, lig). */
	private void jouerCase(int col, int lig) {
		try {
			this.modele.cocher(col, lig);
			this.cases[col][lig].setIcon(images.get(this.modele.getValeur(col, lig)));
			this.cases[col][lig].revalidate();
			this.cases[col][lig].repaint();
			this.joueur.setIcon(images.get(this.modele.getJoueur()));
			this.joueur.revalidate();
			this.joueur.repaint();
			if (this.modele.estTerminee()) {
				String message = this.modele.estGagnee()
					? "Bravo ! Le joueur a gagné !"
					: "Match nul !";
				JOptionPane.showMessageDialog(this.fenetre, message,
					"Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (CaseOccupeeException ex) {
			JOptionPane.showMessageDialog(this.fenetre, ex.getMessage(),
				"Case occupée", JOptionPane.WARNING_MESSAGE);
		}
	}


// La méthode principale
// ---------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MorpionSwing();
			}
		});
	}

}