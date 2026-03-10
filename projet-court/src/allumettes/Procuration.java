package allumettes;

public class Procuration implements Jeu {
    private Jeu jeuReel;

    public Procuration(Jeu jeuReel) { this.jeuReel = jeuReel; }

    public int getNombreAllumettes() { return jeuReel.getNombreAllumettes(); }

    public String toString() { return jeuReel.toString(); }

    public void retirer(int n) throws CoupInvalideException {
        throw new OperationInterditeException("retirer interdite par la procuration");
    }
}