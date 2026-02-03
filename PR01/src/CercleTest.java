import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests pour E12, E13 et E14
 */
public class CercleTest {
    
    public static final double EPSILON = 0.001;
    
    private Point p1, p2;
    
    @Before
    public void setUp() {
        p1 = new Point(0, 0);
        p2 = new Point(6, 0);
    }
    
    // Tests E12
    
    @Test
    public void testE12Centre() {
        Cercle c = new Cercle(p1, p2);
        assertEquals(3.0, c.getCentre().getX(), EPSILON);
        assertEquals(0.0, c.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testE12Rayon() {
        Cercle c = new Cercle(p1, p2);
        assertEquals(3.0, c.getRayon(), EPSILON);
    }
    
    @Test
    public void testE12Couleur() {
        Cercle c = new Cercle(p1, p2);
        assertEquals(Color.blue, c.getCouleur());
    }
    
    @Test
    public void testE12Diagonal() {
        Cercle c = new Cercle(new Point(1, 1), new Point(5, 5));
        assertEquals(3.0, c.getCentre().getX(), EPSILON);
        assertEquals(3.0, c.getCentre().getY(), EPSILON);
        assertEquals(2.828, c.getRayon(), EPSILON);
    }
    
    // Tests E13
    
    @Test
    public void testE13Centre() {
        Cercle c = new Cercle(p1, p2, Color.red);
        assertEquals(3.0, c.getCentre().getX(), EPSILON);
        assertEquals(0.0, c.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testE13Rayon() {
        Cercle c = new Cercle(p1, p2, Color.red);
        assertEquals(3.0, c.getRayon(), EPSILON);
    }
    
    @Test
    public void testE13Couleur() {
        Cercle c = new Cercle(p1, p2, Color.red);
        assertEquals(Color.red, c.getCouleur());
    }
    
    @Test
    public void testE13Yellow() {
        Cercle c = new Cercle(p1, p2, Color.yellow);
        assertEquals(Color.yellow, c.getCouleur());
    }
    
    // Tests E14
    
    @Test
    public void testE14Centre() {
        Cercle c = Cercle.creerCercle(new Point(2, 3), new Point(5, 3));
        assertEquals(2.0, c.getCentre().getX(), EPSILON);
        assertEquals(3.0, c.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testE14Rayon() {
        Cercle c = Cercle.creerCercle(new Point(2, 3), new Point(5, 3));
        assertEquals(3.0, c.getRayon(), EPSILON);
    }
    
    @Test
    public void testE14Couleur() {
        Cercle c = Cercle.creerCercle(new Point(2, 3), new Point(5, 3));
        assertEquals(Color.blue, c.getCouleur());
    }
    
    @Test
    public void testE14Diagonal() {
        Cercle c = Cercle.creerCercle(new Point(0, 0), new Point(3, 4));
        assertEquals(5.0, c.getRayon(), EPSILON);
    }
    
    @Test
    public void testE14Contient() {
        Point centre = new Point(0, 0);
        Point circonference = new Point(3, 4);
        Cercle c = Cercle.creerCercle(centre, circonference);
        assertTrue(c.contient(circonference));
    }
    
}