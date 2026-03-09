package allumettes;

public class StrategieRapide implements Strategie {
    public int getPrise(Jeu jeu) {
        return Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes());
    }
}