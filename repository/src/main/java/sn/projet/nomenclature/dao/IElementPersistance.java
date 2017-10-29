package sn.projet.nomenclature.dao;

import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.Date;
import java.util.List;

/**
 * Interface pour ElementDAO.
 */
public interface IElementPersistance {

    /**
     * vérifie si un <code>Element</code> appartient à une nomenclature pour une date donnée.
     *
     * @param nomenclature code de la Nomenclature
     * @param dateEffet    date Effet de la vérification
     * @param codeElement  tableau des éléments à vérifier
     * @return true si l'element appartient.
     */
    boolean verifierAppartenance(Nomenclature nomenclature, String codeElement, Date dateEffet);

    /**
     * retourne tous les éléments d'une nomenclature à une date donnée.
     *
     * @param nomenclature nomenclature à vérifier
     * @param dateEffet    date d'effet de la vérification
     * @return liste des elements.
     */
    List<Element> rechercherElements(Nomenclature nomenclature, Date dateEffet);

    /**
     * renvoie l<code>Element</code> à travers son code.
     *
     * @param codeElement  code d'element
     * @param nomenclature la noemnclature.
     * @param dateEffet    date d'effet de la vérification
     * @return liste des elements.
     */
    Element rechercherParCode(Nomenclature nomenclature, String codeElement, Date dateEffet);

    /**
     * renvoie l'<code>Element</code> à travers son libelle court.
     *
     * @param libelleCourt libelle court d'element
     * @param nomenclature la nomenclature
     * @param dateEffet    date d'effet de la vérification
     * @return liste d'element
     */
    List<Element> rechercherParLibelleCourt(Nomenclature nomenclature, String libelleCourt, Date dateEffet);

    /**
     * renvoie l'<code>Element</code> à travers son libelle long.
     *
     * @param libelleLong  libelle long d'element
     * @param nomenclature la nomenclature
     * @param dateEffet    date d'effet de la vérification
     * @return liste d'element
     */
    List<Element> rechercherParLibelleLong(Nomenclature nomenclature, String libelleLong, Date dateEffet);

    /**
     * renvoie l'<code>Element</code> à travers son libelle d'impression.
     *
     * @param libelleImpression libelle Impression d'element
     * @param nomenclature      la nomenclature
     * @param dateEffet         date d'effet de la vérification
     * @return liste d'element
     */
    List<Element> rechercherParLibelleImpression(Nomenclature nomenclature, String libelleImpression, Date dateEffet);

    /**
     * renvoie l'<code>Element</code> à travers son attributExterne.
     *
     * @param nomenclature    la nomenclature
     * @param libelleAttribut - Le champ dans lequel rechercher
     * @param valeurAttribut  - La chaine à rechercher dans l'attribut
     * @param date            date d'effet de la vérification
     * @return liste d'element
     */
    List<Element> rechercherParAttributExterne(Nomenclature nomenclature, String libelleAttribut, String valeurAttribut,
                                               Date date);

    /**
     * Recherche le premier élément de la nomenclature <code>codeNomenclature</code>.<br/> La
     * nomenclature doit être ordonnée.
     *
     * @param codeNomenclature - Le code de la nomenclature
     * @return Le premier élément de la nomenclature <code>codeNomenclature</code>, null si la
     * nomenclature n'est pas ordonnée
     */
    Element rechercherPremierElement(String codeNomenclature);

    /**
     * Recherche le dernier élément de la nomenclature <code>codeNomenclature</code>.<br/> La
     * nomenclature doit être ordonnée.
     *
     * @param codeNomenclature - Le code de la nomenclature
     * @return Le dernier élément de la nomenclature <code>codeNomenclature</code>, null si la
     * nomenclature n'est pas ordonnée
     */
    Element rechercherDernierElement(String codeNomenclature);

    /**
     * Recherche l'élément qui précède l'élément <code>codeElement</code>.<br/> La nomenclature doit
     * être ordonnée.
     *
     * @param codeNomenclature - Le code de la nomenclature
     * @param codeElement      - Le code de l'élément qui suit l'élément recherché
     * @return L'élément qui précède l'élément donné, null si l'élément donné est le premier élément
     * ou si la nomenclature n'est pas ordonnée
     */
    Element rechercherElementPrecedent(String codeNomenclature, String codeElement);

    /**
     * Recherche l'élément qui suit l'élément <code>codeElement</code>.<br/> La nomenclature doit
     * être ordonnée.
     *
     * @param codeNomenclature - Le code de la nomenclature
     * @param codeElement      - Le code de l'élément qui précède l'élément recherché
     * @return L'élément qui suit l'élément donné, null si l'élément donné est le dernier élément ou
     * si la nomenclature n'est pas ordonnée
     */
    Element rechercherElementSuivant(String codeNomenclature, String codeElement);

    /**
     * Retourne les éléments de la nomenclature <code>codeNomenclatureDeux</code> qui ont un lien
     * avec les éléments de la nomenclature <code>codeNomenclatureUN</code> et valides à la date
     * <code>dateValiditeLien</code>.
     *
     * @param codeNomenclatureUn   le code de la nomenclature
     * @param codeElementsUn       la liste des codes éléments (séparés par des virgules)
     * @param codeNomenclatureDeux le code de la nomenclature (ou le type de lien)
     * @param dateValiditeLien     la date d'observation (optionnelle)
     * @return les éléments de la nomenclature <code>codeNomenclatureUn</code> qui ont un lien avec
     * les éléments de la nomenclature <code>nomenclature</code>
     */
    String rechercherElementsParLiensParDate(String codeNomenclatureUn, List<String> codeElementsUn,
                                             String codeNomenclatureDeux, String dateValiditeLien);

    /**
     * Retourne les éléments de la nomenclature <code>nomenclature</code> qui correspondent à la
     * période commençant à la date d'effet <code>dateEffet</code> et terminant à la date
     * <code>dateFin</code>
     *
     * @param nomenclature le code de la nomenclature des éléments
     * @param dateEffet    la date délimitant le début de période
     * @param dateFin      la date fermant la période *
     */
    List<Element> rechercherParPeriode(Nomenclature nomenclature, Date dateEffet, Date dateFin);
}
