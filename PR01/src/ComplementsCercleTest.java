import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests complementaires
 */
public class ComplementsCercleTest {
    
    public static final double EPSILON = 0.001;
    
    private Cercle c1;
    
    @Before
    public void setUp() {
        c1 = new Cercle(new Point(0, 0), 5);
    }
    
    @Test
    public void testTranslationSuccessives() {
        c1.translater(5, 3);
        c1.translater(-2, 4);
        assertEquals(3.0, c1.getCentre().getX(), EPSILON);
        assertEquals(7.0, c1.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testPerimetreGrandCercle() {
        Cercle c = new Cercle(new Point(0, 0), 100);
        assertEquals(200 * Math.PI, c.perimetre(), EPSILON);
    }
    
    @Test
    public void testAirePetitCercle() {
        Cercle c = new Cercle(new Point(0, 0), 0.5);
        assertEquals(0.25 * Math.PI, c.aire(), EPSILON);
    }
    
    @Test
    public void testContientCentre() {
        assertTrue(c1.contient(new Point(0, 0)));
    }
    
    @Test
    public void testContientCirconference() {
        assertTrue(c1.contient(new Point(5, 0)));
        assertTrue(c1.contient(new Point(0, 5)));
    }
    
    @Test
    public void testNeContientPas() {
        assertFalse(c1.contient(new Point(100, 100)));
    }
    
    @Test
    public void testSetRayon() {
        c1.setRayon(15);
        assertEquals(15, c1.getRayon(), EPSILON);
        assertEquals(30, c1.getDiametre(), EPSILON);
    }
    
    @Test
    public void testSetDiametre() {
        c1.setDiametre(30);
        assertEquals(15, c1.getRayon(), EPSILON);
    }
    
    @Test
    public void testSetCouleur() {
        c1.setCouleur(Color.red);
        assertEquals(Color.red, c1.getCouleur());
    }
    
    @Test
    public void testEncapsulation() {
        Point centre = c1.getCentre();
        centre.translater(100, 100);
        assertEquals(0.0, c1.getCentre().getX(), EPSILON);
        assertEquals(0.0, c1.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testToString() {
        String s = c1.toString();
        assertTrue(s.contains("C"));
        assertTrue(s.contains("@"));
    }
    
    @Test
    public void testCercleNegatif() {
        Cercle c = new Cercle(new Point(-5, -5), 2);
        assertEquals(2, c.getRayon(), EPSILON);
        assertTrue(c.contient(new Point(-5, -5)));
    }
    
    @Test
    public void testCreerCercleVertical() {
        Cercle c = Cercle.creerCercle(new Point(0, 0), new Point(0, 8));
        assertEquals(8, c.getRayon(), EPSILON);
    }
    
    @Test
    public void testConstructeurVertical() {
        Cercle c = new Cercle(new Point(0, 0), new Point(0, 10));
        assertEquals(0.0, c.getCentre().getX(), EPSILON);
        assertEquals(5.0, c.getCentre().getY(), EPSILON);
        assertEquals(5, c.getRayon(), EPSILON);
    }
    
}