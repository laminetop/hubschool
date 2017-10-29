package sn.projet.nomenclature.domaine.impl;

import sn.projet.nomenclature.dao.IElementPersistance;
import sn.projet.nomenclature.dao.ILienPersistance;
import sn.projet.nomenclature.domaine.IElementLienAttributExterneDomaine;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;
import sn.projet.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Classe domaine.
 */
@Component("elementLienAttributExterneDomaine")
public class ElementLienAttributExterneDomaineImpl implements IElementLienAttributExterneDomaine {

    private static final Logger log = LoggerFactory.getLogger(ElementLienAttributExterneDomaineImpl.class);
    /**
     * elementPersistance.
     */
    @Autowired
    private IElementPersistance elementPersistance;
    /**
     * lienPersistance.
     */
    @Autowired
    private ILienPersistance lienPersistance;

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierAppartenance(final Nomenclature nomenclature, final String codeElement, final Date dateEffet) {
        return elementPersistance.verifierAppartenance(nomenclature, codeElement, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierExistanceLienInfEntreElements(final Element element1, final Element element2,
                                                               final Date dateEffet) {
        return lienPersistance.verifierExistanceLienInf(element1, element2, dateEffet);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierExistanceLienInfEntreDifferentsElements(final Nomenclature nomenclature, final Element element1,
                                                                         final Element element2, final Date dateEffet) {

        if (log.isDebugEnabled()) {
            log.debug("nomenclature : " + nomenclature.getLibelle());
            log.debug("element1 : " + element1.getLibelleCourt());
            log.debug("element2 : " + element2.getLibelleCourt());
        }
        return lienPersistance.verifierExistanceLienInfNomenclatureDifferentes(nomenclature, element1,
                element2, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierExistanceLienSupEntreDifferentsElements(final Nomenclature nomenclature, final Element element1,
                                                                         final Element element2, final Date dateEffet) {

        if (log.isDebugEnabled()) {
            log.debug("nomenclature : " + nomenclature.getLibelle());
            log.debug("element1 : " + element1.getLibelleCourt());
            log.debug("element2 : " + element2.getLibelleCourt());
        }
        return lienPersistance.verifierExistanceLienSupNomenclatureDifferentes(nomenclature, element1,
                element2, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierExistanceLienSupEntreElements(final Element element1, final Element element2,
                                                               final Date dateEffet) {

        return lienPersistance.verifierExistanceLienSup(element1, element2, dateEffet);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElements(final Nomenclature nomenclature, final Date dateEffet) {

        final List<Element> listElements = elementPersistance.rechercherElements(nomenclature, dateEffet);

        return listElements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> rechercherLiensSup(final Nomenclature nomenclature, final Element element,
                                                           final Date dateEffet) {
        return lienPersistance.rechercherLiensSup(element, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> rechercherCodeLiensSup(final String codeNomenclature, final String codeElement,
                                                     final Date dateEffet) {
        return lienPersistance.rechercherCodeLiensSup(codeNomenclature, codeElement, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> rechercherLiensInf(final Nomenclature nomenclature, final Element element,
                                                           final Date dateEffet) {
        return lienPersistance.rechercherLiensInf(element, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> rechercherCodeLiensInf(final String codeNomenclature, final String codeElement,
                                                     final Date dateEffet) {
        return lienPersistance.rechercherCodeLiensInf(codeNomenclature, codeElement, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementParCode(final Nomenclature nomenclature, final String codeElement,
                                                  final Date dateEffet) {
        log.debug("Recherche l'élément [{}, {}] de la nomenclature [{}]",
                new String[]{codeElement, DateUtils.getAAAAMMJJ(dateEffet), nomenclature.getCode()});

        return elementPersistance.rechercherParCode(nomenclature, codeElement, dateEffet);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementParLibelleCourt(final Nomenclature nomenclature, final String libelleCourt,
                                                                final Date dateEffet) {

        return elementPersistance.rechercherParLibelleCourt(nomenclature, libelleCourt, dateEffet);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementParLibelleLong(final Nomenclature nomenclature, final String libelleLong,
                                                               final Date dateEffet) {
        return elementPersistance.rechercherParLibelleLong(nomenclature, libelleLong, dateEffet);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementParLibelleImpression(final Nomenclature nomenclature,
                                                                     final String libelleImpression, final Date dateEffet) {

        return elementPersistance.rechercherParLibelleImpression(nomenclature, libelleImpression,
                dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElementParAttributExterne(final Nomenclature nomenclature,
                                                                   final String libelleAttribut, final String valeurAttribut, final Date date) {

        return elementPersistance.rechercherParAttributExterne(nomenclature, libelleAttribut,
                valeurAttribut, date);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> rechercherLiensInfLex(final Nomenclature nomenclature, final Element element,
                                                              final Date dateEffet) {
        return lienPersistance.rechercherLiensInfLex(nomenclature, element, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> rechercherCodeLiensInfLex(final String codeNomenclature, final String codeNomenclature1,
                                                        final String codeElement, final Date dateEffet) {
        return lienPersistance.rechercherCodeLiensInfLex(codeNomenclature, codeNomenclature1, codeElement, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ElementAttributs> rechercherLiensSupLex(final Nomenclature nomenclature, final Element element,
                                                              final Date dateEffet) {
        return lienPersistance.rechercherLiensSupLex(nomenclature, element, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> rechercherCodeLiensSupLex(final String codeNomenclature, final String codeNomenclature1,
                                                        final String codeElement, final Date dateEffet) {
        return lienPersistance.rechercherCodeLiensSupLex(codeNomenclature, codeNomenclature1, codeElement, dateEffet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherPremierElement(final String codeNomenclature) {
        return elementPersistance.rechercherPremierElement(codeNomenclature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherDernierElement(final String codeNomenclature) {
        return elementPersistance.rechercherDernierElement(codeNomenclature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementPrecedent(final String codeNomenclature, final String codeElement) {
        return elementPersistance.rechercherElementPrecedent(codeNomenclature, codeElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementSuivant(final String codeNomenclature, final String codeElement) {
        return elementPersistance.rechercherElementSuivant(codeNomenclature, codeElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String rechercherElementsParLiensParDate(final String codeNomenclatureUn, final List<String> codeElementsUn,
                                                          final String codeNomenclatureDeux, final String dateValiditeLien) {

        return elementPersistance.rechercherElementsParLiensParDate(codeNomenclatureUn, codeElementsUn, codeNomenclatureDeux,
                dateValiditeLien);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Element> rechercherElementsParPeriode(final Nomenclature nomenclature, final Date dateEffet,
                                                      final Date dateFin) {
        return elementPersistance.rechercherParPeriode(nomenclature, dateEffet, dateFin);

    }
}
