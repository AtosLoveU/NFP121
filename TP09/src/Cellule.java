
public class Cellule {
	private int element;
	private Cellule liaison;
	
	public Cellule(int element, Cellule liaison) {
		this.element = element;
		this.liaison = liaison;
	}
	
	public int getElement() {
		return this.element;
	}
	
	public Cellule getLiaison() {
		return this.liaison;
	}
	
	public void setElement(int e) {
		this.element = e;
	}
	
	public void setLiaison(Cellule l) {
		this.liaison = l;
	}
}
