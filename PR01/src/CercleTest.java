import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests des exigences E12, E13 et E14 de la classe Cercle.
 *
 * <p>E12 : constructeur Cercle(Point, Point) — deux points diametralement opposes, couleur bleue.
 * E13 : constructeur Cercle(Point, Point, Color) — deux points diametralement opposes + couleur.
 * E14 : methode de classe creerCercle(Point, Point) — centre + point de circonference, bleu.</p>
 *
 * @author etudiant
 */
public class CercleTest {

    /** Precision pour les comparaisons de reels. */
    public static final double EPSILON = 0.001;

    // -------------------------------------------------------------------
    // E12 : Cercle(Point, Point)
    // -------------------------------------------------------------------

    /** E12 : le centre est le milieu des deux points. */
    @Test
    public void testE12Centre() {
        Cercle c = new Cercle(new Point(0, 0), new Point(10, 0));
        assertEquals("E12 centre x", 5.0, c.getCentre().getX(), EPSILON);
        assertEquals("E12 centre y", 0.0, c.getCentre().getY(), EPSILON);
    }

    /** E12 : le rayon est la moitie de la distance entre les deux points. */
    @Test
    public void testE12Rayon() {
        Cercle c = new Cercle(new Point(0, 0), new Point(6, 0));
        assertEquals("E12 rayon", 3.0, c.getRayon(), EPSILON);
    }

    /** E12 : la couleur par defaut est le bleu. */
    @Test
    public void testE12Couleur() {
        Cercle c = new Cercle(new Point(0, 0), new Point(4, 0));
        assertEquals("E12 couleur bleue", Color.blue, c.getCouleur());
    }

    /** E12 : cas avec des coordonnees non triviales (points verticaux). */
    @Test
    public void testE12CentreVertical() {
        Cercle c = new Cercle(new Point(2, 1), new Point(2, 7));
        assertEquals("E12 centre x", 2.0, c.getCentre().getX(), EPSILON);
        assertEquals("E12 centre y", 4.0, c.getCentre().getY(), EPSILON);
        assertEquals("E12 rayon", 3.0, c.getRayon(), EPSILON);
    }

    // -------------------------------------------------------------------
    // E13 : Cercle(Point, Point, Color)
    // -------------------------------------------------------------------

    /** E13 : le centre est le milieu des deux points. */
    @Test
    public void testE13Centre() {
        Cercle c = new Cercle(new Point(0, 0), new Point(0, 8), Color.red);
        assertEquals("E13 centre x", 0.0, c.getCentre().getX(), EPSILON);
        assertEquals("E13 centre y", 4.0, c.getCentre().getY(), EPSILON);
    }

    /** E13 : le rayon est la moitie de la distance entre les deux points. */
    @Test
    public void testE13Rayon() {
        Cercle c = new Cercle(new Point(1, 0), new Point(7, 0), Color.green);
        assertEquals("E13 rayon", 3.0, c.getRayon(), EPSILON);
    }

    /** E13 : la couleur est bien celle passee en parametre. */
    @Test
    public void testE13Couleur() {
        Cercle c = new Cercle(new Point(0, 0), new Point(4, 0), Color.red);
        assertEquals("E13 couleur rouge", Color.red, c.getCouleur());
    }

    /** E13 : une autre couleur (vert). */
    @Test
    public void testE13CouleurVert() {
        Cercle c = new Cercle(new Point(0, 0), new Point(4, 0), Color.green);
        assertEquals("E13 couleur verte", Color.green, c.getCouleur());
    }

    // -------------------------------------------------------------------
    // E14 : creerCercle(Point centre, Point pointCirconference)
    // -------------------------------------------------------------------

    /** E14 : le centre du cercle cree est le premier point. */
    @Test
    public void testE14Centre() {
        Cercle c = Cercle.creerCercle(new Point(3, 4), new Point(3, 10));
        assertEquals("E14 centre x", 3.0, c.getCentre().getX(), EPSILON);
        assertEquals("E14 centre y", 4.0, c.getCentre().getY(), EPSILON);
    }

    /** E14 : le rayon est la distance entre les deux points. */
    @Test
    public void testE14Rayon() {
        Cercle c = Cercle.creerCercle(new Point(0, 0), new Point(6, 0));
        assertEquals("E14 rayon", 6.0, c.getRayon(), EPSILON);
    }

    /** E14 : la couleur est le bleu. */
    @Test
    public void testE14Couleur() {
        Cercle c = Cercle.creerCercle(new Point(0, 0), new Point(5, 0));
        assertEquals("E14 couleur bleue", Color.blue, c.getCouleur());
    }

    /**
     * E14 : le point de la circonference doit etre contenu dans le cercle cree.
     * Verifie aussi les points sur la bordure exacte (mutant : strict < au lieu de <=).
     */
    @Test
    public void testE14Contient() {
        Point centre = new Point(1, 2);
        Point circonf = new Point(4, 6);    // distance = 5
        Cercle c = Cercle.creerCercle(centre, circonf);
        // le point de la circonference est contenu (au sens large)
        assertTrue("E14 : pointCirconference doit etre contenu",
                c.contient(circonf));
        // un point a distance exacte = rayon depuis le centre (cas general)
        assertTrue("E14 : point a distance = rayon doit etre contenu",
                c.contient(new Point(6, 2)));
        // un point interieur est contenu
        assertTrue("E14 : centre contenu", c.contient(centre));
        // un point strictement exterieur n'est pas contenu
        assertFalse("E14 : point exterieur non contenu",
                c.contient(new Point(10, 10)));
    }

    /** E14 : cas avec rayon non entier (3, 4, 5). */
    @Test
    public void testE14RayonDiagonal() {
        Cercle c = Cercle.creerCercle(new Point(0, 0), new Point(3, 4));
        assertEquals("E14 rayon diagonal", 5.0, c.getRayon(), EPSILON);
        assertTrue("E14 : (3,4) sur la circonference",
                c.contient(new Point(3, 4)));
        assertFalse("E14 : (4,4) hors du cercle",
                c.contient(new Point(4, 4)));
    }

}
