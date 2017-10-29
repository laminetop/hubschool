package sn.projet.nomenclature.transverse.vo;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

/**
 * AttributExterne Entité mappé directement avec la BDD.
 */
@JsonTypeName("AttributExterne")
public class AttributExterne implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -4393862050641262285L;

    /**
     * Identifiant technique.
     */
    private Long idAttribut;

    /**
     * Libellé de l'attribut.
     */
    private String libelle;

    /**
     * valeur de l'attribut.
     */
    private String valeur;

    /**
     * élément parent de l'attribut.
     */
    private Element element;

    /**
     * Accesseur en lecture de l'attribut idAttribut.
     *
     * @return idAttribut .
     */
    public final Long getIdAttribut() {
        return idAttribut;
    }

    /**
     * Accesseur en écriture de l'attribut idAttribut .
     *
     * @param idAttribut idAttribut à setter.
     */
    public final void setIdAttribut(final Long idAttribut) {
        this.idAttribut = idAttribut;
    }

    /**
     * Accesseur en lecture de l'attribut libelle.
     *
     * @return libelle .
     */
    public final String getLibelle() {
        return libelle;
    }

    /**
     * Accesseur en écriture de l'attribut libelle .
     *
     * @param libelle libelle à setter.
     */
    public final void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Accesseur en lecture de l'attribut valeur.
     *
     * @return valeur .
     */
    public final String getValeur() {
        return valeur;
    }

    /**
     * Accesseur en écriture de l'attribut valeur .
     *
     * @param valeur valeur à setter.
     */
    public final void setValeur(final String valeur) {
        this.valeur = valeur;
    }

    /**
     * Accesseur en lecture de l'attribut element.
     *
     * @return element .
     */
    public final Element getElement() {
        return element;
    }

    /**
     * Accesseur en écriture de l'attribut element .
     *
     * @param element element à setter.
     */
    public final void setElement(final Element element) {
        this.element = element;
    }
}
