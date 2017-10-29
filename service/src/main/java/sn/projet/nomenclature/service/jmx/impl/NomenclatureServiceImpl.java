package sn.projet.nomenclature.service.jmx.impl;

import sn.projet.nomenclature.service.INomenclatureService;
import sn.projet.nomenclature.service.jmx.INomenclatureServiceMBean;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du MBean pour les nomenclatures andado.
 * 
 */
@Service("nomenclatureServiceJMX")
public class NomenclatureServiceImpl implements INomenclatureServiceMBean {

	/** Le loggeur. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NomenclatureServiceImpl.class);

	/** Le service des nomenclatures andado. */
	@Autowired
	private INomenclatureService nomenclatureService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List < Element > rechercherElements(final String codeNomenclature, final String date) {
		try {
			return nomenclatureService.rechercherElementsCourant(codeNomenclature);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List < Element > rechercherElementsCourant(final String codeNomenclature) {
		return rechercherElements(codeNomenclature, DateUtils.getAAAAMMJJ(DateUtils.getDateCourante()));
	}

}
