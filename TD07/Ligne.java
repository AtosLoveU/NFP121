/** SpÃ©TD07ion d'une ligne de texte.
  * @author	Xavier CrÃ©gut (cregut@enseeiht.fr)
  * @version	1.5
  */
public interface Ligne {
	//@ public invariant 0 <= getLongueur();	// La longueur est positive
	//@
	//@ // Le curseur est toujours sur un caractÃ¨re sauf si la ligne est vide.
	//@ public invariant 0 <= getCurseur()  && getCurseur() <= getLongueur();
	//@ public invariant getCurseur() == 0 <==> getLongueur() == 0;

	/** nombre de caractÃ¨res dans la ligne */
	/*@ pure @*/ int getLongueur();

	/** Position du curseur sur la ligne */
	/*@ pure @*/ int getCurseur();

	/** le iÃ¨me caractÃ¨re de la ligne
	 * @param i l'indice du caractÃ¨re
	 * @return le iÃ¨me caractÃ¨re de la ligne
	 */
	//@ requires 1 <= i && i <= getLongueur();	// indice valide
	/*@ pure @*/ char ieme(int i);

	/** Le caractÃ¨re sous le curseur
	 */
	//@ requires getLongueur() > 0;	// la ligne est non vide
	/*@ pure @*/ char getCourant();

	/** Avancer le curseur d'une position Ã  droite.  */
	//@ requires getCurseur() < getLongueur();		// pas Ã  la fin
	//@ ensures getCurseur() == \old(getCurseur()) + 1;	// curseur avancÃ©
	void avancer();

	/** Avancer le curseur d'une position Ã  gauche. */
	//@ requires getCurseur() > 1;			// pas en dÃ©but de ligne
	//@ ensures getCurseur() == \old(getCurseur()) - 1;	// curseur reculÃ©
	void reculer();

	/** Placer le curseur sur le premier caractÃ¨re.  */
	//@ requires getLongueur() > 0;	// ligne non vide
	//@ ensures getCurseur() == 1;	// curseur sur la premiÃ¨re position
	void raz();

	/** Remplacer le caractÃ¨re sous le curseur par le caractÃ¨re c. */
	//@ requires getLongueur() > 0;
	//@ ensures getCourant() == c;
	void remplacer(char c);

	/** Supprimer le caractÃ¨re sous le curseur.  La position du curseur reste
	 * inchangÃ©e.
	 */
	//@ requires getLongueur() > 0;
	//@ ensures getLongueur() == \old(getLongueur()) - 1; // un caractÃ¨re Ã´tÃ©
	//@ ensures getCurseur() == Math.min(\old(getCurseur()), getLongueur());
	void supprimer();

	/** Ajouter le caractÃ¨re c avant le curseur.
	 * Le curseur reste sur le mÃªme caractÃ¨re.
	 */
	//@ requires getLongueur() > 0;		// curseur positionnÃ©
	//@ 
	//@ ensures getLongueur() == \old(getLongueur()) + 1; // un caractÃ¨re ajoutÃ©
	//@ ensures getCurseur() == \old(getCurseur()) + 1;   // curseur inchangÃ©
	//@ ensures getCourant() == \old(getCourant());
	void ajouterAvant(char c);

	/** Ajouter le caractÃ¨re c aprÃ¨s le curseur.
	 * Le curseur reste sur le mÃªme caractÃ¨re.
	 */
	//@ requires getLongueur() > 0;		// curseur positionnÃ©
	//@ ensures getLongueur() == \old(getLongueur()) + 1;   // caractÃ¨re ajoutÃ©
	//@ ensures getCurseur() == \old(getCurseur());	    // curseur inchangÃ©
	//@ ensures getCourant() == \old(getCourant());
	void ajouterApres(char c);

	/** Afficher la ligne en mettant entre crochets [] le caractÃ¨re courant.
	 * Si la ligne est vide, un seul caractÃ¨re tilde(~) est affichÃ©.
	 */
	/*@ pure @*/ void afficher();

	/** Ajouter le caractÃ¨re c Ã  la fin de la ligne.
	 * Le curseur reste sur le mÃªme caractÃ¨re.
	 */
	//@ ensures getLongueur() == \old(getLongueur()) + 1;    // caractÃ¨re ajoutÃ©
	//@ ensures ieme(getLongueur()) == c;		     // Ã  la fin
	//@ ensures (\forall int i; 1 <= i && i <= \old(getLongueur());
	//@						ieme(i) == \old(ieme(i)));
	//@ ensures getLongueur() > 1 ==> getCourant() == \old(getCourant());
	//@ ensures getCurseur() == Math.max(1, \old(getCurseur()));
	void ajouterFin(char c);

	/** Ajouter le caractÃ¨re c au dÃ©but de la ligne
	 * Le curseur reste sur le mÃªme caractÃ¨re.
	 */
	//@ ensures getLongueur() == \old(getLongueur()) + 1;   // caractÃ¨re ajoutÃ©
	//@ ensures ieme(1) == c;				// en premiÃ¨re position
	//@  ensures (\forall int j; j >= 2 && j <= getLongueur();
	//@					ieme((int)j) == \old(ieme((int)(j-1))));
	//@ ensures getLongueur() > 1 ==> getCourant() == \old(getCourant());
	//@ ensures getCurseur() == \old(getCurseur()) + 1;
	void ajouterDebut(char c);

	/** supprimer le premier caractÃ¨re de la ligne.  Le curseur reste sur le
	 * mÃªme caractÃ¨re.
	 */
	//@ requires getLongueur() > 0;
	//@ ensures getLongueur() == \old(getLongueur()) - 1;
	//@ ensures \old(getCurseur()) != 1 ==> getCourant() == \old(getCourant());
	//@ ensures getCurseur()
	//@		== Math.min(Math.max((int)(\old(getCurseur())-1), 1), getLongueur());
	void supprimerPremier();

	/** supprimer le dernier caractÃ¨re de la ligne. Le curseur reste sur le mÃªme
	  * caractÃ¨re.
	  */
	//@ requires getLongueur() > 0;
	//@ ensures getLongueur() == \old(getLongueur()) - 1;
	//@ ensures \old(getCurseur()) < \old(getLongueur())
	//@				==> getCourant() == \old(getCourant());
	//@ ensures getCurseur() == Math.min(\old(getCurseur()), getLongueur());
	void supprimerDernier();

}