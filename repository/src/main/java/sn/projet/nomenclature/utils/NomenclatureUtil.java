package sn.projet.nomenclature.utils;

import sn.projet.nomenclature.model.NomenclatureEntite;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilitaire pour manipuler l'objet NomenclatureEntite.
 * 
 */
public final class NomenclatureUtil {

	/** Le constructeur en privé. */
	private NomenclatureUtil() {
	}

	/**
	 * Copie d'une <code>NomenclatureEntite</code> vers un objet <code>Nomenclature</code>. Ne copie pas les nomenclatures liées.
	 * 
	 * @param src
	 *            l'entité à copier
	 * @param dest
	 *            le VO de destination
	 */
	public static void copie(final NomenclatureEntite src, final Nomenclature dest) {
		if (src == null) {
			return;
		}
		dest.setCode(src.getCode());
		dest.setLibelle(src.getLibelle());
		// dest.setLibelleInferieur(src.getLibelleInferieur());
		// dest.setLibelleSuperieur(src.getLibelleSuperieur());
		dest.setLibelleAttributExterne(src.getLibelleAttributExterne());
	}

	/**
	 * Copie d'une <code>NomenclatureEntite</code> vers un objet <code>Nomenclature</code>. Copie également les nomenclatures
	 * liées.
	 * 
	 * @param src
	 *            l'entité à copier
	 * @param dest
	 *            le VO de destination
	 */
	public static void copieAvecCodeNomenclaturesLies(final NomenclatureEntite src, final Nomenclature dest) {
		if (src == null) {
			return;
		}
		dest.setCode(src.getCode());
		dest.setLibelle(src.getLibelle());
		dest.setLibelleAttributExterne(src.getLibelleAttributExterne());
		List < String > codeNomenclaturesLies = new ArrayList < String >(src.getNomenclature1().size()
			+ src.getNomenclature2().size());

		for (NomenclatureEntite nomenclature1 : src.getNomenclature1()) {
			codeNomenclaturesLies.add(nomenclature1.getCode());
		}
		for (NomenclatureEntite nomenclature2 : src.getNomenclature2()) {
			codeNomenclaturesLies.add(nomenclature2.getCode());
		}
		dest.setCodeNomenclaturesLies(codeNomenclaturesLies);
	}

	/**
	 * Ajoute le code nomenclature <code>codeNomenclature</code> à la liste des codes nomenclatures liées à la nomenclature
	 * <code>nomenclature</code>.
	 * 
	 * @param nomenclature
	 *            la nomenclature auquelle il faut ajouter un nouveau code nomenclature lié
	 * @param codeNomenclature
	 *            le code nomenclature à ajouter
	 */
	public static void ajouteCodeNomenclatureLie(final Nomenclature nomenclature, final String codeNomenclature) {
		List < String > nomenclatureDejaLiees = nomenclature.getCodeNomenclaturesLies();
		if (nomenclatureDejaLiees == null) {
			nomenclatureDejaLiees = new ArrayList < String >(1);
			nomenclatureDejaLiees.add(codeNomenclature);
			nomenclature.setCodeNomenclaturesLies(nomenclatureDejaLiees);
		} else {
			nomenclature.getCodeNomenclaturesLies().add(codeNomenclature);
		}
	}
}
