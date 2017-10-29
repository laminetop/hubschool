package sn.projet.nomenclature.utils;

import sn.projet.nomenclature.model.AttributExterneEntite;
import sn.projet.nomenclature.transverse.vo.AttributExterne;

/**
 * Utilitaire pour la copie d'objet complex AttributExterne.
 * 
 * 
 * 
 */
public final class AttributExterneUtil {

	/** Le constructeur en privé. */
	private AttributExterneUtil() {
	}

	/**
	 * Copie d'un AttributExterne entite vers un AttributExterne.
	 * 
	 * @param src
	 *            objet source
	 * @param dest
	 *            objet destination
	 */
	public static void copie(final AttributExterneEntite src, final AttributExterne dest) {
		if (src == null) {
			return;
		}
		dest.setIdAttribut(src.getIdAttribut());
		dest.setLibelle(src.getLibelle());
		dest.setValeur(src.getValeur());
	}
}
