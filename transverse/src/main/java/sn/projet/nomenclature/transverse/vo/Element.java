package sn.projet.nomenclature.transverse.vo;

import sn.projet.nomenclature.transverse.Constantes;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;

/**
 * VO element.
 */
@XmlType(namespace = Constantes.TYPE_NS)
@ApiModel(description = "Element d'une nomenclature")
@JsonTypeName("Element")
public class Element implements IElement, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8816157956267793682L;

    /**
     * Premier chiffre impair pour calculer le hash code.
     */
    private static final int SEPT = 7;

    /**
     * Deuxième chiffre impair pour calculer le hash code.
     */
    private static final int DIX_SEPT = 17;

    /**
     * id.
     */
    private String id;

    /**
     * codeElement.
     */
    private String codeElement;

    /**
     * libelleCourt.
     */
    private String libelleCourt;

    /**
     * libelleLong.
     */
    private String libelleLong;

    /**
     * libelleImpression.
     */
    private String libelleImpression;

    /**
     * dateEffet.
     */
    private Date dateEffet;

    /**
     * dateFin.
     */
    private Date dateFin;

    /**
     * Les attributs externes de l'élément.
     */
    private Map<String, String> attr = new HashMap<String, String>();

    /**
     * Accesseur en lecture de l'attribut <code>id</code>.
     *
     * @return id .
     */
    @Override
    public final String getId() {
        return id;
    }

    /**
     * Accesseur en écriture de l'attribut id .
     *
     * @param id id à setter.
     */
    @Override
    public final void setId(final String id) {
        this.id = id;
    }

    /**
     * Accesseur en lecture de l'attribut codeElement.
     *
     * @return codeElement .
     */
    @Override
    public final String getCodeElement() {
        return codeElement;
    }

    /**
     * Accesseur en écriture de l'attribut codeElement .
     *
     * @param codeElement codeElement à setter.
     */
    @Override
    public final void setCodeElement(final String codeElement) {
        this.codeElement = codeElement;
    }

    /**
     * Accesseur en lecture de l'attribut libelleCourt.
     *
     * @return libelleCourt .
     */
    @Override
    public final String getLibelleCourt() {
        return libelleCourt;
    }

    /**
     * Accesseur en écriture de l'attribut libelleCourt .
     *
     * @param libelleCourt libelleCourt à setter.
     */
    @Override
    public final void setLibelleCourt(final String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    /**
     * Accesseur en lecture de l'attribut libelleLong.
     *
     * @return libelleLong .
     */
    @Override
    public final String getLibelleLong() {
        return libelleLong;
    }

    /**
     * Accesseur en écriture de l'attribut libelleLong .
     *
     * @param libelleLong libelleLong à setter.
     */
    @Override
    public final void setLibelleLong(final String libelleLong) {
        this.libelleLong = libelleLong;
    }

    /**
     * Accesseur en lecture de l'attribut libelleImpression.
     *
     * @return libelleImpression .
     */
    @Override
    public final String getLibelleImpression() {
        return libelleImpression;
    }

    /**
     * Accesseur en écriture de l'attribut libelleImpression .
     *
     * @param libelleImpression libelleImpression à setter.
     */
    @Override
    public final void setLibelleImpression(final String libelleImpression) {
        this.libelleImpression = libelleImpression;
    }

    /**
     * Accesseur en lecture de l'attribut dateEffet.
     *
     * @return dateEffet .
     */
    @Override
    public final Date getDateEffet() {
        return dateEffet;
    }

    /**
     * Accesseur en écriture de l'attribut dateEffet .
     *
     * @param dateEffet dateEffet à setter.
     */
    @Override
    public final void setDateEffet(final Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Accesseur en lecture de l'attribut dateFin.
     *
     * @return dateFin .
     */
    @Override
    public final Date getDateFin() {
        return dateFin;
    }

    /**
     * Accesseur en écriture de l'attribut dateFin .
     *
     * @param dateFin dateFin à setter.
     */
    @Override
    public final void setDateFin(final Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Accesseur en lecture de l'attribut attr.
     *
     * @return attr .
     */
    public final Map<String, String> getAttr() {
        return attr;
    }

    /**
     * Accesseur en écriture de l'attribut attr .
     *
     * @param attr attr à setter.
     */
    public final void setAttr(final Map<String, String> attr) {
        this.attr = attr;
    }

    /**
     * Accesseur en lecture de la valeur de attrib.
     *
     * @param attribut attribut à getter.
     * @return valeur.
     */
    public final String getAttribut(final String attribut) {
        return attr.get(attribut);
    }

    /**
     * Accesseur en écriture de l'attribut attr .
     *
     * @param attribut attribut à setter.
     * @param valeur   valeur de l'attribut
     */
    public final void setAttribut(final String attribut, final String valeur) {
        attr.put(attribut, valeur);
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
        if (objet instanceof Element) {
            Element element = (Element) objet;
            if (element != null) {
                return equalsElement(element);
            }
        }
        return false;
    }

    /**
     * Compare l'élément à <code>this</code>.
     *
     * @param element l'objet à comparer (no nullable)
     * @return <code>true</code> si les objets sont égaux, <code>false</code> sinon
     */
    private boolean equalsElement(final Element element) {
        if (element.getId() == null || !element.getId().equals(this.getId())) {
            return false;
        }
        if (element.getCodeElement() == null || !element.getCodeElement().equals(this.getCodeElement())) {
            return false;
        }
        boolean dateEgale = equalsDate(this.getDateEffet(), element.getDateEffet());
        if (!dateEgale) {
            return false;
        }
        dateEgale = equalsDate(this.getDateFin(), element.getDateFin());
        if (!dateEgale) {
            return false;
        }

        return true;
    }

    /**
     * Compare les 2 dates.
     *
     * @param date          la date de comparaison
     * @param dateAComparer la date à comparer
     * @return <code>true</code> si les dates sont égales, <code>false</code> sinon
     */
    private boolean equalsDate(final Date date, final Date dateAComparer) {
        if ((dateAComparer == null && date != null) || (dateAComparer != null && date == null)) {
            return false;
        } else if (!dateAComparer.equals(date)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        // On choisit 2 nombres impairs.
        // Les nombres 7 et 17 ont été choisit de manière totalement arbitraire.
        // Il est juste préférable de ne pas utiliser les mêmes pour toutes les classes.
        int result = SEPT;
        final int multiplier = DIX_SEPT;

        // Pour chaque attribut, on calcule le hashcode
        // que l'on ajoute au résultat après l'avoir multiplié par le nombre "multiplieur" :
        result = multiplier * result + (this.getId() != null ? this.getId().hashCode() : 0);
        result = multiplier * result + (this.getCodeElement() != null ? this.getCodeElement().hashCode() : 0);
        result = multiplier * result + (this.getLibelleCourt() != null ? this.getLibelleCourt().hashCode() : 0);
        result = multiplier * result + (this.getLibelleLong() != null ? this.getLibelleLong().hashCode() : 0);
        result = multiplier * result + (this.getLibelleImpression() != null ? this.getLibelleImpression().hashCode() : 0);
        result = multiplier * result + (this.getDateEffet() != null ? this.getDateEffet().hashCode() : 0);
        result = multiplier * result + (this.getDateFin() != null ? this.getDateFin().hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(codeElement);
        sb.append("|");
        sb.append(libelleCourt);
        return sb.toString();
    }
}
