package allumettes;

public class StrategieExpert implements Strategie {
    public int getPrise(Jeu jeu) {
        int n = jeu.getNombreAllumettes();
        int prise = (n - 1) % (Jeu.PRISE_MAX + 1);
        return prise == 0 ? 1 : prise;
    }
}