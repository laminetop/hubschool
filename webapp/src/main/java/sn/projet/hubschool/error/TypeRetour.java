package sn.projet.hubschool.error;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type de retour generique, fonctionnel ou technique
 *
 */
public enum TypeRetour {

    TECHNIQUE("retourTechnique"), 
    FONCTIONNEL("retourFonctionnel");

    private final String value;

    TypeRetour(String value) {
        this.value = value;
    }

    /**
     * valeur de l'objet
     * @return
     */    
    @JsonValue
    public final String value() {
        return value;
    }

    /**
     * transcodifie une valeur d'un backoffice exterieur en TypeRetour
     * @param v
     * @return
     */        
    public static TypeRetour fromValue(String v) {
        for (TypeRetour retour : TypeRetour.values()) {
            if (String.valueOf(retour.value()).equals(v)) {
                return retour;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
