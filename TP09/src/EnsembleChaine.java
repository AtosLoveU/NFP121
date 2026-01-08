import java.util.ArrayList;
import java.util.List;

public class EnsembleChaine implements Ensemble{

	List<Cellule> elements = new ArrayList<>();
	private Cellule premier;	
	
	public EnsembleChaine(List<Cellule> elements) {
		this.elements = elements;
		this.premier = null;
	}
	
	@Override
	public int cardinal() {
		return this.elements.size();
	}

	@Override
	public boolean estVide() {
		return cardinal() == 0;
	}

	@Override
	public boolean contient(int x) {

	Cellule actuelle = this.premier;
		while (actuelle.getLiaison() != null) {
			if(actuelle.getElement() == x) {
				return true;
			}
			actuelle = actuelle.getLiaison();
		}	
		return false;
	}

	
	@Override
	public void ajouter(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(int x) {
		// TODO Auto-generated method stub
		
	}

}
