package allumettes;

import java.lang.reflect.Field;

public class StrategieSwing implements Strategie {

    private final FenetreJoueur fenetre;

    public StrategieSwing(String nomJoueur) {
        fenetre = new FenetreJoueur(nomJoueur);
    }

    public int getPrise(Jeu jeu) {
        fenetre.mettreAJour(jeu.getNombreAllumettes());
        fenetre.setVisible(true);

        int choix = fenetre.attendreChoix();

        while (choix == -1) {
            // Le joueur a cliqué "tricher"
            tricher(jeu, fenetre.getNbATricher());
            fenetre.mettreAJour(jeu.getNombreAllumettes());
            choix = fenetre.attendreChoix();
        }

        fenetre.setVisible(false);
        return choix;
    }

    private void tricher(Jeu jeu, int nbATricher) {
        Jeu jeuReel = extraireJeuReel(jeu);
        int nbActuel = jeuReel.getNombreAllumettes();

        if (nbActuel == 1) {
            // Cas spécial : ajouter une allumette par réflexion
            ajouterAllumette(jeuReel);
            System.out.println("[Je triche... 1 allumette en plus]");
        } else {
            // Retirer nbATricher allumettes discrètement
            retirerDiscrètement(jeuReel, nbATricher);
            System.out.println("[Je triche... " + nbATricher
                + " allumette" + (nbATricher > 1 ? "s" : "")
                + " en moins]");
        }
    }

    private Jeu extraireJeuReel(Jeu jeu) {
        if (jeu instanceof Procuration) {
            try {
                Field champ = Procuration.class.getDeclaredField("jeuReel");
                champ.setAccessible(true);
                return (Jeu) champ.get(jeu);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Ne devrait pas arriver
            }
        }
        return jeu;
    }

    private void retirerDiscrètement(Jeu jeuReel, int nbATricher) {
        int aRetirer = Math.min(nbATricher,
            jeuReel.getNombreAllumettes() - 1);
        try {
            jeuReel.retirer(aRetirer);
        } catch (CoupInvalideException e) {
            // Ne devrait pas arriver
        }
    }

    private void ajouterAllumette(Jeu jeuReel) {
        try {
            Field champ = JeuImpl.class.getDeclaredField("nbAllumettes");
            champ.setAccessible(true);
            int actuel = (int) champ.get(jeuReel);
            champ.set(jeuReel, actuel + 1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Ne devrait pas arriver
        }
    }
}