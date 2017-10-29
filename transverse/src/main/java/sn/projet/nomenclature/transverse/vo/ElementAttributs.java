package sn.projet.nomenclature.transverse.vo;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiModel;

/**
 * Le VO pour représenter le couple Element et attributs sur le lien.
 */
@ApiModel(description = "VO pour représenter le couple Element et attributs sur le lien.")
@JsonTypeName("ElementAttributs")
public class ElementAttributs implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6576600467422387160L;

    /**
     * Premier chiffre impair pour calculer le hash code.
     */
    private static final int ONZE = 11;

    /**
     * Deuxième chiffre impair pour calculer le hash code.
     */
    private static final int DIX_SEPT = 17;

    /**
     * L'élément.
     */
    private Element element;

    /**
     * Les attributs du lien.
     */
    private Map<String, String> attributsLiens;

    /**
     * Constructeur par défaut.
     */
    public ElementAttributs() {
    }

    /**
     * Accesseur en lecture de l'attribut <code>element</code>.
     *
     * @return l'élément
     */
    public final Element getElement() {
        return this.element;
    }

    /**
     * Accesseur en écriture de l'attribut <code>element</code>.
     *
     * @param element l'élément à setter.
     */
    public final void setElement(final Element element) {
        this.element = element;
    }

    /**
     * Accesseur en lecture de l'attribut <code>attributsLiens</code>.
     *
     * @return les attributs du lien
     */
    public final Map<String, String> getAttributsLiens() {
        return this.attributsLiens;
    }

    /**
     * Accesseur en écriture de l'attribut <code>attributsLiens</code>.
     *
     * @param attributsLiens les attributs du lien à setter
     */
    public final void setAttributsLiens(final Map<String, String> attributsLiens) {
        this.attributsLiens = attributsLiens;
    }

    /**
     * Rédéfinition de la méthode equals.<br/> Nécessaire pour éviter des doublons lors d'ajout dans
     * un Set.
     *
     * @param objet l'objet à comparer
     * @return <code>true</code> si les objets sont égaux, <code>false</code> sinon
     */
    @Override
    public final boolean equals(final Object objet) {
        // Vérification de l'égalité des références
        if (objet == this) {
            return true;
        }

        // Vérification du type du paramètre
        if (objet instanceof ElementAttributs) {
            ElementAttributs elementAComparer = (ElementAttributs) objet;
            if (this.getElement().equals(elementAComparer.getElement())) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        // On choisit 2 nombres impairs.
        // Les nombres 11 et 17 ont été choisit de manière totalement arbitraire.
        // Il est juste préférable de ne pas utiliser les mêmes pour toutes les classes.
        int result = ONZE;
        final int multiplier = DIX_SEPT;

        // Pour chaque attribut, on calcule le hashcode
        // que l'on ajoute au résultat après l'avoir multiplié par le nombre "multiplieur" :
        result = multiplier * result + (this.getElement() != null ? this.getElement().hashCode() : 0);
        result = multiplier * result + (this.getAttributsLiens() != null ? this.getAttributsLiens().hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return this.element + " => " + this.attributsLiens;
    }
}
