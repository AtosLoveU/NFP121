package editeur.commande;

import editeur.Ligne;

public class CommandeCurseurRaz extends CommandeLigne {
    public CommandeCurseurRaz(Ligne l) {
         super(l); 
        }

    public void executer() {
         ligne.raz(); 
        }

    public boolean estExecutable() { 
        return ligne.getLongueur() > 0; 
    }
}