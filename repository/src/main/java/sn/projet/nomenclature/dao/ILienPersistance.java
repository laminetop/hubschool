package sn.projet.nomenclature.dao;

import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.Date;
import java.util.List;

/**
 * Interface pour LienDAO.
 */
public interface ILienPersistance {

    /**
     * vérifie s'il existe un lien d'inferiorité entre deux éléments de la même nomenclature pour
     * une date donnée.
     *
     * @param element1  code du premier élément
     * @param element2  code du deuxième élément
     * @param dateEffet date Effet de la vérification
     * @return true si lien existe
     */
    boolean verifierExistanceLienInf(Element element1, Element element2, Date dateEffet);

    /**
     * vérifie s'il existe un lien de superiorité entre deux éléments de la même nomenclature pour
     * une date donnée.
     *
     * @param element1  code du premier élément
     * @param element2  code du deuxième élément
     * @param dateEffet date Effet de la vérification
     * @return true si lien existe
     */
    boolean verifierExistanceLienSup(Element element1, Element element2, Date dateEffet);

    /**
     * vérifie s'il existe un lien d'inferiorité entre deux éléments de nomenclatures différents
     * pour une date donnée .
     *
     * @param nomenclature nomenclature du deuxième élément
     * @param element1     élément1
     * @param element2     élément2
     * @param dateEffet    date de verification
     * @return true si lien existe
     */

    boolean verifierExistanceLienInfNomenclatureDifferentes(Nomenclature nomenclature, Element element1, Element element2,
                                                            Date dateEffet);

    /**
     * vérifie s'il existe un lien de superiorité entre deux éléments de nomenclatures différents
     * pour une date donnée .
     *
     * @param nomenclature nomenclature du deuxième élément
     * @param element1     élément1
     * @param element2     élément2
     * @param dateEffet    date de verification
     * @return true si lien existe
     */
    boolean verifierExistanceLienSupNomenclatureDifferentes(Nomenclature nomenclature, Element element1, Element element2,
                                                            Date dateEffet);

    /**
     * récupère tous les liens existants d'un élément pour une date donnée.
     *
     * @param element   code de l'élément
     * @param dateEffet date Effet de la vérification
     * @return la liste des elements.
     */
    List<ElementAttributs> rechercherLiensSup(Element element, Date dateEffet);

    /**
     * récupère tous les codes des liens existants d'un élément pour une date donnée.
     *
     * @param codeNomenclature Le code de la nomenclature
     * @param codeElement      code de l'élément
     * @param dateEffet        date d'effet de la vérification
     * @return la liste des codes des éléments
     */
    List<String> rechercherCodeLiensSup(String codeNomenclature, String codeElement, Date dateEffet);

    /**
     * récupère tous les liens existants d'un élément pour une date donnée.
     *
     * @param element   code de l'élément
     * @param dateEffet date Effet de la vérification
     * @return liste des elements.
     */
    List<ElementAttributs> rechercherLiensInf(Element element, Date dateEffet);

    /**
     * récupère tous les codes des liens existants d'un élément pour une date donnée.
     *
     * @param codeNomenclature Le code de la nomenclature
     * @param codeElement      code de l'élément
     * @param dateEffet        date d'effet de la vérification
     * @return liste des codes des éléments
     */
    List<String> rechercherCodeLiensInf(String codeNomenclature, String codeElement, Date dateEffet);

    /**
     * récupère tous les liens de l'élément appartenant à la nomenclature pour une date donnée .
     *
     * @param nomenclature nomenclature du deuxième élément
     * @param element      élément1
     * @param dateEffet    date de verification
     * @return la liste des elements.
     */
    List<ElementAttributs> rechercherLiensInfLex(Nomenclature nomenclature, Element element, Date dateEffet);

    /**
     * récupère tous les codes des liens de l'élément appartenant à la nomenclature pour une date
     * donnée .
     *
     * @param codeNomenclature  Le code de la nomenclature du deuxième élément
     * @param codeNomenclature1 Le code de la nomenclature du premier élément
     * @param codeElement       Le code de l'élément1
     * @param dateEffet         date de verification
     * @return la liste des codes des éléments.
     */
    List<String> rechercherCodeLiensInfLex(String codeNomenclature, String codeNomenclature1, String codeElement,
                                           Date dateEffet);

    /**
     * récupère tous les liens de l'élément appartenant à la nomenclature pour une date donnée .
     *
     * @param nomenclature nomenclature du deuxième élément
     * @param element      élément1
     * @param dateEffet    date de verification
     * @return la liste des elements.
     */
    List<ElementAttributs> rechercherLiensSupLex(Nomenclature nomenclature, Element element, Date dateEffet);

    /**
     * récupère tous les liens de l'élément appartenant à la nomenclature pour une date donnée .
     *
     * @param codeNomenclature  Le code de la nomenclature du deuxième élément
     * @param codeNomenclature1 Le code de la nomenclature du premier élément
     * @param codeElement       Le code de l'élément1
     * @param dateEffet         date de verification
     * @return la liste des elements.
     */
    List<String> rechercherCodeLiensSupLex(String codeNomenclature, String codeNomenclature1, String codeElement,
                                           Date dateEffet);
}
