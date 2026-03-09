import java.awt.Color;

/**
 * Classe representant un cercle geometrique dans un plan cartesien.
 *
 * <p>Un cercle est defini par son centre (un Point), son rayon (un reel
 * strictement positif) et sa couleur. Il implemente l'interface Mesurable2D
 * pour fournir son perimetre et son aire.</p>
 */
public class Cercle implements Mesurable2D {

    /** Valeur de PI issue de la classe Math. */
    public static final double PI = Math.PI;

    /** Centre du cercle. */
    private Point centre;

    /** Rayon du cercle (strictement positif). */
    private double rayon;

    /** Couleur de la circonference du cercle. */
    private Color couleur;

    // ---------------------------------------------------------------
    // Constructeur prive de base (seul a initialiser les attributs)
    // ---------------------------------------------------------------

    /**
     * Constructeur prive : initialise les trois attributs du cercle.
     *
     * @param centreCercle  centre du cercle (non null)
     * @param rayonCercle   rayon du cercle (strictement positif)
     * @param couleurCercle couleur de la circonference (non null)
     */
    private Cercle(Point centreCercle, double rayonCercle, Color couleurCercle) {
        assert centreCercle != null;
        assert rayonCercle > 0;
        assert couleurCercle != null;
        this.centre = new Point(centreCercle.getX(), centreCercle.getY());
        this.rayon = rayonCercle;
        this.couleur = couleurCercle;
    }

    // ---------------------------------------------------------------
    // Helpers statiques prives pour le constructeur par deux points
    // ---------------------------------------------------------------

    /**
     * Calculer le centre a partir de deux points diametralement opposes.
     * Verifie les preconditions sur les deux points.
     *
     * @param p1 premier point (non null)
     * @param p2 second point (non null, distinct de p1)
     * @return le point milieu de p1 et p2
     */
    private static Point milieu(Point p1, Point p2) {
        assert p1 != null;
        assert p2 != null;
        assert p1.distance(p2) > 0;
        return new Point((p1.getX() + p2.getX()) / 2.0,
                         (p1.getY() + p2.getY()) / 2.0);
    }

    /**
     * Calculer le rayon a partir de deux points diametralement opposes.
     *
     * @param p1 premier point (non null)
     * @param p2 second point (non null, distinct de p1)
     * @return la moitie de la distance entre p1 et p2
     */
    private static double demiDistance(Point p1, Point p2) {
        return p1.distance(p2) / 2.0;
    }

    // ---------------------------------------------------------------
    // Constructeurs publics (3 au total)
    // ---------------------------------------------------------------

    /**
     * Construire un cercle a partir de son centre et de son rayon.
     * La couleur est initialisee a bleu.
     *
     * @param centreCercle centre du cercle (non null)
     * @param rayonCercle  rayon du cercle (strictement positif)
     */
    public Cercle(Point centreCercle, double rayonCercle) {
        this(centreCercle, rayonCercle, Color.blue);
    }

    /**
     * Construire un cercle a partir de deux points diametralement opposes.
     * La couleur est initialisee a bleu.
     *
     * @param point1 premier point extremite du diametre (non null)
     * @param point2 second point extremite du diametre (non null, different de point1)
     */
    public Cercle(Point point1, Point point2) {
        this(point1, point2, Color.blue);
    }

    /**
     * Construire un cercle a partir de deux points diametralement opposes et d'une couleur.
     *
     * @param point1        premier point extremite du diametre (non null)
     * @param point2        second point extremite du diametre (non null, different de point1)
     * @param couleurCercle couleur de la circonference (non null)
     */
    public Cercle(Point point1, Point point2, Color couleurCercle) {
        this(milieu(point1, point2), demiDistance(point1, point2), couleurCercle);
    }

    // ---------------------------------------------------------------
    // Fabrique statique
    // ---------------------------------------------------------------

    /**
     * Creer un cercle a partir de son centre et d'un point de sa circonference.
     * La couleur est initialisee a bleu.
     *
     * @param centreCercle       centre du cercle (non null)
     * @param pointCirconference point sur la circonference (non null, different du centre)
     * @return un nouveau cercle bleu de centre centreCercle passant par pointCirconference
     */
    public static Cercle creerCercle(Point centreCercle, Point pointCirconference) {
        assert centreCercle != null;
        assert pointCirconference != null;
        assert centreCercle.distance(pointCirconference) > 0;
        return new Cercle(centreCercle, centreCercle.distance(pointCirconference));
    }

    // ---------------------------------------------------------------
    // Methodes
    // ---------------------------------------------------------------

    /**
     * Translater le cercle d'un deplacement (dx, dy).
     *
     * @param dx deplacement suivant l'axe des X
     * @param dy deplacement suivant l'axe des Y
     */
    public void translater(double dx, double dy) {
        this.centre.translater(dx, dy);
    }

    /**
     * Obtenir le centre du cercle.
     *
     * @return une copie du centre du cercle
     */
    public Point getCentre() {
        return new Point(this.centre.getX(), this.centre.getY());
    }

    /**
     * Obtenir le rayon du cercle.
     *
     * @return valeur du rayon
     */
    public double getRayon() {
        return this.rayon;
    }

    /**
     * Obtenir le diametre du cercle.
     *
     * @return valeur du diametre (deux fois le rayon)
     */
    public double getDiametre() {
        return 2 * this.rayon;
    }

    /**
     * Indiquer si un point est a l'interieur (au sens large) du cercle.
     *
     * @param point point a tester (non null)
     * @return true si le point est dans le cercle ou sur sa circonference
     */
    public boolean contient(Point point) {
        assert point != null;
        return this.centre.distance(point) <= this.rayon;
    }

    /**
     * Obtenir le perimetre du cercle.
     *
     * @return perimetre donne par la formule 2 * PI * rayon
     */
    public double perimetre() {
        return 2 * PI * this.rayon;
    }

    /**
     * Obtenir l'aire de la surface delimitee par le cercle.
     *
     * @return aire donnee par la formule PI * rayon^2
     */
    public double aire() {
        return PI * this.rayon * this.rayon;
    }

    /**
     * Obtenir la couleur de la circonference du cercle.
     *
     * @return couleur du cercle
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Changer la couleur de la circonference du cercle.
     *
     * @param nouvelleCouleur nouvelle couleur (non null)
     */
    public void setCouleur(Color nouvelleCouleur) {
        assert nouvelleCouleur != null;
        this.couleur = nouvelleCouleur;
    }

    /**
     * Changer le rayon du cercle.
     *
     * @param nouveauRayon nouveau rayon (strictement positif)
     */
    public void setRayon(double nouveauRayon) {
        assert nouveauRayon > 0;
        this.rayon = nouveauRayon;
    }

    /**
     * Changer le diametre du cercle.
     *
     * @param nouveauDiametre nouveau diametre (strictement positif)
     */
    public void setDiametre(double nouveauDiametre) {
        assert nouveauDiametre > 0;
        this.rayon = nouveauDiametre / 2.0;
    }

    /**
     * Retourner une representation textuelle du cercle.
     * Format : Cr@(a, b) ou r est le rayon et (a, b) le centre.
     *
     * @return chaine representant le cercle
     */
    @Override
    public String toString() {
        return "C" + this.rayon + "@" + this.centre.toString();
    }

}
