package sn.projet.nomenclature.transverse.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;

/**
 * L'interface qui définit les attributs que possèdent tout élément de la nomenclature.
 */
@ApiModel(description = "L'interface qui définit les attributs que possèdent tout élément de la nomenclature")
public interface IElement extends Serializable {

    /**
     * Format des dates des éléments.
     */
    String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * Date de fin.
     */
    String DATE_FIN = "dateFin";

    /**
     * Date d'effet.
     */
    String DATE_EFFET = "dateEffet";

    /**
     * Retourne l'id global de l'élément <b>Utile que pour la gestion interne du framework.</b>
     *
     * @return L'id global de l'élément
     */
    String getId();

    /**
     * Sette l'id global de l'élément.
     *
     * @param id - L'id global de l'élément
     */
    void setId(String id);

    /**
     * Retourne le code l'élément.
     *
     * @return Le code l'élément
     */
    String getCodeElement();

    /**
     * Sette le code l'élément.
     *
     * @param codeElement - Le code l'élément
     */
    void setCodeElement(String codeElement);

    /**
     * Retourne le libellé court de l'élément.
     *
     * @return Le libellé court de l'élément
     */
    String getLibelleCourt();

    /**
     * Sette le libellé court de l'élément.
     *
     * @param libelleCourt - Le libellé court de l'élément
     */
    void setLibelleCourt(String libelleCourt);

    /**
     * Retourne le libellé long de l'élément.
     *
     * @return Le libellé long de l'élément
     */
    String getLibelleLong();

    /**
     * Sette le libellé long de l'élément.
     *
     * @param libelleLong - Le libellé long de l'élément
     */
    void setLibelleLong(String libelleLong);

    /**
     * Retourne le libellé d'impression de l'élément.
     *
     * @return Le libellé d'impression de l'élément
     */
    String getLibelleImpression();

    /**
     * Sette le libellé d'impression de l'élément.
     *
     * @param libelleImpression - Le libellé d'impression de l'élément
     */
    void setLibelleImpression(String libelleImpression);

    /**
     * Retourne la date d'effet de l'élément.
     *
     * @return La date d'effet de l'élément
     */
    Date getDateEffet();

    /**
     * Sette la date d'effet de l'élément.
     *
     * @param dateEffet - La date d'effet de l'élément
     */
    void setDateEffet(Date dateEffet);

    /**
     * Retourne la date fin de l'élément.
     *
     * @return La date fin de l'élément
     */
    Date getDateFin();

    /**
     * Sette la date fin de l'élément.
     *
     * @param dateFin - La date fin de l'élément
     */
    void setDateFin(Date dateFin);
}
