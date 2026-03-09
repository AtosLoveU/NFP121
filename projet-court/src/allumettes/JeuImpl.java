package allumettes;

public class JeuImpl implements Jeu {
    private int nbAllumettes;

    public JeuImpl(int nbAllumettes) {
        this.nbAllumettes = nbAllumettes;
    }

    public int getNombreAllumettes() { return nbAllumettes; }

    public void retirer(int n) throws CoupInvalideException {
        if (n < 1)             throw new CoupInvalideException(n, "< 1");
        if (n > nbAllumettes)  throw new CoupInvalideException(n, "> " + nbAllumettes);
        if (n > PRISE_MAX)     throw new CoupInvalideException(n, "> " + PRISE_MAX);
        nbAllumettes -= n;
    }

    public String toString() {
        return "Allumettes restantes : " + nbAllumettes;
    }
}