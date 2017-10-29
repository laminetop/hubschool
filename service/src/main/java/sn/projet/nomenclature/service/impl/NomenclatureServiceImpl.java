package sn.projet.nomenclature.service.impl;

import sn.projet.exception.FonctionnelleException;
import sn.projet.exception.IExceptionFactory;
import sn.projet.nomenclature.domaine.IElementLienAttributExterneDomaine;
import sn.projet.nomenclature.domaine.INomenclatureDomaine;
import sn.projet.nomenclature.service.INomenclatureService;
import sn.projet.nomenclature.service.utils.Dijkstra;
import sn.projet.nomenclature.service.utils.GrapheImpl;
import sn.projet.nomenclature.transverse.Constantes;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;
import sn.projet.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Nomenclature Service.
 */
@Transactional(readOnly = true)
@Service("nomenclatureService")
public class NomenclatureServiceImpl implements INomenclatureService {

    /**
     * Message de logs pour afficher le chemin entre 2 nomenclatures.
     */
    private static final String MSG_CHEMIN_NOMENCLATURE = "Chemin entre les nomenclatures [{}, {}] = [{}]";
    private static final Logger log = LoggerFactory.getLogger(NomenclatureServiceImpl.class);
    @Autowired
    protected IExceptionFactory factory;
    /**
     * La couche domaine Element, Lien et Attribut Externe.
     */
    @Autowired
    private IElementLienAttributExterneDomaine elementLienAttributExterneDomaine;
    /**
     * La couche domaine Nomenclature.
     */
    @Autowired
    private INomenclatureDomaine nomenclatureDomaine;

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierExistanceLien(final String codeNomenclature, final String codeElement1,
                                               final String libelleNatureLien, final String codeElement2, final Date dateEffet) throws FonctionnelleException {

        boolean existeLien = false;

        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);

        Element element1 = elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature, codeElement1, dateEffet);

        log.debug("Element1 : " + element1.getLibelleCourt());

        if (libelleNatureLien.equals(Constantes.ATTR_RECHERCHE_INF)) {
            log.debug(Constantes.ATTR_RECHERCHE_INF);

            Element element2 = elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature, codeElement2, dateEffet);

            if (log.isDebugEnabled()) {
                log.debug("libelleElement1 : " + element1.getLibelleCourt());
                log.debug("libelleElement2 : " + element2.getLibelleCourt());
            }

            existeLien = elementLienAttributExterneDomaine.verifierExistanceLienInfEntreElements(element1, element2, dateEffet);
        } else if (libelleNatureLien.equals(Constantes.ATTR_RECHERCHE_SUP)) {
            log.debug(Constantes.ATTR_RECHERCHE_SUP);
            Element element2 = elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature, codeElement2, dateEffet);
            if (log.isDebugEnabled()) {

                log.debug("libelleElement1 : " + element1.getLibelleCourt());
                log.debug("libelleElement2 : " + element2.getLibelleCourt());
            }
            existeLien = elementLienAttributExterneDomaine.verifierExistanceLienSupEntreElements(element2, element1, dateEffet);
        } else {
            // int result = nomenclature.getLibelle().compareTo(libelleNatureLien);
            Nomenclature temp = rechercherNomenclatureParLienNomenclature1(nomenclature.getCode(), libelleNatureLien);

            Nomenclature nomenclature2 = nomenclatureDomaine.rechercherNomenclatureParLibelle(libelleNatureLien);
            Element element2 = elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature2, codeElement2, dateEffet);
            if (log.isDebugEnabled()) {
                log.debug("nomenclature2 est : " + nomenclature2.getLibelle());
                log.debug("element2 est : " + element2.getLibelleCourt());
            }

            if (temp != null) {
                if (log.isDebugEnabled()) {
                    log.debug(nomenclature.getLibelle() + " est supérieur à " + nomenclature2.getLibelle());
                }
                existeLien = elementLienAttributExterneDomaine.verifierExistanceLienSupEntreDifferentsElements(nomenclature2,
                        element1, element2, dateEffet);
            } else {
                temp = rechercherNomenclatureParLienNomenclature2(nomenclature.getCode(), libelleNatureLien);
                if (temp != null) {
                    if (log.isDebugEnabled()) {
                        log.debug(nomenclature.getLibelle() + " est inférieur à " + nomenclature2.getLibelle());
                    }
                    existeLien = elementLienAttributExterneDomaine.verifierExistanceLienInfEntreDifferentsElements(nomenclature2,
                            element1, element2, dateEffet);
                }
            }
        }

        return existeLien;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierExistanceLienCourant(final String codeNomenclature, final String codeElement1,
                                                      final String libelleNatureLien, final String codeElement2) throws FonctionnelleException {

        return verifierExistanceLien(codeNomenclature, codeElement1, libelleNatureLien, codeElement2, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElement(final String codeNomenclature, final String codeElement, final Date dateEffet)
            throws FonctionnelleException {

        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);

        elementLienAttributExterneDomaine.verifierAppartenance(nomenclature, codeElement, dateEffet);

        if (log.isDebugEnabled()) {
            log.debug("verifierAppartenance est passé");
        }

        Element element = elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature, codeElement, dateEffet);

        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementCourant(final String codeNomenclature, final String codeElement) throws FonctionnelleException {

        return rechercherElement(codeNomenclature, codeElement, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElements(final String codeNomenclature, final Date dateEffet) throws FonctionnelleException {

        if (log.isDebugEnabled()) {
            log.debug("Recherche Elements pour la nomenclature " + codeNomenclature);
        }

        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);

        return elementLienAttributExterneDomaine.rechercherElements(nomenclature, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementsCourant(final String codeNomenclature) throws FonctionnelleException {

        return rechercherElements(codeNomenclature, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementsCourant(final String codeNomenclature, final String[] codeElements)
            throws FonctionnelleException {

        return rechercherElements(codeNomenclature, codeElements, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElements(final String codeNomenclature, final String[] codeElements,
                                                  final Date dateRecherche) throws FonctionnelleException {

        if (log.isDebugEnabled()) {
            log.debug("Recherche Elements pour la nomenclature " + codeNomenclature);
        }

        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);

        List<Element> listes = new ArrayList<Element>();

        for (String code : codeElements) {
            try {
                listes.add(elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature, code, dateRecherche));
            } catch (Exception fe) {
                log.error(fe.getMessage());
            }
        }

        return listes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> rechercherLiensParNomenclature(final String codeNomenclature, final String[] elements,
                                                                       final String typeLien, final Date dateEffet) throws FonctionnelleException {

        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);
        // Utilisation d'un Set pour ne pas avoir de doublons
        Set<ElementAttributs> listElements = new HashSet<ElementAttributs>();
        List<ElementAttributs> list = null;
        Element element = null;
        Nomenclature temp = null;
        Nomenclature nomenclature2 = null;

        for (int i = 0; i < elements.length; i++) {
            log.debug("Recherche les liens pour l'élément [{}]", elements[i]);

            element = elementLienAttributExterneDomaine.rechercherElementParCode(nomenclature, elements[i], dateEffet);
            log.debug("Recherche les liens pour l'élément [{}], libelleCourt [{}] ",
                    new String[]{elements[i], element.getLibelleCourt()});

            if (typeLien.equals(Constantes.ATTR_RECHERCHE_SUP)) {
                log.debug("Le type de lien est Supérieur");

                list = elementLienAttributExterneDomaine.rechercherLiensSup(nomenclature, element, dateEffet);

            } else if (typeLien.equals(Constantes.ATTR_RECHERCHE_INF)) {
                log.debug("Le type de lien est Inférieur");

                list = elementLienAttributExterneDomaine.rechercherLiensInf(nomenclature, element, dateEffet);
            } else {
                log.debug("Le type de lien n'est ni Supérieur, ni Inférieur."
                        + " On compare par rapport au rangement dans la table LIEN_NOMENCLATURE");

                temp = rechercherNomenclatureParLienNomenclature1(nomenclature.getCode(), typeLien);

                if (temp != null) {
                    nomenclature2 = nomenclatureDomaine.rechercherNomenclature(typeLien);
                    log.debug("On recherche les éléments de la nomenclature [{}] dans la colonne de droite "
                                    + "et qui sont liés à la nomenclature [{}]",
                            new String[]{nomenclature.getCode(), nomenclature2.getCode()});
                    // On recherche les éléments dans la colonne de droite
                    list = elementLienAttributExterneDomaine.rechercherLiensSupLex(nomenclature2, element, dateEffet);

                } else {
                    temp = rechercherNomenclatureParLienNomenclature2(nomenclature.getCode(), typeLien);
                    if (temp != null) {
                        nomenclature2 = nomenclatureDomaine.rechercherNomenclature(typeLien);
                        log.debug("La nomenclature [{}] est à droite de la nomenclature [{}]",
                                new String[]{nomenclature.getCode(), nomenclature2.getCode()});
                        // On recherche les éléments dans la colonne de gauche
                        list = elementLienAttributExterneDomaine.rechercherLiensInfLex(nomenclature2, element, dateEffet);
                    } else {
                        log.error("{} [{}] {}", new String[]{"ren.nomenclature.man.008",
                                "Il n'existe pas de lien entre les nomenclatures [" + codeNomenclature + "] et [" + typeLien + "] "});
                    }
                }
            }

            if (list != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Les éléments suivants seront ajoutés à la liste des liens");
                    for (Iterator<ElementAttributs> iter = list.iterator(); iter.hasNext(); ) {
                        log.debug("Elément=" + iter.next().getElement().getCodeElement());
                    }
                }
                listElements.addAll(list);
            }
        }

        return new ArrayList<ElementAttributs>(listElements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> rechercherLiensCourantsParNomenclature(final String codeNomenclature,
                                                                               final String[] elements, final String typeLien) throws FonctionnelleException {
        return rechercherLiensParNomenclature(codeNomenclature, elements, typeLien, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, List<ElementAttributs>> rechercherCodeLiensParNomenclature(final String codeNomenclature,
                                                                                        final String[] codeElements, final String typeLien, final Date dateEffet) {

        Map<String, List<ElementAttributs>> map = null;
        String[] newCodeElements = new String[1];
        List<ElementAttributs> newElements = null;

        if (codeElements != null && typeLien != null) {
            for (int i = 0; i < codeElements.length; i++) {
                newCodeElements[0] = codeElements[i];

                try {
                    newElements = rechercherLiensParNomenclature(codeNomenclature, newCodeElements, typeLien, dateEffet);

                    if (newElements != null) {
                        if (map == null) {
                            map = new HashMap<String, List<ElementAttributs>>(codeElements.length);
                        }
                        if (log.isDebugEnabled()) {
                            log.debug("Les éléments suivants seront ajoutés à la liste des liens");
                            for (ElementAttributs element : newElements) {
                                log.debug("Elément={}", element.getElement().getCodeElement());
                            }
                        }

                        map.put(codeNomenclature + "/" + codeElements[i] + "/" + typeLien, newElements);
                    }
                } catch (Exception fe) {

                    if (map == null) {
                        map = new HashMap<String, List<ElementAttributs>>(codeElements.length);
                    }
                    map.put(codeNomenclature + "/" + codeElements[i] + "/" + typeLien, new ArrayList<ElementAttributs>(0));
                }
            }
        }
        return map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, List<ElementAttributs>> rechercherCodeLiensCourantsParNomenclature(
            final String codeNomenclature, final String[] codeElements, final String typeLien) {
        return rechercherCodeLiensParNomenclature(codeNomenclature, codeElements, typeLien, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Nomenclature> rechercherNomenclatures() {
        List<Nomenclature> rets = null;
        rets = nomenclatureDomaine.rechercherNomenclatures();

        return rets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherParAttribut(final String codeNomenclature, final String attribut,
                                                     final String valeurAttribut, final Date dateRecherche) throws FonctionnelleException {

        List<Element> elements = new ArrayList<Element>(0);
        if (log.isDebugEnabled()) {
            log.debug("Recherche par Attribut " + codeNomenclature + "-" + attribut + "-" + valeurAttribut);
        }
        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);
        if (attribut.equals(Constantes.ATTR_LIBELLE_COURT)) {
            elements = elementLienAttributExterneDomaine.rechercherElementParLibelleCourt(nomenclature, valeurAttribut,
                    dateRecherche);
        } else if (attribut.equals(Constantes.ATTR_LIBELLE_LONG)) {
            elements = elementLienAttributExterneDomaine.rechercherElementParLibelleLong(nomenclature, valeurAttribut,
                    dateRecherche);
        } else if (attribut.equals(Constantes.ATTR_LIBELLE_IMPRESSION)) {
            elements = elementLienAttributExterneDomaine.rechercherElementParLibelleImpression(nomenclature, valeurAttribut,
                    dateRecherche);
        } else if (nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().contains(attribut)) {
            elements = elementLienAttributExterneDomaine.rechercherElementParAttributExterne(nomenclature, attribut,
                    valeurAttribut, dateRecherche);
        } else {
            factory.throwFonctionnalException(Constantes.ERR_BAD_REQUEST, new String[]{attribut, codeNomenclature});
        }

        return elements;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherCourantParAttribut(final String codeNomenclature, final String codeAttribut,
                                                            final String valeurAttribut) throws FonctionnelleException {
        return rechercherParAttribut(codeNomenclature, codeAttribut, valeurAttribut, DateUtils.getDateCourante().getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherPremierElement(final String codeNomenclature) {
        return elementLienAttributExterneDomaine.rechercherPremierElement(codeNomenclature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherDernierElement(final String codeNomenclature) {
        return elementLienAttributExterneDomaine.rechercherDernierElement(codeNomenclature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementPrecedent(final String codeNomenclature, final String codeElement) {
        return elementLienAttributExterneDomaine.rechercherElementPrecedent(codeNomenclature, codeElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementSuivant(final String codeNomenclature, final String codeElement) {
        return elementLienAttributExterneDomaine.rechercherElementSuivant(codeNomenclature, codeElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String rechercherCheminEntreNomenclatures(final String premierCodeNomenclature,
                                                           final String dernierCodeNomenclature) {
        if (premierCodeNomenclature == null || dernierCodeNomenclature == null) {
            log.error("{} [{}] {}", new String[]{

                    "ren.nomenclature.man.007",
                    "Impossible de rechercher le chemin entre les nomenclatures [" + premierCodeNomenclature + "] et [" + "{"
                            + dernierCodeNomenclature + "}] car une des nomenclatures est null"});
            return null;
        }
        // Récupération de toutes les nomenclatures
        List<Nomenclature> nomenclatures = rechercherNomenclaturesAvecLiens();
        if (nomenclatures != null) {
            List<Object> sommets = Dijkstra.rechercherLePlusCourtChemin(new GrapheImpl(nomenclatures),
                    premierCodeNomenclature, dernierCodeNomenclature);
            log.debug(MSG_CHEMIN_NOMENCLATURE, new Object[]{premierCodeNomenclature, dernierCodeNomenclature, sommets});
            if (sommets != null) {
                return StringUtils.join(sommets, "/");
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Nomenclature> rechercherNomenclaturesAvecLiens() {
        return nomenclatureDomaine.rechercherNomenclaturesAvecLiens();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Nomenclature rechercherNomenclatureParLienNomenclature1(final String codeNomenclature,
                                                                         final String codeNomenclature1) {
        return nomenclatureDomaine.rechercherNomenclatureParLienNomenclature1(codeNomenclature, codeNomenclature1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Nomenclature rechercherNomenclatureParLienNomenclature2(final String codeNomenclature,
                                                                         final String codeNomenclature2) {
        return nomenclatureDomaine.rechercherNomenclatureParLienNomenclature2(codeNomenclature, codeNomenclature2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String rechercherElementsParLiensParDate(final String codeNomenclatureUn, final List<String> codeElementsUn,
                                                          final String codeNomenclatureDeux, final String dateValiditeLien) {

        return elementLienAttributExterneDomaine.rechercherElementsParLiensParDate(codeNomenclatureUn, codeElementsUn,
                codeNomenclatureDeux, dateValiditeLien);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementsSurPeriode(final String codeNomenclature, final Date dateEffet,
                                                            final Date dateFin) throws FonctionnelleException {

        if (log.isDebugEnabled()) {
            log.debug("Recherche Elements par période pour la nomenclature " + codeNomenclature);
        }

        if (dateFin != null) {
            if (dateEffet.compareTo(dateFin) > 0) {
                factory.throwFonctionnalException(Constantes.ERR_DATE, codeNomenclature);
            }
        }

        Nomenclature nomenclature = nomenclatureDomaine.rechercherNomenclature(codeNomenclature);
        if (codeNomenclature == null) {
            factory.throwFonctionnalException(Constantes.ERR_AUCUNE_NOMENCLATURE, codeNomenclature);
        }

        return elementLienAttributExterneDomaine.rechercherElementsParPeriode(nomenclature, dateEffet, dateFin);

    }
}
