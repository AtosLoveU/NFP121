import java.lang.reflect.*;
import java.util.*;

/** L'objectif est de faire un lanceur simple sans utiliser toutes les clases
  * de notre architecture JUnit.   Il permet juste de valider la compréhension
  * de l'introspection en Java.
  */
public class LanceurIndependant {
	private int nbTestsLances;
	private int nbErreurs;
	private int nbEchecs;
	private List<Throwable> erreurs = new ArrayList<>();

	public LanceurIndependant(String... nomsClasses) {
	    System.out.println();

		// Lancer les tests pour chaque classe
		for (String nom : nomsClasses) {
			try {
				System.out.print(nom + " : ");
				this.testerUneClasse(nom);
				System.out.println();
			} catch (ClassNotFoundException e) {
				System.out.println(" Classe inconnue !");
			} catch (Exception e) {
				System.out.println(" Problème : " + e);
				e.printStackTrace();
			}
		}

		// Afficher les erreurs
		for (Throwable e : erreurs) {
			System.out.println();
			e.printStackTrace();
		}

		// Afficher un bilan
		System.out.println();
		System.out.printf("%d tests lancés dont %d échecs et %d erreurs.\n",
				nbTestsLances, nbEchecs, nbErreurs);
	}


	public int getNbTests() {
		return this.nbTestsLances;
	}


	public int getNbErreurs() {
		return this.nbErreurs;
	}


	public int getNbEchecs() {
		return this.nbEchecs;
	}


	private void testerUneClasse(String nomClasse)
		throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
						  InstantiationException, IllegalAccessException
	{
		// Récupérer la classe
        Class<?> clazz = Class.forName(nomClasse);

		// Récupérer les méthodes "preparer" et "nettoyer"
		Method preparer = null;
		Method nettoyer = null;
		try {
            preparer = clazz.getMethod("preparer");
        } catch (NoSuchMethodException e) {
            // Pas de méthode preparer : on laisse null
        }
        try {
            nettoyer = clazz.getMethod("nettoyer");
        } catch (NoSuchMethodException e) {
            // Pas de méthode nettoyer : on laisse null
        }

		// Instancier l'objet qui sera le récepteur des tests
        Object objet = clazz.getDeclaredConstructor().newInstance();

		// Exécuter les méthods de test
		for (Method m : clazz.getMethods()) {
			// Une méthode de test est publique, d'instance (non static), sans paramètre, et son nom commence par "test"
            if (m.getName().startsWith("test")
                    && m.getParameterCount() == 0
                    && !Modifier.isStatic(m.getModifiers())) {
 
                this.nbTestsLances++;
                try {
                    // 1. Appeler preparer (si elle existe)
                    if (preparer != null) {
                        preparer.invoke(objet);
                    }
 
                    // 2. Exécuter le test
                    m.invoke(objet);
                    System.out.print(".");   // test réussi
 
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof Echec) {
                        // Échec fonctionnel (Assert.assertTrue a échoué)
                        this.nbEchecs++;
                        System.out.print("E");
                        erreurs.add(cause);
                    } else {
                        // Erreur inattendue (exception levée dans le test)
                        this.nbErreurs++;
                        System.out.print("X");
                        erreurs.add(cause);
                    }
                } catch (Exception e) {
                    this.nbErreurs++;
                    System.out.print("X");
                    erreurs.add(e);
                } finally {
                    // 3. Appeler nettoyer (si elle existe), même en cas d'erreur
                    if (nettoyer != null) {
                        try {
                            nettoyer.invoke(objet);
                        } catch (Exception e) {
                            // on ignore les erreurs de nettoyage
                        }
                    }
                }
            }
        }
	}

	public static void main(String... args) {
		LanceurIndependant lanceur = new LanceurIndependant(args);
	}

}
