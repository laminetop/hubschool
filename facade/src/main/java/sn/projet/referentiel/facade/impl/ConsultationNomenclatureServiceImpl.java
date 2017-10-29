package sn.projet.referentiel.facade.impl;

import sn.projet.exception.FonctionnelleException;
import sn.projet.nomenclature.service.INomenclatureService;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;
import sn.projet.referentiel.facade.IConsultationNomenclatureService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static sn.projet.utils.DateUtils.getCalendarISO;
import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Implémentation des services du MAN Nomenclature.
 */
@Service("nomenclatureREST")
public class ConsultationNomenclatureServiceImpl implements IConsultationNomenclatureService {

    /**
     * Le loggueur.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultationNomenclatureServiceImpl.class);

    /**
     * Message pour afficher le path de la requête REST.
     */
    private static final String MSG_PATH_REQUEST = "Path de la requête [{}]";

    /**
     * La couche service des nomenclatures.
     */
    @Autowired
    private INomenclatureService nomenclatureService;

    @Value("${referentiel.config.tag}")
    private String tagId;


    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Nomenclature> getNomenclatures() {
        LOGGER.debug("Recherche de toutes les nomenclatures");
        return nomenclatureService.rechercherNomenclatures();

    }

    /**
     * {@inheritDoc}
     *
     * Lors de la recherche de tous les éléments d'une nomenclature, un point de synchronisation et
     * une mise en cache du flux Atom sont réalisés. Le but est d'éviter de regénérer les mêmes flux
     * Atom plusieurs fois.<br/> De plus, un contrôle du nombre de threads simultanés est fait pour
     * éviter que trop de calculs de flux Atom de nomenclatures différentes soient effectués en même
     * temps.
     */
    @Override
    public final List<Element> getElementsParNomenclature(final String codeNomenclature, final String dateQuery,
                                                          final String typeCritere, final String valeurCritere) throws FonctionnelleException {
        LOGGER.debug("Recherche ElementsParNomenclature");

        if (StringUtils.isEmpty(typeCritere) || StringUtils.isEmpty(valeurCritere)) {
            // Rechercher tous les éléments d'une nomenclature
            return rechercherElementsParNomenclature(codeNomenclature, dateQuery);
        } else {
            // Rechercher les éléments par attributs
            return rechercherElementsParAttribut(codeNomenclature, typeCritere, valeurCritere, dateQuery);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> getElementsParLiens(final String codeNomenclature, final String codeElements, final String typeLien,
                                                            final String dateQuery) throws FonctionnelleException {

        String[] codeElementsTries = codeElements.split(",");
        Arrays.sort(codeElementsTries);

        LOGGER.debug("Recherche getElementsParLiens");

        List<ElementAttributs> elements = null;

        if (isEmpty(dateQuery)) {
            elements = nomenclatureService.rechercherLiensCourantsParNomenclature(codeNomenclature, codeElementsTries,
                    typeLien);

        } else {
            elements = nomenclatureService.rechercherLiensParNomenclature(codeNomenclature, codeElementsTries, typeLien,
                    getCalendarISO(dateQuery));
        }
        return Optional.ofNullable(elements).orElse(new ArrayList<ElementAttributs>());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, List<ElementAttributs>> getCodeElementsParLiens(final String codeNomenclature, final String codeElements,
                                                                             final String typeLien, final String dateQuery) {

        LOGGER.debug("Recherche getCodeElementsParLiens");


        Map<String, List<ElementAttributs>> map = null;

        if (isEmpty(dateQuery)) {
            map = nomenclatureService.rechercherCodeLiensCourantsParNomenclature(codeNomenclature, codeElements.split(","),
                    typeLien);

        } else {
            map = nomenclatureService.rechercherCodeLiensParNomenclature(codeNomenclature, codeElements.split(","), typeLien,
                    getCalendarISO(dateQuery));
        }
        return Optional.ofNullable(map).orElse(new HashMap<String, List<ElementAttributs>>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierLien(final String codeNomenclature, final String element1, final String typeLien,
                                      final String element2, final String dateQuery) throws FonctionnelleException {
        boolean ret = false;

        LOGGER.debug("Recherche verifierLien");

        if (isEmpty(dateQuery)) {
            ret = nomenclatureService.verifierExistanceLienCourant(codeNomenclature, element1, typeLien, element2);
        } else {
            ret = nomenclatureService.verifierExistanceLien(codeNomenclature, element1, typeLien, element2,
                    getCalendarISO(dateQuery));
        }
        return ret;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> getElementsParCode(final String codeNomenclature, final String codeElements, final String dateQuery) throws FonctionnelleException {

        LOGGER.debug("Recherche getElementsParCode");

        String[] codeElementsTries = codeElements.split(",");
        Arrays.sort(codeElementsTries);
        List<Element> liste = null;

        if (codeElementsTries.length == 1) {
            liste = new ArrayList<Element>(codeElementsTries.length);
            Element element = null;
            if (isEmpty(dateQuery)) {
                element = nomenclatureService.rechercherElementCourant(codeNomenclature, codeElements);
            } else {
                element = nomenclatureService.rechercherElement(codeNomenclature, codeElements, getCalendarISO(dateQuery));
            }
            liste.add(element);
        } else {
            if (isEmpty(dateQuery)) {
                liste = nomenclatureService.rechercherElementsCourant(codeNomenclature, codeElementsTries);
            } else {
                liste = nomenclatureService
                        .rechercherElements(codeNomenclature, codeElementsTries, getCalendarISO(dateQuery));
            }
        }
        return liste;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getCheminEntreLiens(final String premierCodeNomenclature, final String dernierCodeNomenclature) {
        LOGGER.debug("Recherche getCheminEntreLiens");
        return nomenclatureService.rechercherCheminEntreNomenclatures(premierCodeNomenclature,
                dernierCodeNomenclature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String rechercherElementsParLiensParDate(final String codeNomenclatureUn, final String codeElementsUn,
                                                          final String codeNomenclatureDeux, final String dateValiditeLien) {
        List<String> codeElementsUnList = new ArrayList<String>(Arrays.asList(codeElementsUn.split(",")));

        return nomenclatureService.rechercherElementsParLiensParDate(codeNomenclatureUn, codeElementsUnList, codeNomenclatureDeux,
                dateValiditeLien);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> getElementsParPeriode(final String codeNomenclature, final String dateEffet, final String dateFin) throws FonctionnelleException {

        LOGGER.debug("Recherche getElementsParPeriode");
        List<Element> listes = null;
        // Rechercher les éléments par attributs
        try {
            listes = nomenclatureService.rechercherElementsSurPeriode(codeNomenclature, getCalendarISO(dateEffet),
                    getCalendarISO(dateFin));
        } catch (IllegalArgumentException e) {
            listes = nomenclatureService.rechercherElementsSurPeriode(codeNomenclature, getCalendarISO(dateEffet), null);
        }
        return listes;
    }


    /**
     * Recherche les éléments par attribut.
     *
     * @param codeNomenclature le code de la nomenclature des éléments recherchés
     * @param codeAttribut     le critère de recherche
     * @param valeurAttribut   la chaine à rechercher
     * @param date             la date d'observation (nullable)
     * @return l'ensemble des éléments de la nomenclature <code>codeNomenclature</code>
     * @throws FonctionnelleException s'il n'y a pas d'éléments
     */
    private List<Element> rechercherElementsParAttribut(final String codeNomenclature, final String codeAttribut,
                                                        final String valeurAttribut, final String date) throws FonctionnelleException {
        if (isEmpty(date)) {
            return nomenclatureService.rechercherCourantParAttribut(codeNomenclature, codeAttribut, valeurAttribut);
        } else {
            return nomenclatureService
                    .rechercherParAttribut(codeNomenclature, codeAttribut, valeurAttribut, getCalendarISO(date));
        }
    }


    /**
     * Rechercher l'ensemble des éléments de la nomenclature <code>codeNomenclature</code>.
     *
     * @param codeNomenclature le code de la nomenclature des éléments recherchés
     * @param date             la date d'observation (nullable)
     * @return l'ensemble des éléments de la nomenclature <code>codeNomenclature</code>
     * @throws FonctionnelleException s'il n'y a pas d'éléments
     */
    private List<Element> rechercherElementsParNomenclature(final String codeNomenclature, final String date) throws FonctionnelleException {
        if (isEmpty(date)) {
            return nomenclatureService.rechercherElementsCourant(codeNomenclature);
        } else {
            return nomenclatureService.rechercherElements(codeNomenclature, getCalendarISO(date));
        }
    }

}
