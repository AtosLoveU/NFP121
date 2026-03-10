package allumettes;

public class StrategieTricheur implements Strategie {
    public int getPrise(Jeu jeu) {
        System.out.println("[Je triche...]");
        while (jeu.getNombreAllumettes() > 2) {
            int aRetirer = Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes() - 2);
            try {
                jeu.retirer(aRetirer); // Lance OperationInterditeException si proxy
            } catch (CoupInvalideException e) {
                // Ne devrait pas arriver
            }
        }
        System.out.println("[Allumettes restantes : " + jeu.getNombreAllumettes() + "]");
        return 1;
    }
}