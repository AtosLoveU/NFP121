package TD07;

import java.util.ArrayList;

public class LigneTab implements Ligne{

    private ArrayList<Character> caracteres;
    private int curseur;

    public LigneTab(int capacite){
        this.caracteres = new ArrayList<>();
        this.curseur = 0;
    }

    public int getLongueur() {
        return caracteres.size();
    }

        public int getCurseur() {
        return curseur;
    }

    public void avancer(){
		if(getCurseur() < getLongueur()){
			curseur++;
		}
	}

    public void raz(){
		if(getLongueur() > 0){
			curseur = 1;
		}
	}

}
