package sn.projet.nomenclature.service.jmx;

import sn.projet.nomenclature.transverse.vo.Element;

import java.util.List;

/**
 * Services de la nomenclature andado exposés en JMX.
 * 
 */
public interface INomenclatureServiceMBean {

	/**
	 * Rechercher tous les éléments de la nomenclature <code>codeNomenclature</code> qui sont valides à la date <code>date</code>.
	 * 
	 * @param codeNomenclature
	 *            - Le code de la nomenclature
	 * @param date
	 *            - La date d'observation
	 * @return Les éléments de la nomenclature <code>codeNomenclature</code>, null sinon
	 */
	List < Element > rechercherElements(String codeNomenclature, String date);

	/**
	 * Rechercher tous les éléments de la nomenclature <code>codeNomenclature</code> qui sont valides à la date du jour.
	 * 
	 * @param codeNomenclature
	 *            - Le code de la nomenclature
	 * @return Les éléments de la nomenclature <code>codeNomenclature</code>, null sinon
	 */
	List < Element > rechercherElementsCourant(String codeNomenclature);
}
