package allumettes;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {

	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
        try {
            verifierNombreArguments(args);
            Scanner scanner = new Scanner(System.in);

            boolean confiant = args[0].equals("-confiant");
            int debut = confiant ? 1 : 0;

            Joueur j1 = creerJoueur(args[debut], scanner);
            Joueur j2 = creerJoueur(args[debut + 1], scanner);

            Arbitre arbitre = new Arbitre(j1, j2, confiant);
            Jeu jeu = new JeuImpl(13);
            arbitre.arbitrer(jeu);

        } catch (ConfigurationException e) {
            System.out.println();
            System.out.println("Erreur : " + e.getMessage());
            afficherUsage();
            System.exit(1);
        }
    }

	private static Joueur creerJoueur(String description, Scanner scanner) {
        String[] parties = description.split("@");
        if (parties.length != 2) {
            throw new ConfigurationException("Format invalide : " + description);
        }
        String nom = parties[0];
        String stratNom = parties[1];
        Strategie strategie = creerStrategie(stratNom, nom, scanner);
        return new Joueur(nom, strategie);
    }

	private static Strategie creerStrategie(String nom, String joueurNom, Scanner scanner) {
        switch (nom) {
            case "rapide":  return new StrategieRapide();
            case "naif":    return new StrategieNaif();
            case "expert":  return new StrategieExpert();
            case "humain":  return new StrategieHumain(joueurNom, scanner);
            case "tricheur": return new StrategieTricheur();
            default: throw new ConfigurationException("Strategie inconnue : " + nom);
        }
    }

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}
