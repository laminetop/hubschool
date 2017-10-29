package sn.projet.nomenclature.domaine.impl;

import sn.projet.exception.FonctionnelleException;
import sn.projet.exception.IExceptionFactory;
import sn.projet.nomenclature.dao.INomenclaturePersistance;
import sn.projet.nomenclature.domaine.INomenclatureDomaine;
import sn.projet.nomenclature.transverse.Constantes;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe domaine.
 */
@Component("nomenclatureDomaine")
public class NomenclatureDomaineImpl implements INomenclatureDomaine {

    @Autowired
    protected IExceptionFactory factory;
    /**
     * nomenclaturePersistance.
     */
    @Autowired
    private INomenclaturePersistance nomenclaturePersistance;

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Nomenclature> rechercherNomenclatures() {
        return nomenclaturePersistance.lister();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Nomenclature rechercherNomenclature(final String codeNomenclature) throws FonctionnelleException {

        final Nomenclature nomenclature;

        if (codeNomenclature == null) {
            nomenclature = nomenclaturePersistance.rechercherParCode("");
        } else {
            nomenclature = nomenclaturePersistance.rechercherParCode(codeNomenclature);
        }

        if (nomenclature == null) {
            factory.throwFonctionnalException(Constantes.ERR_NOMENCLATURE_NEXIST, codeNomenclature);
        }

        return nomenclature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Nomenclature rechercherNomenclatureParLibelle(final String libelleNomenclature) throws FonctionnelleException {

        final Nomenclature nomenclature = nomenclaturePersistance.rechercherParCode(libelleNomenclature);

        if (nomenclature == null) {
            factory.throwFonctionnalException(Constantes.ERR_NOMENCLATURE_NEXIST, libelleNomenclature);
        }

        return nomenclature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Nomenclature> rechercherNomenclaturesAvecLiens() {
        return nomenclaturePersistance.rechercherNomenclaturesAvecLiens();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Nomenclature rechercherNomenclatureParLienNomenclature1(final String codeNomenclature,
                                                                         final String codeNomenclature1) {
        return nomenclaturePersistance.rechercherNomenclatureParLienNomenclature1(codeNomenclature, codeNomenclature1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Nomenclature rechercherNomenclatureParLienNomenclature2(final String codeNomenclature,
                                                                         final String codeNomenclature2) {
        return nomenclaturePersistance.rechercherNomenclatureParLienNomenclature2(codeNomenclature, codeNomenclature2);
    }
}
