package allumettes;

import java.util.ArrayList;
import java.util.List;

public class Arbitre {
    private Joueur j1;
    private Joueur j2;
    private boolean confiant;

    public Arbitre(Joueur j1, Joueur j2) {
        this(j1, j2, false);
    }

    public Arbitre(Joueur j1, Joueur j2, boolean confiant) {
        this.j1 = j1;
        this.j2 = j2;
        this.confiant = confiant;
    }

    public void arbitrer(Jeu jeu) {
        Joueur[] joueurs = {j1, j2};
        Jeu jeuPourJoueur = confiant ? jeu : new Procuration(jeu);
        int tour = 0;
        Joueur perdant = null;
        List<int[]> coups = new ArrayList<>();

        while (jeu.getNombreAllumettes() > 0) {
            Joueur joueurCourant = joueurs[tour % 2];
            System.out.println("Allumettes restantes : "
                + jeu.getNombreAllumettes());

            int prise;
            try {
                prise = joueurCourant.getPrise(jeuPourJoueur);
            } catch (OperationInterditeException e) {
                System.out.println("Abandon de la partie car "
                    + joueurCourant.getNom() + " triche !");
                genererXML(joueurs, coups, null,
                    joueurCourant.getNom());
                return;
            }

            System.out.println(joueurCourant.getNom() + " prend "
                + prise + " " + pluriel(prise) + ".");

            try {
                jeu.retirer(prise);
                System.out.println();
                coups.add(new int[]{tour % 2, prise});
                perdant = joueurCourant;
                tour++;
            } catch (CoupInvalideException e) {
                System.out.println("Impossible ! Nombre invalide : "
                    + e.getCoup() + " (" + e.getProbleme() + ")");
                System.out.println();
            }
        }

        Joueur gagnant = (perdant == j1) ? j2 : j1;
        System.out.println(perdant.getNom() + " perd !");
        System.out.println(gagnant.getNom() + " gagne !");
        genererXML(joueurs, coups, gagnant.getNom(), null);
    }

    private void genererXML(Joueur[] joueurs, List<int[]> coups,
                             String gagnant, String tricheur) {
        List<String> noms = new ArrayList<>();
        noms.add(joueurs[0].getNom());
        noms.add(joueurs[1].getNom());
        new GenerateurXML().generer(coups, noms, gagnant, tricheur);
    }

    private String pluriel(int n) {
        return n >= 2 ? "allumettes" : "allumette";
    }
}