package sn.projet.referentiel.facade;

import sn.projet.exception.FonctionnelleException;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * Définition des services de consultation des nomenclatures.
 */
public interface IConsultationNomenclatureService {

    /**
     * Retourne les nomenclatures.
     *
     * @return les nomenclatures
     */
    @Cacheable(value = "transport")
    List<Nomenclature> getNomenclatures();

    /**
     * Retourne l'ensemble des éléments de la nomenclature <code>codeNomenclature</code> ou les
     * éléments qui correspondent au critère <code>typeCritere</code> et la valeur de ce critère
     * <code>valeurCritere</code>.<br/> <br/> Exemples : <br/> - http://localhost/myapp/referentiel/ACA
     * : recherche toutes les academies valides à la date du jour.<br/> -
     * http://localhost/myapp/referentiel/ACA?date=1900-01-01 : recherche toutes les academies
     * valides au 1er Janvier 1900.<br/> - http://localhost/myapp/referentiel/ACA?libelleCourt=toto
     * : recherche les academies dont le libellé court contient la chaine "toto" et valides à la
     * date du jour.<br/> - http://localhost/myapp/referentiel/ACA?libelleCourt=toto&date=1900-01-01
     * : recherche les academies dont le libellé court contient la chaine "toto" et valides au 1er
     * Janvier 1900.<br/> <br/>
     *
     * @param codeNomenclature le code de la nomenclature
     * @param dateQuery        la date d'observation (optionnelle)
     * @param typeCritere      le type de critère de recherche (optionnelle)
     * @param valeurCritere    la valeur du critère de recherche (optionnelle)
     * @return les éléments de la nomenclature <code>codeNomenclature</code>
     */
    @Cacheable(value = "transport")
    List<Element> getElementsParNomenclature(final String codeNomenclature,
                                             final String dateQuery,
                                             final String typeCritere,
                                             final String valeurCritere) throws FonctionnelleException;

    /**
     * Retourne les éléments de la nomenclature <code>nomenclature</code> qui ont un lien avec les
     * éléments de la nomenclature <code>typeLien</code> et valides à la date
     * <code>dateQuery</code>. Pour rechercher les liens entre éléments d'une même nomenclature,
     * <code>typeLien</code> est égal à <code>inf</code> ou <code>sup</code>.<br/> <br/> Exemples :
     * <br/> - http://localhost/myapp/referentiel/ACA/001,002/DPT : recherche les départements des
     * académies 001 et 002 valides à la date du jour.<br/> - http://localhost/myapp/referentiel/ACA/001,002/DPT?date=2000-01-01
     * : recherche les départements des académies 001 et 002 valides au 1er Janvier 2000.<br/> -
     * http://localhost/myapp/referentiel/ACA/001,002/inf : recherche les académies inférieures aux
     * académies 001 et 002 et valides à la date du jour.<br/> - http://localhost/myapp/referentiel/ACA/001,002/inf?date=2000-01-01
     * : recherche les académies inférieures aux académies 001 et 002 et valides au 1er Janvier
     * 2000.<br/> <br/>
     *
     * @param codeNomenclature le code de la nomenclature
     * @param codeElements     la liste des codes éléments (séparés par des virgules)
     * @param typeLien         le code de la nomenclature (ou le type de lien)
     * @param dateQuery        la date d'observation (optionnelle)
     * @return les éléments de la nomenclature <code>nomenclature</code> qui ont un lien avec les
     * éléments de la nomenclature <code>typeLien</code>
     */
    @Cacheable(value = "transport")
    List<ElementAttributs> getElementsParLiens(final String codeNomenclature,
                                               final String codeElements,
                                               final String typeLien,
                                               final String dateQuery) throws FonctionnelleException;

    /**
     * Recherche des codes des liens entre la nomenclature <code>nomenclature</code> et la
     * nomenclature <code>typeLien</code>.
     *
     * @param codeNomenclature le code de la nomenclature
     * @param codeElements     la liste des codes éléments (séparés par des virgules)
     * @param typeLien         le code de la nomenclature (ou le type de lien)
     * @param dateQuery        la date d'observation (optionnelle)
     * @return les codes des liens
     */
    @Cacheable(value = "transport")
    Map<String, List<ElementAttributs>> getCodeElementsParLiens(final String codeNomenclature,
                                                                final String codeElements, final String typeLien,
                                                                final String dateQuery);

    /**
     * Vérifie si l'élément <code>element1</code> est lié à l'élément <code>element2</code> par le
     * lien <code>typeLien</code>.
     *
     * @param codeNomenclature le code de la nomenclature des éléments
     * @param element1         le premier élément
     * @param typeLien         le type de lien
     * @param element2         le deuxième élément
     * @param dateQuery        la date d'observation (optionnelle)
     * @return 200 si les éléments ont un lien, erreur sinon
     */
    boolean verifierLien(final String codeNomenclature,
                         final String element1,
                         final String typeLien,
                         final String element2,
                         final String dateQuery) throws FonctionnelleException;

    /**
     * Retourne l'élément de la nomenclature <code>nomenclature</code> qui a le code
     * <code>codeElement</code>.
     *
     * @param codeNomenclature le code de la nomenclature auquel l'élément appartient
     * @param codeElements     les codes des éléments à rechercher (séparés par des virgules)
     * @param dateQuery        la date d'observation de l'élément (optionnelle)
     * @return l'élément
     */
    List<Element> getElementsParCode(final String codeNomenclature,
                                     final String codeElements,
                                     final String dateQuery) throws FonctionnelleException;

    /**
     * Retourne le chemin entre la nomenclature <code>premierCodeNomenclature</code> et la
     * nomenclature <code>dernierCodeNomenclature</code>.<br/> <br/> Exemples :<br/>
     *
     * <br/>
     *
     * @param premierCodeNomenclature le code nomenclature de départ
     * @param dernierCodeNomenclature le code nomenclature d'arrivée
     * @return le chemin entre la nomenclature <code>premierCodeNomenclature</code> et la
     * nomenclature <code>dernierCodeNomenclature</code>
     */
    String getCheminEntreLiens(final String premierCodeNomenclature,
                               final String dernierCodeNomenclature);

    /**
     * Retourne les éléments de la nomenclature <code>nomenclature</code> qui ont un lien avec les
     * éléments de la nomenclature <code>typeLien</code> et valides à la date
     * <code>dateQuery</code>. Pour rechercher les liens entre éléments d'une même nomenclature,
     * <code>typeLien</code> est égal à <code>inf</code> ou <code>sup</code>.<br/>
     *
     * @param codeNomenclatureUn   le code de la nomenclature
     * @param codeElementsUn       la liste des codes éléments (séparés par des virgules)
     * @param codeNomenclatureDeux le code de la nomenclature (ou le type de lien)
     * @param dateValiditeLien     la date d'observation (optionnelle) * @throws
     *                             FonctionnelleException exception fonctionnelle.
     * @return les éléments de la nomenclature <code>nomenclature</code> qui ont un lien avec les
     * éléments de la nomenclature <code>typeLien</code>
     */
    String rechercherElementsParLiensParDate(final String codeNomenclatureUn,
                                             final String codeElementsUn,
                                             final String codeNomenclatureDeux,
                                             final String dateValiditeLien);

    /**
     * Retourne les éléments de la nomenclature <code>nomenclature</code> qui correspondent à la
     * période commençant à la date d'effet <code>dateEffet</code> et terminant à la date
     * <code>dateFin</code>
     *
     * @param codeNomenclature le code de la nomenclature des éléments
     * @param dateEffet        la date délimitant le début de période
     * @param dateFin          la date fermant la période. Peut être null.
     *
     *                         </br>Exemples : </br> http://localhost:8080/myapp/referentiel/periode/ASC/2009-09-01/2012-08-31
     *                         </br> http://localhost:8080/myapp/referentiel/periode/ASC/2009-09-01/null
     */
    List<Element> getElementsParPeriode(final String codeNomenclature,
                                        final String dateEffet,
                                        final String dateFin) throws FonctionnelleException;

}
