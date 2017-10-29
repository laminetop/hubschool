package sn.projet.nomenclature.dao.impl;

import sn.projet.nomenclature.dao.IElementPersistance;
import sn.projet.nomenclature.model.ElementEntite;
import sn.projet.nomenclature.transverse.Constantes;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.Nomenclature;
import sn.projet.nomenclature.utils.ElementUtil;
import sn.projet.utils.DateUtils;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * L'implémentation de la couche persistance d'un objet Element.
 */
@Repository("elementPersistance")
public class ElementPersistanceImpl implements IElementPersistance {

    /**
     * L'entity manager.
     */
    @PersistenceContext(unitName = "hubschool-nomenclature-pu")
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean verifierAppartenance(final Nomenclature nomenclature, final String codeElement, final Date dateEffet) {

        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_VERIFIER_APPARTENANCE);
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_CODE_ELEMENT, codeElement);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);

        final List<?> results = requete.getResultList();

        return !results.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherElements(final Nomenclature nomenclature, final Date dateEffet) {

        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENTS);
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);
        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        List<?> elementsEnBase = requete.getResultList();
        final List<Element> elements = new ArrayList<Element>();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {
                ElementEntite elementEnBase = (ElementEntite) o;
                final Element element = new Element();
                ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);
                elements.add(element);
            }
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherParCode(final Nomenclature nomenclature, final String codeElement, final Date dateEffet) {

        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_CODE);

        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_CODE_ELEMENT, codeElement);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);
        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        final List<?> elementsEnBase = requete.getResultList();
        Element element = null;

        if (elementsEnBase != null && !elementsEnBase.isEmpty() && elementsEnBase.size() != 0) {
            ElementEntite elementEnBase = (ElementEntite) elementsEnBase.get(0);
            element = new Element();
            ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);

        }
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherParLibelleCourt(final Nomenclature nomenclature, final String libelleCourt,
                                                         final Date dateEffet) {

        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_COURT);
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_LIBELLE_COURT, libelleCourt);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);
        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        final List<?> elementsEnBase = requete.getResultList();
        final List<Element> elements = new ArrayList<Element>();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {
                ElementEntite elementEnBase = (ElementEntite) o;
                final Element element = new Element();
                ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);
                elements.add(element);
            }
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherParLibelleLong(final Nomenclature nomenclature, final String libelleLong,
                                                        final Date dateEffet) {

        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_LONG);
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_LIBELLE_LONG, libelleLong);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);
        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        final List<?> elementsEnBase = requete.getResultList();
        final List<Element> elements = new ArrayList<Element>();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {
                ElementEntite elementEnBase = (ElementEntite) o;
                final Element element = new Element();
                ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);
                elements.add(element);
            }
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherParLibelleImpression(final Nomenclature nomenclature, final String libelleImpression,
                                                              final Date dateEffet) {

        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_IMPRESSION);
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_LIBELLE_IMPRESSION, libelleImpression);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);

        final List<?> elementsEnBase = requete.getResultList();
        final List<Element> elements = new ArrayList<Element>();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {
                ElementEntite elementEnBase = (ElementEntite) o;
                final Element element = new Element();
                ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);
                elements.add(element);
            }
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Element> rechercherParAttributExterne(final Nomenclature nomenclature, final String libelleAttribut,
                                                            final String valeurAttribut, final Date date) {

        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_ATTRIBUT_EXTERNE);

        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
        requete.setParameter(Constantes.ATTR_LIBELLE_ATTRIBUT, libelleAttribut);
        requete.setParameter(Constantes.ATTR_VALEUR_ATTRIBUT, valeurAttribut);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, date);

        final List<?> elementsEnBase = requete.getResultList();
        final List<Element> elements = new ArrayList<Element>();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {
                ElementEntite elementEnBase = (ElementEntite) o;
                final Element element = new Element();
                ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);
                elements.add(element);
            }
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherPremierElement(final String codeNomenclature) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherDernierElement(final String codeNomenclature) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementPrecedent(final String codeNomenclature, final String codeElement) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Element rechercherElementSuivant(final String codeNomenclature, final String codeElement) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String rechercherElementsParLiensParDate(final String codeNomenclatureUn, final List<String> codeElementsUn,
                                                          final String codeNomenclatureDeux, final String dateValiditeLien) {

        StringBuilder stRequete = new StringBuilder();

        stRequete.append("Select lien.element2.codeElement from LienEntite as lien "
                + " where lien.element1.codeElement in (:codeElement1) and lien.element1.nomenclature.code= :codeNomenclature1 "
                + " and lien.element2.nomenclature.code= :codeNomenclature2 "
                + " and :dateEffet >= lien.dateEffet and (lien.dateFin is null or :dateEffet <= lien.dateFin)");
        final Query requete = entityManager.createQuery(stRequete.toString());
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE2_LIEN, codeNomenclatureDeux);
        requete.setParameter(Constantes.ATTR_DATE_EFFET_LIEN, DateUtils.getDateFormat(DateUtils.FORMAT_ISO_DATE).format(dateValiditeLien));
        requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE1_LIEN, codeNomenclatureUn);
        requete.setParameter(Constantes.ATTR_CODE_ELEMENT1_LIEN, codeElementsUn);
        List<?> elementsEnBase = requete.getResultList();
        StringBuilder elements = new StringBuilder();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {

                elements.append(o.toString());
                elements.append(",");

            }
        }
        return elements.toString();

    }

    /**
     * Méthode qui exécute une requête de recherche de "enfants" (recherche d'une grille en partant
     * d'un grade, d'un indice en partant d'un échelon, etc.).
     *
     * @param entityManager l'entity manager
     * @param hql           la requête
     * @param params        les paramètres de la requête
     * @return une liste (de string la plupart du temps) correspondant aux "enfants"
     */
    private List<?> chercherEnfants(final EntityManager entityManager, final String hql, final Map<String, Object> params) {
        Query requete = entityManager.createNamedQuery(hql);
        for (String clef : params.keySet()) {
            requete.setParameter(clef, params.get(clef));
        }
        return requete.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Element> rechercherParPeriode(final Nomenclature nomenclature, final Date dateEffet, final Date dateFin) {

        final Query requete;

        if (dateFin == null) {
            requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE_DATEFIN_NULL);
            requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
            requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);
        } else {
            requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE);
            requete.setParameter(Constantes.ATTR_CODE_NOMENCLATURE, nomenclature.getCode());
            requete.setParameter(Constantes.ATTR_DATE_EFFET_ELEMENT, dateEffet);
            requete.setParameter(Constantes.ATTR_DATE_FIN_ELEMENT, dateFin);
        }

        final boolean possessionAttributExt = nomenclature.getLibelleAttributExterne() != null
                && nomenclature.getLibelleAttributExterne().trim().length() != 0;
        List<?> elementsEnBase = requete.getResultList();
        final List<Element> elements = new ArrayList<Element>();

        if (elementsEnBase != null) {
            for (Object o : elementsEnBase) {
                ElementEntite elementEnBase = (ElementEntite) o;
                final Element element = new Element();
                ElementUtil.copie(elementEnBase, element, nomenclature.getCode(), possessionAttributExt);
                elements.add(element);
            }
        }
        return elements;
    }
}
