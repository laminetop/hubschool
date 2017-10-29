package sn.projet.nomenclature.transverse.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;

/**
 * Lien VO.
 */
@ApiModel(description = "Lien entre deux Elements")
@JsonTypeName("Lien")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lien implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7435691379130976102L;

    /**
     * Identifiant technique.
     */
    private Long idLien;

    /**
     * Date d'effet du lien.
     */
    private Date dateEffet;

    /**
     * Date de fin du lien.
     */
    private Date dateFin;

    /**
     * 1er élément du lien.
     */
    private Element element1;

    /**
     * 2eme élément du lien.
     */
    private Element element2;

    /**
     * Constructeur.
     */
    public Lien() {
    }

    /**
     * Accesseur en lecture de l'attribut idLien.
     *
     * @return idLien .
     */
    public final Long getIdLien() {
        return idLien;
    }

    /**
     * Accesseur en écriture de l'attribut idLien .
     *
     * @param idLien idLien à setter.
     */
    public final void setIdLien(final Long idLien) {
        this.idLien = idLien;
    }

    /**
     * Accesseur en lecture de l'attribut dateEffet.
     *
     * @return dateEffet .
     */
    public final Date getDateEffet() {
        return dateEffet;
    }

    /**
     * Accesseur en écriture de l'attribut dateEffet .
     *
     * @param dateEffet dateEffet à setter.
     */
    public final void setDateEffet(final Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Accesseur en lecture de l'attribut dateFin.
     *
     * @return dateFin .
     */
    public final Date getDateFin() {
        return dateFin;
    }

    /**
     * Accesseur en écriture de l'attribut dateFin .
     *
     * @param dateFin dateFin à setter.
     */
    public final void setDateFin(final Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Accesseur en lecture de l'attribut element1.
     *
     * @return element1 .
     */
    public final Element getElement1() {
        return element1;
    }

    /**
     * Accesseur en écriture de l'attribut element1 .
     *
     * @param element1 element1 à setter.
     */
    public final void setElement1(final Element element1) {
        this.element1 = element1;
    }

    /**
     * Accesseur en lecture de l'attribut element2.
     *
     * @return element2 .
     */
    public final Element getElement2() {
        return element2;
    }

    /**
     * Accesseur en écriture de l'attribut element2 .
     *
     * @param element2 element2 à setter.
     */
    public final void setElement2(final Element element2) {
        this.element2 = element2;
    }
}
