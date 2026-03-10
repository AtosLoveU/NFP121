package allumettes;
import java.util.Scanner;

public class StrategieHumain implements Strategie {
    private String nom;
    private Scanner scanner;

    public StrategieHumain(String nom, Scanner scanner) {
        this.nom = nom;
        this.scanner = scanner;
    }

    public int getPrise(Jeu jeu) {
        while (true) {
            System.out.print(nom + ", combien d'allumettes ? ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                scanner.nextLine();
                return n;
            }
            String saisie = scanner.next();
            scanner.nextLine();
            if (saisie.equals("triche")) {
                try {
                    jeu.retirer(1); // Déclenche OperationInterditeException si proxy
                } catch (CoupInvalideException e) {
                    // Ne devrait pas arriver avec logique correcte
                }
                System.out.println("[Une allumette en moins, plus que "
                    + jeu.getNombreAllumettes() + ". chutt ]");
            } else {
                System.out.println("Vous devez donner un entier.");
            }
        }
    }
}