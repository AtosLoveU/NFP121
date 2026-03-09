package allumettes;
import org.junit.Test;
import static org.junit.Assert.*;

public class StrategieRapideTest {

    // Jeu minimal en classe interne pour ne pas dépendre de JeuImpl
    private Jeu jeuAvec(int n) {
        return new Jeu() {
            int nb = n;
            public int getNombreAllumettes() { return nb; }
            public void retirer(int x) throws CoupInvalideException { nb -= x; }
        };
    }

    @Test
    public void priseSurJeuA13() throws CoupInvalideException {
        assertEquals(3, new StrategieRapide().getPrise(jeuAvec(13)));
    }

    @Test
    public void priseSurJeuA3() throws CoupInvalideException {
        assertEquals(3, new StrategieRapide().getPrise(jeuAvec(3)));
    }

    @Test
    public void priseSurJeuA2() throws CoupInvalideException {
        assertEquals(2, new StrategieRapide().getPrise(jeuAvec(2)));
    }

    @Test
    public void priseSurJeuA1() throws CoupInvalideException {
        assertEquals(1, new StrategieRapide().getPrise(jeuAvec(1)));
    }
}