package sn.projet.hubschool.error;

/**
 * Retour generique, fonctionnel ou technique
 */
public class DtoRestRetour {

    private String codeRetour;

    private String libelleRetour;

    private TypeRetour typeRetour;

    /**
     * Constructeur simple
     */
    public DtoRestRetour() {
        super();
    }

    /**
     * constructeur avec champs initialises
     * 
     * @param codeRetour
     * @param libelleRetour
     * @param typeRetour
     */
    public DtoRestRetour(String codeRetour, String libelleRetour, TypeRetour typeRetour) {
        super();
        this.codeRetour = codeRetour;
        this.libelleRetour = libelleRetour;
        this.typeRetour = typeRetour;
    }

    /**
     * Gets the value of the codeRetour property.
     * 
     * @return possible object is {@link String }
     */
    public String getCodeRetour() {
        return codeRetour;
    }

    /**
     * Sets the value of the codeRetour property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setCodeRetour(String value) {
        this.codeRetour = value;
    }

    /**
     * Gets the value of the libelleRetour property.
     * 
     * @return possible object is {@link String }
     */
    public String getLibelleRetour() {
        return libelleRetour;
    }

    /**
     * Sets the value of the libelleRetour property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setLibelleRetour(String value) {
        this.libelleRetour = value;
    }

    /**
     * Gets the value of the typeRetour property.
     * 
     * @return possible object is {@link String }
     */
    public TypeRetour getTypeRetour() {
        return typeRetour;
    }

    /**
     * Sets the value of the typeRetour property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setTypeRetour(TypeRetour typeRetour) {
        this.typeRetour = typeRetour;
    }

}
