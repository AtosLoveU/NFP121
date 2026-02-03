import java.awt.Color;

/**
 * Classe Cercle
 */
public class Cercle implements Mesurable2D {
    
    public static final double PI = Math.PI;
    
    private Point centre;
    private double rayon;
    private Color couleur;
    
    // Constructeur avec centre et rayon
    public Cercle(Point centreCercle, double rayonCercle) {
        assert centreCercle != null;
        assert rayonCercle > 0;
        
        this.centre = new Point(centreCercle.getX(), centreCercle.getY());
        this.rayon = rayonCercle;
        this.couleur = Color.blue;
    }
    
    // Constructeur avec deux points diamétralement opposés
    public Cercle(Point point1, Point point2) {
        assert point1 != null;
        assert point2 != null;
        assert point1.distance(point2) > 0;
        
        double centreX = (point1.getX() + point2.getX()) / 2.0;
        double centreY = (point1.getY() + point2.getY()) / 2.0;
        this.centre = new Point(centreX, centreY);
        this.rayon = point1.distance(point2) / 2.0;
        this.couleur = Color.blue;
    }
    
    // Constructeur avec deux points et couleur
    public Cercle(Point point1, Point point2, Color couleurCercle) {
        assert point1 != null;
        assert point2 != null;
        assert point1.distance(point2) > 0;
        assert couleurCercle != null;
        
        double centreX = (point1.getX() + point2.getX()) / 2.0;
        double centreY = (point1.getY() + point2.getY()) / 2.0;
        this.centre = new Point(centreX, centreY);
        this.rayon = point1.distance(point2) / 2.0;
        this.couleur = couleurCercle;
    }
    
    // Méthode statique pour créer un cercle
    public static Cercle creerCercle(Point centreCercle, Point pointCirconference) {
        assert centreCercle != null;
        assert pointCirconference != null;
        assert centreCercle.distance(pointCirconference) > 0;
        
        double rayonCalcule = centreCercle.distance(pointCirconference);
        return new Cercle(centreCercle, rayonCalcule);
    }
    
    public void translater(double dx, double dy) {
        this.centre.translater(dx, dy);
    }
    
    public Point getCentre() {
        return new Point(this.centre.getX(), this.centre.getY());
    }
    
    public double getRayon() {
        return this.rayon;
    }
    
    public double getDiametre() {
        return 2 * this.rayon;
    }
    
    public boolean contient(Point point) {
        assert point != null;
        return this.centre.distance(point) <= this.rayon;
    }
    
    public double perimetre() {
        return 2 * PI * this.rayon;
    }
    
    public double aire() {
        return PI * this.rayon * this.rayon;
    }
    
    public Color getCouleur() {
        return this.couleur;
    }
    
    public void setCouleur(Color nouvelleCouleur) {
        assert nouvelleCouleur != null;
        this.couleur = nouvelleCouleur;
    }
    
    public void setRayon(double nouveauRayon) {
        assert nouveauRayon > 0;
        this.rayon = nouveauRayon;
    }
    
    public void setDiametre(double nouveauDiametre) {
        assert nouveauDiametre > 0;
        this.rayon = nouveauDiametre / 2.0;
    }
    
    @Override
    public String toString() {
        return "C" + this.rayon + "@" + this.centre.toString();
    }
    
}