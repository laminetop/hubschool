package sn.projet.nomenclature.dao;

import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.List;

/**
 * Interface pour NomenclatureDAO.
 * 
 */
public interface INomenclaturePersistance {

	/**
	 * Récupère la liste des nomenclatures existantes.
	 * 
	 * @return la liste des nomenclature
	 */
	List < Nomenclature > lister();

	/**
	 * Récupère la nomenclature <code>codeNomenclature</code>.
	 * 
	 * @param codeNomenclature
	 *            le code de la nomenclature
	 * @return la nomenclature dont le code est <code>codeNomenclature</code>
	 */
	Nomenclature rechercherParCode(String codeNomenclature);

	/**
	 * Recherche toutes les nomenclatures avec les nomenclatures qui lui sont liées.
	 * 
	 * @return l'ensemble des nomenclatures avec les nomenclatures qui lui sont liées
	 */
	List < Nomenclature > rechercherNomenclaturesAvecLiens();

	/**
	 * Recherche la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la nomenclature
	 * <code>codeNomenclature1</code>.
	 * 
	 * @param codeNomenclature
	 *            la nomenclature à rechercher
	 * @param codeNomenclature1
	 *            le nomenclature reliée
	 * @return la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la nomenclature
	 *         <code>codeNomenclature1</code>, null sinon
	 */
	Nomenclature rechercherNomenclatureParLienNomenclature1(final String codeNomenclature, final String codeNomenclature1);

	/**
	 * Recherche la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la nomenclature
	 * <code>codeNomenclature2</code>.
	 * 
	 * @param codeNomenclature
	 *            la nomenclature à rechercher
	 * @param codeNomenclature2
	 *            le nomenclature reliée
	 * @return la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la nomenclature
	 *         <code>codeNomenclature2</code>, null sinon
	 */
	Nomenclature rechercherNomenclatureParLienNomenclature2(final String codeNomenclature, final String codeNomenclature2);
}
