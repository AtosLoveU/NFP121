import afficheur.Ecran;
import java.awt.Color;

/**
  * Exemple d'utilisation de la classe Ecran.
  */
class ExempleEcran {

    public static void main(String[] args) {
        // 1. Construire un écran (Titre, Largeur, Hauteur)
        Ecran testEcran = new Ecran("test_Titre", 400, 250, 15);

        // 2. Dessiner un point vert de coordonnées (1, 2)
        // Note : Selon la version, la méthode peut être dessinerPoint(x, y, couleur)
        testEcran.dessinerPoint(1, 2, Color.GREEN);

        // 3. Dessiner un segment rouge d'extrémités (6, 2) et (11, 9)
        testEcran.dessinerLigne(6, 2, 11, 9, Color.RED);

        // 4. Dessiner un cercle jaune de centre (4, 3) et rayon 2.5
        testEcran.dessinerCercle(4, 3, 2.5, Color.YELLOW);

        // 5. Dessiner le texte "Premier dessin" en bleu à la position (1, -2)
        testEcran.dessinerTexte(1, -2, "Premier dessin", Color.BLUE);
        
        // Rafraîchir l'affichage si nécessaire
        testEcran.rafraichir();
    }

}