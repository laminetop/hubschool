package sn.projet.nomenclature.dao.impl;

import sn.projet.nomenclature.dao.INomenclaturePersistance;
import sn.projet.nomenclature.model.NomenclatureEntite;
import sn.projet.nomenclature.transverse.Constantes;
import sn.projet.nomenclature.transverse.vo.Nomenclature;
import sn.projet.nomenclature.utils.NomenclatureUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * L'implémentation de la couche persistance d'un objet Nomenclature.
 * 
 */
@Repository("nomenclaturePersistance")
public class NomenclaturePersistanceImpl implements INomenclaturePersistance {

	/** Le gestionnaire des entités. */
	@PersistenceContext(unitName = "hubschool-nomenclature-pu")
	private EntityManager entityManager;

	private static final Log LOG = LogFactory.getLog(NomenclaturePersistanceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Nomenclature rechercherParCode(final String codeNomenclature) {
		final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_NOMENCLATURE_PAR_CODE);
		requete.setParameter(Constantes.PARAM_CODENOMENCLATURE, codeNomenclature);

		List < ? > nomenclaturesEnBase = requete.getResultList();
		Nomenclature nomenclature = null;

		if (nomenclaturesEnBase != null && nomenclaturesEnBase.size() == 1) {
			NomenclatureEntite nomenclatureEnBase = (NomenclatureEntite) nomenclaturesEnBase.get(0);
			nomenclature = new Nomenclature();
			NomenclatureUtil.copie(nomenclatureEnBase, nomenclature);
		}
		return nomenclature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List < Nomenclature > lister() {
		final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_NOMENCLATURE);
		final List < ? > nomenclaturesEnBase = requete.getResultList();
		final List < Nomenclature > nomenclatures = new ArrayList < Nomenclature >();

		if (nomenclaturesEnBase != null) {
			NomenclatureEntite nomenclatureEnBase = null;
			Nomenclature nomenclature = null;

			for (Object o : nomenclaturesEnBase) {
				nomenclatureEnBase = (NomenclatureEntite) o;
				nomenclature = new Nomenclature();
				NomenclatureUtil.copie(nomenclatureEnBase, nomenclature);
				nomenclatures.add(nomenclature);
			}
		}
		return nomenclatures;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List < Nomenclature > rechercherNomenclaturesAvecLiens() {
		final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_ALL_NOMENS_ET_LIEN_ENTRE_NOMENS);
		final List < ? > nomenclaturesEnBase = requete.getResultList();
		final List < Nomenclature > nomenclatures = new ArrayList < Nomenclature >();
		Map < String, Nomenclature > nomenclatureDejaTraitees = null;

		if (nomenclaturesEnBase != null) {
			NomenclatureEntite nomenclatureEnBase = null;
			NomenclatureEntite newNomenclatureLiee = null;
			Nomenclature nomenclature = null;
			Nomenclature nomenclatureDejaTraitee = null;
			nomenclatureDejaTraitees = new HashMap < String, Nomenclature >(1);

			for (Object o : nomenclaturesEnBase) {
				Object[] resultat = (Object[]) o;
				nomenclatureEnBase = (NomenclatureEntite) resultat[0];

				nomenclatureDejaTraitee = nomenclatureDejaTraitees.get(nomenclatureEnBase.getCode());
				if (nomenclatureDejaTraitee == null) {
					// On la transforme en VO leger
					nomenclature = new Nomenclature();
					NomenclatureUtil.copie(nomenclatureEnBase, nomenclature);
					nomenclatures.add(nomenclature);
					nomenclatureDejaTraitees.put(nomenclature.getCode(), nomenclature);
				}
			}

			// A ce stade, la map contient l'ensemble des nomenclatures legères (cad sans les liens entre nomenclatures)
			for (Object o : nomenclaturesEnBase) {
				Object[] resultat = (Object[]) o;
				nomenclatureEnBase = (NomenclatureEntite) resultat[0];
				newNomenclatureLiee = (NomenclatureEntite) resultat[1];

				if (newNomenclatureLiee != null) {
					// La nomenclature est liée à une autre nomenclature
					nomenclature = nomenclatureDejaTraitees.get(nomenclatureEnBase.getCode());
					// On récupère sa liste des nomenclatures liées
					NomenclatureUtil.ajouteCodeNomenclatureLie(nomenclature, newNomenclatureLiee.getCode());
					nomenclatureDejaTraitees.put(nomenclatureEnBase.getCode(), nomenclature);

					// Il faut maintenant relié la 2ième nomenclature à la première
					nomenclature = nomenclatureDejaTraitees.get(newNomenclatureLiee.getCode());
					// On récupère sa liste des nomenclatures liées
					NomenclatureUtil.ajouteCodeNomenclatureLie(nomenclature, nomenclatureEnBase.getCode());
					nomenclatureDejaTraitees.put(newNomenclatureLiee.getCode(), nomenclature);
				}
			}
		}

		List < Nomenclature > results = null;
		if (nomenclatureDejaTraitees != null) {
			results = new ArrayList < Nomenclature >(nomenclatureDejaTraitees.size());
			for (Nomenclature n : nomenclatureDejaTraitees.values()) {
				results.add(n);
			}
		}
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Nomenclature rechercherNomenclatureParLienNomenclature1(final String codeNomenclature,
		final String codeNomenclature1) {
		final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_NOMENCLATURE_PAR_CODE);
		requete.setParameter(Constantes.PARAM_CODENOMENCLATURE, codeNomenclature);
		NomenclatureEntite nomenclatureEnBase = null;
		Nomenclature nomenclature = null;
		boolean found = false;
		try {
			nomenclatureEnBase = (NomenclatureEntite) requete.getSingleResult();
			List < NomenclatureEntite > nomenclature1s = nomenclatureEnBase.getNomenclature1();
			for (NomenclatureEntite nomenclature1 : nomenclature1s) {
				// Si le code de la nomenclature de droite correspond au code <code>codeNomenclature1</code>
				if (nomenclature1.getCode().equals(codeNomenclature1)) {
					found = true;
				}
			}

			if (found) {
				nomenclature = new Nomenclature();
				NomenclatureUtil.copie(nomenclatureEnBase, nomenclature);
			}
		} catch (NoResultException nre) {
			LOG.error("la nomenclature " + codeNomenclature + " n'existe pas ");

		}
		return nomenclature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Nomenclature rechercherNomenclatureParLienNomenclature2(final String codeNomenclature,
		final String codeNomenclature2) {
		final Query requete = entityManager.createNamedQuery(Constantes.REQUETE_RECHERCHER_NOMENCLATURE_PAR_CODE);
		requete.setParameter(Constantes.PARAM_CODENOMENCLATURE, codeNomenclature);
		NomenclatureEntite nomenclatureEnBase = null;
		Nomenclature nomenclature = null;
		boolean found = false;
		try {
			nomenclatureEnBase = (NomenclatureEntite) requete.getSingleResult();
			List < NomenclatureEntite > nomenclature2s = nomenclatureEnBase.getNomenclature2();
			for (NomenclatureEntite nomenclature2 : nomenclature2s) {
				// Si le code de la nomenclature de gauche correspond au code <code>codeNomenclature1</code>
				if (nomenclature2.getCode().equals(codeNomenclature2)) {
					found = true;
				}
			}

			if (found) {
				nomenclature = new Nomenclature();
				NomenclatureUtil.copie(nomenclatureEnBase, nomenclature);
			}
		} catch (NoResultException nre) {
			LOG.error("la nomenclature " + codeNomenclature + " n'existe pas ");

		}
		return nomenclature;
	}
}
