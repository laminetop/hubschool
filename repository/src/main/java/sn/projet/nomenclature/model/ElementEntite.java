package sn.projet.nomenclature.model;

import sn.projet.nomenclature.transverse.Constantes;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Entité qui mappe la table ELEMENT.
 */
@Entity
@Table(name = "ELEMENT", uniqueConstraints = {@UniqueConstraint(columnNames = {"CODEELEMENT", "DATEEFFET"})})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQueries({
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENTS, query = "SELECT element from ElementEntite as element "
                + "where element.nomenclature.code = :code and " + ElementEntite.BETWEEN_DATE + ")"),
        @NamedQuery(name = Constantes.REQUETE_VERIFIER_APPARTENANCE, query = "SELECT element from ElementEntite as element "
                + "where element.nomenclature.code = :code and element.codeElement like :codeElement and " + ElementEntite.BETWEEN_DATE
                + ")"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_CODE, query = "select element from ElementEntite as element "
                + "where element.nomenclature.code = :code and element.codeElement = :codeElement and " + ElementEntite.BETWEEN_DATE
                + ")"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_COURT, query = "select element "
                + "from ElementEntite as element where element.nomenclature.code = :code "
                + "and element.libelleCourt like '%'||:libelleCourt||'%' and " + ElementEntite.BETWEEN_DATE + ")"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_LONG, query = "select element from ElementEntite as element "
                + "where element.nomenclature.code = :code and element.libelleLong like '%'||:libelleLong||'%' and "
                + ElementEntite.BETWEEN_DATE + ")"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_IMPRESSION, query = "select element from ElementEntite as element "
                + "where element.nomenclature.code = :code and element.libelleImpression like '%'||:libelleImpression||'%' and "
                + ElementEntite.BETWEEN_DATE + ")"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENT_PAR_ATTRIBUT_EXTERNE, query = "select element "
                + "from AttributExterneEntite as attributExterne join attributExterne.element as element "
                + "where element.nomenclature.code = :code and attributExterne.libelle like :libelle "
                + "and attributExterne.valeur like :valeur and " + ElementEntite.BETWEEN_DATE + ")")
        // ,
        // @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENT_AVEC_UN_ORDRE, query = "select element from ElementEntite as e"
        // + "where e.ordre = :ordre and e.nomenclature.code = :codeNomenclature"),
        // @NamedQuery(name = Constantes.REQUETE_RECHERCHER_DERNIER_ELEMENT, query = "select element from ElementEntite as e"
        // + "where e.ordre = ( select max(elt.ordre) from ElementEntite as elt where elt.nomenclature.code = :codeNomenclature)")

        ,

        // Requêtes de recherche de liens entre elements
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_LIENS_SUP, query = "select lien.element2, attrLiens, lien.dateEffet, lien.dateFin "
                + "from LienEntite as lien left outer join lien.attributsLiens as attrLiens "
                + "where lien.element1.codeElement = :codeElement1 and lien.element1.nomenclature.code= :codeNomenclature1 "
                + "and lien.element2.nomenclature.code= :codeNomenclature1 and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN
                + LienEntite.AND + LienEntite.LIEN_ELEMENT2_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_LIENS_INF, query = "select lien.element1, attrLiens, lien.dateEffet, lien.dateFin "
                + "from LienEntite as lien left outer join lien.attributsLiens as attrLiens "
                + "where lien.element2.codeElement = :codeElement2 and lien.element2.nomenclature.code= :codeNomenclature2 "
                + "and lien.element1.nomenclature.code= :codeNomenclature2 and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN
                + LienEntite.AND + LienEntite.LIEN_ELEMENT1_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_CODE_LIENS_SUP, query = "select element2.codeElement from LienEntite as lien "
                + "where lien.element1.codeElement = :codeElement1 and lien.element1.nomenclature.code= :codeNomenclature1 "
                + "and lien.element2.nomenclature.code= :codeNomenclature1 and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN
                + LienEntite.AND + LienEntite.LIEN_ELEMENT2_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_CODE_LIENS_INF, query = "select element1.codeElement from LienEntite as lien "
                + "where lien.element2.codeElement = :codeElement2 and lien.element2.nomenclature.code= :codeNomenclature2 "
                + "and lien.element1.nomenclature.code= :codeNomenclature2 and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN
                + LienEntite.AND + LienEntite.LIEN_ELEMENT1_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_LIENS_INF_LEX, query = "select element1, attrLiens, lien.dateEffet, lien.dateFin "
                + "from LienEntite as lien "
                + "join lien.element1 as element1 left outer join lien.attributsLiens as attrLiens where lien.element2.codeElement = :codeElement2 "
                + "and lien.element2.nomenclature.code= :codeNomenclature2 and element1.nomenclature.code = :code and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN
                + LienEntite.AND
                + LienEntite.LIEN_ELEMENT1_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_LIENS_SUP_LEX, query = "select element2, attrLiens, lien.dateEffet, lien.dateFin "
                + "from LienEntite as lien "
                + "join lien.element2 as element2 left outer join lien.attributsLiens as attrLiens where lien.element1.codeElement = :codeElement1 "
                + "and lien.element1.nomenclature.code= :codeNomenclature1 and element2.nomenclature.code = :code and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN
                + LienEntite.AND
                + LienEntite.LIEN_ELEMENT2_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_CODE_LIENS_INF_LEX, query = "select element1.codeElement "
                + "from LienEntite as lien join lien.element1 as element1 where lien.element2.codeElement = :codeElement2 "
                + "and lien.element2.nomenclature.code= :codeNomenclature2 and element1.nomenclature.code = :code and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN + LienEntite.AND
                + LienEntite.LIEN_ELEMENT1_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_CODE_LIENS_SUP_LEX, query = "select element2.codeElement "
                + "from LienEntite as lien join lien.element2 as element2 where lien.element1.codeElement = :codeElement1 "
                + "and lien.element1.nomenclature.code= :codeNomenclature1 and element2.nomenclature.code = :code and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN + LienEntite.AND
                + LienEntite.LIEN_ELEMENT2_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_INDICES, query = "select lien.element1 from LienEntite as lien where lien.element2.id "
                + "in (:idSup) and  lien.element1.nomenclature.code = :codeInf and "
                + ":indice < lien.element1.codeElement and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_CORPS_PAR_INDICE, query = "select element.codeElement from ElementEntite as element "
                + "where element.id in (select lien.element2.id from LienEntite as lien where lien.element1.id in ("
                + "select lien.element2.id from LienEntite as lien where lien.element1.id in ("
                + "select lien.element1.id from LienEntite as lien where lien.element2.id in ("
                + "select lien.element2.id from LienEntite as lien where lien.element1.id in ("
                + "select element.id from ElementEntite as element where element.nomenclature.id = "
                + "(select nomenclature.id from NomenclatureEntite as nomenclature where nomenclature.code = 'N_INDICE_FP') "
                + "and CAST(:indice as int) < CAST(element.codeElement as int)"
                + ") and lien.element2.id in ("
                + "select element.id from ElementEntite as element where element.nomenclature.id = "
                + "(select nomenclature.id from NomenclatureEntite as nomenclature where nomenclature.code = 'N_ECHELON_FP')"
                + ")"
                + ") and lien.element1.id in ("
                + "select element.id from ElementEntite as element where element.nomenclature.id = "
                + "(select nomenclature.id from NomenclatureEntite as nomenclature where nomenclature.code = 'N_GRILLE_INDICIAIRE_FP')"
                + ")"
                + ") and lien.element2.id in ("
                + "select element.id from ElementEntite as element where element.nomenclature.id = "
                + "(select nomenclature.id from NomenclatureEntite as nomenclature where nomenclature.code = 'N_GRADE_FP')"
                + ")"
                + ") and lien.element2.id in ("
                + "select element.id from ElementEntite as element where element.nomenclature.id = "
                + "(select nomenclature.id from NomenclatureEntite as nomenclature where nomenclature.code = 'N_CORPS_FP')"
                + ") ) and"
                + ElementEntite.BETWEEN_DATE + ")"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENTS_POSSEDES, query = "select lien.element1.id from LienEntite as lien where "
                + "lien.element2.id in (:idSup) and  lien.element1.nomenclature.code = :codeInf and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENTS_POSSEDANT, query = "select lien.element2.id from LienEntite as lien where "
                + "lien.element1.id in (:idInf) and  lien.element2.nomenclature.code = :codeSup and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE, query = "SELECT element from ElementEntite as element "
                + "where element.nomenclature.code = :code and ( (element.dateEffet <= :dateEffet and (element.dateFin>=:dateEffet or element.dateFin is null)) OR "
                + "(element.dateEffet>=:dateEffet and element.dateFin<=:dateFin) OR "
                + "(element.dateEffet<=:dateFin and (element.dateFin>=:dateFin OR element.dateFin is null)))"),
        @NamedQuery(name = Constantes.REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE_DATEFIN_NULL, query = "SELECT element from ElementEntite as element "
                + "where element.nomenclature.code = :code and ((element.dateEffet<=:dateEffet and (element.dateFin>=:dateEffet or element.dateFin is null)) OR (element.dateEffet>=:dateEffet))")

})
public class ElementEntite implements Serializable {

    /**
     * Contrainte élément compris entre date d'effet and date de fin.
     */
    public static final String BETWEEN_DATE = ":dateEffet >= element.dateEffet "
            + "and (element.dateFin is null or :dateEffet <= element.dateFin";
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -7929131553613552581L;
    /**
     * Identifiant technique.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDELEMENT", columnDefinition = "serial", nullable = false)
    private Long id;

    /**
     * Code de l'élément.
     */
    @Column(name = "CODEELEMENT", nullable = false, length = Constantes.LONGUEUR_CODE)
    private String codeElement;

    /**
     * Libelle court de l'élément.
     */
    @Column(name = "LIBELLECOURT", nullable = false, length = Constantes.LONGUEUR_NOM_ATTRIBUT_EXTERNE)
    private String libelleCourt;

    /**
     * Libelle long de l'élément.
     */
    @Column(name = "LIBELLELONG", length = Constantes.LONGUEUR_LIBELLE_LONG, nullable = false)
    private String libelleLong;

    /**
     * Libelle d'impression de l'élément.
     */
    @Column(name = "LIBELLEIMPRESSION", length = Constantes.LONGUEUR_LIBELLE_LONG, nullable = false)
    private String libelleImpression;

    /**
     * Date d'effet de l'élément.
     */
    @Column(name = "DATEEFFET", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEffet;

    /**
     * date de fin de l'élément.
     */
    @Column(name = "DATEFIN")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    /**
     * Nomenclature liée à l'élément.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOMENCLATURE_IDNOMENCLATURE", nullable = false, insertable = false, updatable = false)
    private NomenclatureEntite nomenclature;

    /**
     * attr.
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ELEMENT_IDELEMENT", nullable = false, insertable = false, updatable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private List<AttributExterneEntite> attr = new ArrayList<AttributExterneEntite>();

    /**
     * Constructeur.
     */
    public ElementEntite() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "<'" + codeElement + "','" + libelleCourt + "','" + libelleLong + ">";
    }

    /**
     * Accesseur en lecture de l'attribut id.
     *
     * @return id .
     */

    public final Long getId() {
        return id;
    }

    /**
     * Accesseur en écriture de l'attribut id .
     *
     * @param id id à setter.
     */

    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     * Accesseur en lecture de l'attribut codeElement.
     *
     * @return codeElement .
     */

    public final String getCodeElement() {
        return codeElement;
    }

    /**
     * Accesseur en écriture de l'attribut codeElement .
     *
     * @param codeElement codeElement à setter.
     */

    public final void setCodeElement(final String codeElement) {
        this.codeElement = codeElement;
    }

    /**
     * Accesseur en lecture de l'attribut libelleCourt.
     *
     * @return libelleCourt .
     */

    public final String getLibelleCourt() {
        return libelleCourt;
    }

    /**
     * Accesseur en écriture de l'attribut libelleCourt .
     *
     * @param libelleCourt libelleCourt à setter.
     */

    public final void setLibelleCourt(final String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    /**
     * Accesseur en lecture de l'attribut libelleLong.
     *
     * @return libelleLong .
     */

    public final String getLibelleLong() {
        return libelleLong;
    }

    /**
     * Accesseur en écriture de l'attribut libelleLong .
     *
     * @param libelleLong libelleLong à setter.
     */

    public final void setLibelleLong(final String libelleLong) {
        this.libelleLong = libelleLong;
    }

    /**
     * Accesseur en lecture de l'attribut libelleImpression.
     *
     * @return libelleImpression .
     */

    public final String getLibelleImpression() {
        return libelleImpression;
    }

    /**
     * Accesseur en écriture de l'attribut libelleImpression .
     *
     * @param libelleImpression libelleImpression à setter.
     */

    public final void setLibelleImpression(final String libelleImpression) {
        this.libelleImpression = libelleImpression;
    }

    /**
     * Accesseur en lecture de l'attribut dateEffet.
     *
     * @return dateEffet .
     */

    public final Date getDateEffet() {
        return dateEffet;
    }

    /**
     * Accesseur en écriture de l'attribut dateEffet .
     *
     * @param dateEffet dateEffet à setter.
     */

    public final void setDateEffet(final Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Accesseur en lecture de l'attribut dateFin.
     *
     * @return dateFin .
     */

    public final Date getDateFin() {
        return dateFin;
    }

    /**
     * Accesseur en écriture de l'attribut dateFin .
     *
     * @param dateFin dateFin à setter.
     */

    public final void setDateFin(final Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Accesseur en lecture de l'attribut nomenclature.
     *
     * @return nomenclature .
     */

    public final NomenclatureEntite getNomenclature() {
        return nomenclature;
    }

    /**
     * Accesseur en écriture de l'attribut nomenclature .
     *
     * @param nomenclature nomenclature à setter.
     */

    public final void setNomenclature(final NomenclatureEntite nomenclature) {
        this.nomenclature = nomenclature;
    }

    /**
     * Accesseur en lecture de l'attribut attr.
     *
     * @return attr .
     */

    public final List<AttributExterneEntite> getAttr() {
        return attr;
    }

    /**
     * Accesseur en écriture de l'attribut attr .
     *
     * @param attr attr à setter.
     */

    public final void setAttr(final List<AttributExterneEntite> attr) {
        this.attr = attr;
    }

}
