import java.util.*;

public class Annuaire {

    private final Map<String, String> personnelVersBureau;
    private final Map<String, Set<String>> bureauVersPersonnels;

    public Annuaire() {
        this.personnelVersBureau  = new TreeMap<>();
        this.bureauVersPersonnels = new TreeMap<>();
    }

    public void enregistrerArrivee(String nom, String bureau) throws Exception {
        if (personnelVersBureau.containsKey(nom)) {
            throw new Exception(nom);
        }
        personnelVersBureau.put(nom, bureau);
        bureauVersPersonnels
            .computeIfAbsent(bureau, b -> new TreeSet<>())
            .add(nom);
    }
    

    public void modifierBureau(String nom, String newBureau) throws Exception {
        String ancienBureau = getBureau(nom);

        Set<String> occupants = bureauVersPersonnels.get(ancienBureau);
        occupants.remove(nom);
        if (occupants.isEmpty()) {
            bureauVersPersonnels.remove(ancienBureau);
        }

        personnelVersBureau.put(nom, newBureau);
        bureauVersPersonnels
            .computeIfAbsent(newBureau, b -> new TreeSet<>())
            .add(nom);
        
        
    }
    
    public void enregistrerDepart(String nom) throws Exception {
        String bureau = getBureau(nom);

        personnelVersBureau.remove(nom);

        Set<String> occupants = bureauVersPersonnels.get(bureau);
        occupants.remove(nom);
        if (occupants.isEmpty()) {
            bureauVersPersonnels.remove(bureau);
        }
    }
    
    public Collection<String> personnels() {
        return Collections.unmodifiableSet(personnelVersBureau.keySet());
    }

    public Collection<String> bureaux() {
        return Collections.unmodifiableSet(bureauVersPersonnels.keySet());
    }

    
    private String getBureau(String nom)throws Exception{
        String bureau = personnelVersBureau.get(nom);
        if (bureau == null) {
            throw new Exception(nom);
        }
        return bureau;
    }
}