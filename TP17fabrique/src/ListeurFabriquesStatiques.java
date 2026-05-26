import java.lang.reflect.*;
import java.util.*;

class ListeurFabriquesStatiques implements Listeur {

    @Override
    public List<Method> getMethodes(String nomClasse) throws Exception {
        Class<?> classCree = Class.forName(nomClasse);
        List<Method> fabriques = new ArrayList<>();

        for (Method m : classCree.getDeclaredMethods()) {

            //méthode statique
            if (!Modifier.isStatic(m.getModifiers())) continue;

            //type de retour est la classe elle-même
            if (!m.getReturnType().equals(classCree)) continue;
            
            //aucun paramètre n'a pour type cette classe
            boolean paramDeTypeClasse = false;
            for (Class<?> param : m.getParameterTypes()) {
                if (param.equals(classCree)) {
                    paramDeTypeClasse = true;
                    break;
                }
            }
            if (paramDeTypeClasse) continue;
            
            fabriques.add(m);

        }

        return fabriques;
    }
}