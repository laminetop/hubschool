package sn.projet.nomenclature.domaine;

import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.Date;
import java.util.List;

/**
 * interface pour ElementLienAttributExterneDomaine
 */
public interface IElementLienAttributExterneDomaine {

    /**
     * vérifie si l'<code>Element</code> appartient à la Nomenclature pour une date donnée.
     *
     * @param nomenclature le code de la nomenclature
     * @param codeElement  le code élément
     * @param dateEffet    la date d'effet pour la vérification
     * @return true si l'appartient .
     */
    boolean verifierAppartenance(Nomenclature nomenclature, String codeElement, Date dateEffet);

    /**
     * vérifie s'il existe un lien d'inferiorité entre deux éléments d'une nomenclature donnée.
     *
     * @param element1  code du premier élément
     * @param element2  code du deuxième élément
     * @param dateEffet date effet de la vérification
     * @return true si il existe un lien inf entre elements.
     */
    boolean verifierExistanceLienInfEntreElements(Element element1, Element element2, Date dateEffet);

    /**
     * vérifie s'il existe un lien de superiorité entre deux éléments d'une nomenclature donnée.
     *
     * @param element1  code du premier élément
     * @param element2  code du deuxième élément
     * @param dateEffet date effet de la vérification
     * @return true si il existe un lien sup entre elements.
     */
    boolean verifierExistanceLienSupEntreElements(Element element1, Element element2, Date dateEffet);

    /**
     * vérifie s'il existe un lien d'infériorité entre deux éléments de nomenclatures différents
     * pour une date donnée.
     *
     * @param nomenclature nomenclature de l'élément2
     * @param element1     element1.
     * @param element2     element2.
     * @param dateEffet    date d'effet.
     * @return true si il existe un lien inf entre elements différents.
     */
    boolean verifierExistanceLienInfEntreDifferentsElements(Nomenclature nomenclature, Element element1, Element element2,
                                                            Date dateEffet);

    /**
     * vérifie s'il existe un lien de superiorité entre deux éléments de nomenclatures différents
     * pour une date donnée.
     *
     * @param nomenclature nomenclature de l'élément2
     * @param element1     element1.
     * @param element2     element2.
     * @param dateEffet    date d'effet.
     * @return true si il existe un lien sup entre elements différents.
     */
    boolean verifierExistanceLienSupEntreDifferentsElements(Nomenclature nomenclature, Element element1, Element element2,
                                                            Date dateEffet);

    /**
     * renvoie tous les éléments appartenant à une nomenclature pour une date donnée.
     *
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date effet de la recherche
     * @return la liste des elements.
     */
    List<Element> rechercherElements(Nomenclature nomenclature, Date dateEffet);

    /**
     * renvoie la liste des liens existants pour l'élément de la nomenclature pour une date donnée.
     *
     * @param nomenclature code de l'élément
     * @param element      code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @return la liste des elements.
     */
    List<ElementAttributs> rechercherLiensSup(Nomenclature nomenclature, Element element, Date dateEffet);

    /**
     * renvoie la liste des codes des liens existants pour l'élément de la nomenclature pour une
     * date donnée.
     *
     * @param codeNomenclature code de la nomenclature
     * @param codeElement      code de l'élément
     * @param dateEffet        date de recherche
     * @return la liste des codes des éléments.
     */
    List<String> rechercherCodeLiensSup(String codeNomenclature, String codeElement, Date dateEffet);

    /**
     * renvoie la liste des liens existants pour l'élément de la nomenclature pour une date donnée.
     *
     * @param element      code de l'élément
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @return liste des elements.
     */
    List<ElementAttributs> rechercherLiensInf(Nomenclature nomenclature, Element element, Date dateEffet);

    /**
     * renvoie la liste des codes des liens existants pour l'élément de la nomenclature pour une
     * date donnée.
     *
     * @param codeNomenclature code de la nomenclature
     * @param codeElement      code de l'élément
     * @param dateEffet        date de recherche
     * @return liste des codes des éléments.
     */
    List<String> rechercherCodeLiensInf(String codeNomenclature, String codeElement, Date dateEffet);

    /**
     * renvoie le <code>Element</code> à travers son code.
     *
     * @param codeElement  code de l'élément
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @return element.
     */
    Element rechercherElementParCode(Nomenclature nomenclature, String codeElement, Date dateEffet);

    /**
     * renvoie le <code>Element</code> à travers son libelle court.
     *
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @param libelleCourt le libellé court
     * @return la liste des elements.
     */
    List<Element> rechercherElementParLibelleCourt(Nomenclature nomenclature, String libelleCourt, Date dateEffet);

    /**
     * renvoie le <code>Element</code> à travers son libelle long.
     *
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @param libelleLong  le libellé long
     * @return la liste des elements.
     */
    List<Element> rechercherElementParLibelleLong(Nomenclature nomenclature, String libelleLong, Date dateEffet);

    /**
     * renvoie le <code>Element</code> à travers son libelle impression.
     *
     * @param nomenclature      code de la nomenclature en question
     * @param dateEffet         date de recherche
     * @param libelleImpression le libellé impression
     * @return liste des elements.
     */
    List<Element> rechercherElementParLibelleImpression(Nomenclature nomenclature, String libelleImpression, Date dateEffet)
    ;

    /**
     * renvoie le <code>Element</code> à travers un attribut externe.
     *
     * @param nomenclature    code de la nomenclature en question
     * @param date            date de recherche
     * @param valeurAttribut  la valeur l'attribut externe de recherche
     * @param libelleAttribut l'attribut externe de recherche
     * @return liste des elements.
     */
    List<Element> rechercherElementParAttributExterne(Nomenclature nomenclature, String libelleAttribut,
                                                      String valeurAttribut, Date date);

    /**
     * renvoie tous les <code>Element</code> de la nomenclature en paramètre qui ont un lien avec
     * l'élement en paramètre.
     *
     * @param element      l'élément
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @return la liste d'elements.
     */
    List<ElementAttributs> rechercherLiensInfLex(Nomenclature nomenclature, Element element, Date dateEffet);

    /**
     * renvoie tous les <code>Element</code> de la nomenclature en paramètre qui ont un lien avec
     * l'élement en paramètre.
     *
     * @param codeNomenclature  code de la nomenclature en question
     * @param codeNomenclature1 le code de la nomenclature du premier élément
     * @param codeElement       le code de l'élément
     * @param dateEffet         date de recherche
     * @return La liste des codes des éléments.
     */
    List<String> rechercherCodeLiensInfLex(String codeNomenclature, String codeNomenclature1, String codeElement,
                                           Date dateEffet);

    /**
     * renvoie tous les <code>Element</code> appartenant à la nomenclature en lien avec l'élément en
     * paramètre pour une date donnée.
     *
     * @param element      l'élément
     * @param nomenclature code de la nomenclature en question
     * @param dateEffet    date de recherche
     * @return la liste d'elements.
     */
    List<ElementAttributs> rechercherLiensSupLex(Nomenclature nomenclature, Element element, Date dateEffet);

    /**
     * renvoie tous les <code>Element</code> appartenant à la nomenclature en lien avec l'élément en
     * paramètre pour une date donnée.
     *
     * @param codeNomenclature  code de la nomenclature en question
     * @param codeNomenclature1 le code de la nomenclature du premier élément
     * @param codeElement       le code de l'élément
     * @param dateEffet         date de recherche
     * @return la liste d'elements.
     */
    List<String> rechercherCodeLiensSupLex(String codeNomenclature, String codeNomenclature1, String codeElement,
                                           Date dateEffet);

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
     * Retourne les éléments de la nomenclature <code>nomenclature</code> qui correspondent à la
     * période commençant à la date d'effet <code>dateEffet</code> et terminant à la date
     * <code>dateFin</code>
     *
     * @param codeNomenclature le code de la nomenclature des éléments
     * @param dateEffet        la date délimitant le début de période
     * @param dateFin          la date fermant la période *
     */
    List<Element> rechercherElementsParPeriode(Nomenclature nomenclature, Date dateEffet, Date dateFin)
    ;

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
}
