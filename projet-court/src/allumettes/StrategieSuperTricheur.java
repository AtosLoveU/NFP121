package allumettes;

import java.lang.reflect.Field;

public class StrategieSuperTricheur implements Strategie {

    public int getPrise(Jeu jeu) {
        System.out.println("[Je triche...]");
        Jeu jeuReel = extraireJeuReel(jeu);
        viderJusquaDeuxAllumettes(jeuReel);
        System.out.println("[Allumettes restantes : "
            + jeu.getNombreAllumettes() + "]");
        return 1;
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

    private void viderJusquaDeuxAllumettes(Jeu jeuReel) {
        while (jeuReel.getNombreAllumettes() > 2) {
            int aRetirer = Math.min(Jeu.PRISE_MAX,
                jeuReel.getNombreAllumettes() - 2);
            try {
                jeuReel.retirer(aRetirer);
            } catch (CoupInvalideException e) {
                // Ne devrait pas arriver
            }
        }
    }
}