package sn.projet.nomenclature.utils;

import sn.projet.nomenclature.model.AttributLienEntite;
import sn.projet.nomenclature.model.ElementEntite;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.utils.DateUtils;

import java.util.*;

/**
 * Utilitaire pour transformer les entités <code>ElementEntite</code> et <code>AttributLienEntite</code> en
 * <code>ElementAttributs</code>.
 * 
 */
public final class ElementAttributsUtil {

	/** L'index pour récupérer l'élément retournée par la requête. */
	private static final int INDEX_REQUETE_ELEMENT = 0;

	/** L'index pour récupérer l'attributlien retournée par la requête. */
	private static final int INDEX_REQUETE_ATTRIBUTLIEN = 1;

	/** L'index pour récupérer la date d'effet du lien retournée par la requête. */
	private static final int INDEX_REQUETE_DATEEFFET = 2;

	/** L'index pour récupérer a date de fin du lien retournée par la requête. */
	private static final int INDEX_REQUETE_DATEFIN = 3;

	/** Le nombre d'attributs minimum pour chaque lien (dateEffet + dateFin + others). */
	private static final int NB_ATTRIBUTLIEN_MINIMUM = 3;

	/**
	 * Constructeur.
	 */
	private ElementAttributsUtil() {
	}

	/**
	 * Copie les objets <code>elementSrc</code>, <code>attrLienSrc</code> dans <code>elementAttributsDest</code>.
	 * 
	 * @param elementSrc
	 *            l'élément à copier
	 * @param attrLienSrc
	 *            les attributs du lien à copier
	 * @param elementAttributsDest
	 *            l'objet destination
	 * @param codeNomenclature
	 *            le code de la nomenclature de l'élément <code>elementSrc</code>
	 * @param attributExterne
	 *            <code>tre</code> si l'élément <code>elementSrc</code> a des attributs externes, <code>false</code> sinon
	 */
	public static void copie(final ElementEntite elementSrc, final List < AttributLienEntite > attrLienSrc,
		final ElementAttributs elementAttributsDest, final String codeNomenclature, final boolean attributExterne) {
		if (elementSrc == null) {
			return;
		}
		Element dest = new Element();
		ElementUtil.copie(elementSrc, dest, codeNomenclature, attributExterne);
		elementAttributsDest.setElement(dest);

		if (attrLienSrc != null) {
			Map < String, String > attrLiens = new HashMap < String, String >(attrLienSrc.size());
			for (AttributLienEntite attrLien : attrLienSrc) {
				if (attrLien != null) {
					attrLiens.put(attrLien.getLibelle(), attrLien.getValeur());
				}
			}
			elementAttributsDest.setAttributsLiens(attrLiens);
		}
	}

	/**
	 * Convertit une liste <code>ElementAttributsEntite</code> en une liste <code>ElementAttributs</code>.
	 * 
	 * @param elementAttributsEnBase
	 *            l'entité <code>ElementAttributsEntite</code> à convertir
	 * @param codeNomenclature
	 *            le code de la nomenclature de l'élément <code>elementAttributsEnBase</code>
	 * @param attributExterne
	 *            <code>tre</code> si l'élément <code>elementSrc</code> a des attributs externes, <code>false</code> sinon
	 * @return convertit une liste <code>ElementAttributsEntite</code> en une liste <code>ElementAttributs</code>
	 */
	public static List < ElementAttributs > transformeElementAttributsEntiteEnElementAttributs(
		final List < ? > elementAttributsEnBase, final String codeNomenclature, final boolean attributExterne) {
		final List < ElementAttributs > elementAttributs = new ArrayList < ElementAttributs >();
		// Résultat du lien avec les attributs du lien
		final Map < ElementEntite, List < AttributLienEntite >> results = new HashMap < ElementEntite, List < AttributLienEntite >>();

		if (elementAttributsEnBase != null) {
			ElementEntite elementEnBase = null;
			AttributLienEntite attributLienEnBase = null;
			Object[] objets = null;
			List < AttributLienEntite > attributLiensEnBase = null;
			Calendar dateEffetDuLien = null;
			Calendar dateFinDuLien = null;
			for (Object o : elementAttributsEnBase) {
				objets = (Object[]) o;
				elementEnBase = (ElementEntite) objets[INDEX_REQUETE_ELEMENT];
				attributLienEnBase = (AttributLienEntite) objets[INDEX_REQUETE_ATTRIBUTLIEN];
				dateEffetDuLien = (Calendar) objets[INDEX_REQUETE_DATEEFFET];
				dateFinDuLien = (Calendar) objets[INDEX_REQUETE_DATEFIN];

				if (results.containsKey(elementEnBase)) {
					attributLiensEnBase = results.get(elementEnBase);
					attributLiensEnBase.add(attributLienEnBase);
				} else {
					attributLiensEnBase = new ArrayList < AttributLienEntite >(NB_ATTRIBUTLIEN_MINIMUM);
					attributLiensEnBase.add(attributLienEnBase);
					attributLiensEnBase.add(convertitCalendarEnAttributLienEntite("dateEffet", dateEffetDuLien));
					attributLiensEnBase.add(convertitCalendarEnAttributLienEntite("dateFin", dateFinDuLien));
					results.put(elementEnBase, attributLiensEnBase);
				}
			}

			ElementAttributs eltAttr = null;
			for (ElementEntite elementEntite : results.keySet()) {
				eltAttr = new ElementAttributs();
				ElementAttributsUtil.copie(elementEntite, results.get(elementEntite), eltAttr, codeNomenclature, attributExterne);
				elementAttributs.add(eltAttr);
			}
		}
		return elementAttributs;
	}

	/**
	 * Crée et initialise un objet <code>AttributLienEntite</code> à partir des paramètres donnés.
	 * 
	 * @param nomLibelle
	 *            le nom du libellé
	 * @param valeurLibelle
	 *            la valeur du libellé
	 * @return l'objet <code>AttributLienEntite</code> initialisé à partir des paramètres donnés
	 */
	private static AttributLienEntite convertitCalendarEnAttributLienEntite(final String nomLibelle, final Calendar valeurLibelle) {
		AttributLienEntite attributLienEntite = new AttributLienEntite();
		attributLienEntite.setLibelle(nomLibelle);
		attributLienEntite.setValeur(DateUtils.getAAAAMMJJ(valeurLibelle));
		return attributLienEntite;
	}
}
