package allumettes;

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

        while (jeu.getNombreAllumettes() > 0) {
            Joueur joueurCourant = joueurs[tour % 2];
            System.out.println("Allumettes restantes : " + jeu.getNombreAllumettes());

            int prise;
            try {
                prise = joueurCourant.getPrise(jeuPourJoueur);
            } catch (OperationInterditeException e) {
                System.out.println("Abandon de la partie car "
                    + joueurCourant.getNom() + " triche !");
                return;
            }

            System.out.println(joueurCourant.getNom() + " prend "
                + prise + " " + pluriel(prise) + ".");

            try {
                jeu.retirer(prise);
                System.out.println();
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
    }

    private String pluriel(int n) {
        return n >= 2 ? "allumettes" : "allumette";
    }
}