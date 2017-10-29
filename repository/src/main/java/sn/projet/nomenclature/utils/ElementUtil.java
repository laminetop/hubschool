package sn.projet.nomenclature.utils;

import sn.projet.nomenclature.model.AttributExterneEntite;
import sn.projet.nomenclature.model.ElementEntite;
import sn.projet.nomenclature.transverse.vo.Element;

import java.util.Collection;

/**
 * Utilitaire pour la copie d'objet complex Element.
 * 
 * 
 * 
 */
public final class ElementUtil {

	/** Le constructeur en privé. */
	private ElementUtil() {
	}

	/**
	 * Copie d'un Element entite vers un Element.
	 * 
	 * @param src
	 *            objet source
	 * @param dest
	 *            objet destination
	 * @param codenomenclature
	 *            - Le code de la nomenclature
	 * @param attributExterne
	 *            - <code>true</code> si l'élément a des attributs externes, <code>false</code> sinon
	 */
	public static void copie(final ElementEntite src, final Element dest, final String codenomenclature,
		final boolean attributExterne) {
		if (src == null) {
			return;
		}

		dest.setId("/" + codenomenclature.trim() + "/" + src.getCodeElement().trim());
		dest.setCodeElement(src.getCodeElement());
		dest.setDateEffet(src.getDateEffet());
		dest.setDateFin(src.getDateFin());
		dest.setLibelleCourt(src.getLibelleCourt());
		dest.setLibelleImpression(src.getLibelleImpression());
		dest.setLibelleLong(src.getLibelleLong());

		if (attributExterne) {
			Collection < AttributExterneEntite > attribexters = src.getAttr();
			for (AttributExterneEntite attributE : attribexters) {
				dest.setAttribut(attributE.getLibelle(), attributE.getValeur());
			}
		}
	}
}
