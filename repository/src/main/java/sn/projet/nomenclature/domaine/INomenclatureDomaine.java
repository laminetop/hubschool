package sn.projet.nomenclature.domaine;

import sn.projet.exception.FonctionnelleException;
import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.List;

/**
 * Interface pour NomenclatureDomaine.
 */
public interface INomenclatureDomaine {

    /**
     * Renvoie la liste des nomenclatures existantes.
     *
     * @return La liste des nomenclatures
     * @throws Exception Exception fonctionnelle si on trouve pas de nomenclatures
     */
    List<Nomenclature> rechercherNomenclatures();

    /**
     * Récupère la nomenclature à partir de son code.
     *
     * @param codeNomenclature - Le code de la nomenclature à rechercher
     * @return La nomenclature
     * @throws Exception Exception fonctionnelle si la nomenclature n'est pas trouvée
     */
    Nomenclature rechercherNomenclature(String codeNomenclature) throws FonctionnelleException;

    /**
     * Récupère la nomenclature à partir de son libellé.
     *
     * @param libelleNomenclature - Le libellé de la nomenclature recherchée
     * @return La nomenclature
     * @throws Exception exception fonctionnelle
     */
    Nomenclature rechercherNomenclatureParLibelle(String libelleNomenclature) throws FonctionnelleException;

    /**
     * Recherche toutes les nomenclatures avec les nomenclatures qui lui sont liées.
     *
     * @return l'ensemble des nomenclatures avec les nomenclatures qui lui sont liées
     */
    List<Nomenclature> rechercherNomenclaturesAvecLiens();

    /**
     * Recherche la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature1</code>.
     *
     * @param codeNomenclature  la nomenclature à rechercher
     * @param codeNomenclature1 le nomenclature reliée
     * @return la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature1</code>, null sinon
     */
    Nomenclature rechercherNomenclatureParLienNomenclature1(final String codeNomenclature, final String codeNomenclature1);

    /**
     * Recherche la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature2</code>.
     *
     * @param codeNomenclature  la nomenclature à rechercher
     * @param codeNomenclature2 le nomenclature reliée
     * @return la nomenclature dont le code est <code>codeNomenclature</code> et qui est liée à la
     * nomenclature <code>codeNomenclature2</code>, null sinon
     */
    Nomenclature rechercherNomenclatureParLienNomenclature2(final String codeNomenclature, final String codeNomenclature2);
}
