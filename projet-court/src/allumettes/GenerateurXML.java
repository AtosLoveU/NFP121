package allumettes;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateurXML {

    private static final String NOM_FICHIER = "deroulement.xml";
    private static final String NOM_DTD     = "deroulement.dtd";

    public void generer(List<int[]> coups,
                        List<String> nomsJoueurs,
                        String gagnant,
                        String tricheur) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(NOM_FICHIER))) {
            ecrireEntete(out);
            ecrireCoups(out, coups, nomsJoueurs);
            ecrireResultat(out, gagnant, tricheur);
            out.println("</deroulement>");
        } catch (IOException e) {
            System.err.println("Erreur écriture XML : " + e.getMessage());
        }
    }

    private void ecrireEntete(PrintWriter out) {
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<!DOCTYPE deroulement SYSTEM \"" + NOM_DTD + "\">");
        out.println("<deroulement>");
    }

    private void ecrireCoups(PrintWriter out,
                              List<int[]> coups,
                              List<String> nomsJoueurs) {
        for (int i = 0; i < coups.size(); i++) {
            int[] coup = coups.get(i);
            // coup[0] = numéro du joueur (0 ou 1), coup[1] = allumettes prises
            out.println("  <coup numero=\"" + (i + 1)
                + "\" joueur=\"" + nomsJoueurs.get(coup[0])
                + "\" allumettes=\"" + coup[1] + "\"/>");
        }
    }

    private void ecrireResultat(PrintWriter out,
                                 String gagnant,
                                 String tricheur) {
        if (tricheur != null) {
            out.println("  <resultat tricheur=\"" + tricheur + "\"/>");
        } else {
            out.println("  <resultat gagnant=\"" + gagnant + "\"/>");
        }
    }
}