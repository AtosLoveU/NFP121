package allumettes;
import java.util.Random;

public class StrategieNaif implements Strategie {
    private Random random = new Random();

    public int getPrise(Jeu jeu) {
        return 1 + random.nextInt(Jeu.PRISE_MAX);
    }
}