package sn.projet.nomenclature.service;

import sn.projet.exception.FonctionnelleException;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface pour le service des nomenclatures andado.
 */
public interface INomenclatureService {

    /**
     * vérifie s'il existe un lien entre deux éléments d'une <code>Nomenclature</code> donnée pour
     * une date.
     *
     * @param codeNomenclature  code de la nomenclature en entrée
     * @param codeElement1      code du premier élément
     * @param libelleNatureLien libellé de la nature du lien
     * @param codeElement2      code du deuxième élément
     * @param dateEffet         date pour laquelle on veut vérifier
     * @return true si le lien existe.
     * @throws FonctionnelleException Erreur 404 : la nomenclature ou l'un des deux éléments
     *                                n'existe pas
     */
    @Cacheable(value = "service_date")
    boolean verifierExistanceLien(String codeNomenclature, String codeElement1, String libelleNatureLien, String codeElement2,
                                  Date dateEffet) throws FonctionnelleException;

    /**
     * vérifie s'il existe un lien entre deux éléments d'une <code>Nomenclature</code> donnée .
     *
     * @param codeNomenclature  code de la nomenclature en entrée
     * @param codeElement1      code du premier élément
     * @param libelleNatureLien libellé de la nature du lien
     * @param codeElement2      code du deuxième élément
     * @return true si le lien existe
     * @throws FonctionnelleException Erreur 404 : la nomenclature ou l'un des deux éléments
     *                                n'existe pas
     */
    @Cacheable(value = "service")
    boolean verifierExistanceLienCourant(String codeNomenclature, String codeElement1, String libelleNatureLien,
                                         String codeElement2) throws FonctionnelleException;

    /**
     * récupérer tous les détails (Libellé court, Libellé Long, Libellé Impression, Date effet, Date
     * fin) d'un élément qui appartient à une nomenclature pour une date donnée.
     *
     * @param codeNomenclature le code de la <code>Nomenclature</code>
     * @param codeElement      le code de l'élément dont on veut récupérer les détails
     * @param dateEffet        la date pour laquelle on veut récupérer les détails de l'élément
     * @return element.
     * @throws FonctionnelleException Exception fonctionnelle 404 : si la <code>Nomenclature</code>
     *                                ou l'élément n'existe pas
     */
    @Cacheable(value = "service_date")
    Element rechercherElement(String codeNomenclature, String codeElement, Date dateEffet) throws FonctionnelleException;

    /**
     * récupérer tous les détails (Libellé court, Libellé Long, Libellé Impression, Date effet, Date
     * fin) d'un élément qui appartient à une <code>Nomenclature</code>.
     *
     * @param codeNomenclature le code de la <code>Nomenclature</code>
     * @param codeElement      le code de l'élément dont on veut récupérer les détails
     * @return element.
     * @throws Exception Exception fonctionnelle 404 : si la <code>Nomenclature</code> ou l'élément
     *                   n'existe pas
     */
    @Cacheable(value = "service")
    Element rechercherElementCourant(String codeNomenclature, String codeElement) throws FonctionnelleException;

    /**
     * récupérer la liste des éléments qui appartiennent à une <code>Nomenclature</code> pour une
     * date donnée .
     *
     * @param codeNomenclature code de la <code>Nomenclature</code>
     * @param dateEffet        date Effet de la vérification
     * @return la liste des elements
     * @throws FonctionnelleException Exception fonctionnelle 404 : si la nomenclature n'existe pas
     */
    @Cacheable(value = "service_date")
    List<Element> rechercherElements(String codeNomenclature, Date dateEffet) throws FonctionnelleException;

    /**
     * récupérer la liste des éléments qui appartiennent à une <code>Nomenclature</code> .
     *
     * @param codeNomenclature code de la <code>Nomenclature</code>
     * @return la liste des elements
     * @throws FonctionnelleException Exception fonctionnelle 404 : si la nomenclature n'existe pas
     */
    @Cacheable(value = "service")
    List<Element> rechercherElementsCourant(String codeNomenclature) throws FonctionnelleException;

    /**
     * récupérer la liste des éléments qui appartiennent à une <code>Nomenclature</code> .
     *
     * @param codeNomenclature code de la <code>Nomenclature</code>
     * @param codeElements     code des Elements à retournés
     * @return la liste des elements.
     * @throws FonctionnelleException Exception fonctionnelle 404 : si la nomenclature n'existe pas
     */
    @Cacheable(value = "service")
    List<Element> rechercherElementsCourant(String codeNomenclature, String[] codeElements) throws FonctionnelleException;

    /**
     * récupère les liens d'une liste d'éléments d'une <code>Nomenclature</code> pour une date
     * donnée .
     *
     * @param codeNomenclature code de la <code>Nomenclature</code> en question
     * @param elements         liste des éléments
     * @param typeLien         le type de lien
     * @param dateEffet        date pour laquelle on veut vérifier
     * @return la liste des elements
     * @throws Exception Exception fonctionnelle : 404 si la nomenclature ou l'un des éléments
     *                   n'existe pas
     */
    @Cacheable(value = "service_date")
    List<ElementAttributs> rechercherLiensParNomenclature(String codeNomenclature, String[] elements, String typeLien,
                                                          Date dateEffet) throws FonctionnelleException;

    /**
     * récupère les liens d'une liste d'éléments d'une <code>Nomenclature</code> .
     *
     * @param codeNomenclature code de la <code>Nomenclature</code> en question
     * @param elements         liste des éléments
     * @param typeLien         le type de lien
     * @return la liste des elements
     * @throws FonctionnelleException FonctionnelleException fonctionnelle : 404 si la nomenclature
     *                                ou l'un des éléments n'existe pas
     */
    @Cacheable(value = "service")
    List<ElementAttributs> rechercherLiensCourantsParNomenclature(String codeNomenclature, String[] elements, String typeLien)
            throws FonctionnelleException;

    /**
     * Recherche les éléments de la nomenclature <code>typeLien</code> qui sont liés aux éléments
     * <code>codeElements</code> de la nomenclature <code>codeNomenclature</code>.
     *
     * @param codeNomenclature code de la <code>Nomenclature</code> en question
     * @param codeElements     liste des éléments
     * @param typeLien         le type de lien ("sup", "inf") ou un code nomenclature
     * @param dateEffet        la date d'observation
     * @return la liste des éléments par code éléments
     */
    @Cacheable(value = "service_date")
    Map<String, List<ElementAttributs>> rechercherCodeLiensParNomenclature(final String codeNomenclature,
                                                                           final String[] codeElements, final String typeLien, final Date dateEffet);

    /**
     * Recherche les éléments de la nomenclature <code>typeLien</code> qui sont liés aux éléments
     * <code>codeElements</code> de la nomenclature <code>codeNomenclature</code>.
     *
     * @param codeNomenclature code de la <code>Nomenclature</code> en question
     * @param codeElements     liste des éléments
     * @param typeLien         le type de lien ("sup", "inf") ou un code nomenclature
     * @return la liste des éléments par code éléments
     */
    @Cacheable(value = "service")
    Map<String, List<ElementAttributs>> rechercherCodeLiensCourantsParNomenclature(final String codeNomenclature,
                                                                                   final String[] codeElements, final String typeLien);

    /**
     * récupère la liste des <code>Nomenclature</code> existantes.
     *
     * @return la liste des nomenclatures
     * @throws FonctionnelleException FonctionnelleException fonctionnelle : 404 si la nomenclature
     *                                ou l'un des éléments n'existe pas
     */
    @Cacheable(value = "service")
    List<Nomenclature> rechercherNomenclatures();

    /**
     * rechercher un élément par un atrribut interne (code, libelle court, libelle Long, libelle
     * Impression) ou externe (attribut Externe).
     *
     * @param codeNomenclature code la nomenclature
     * @param codeAttribut     code de l'attribut
     * @param valeurAttribut   valeur de l'attribut
     * @return la liste des elements.
     * @throws FonctionnelleException FonctionnelleException fonctionnelle : 404 si la nomenclature
     *                                ou l'un des éléments n'existe pas
     */
    @Cacheable(value = "service")
    List<Element> rechercherCourantParAttribut(String codeNomenclature, String codeAttribut, String valeurAttribut)
            throws FonctionnelleException;

    /**
     * rechercher un élément par un atrribut interne (code, libelle court, libelle Long, libelle
     * Impression) ou externe (attribut Externe).
     *
     * @param codeNomenclature code la nomenclature
     * @param codeAttribut     code de l'attribut
     * @param valeurAttribut   valeur de l'attribut
     * @param dateRecherche    date a quelle on recherche
     * @return la liste des elements.
     * @throws FonctionnelleException FonctionnelleException fonctionnelle : 404 si la nomenclature
     *                                ou l'un des éléments n'existe pas
     */
    @Cacheable(value = "service_date")
    List<Element> rechercherParAttribut(String codeNomenclature, String codeAttribut, String valeurAttribut,
                                        Date dateRecherche) throws FonctionnelleException;

    /**
     * rechercher un élément par un atrribut interne (code, libelle court, libelle Long, libelle
     * Impression) ou externe (attribut Externe).
     *
     * @param codeNomenclature code la nomenclature
     * @param codeElements     les codes des elements
     * @param dateRecherche    date a quelle on recherche
     * @return la liste des elements.
     * @throws FonctionnelleException FonctionnelleException fonctionnelle : 404 si la nomenclature
     *                                ou l'un des éléments n'existe pas
     */
    List<Element> rechercherElements(String codeNomenclature, String[] codeElements, Date dateRecherche) throws FonctionnelleException;

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
     * Recherche le chemin entre la nomenclature <code>premierCodeNomenclature</code> et la
     * nomenclature <code>dernierCodeNomenclature</code>.
     *
     * @param premierCodeNomenclature - Le code de la nomenclature de départ
     * @param dernierCodeNomenclature - Le code de la nomenclature d'arrivée
     * @return Le chemin entre la nomenclature <code>premierCodeNomenclature</code> et la
     * nomenclature <code>dernierCodeNomenclature</code>.
     */
    String rechercherCheminEntreNomenclatures(String premierCodeNomenclature, String dernierCodeNomenclature);

    /**
     * Recherche toutes les nomenclatures avec les nomenclatures qui lui sont liées.
     *
     * @return l'ensemble des nomenclatures avec les nomenclatures qui lui sont liées
     */
    List<Nomenclature> rechercherNomenclaturesAvecLiens();

    /**
     * Recherche la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature1</code>.
     *
     * @param codeNomenclature  la nomenclature à rechercher
     * @param codeNomenclature1 le nomenclature reliée
     * @return la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature1</code>, null sinon
     */
    Nomenclature rechercherNomenclatureParLienNomenclature1(final String codeNomenclature, final String codeNomenclature1);

    /**
     * Recherche la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature2</code>.
     *
     * @param codeNomenclature  la nomenclature à rechercher
     * @param codeNomenclature2 le nomenclature reliée
     * @return la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature2</code>, null sinon
     */
    Nomenclature rechercherNomenclatureParLienNomenclature2(final String codeNomenclature, final String codeNomenclature2);

    /**
     * Retourne les éléments de la nomenclature <code>nomenclature</code> qui correspondent à la
     * période commençant à la date d'effet <code>dateEffet</code> et terminant à la date
     * <code>dateFin</code>
     *
     * @param codeNomenclature le code de la nomenclature des éléments
     * @param dateEffet        la date délimitant le début de période
     * @param dateFin          la date fermant la période
     * @throws FonctionnelleException *
     */
    List<Element> rechercherElementsSurPeriode(String codeNomenclature, Date dateEffet, Date dateFin) throws FonctionnelleException;

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
