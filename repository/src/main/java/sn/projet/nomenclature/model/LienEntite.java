package sn.projet.nomenclature.model;

import sn.projet.nomenclature.transverse.Constantes;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Entité qui mappe la table LIEN.
 */
@Entity
@Table(name = "LIEN", uniqueConstraints = {@UniqueConstraint(columnNames = {"ELEMENT1_IDELEMENT", "ELEMENT2_IDELEMENT"})})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQueries({
        // Requetes de vérification de l'existance d'un lien
        @NamedQuery(name = Constantes.REQUETE_VERIFIER_EXISTANCE_LIEN_INF, query = "select lien from LienEntite as lien "
                + "where lien.element2.codeElement = :codeElement1 and lien.element2.nomenclature.code= :codeNomenclature1 "
                + "and lien.element1.codeElement = :codeElement2 and lien.element1.nomenclature.code= :codeNomenclature2 "
                + "and :dateEffet >= lien.dateEffet and (lien.dateFin is null or :dateEffet <= lien.dateFin))"),
        @NamedQuery(name = Constantes.REQUETE_VERIFIER_EXISTANCE_LIEN_INF_NOMENCLATURE_DIFFERENTES, query = "select lien "
                + "from LienEntite as lien join lien.element1 as element1 where element1.nomenclature.code = :code "
                + "and lien.element2.codeElement = :codeElement1 "
                + "and lien.element2.nomenclature.code= :codeNomenclature1 and lien.element1.codeElement = :codeElement2 "
                + "and lien.element1.nomenclature.code= :codeNomenclature2 and :dateEffet >= lien.dateEffet "
                + "and (lien.dateFin is null or :dateEffet <= lien.dateFin)"),
        @NamedQuery(name = Constantes.REQUETE_VERIFIER_EXISTANCE_LIEN_SUP, query = "select lien from LienEntite as lien "
                + "where lien.element1.codeElement = :codeElement1 and lien.element1.nomenclature.code= :codeNomenclature1 "
                + "and lien.element2.codeElement = :codeElement2 and lien.element2.nomenclature.code= :codeNomenclature2 and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN),
        @NamedQuery(name = Constantes.REQUETE_VERIFIER_EXISTANCE_LIEN_SUP_NOMENCLATURE_DIFFERENTES, query = "select lien "
                + "from LienEntite as lien join lien.element2 as element2 where element2.nomenclature.code = :code "
                + "and lien.element2.codeElement = :codeElement2 and lien.element2.nomenclature.code= :codeNomenclature2 "
                + "and lien.element1.codeElement = :codeElement1 and lien.element1.nomenclature.code= :codeNomenclature1 and "
                + LienEntite.LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN)})
public class LienEntite implements Serializable {

    /**
     * Contrainte : lien compris entre date d'effet et date de fin.
     */
    public static final String LIEN_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN = ":dateEffet >= lien.dateEffet "
            + "and (lien.dateFin is null or :dateEffet <= lien.dateFin)";
    /**
     * Contrainte : L'élément element1 du lien compris entre date d'effet et date de fin.
     */
    public static final String LIEN_ELEMENT1_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN = ":dateEffet >= lien.element1.dateEffet "
            + "and (lien.element1.dateFin is null or :dateEffet <= lien.element1.dateFin)";
    /**
     * Contrainte : L'élément element2 du lien compris entre date d'effet et date de fin.
     */
    public static final String LIEN_ELEMENT2_VALIDE_ENTRE_DATEEFFET_ET_DATEFIN = ":dateEffet >= lien.element2.dateEffet "
            + "and (lien.element2.dateFin is null or :dateEffet <= lien.element2.dateFin)";
    /**
     * Clause SQL AND.
     */
    public static final String AND = " and ";
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3787873600534599785L;
    /**
     * Identifiant technique.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLIEN", columnDefinition = "serial", nullable = false)
    private Long idLien;

    /**
     * Date d'effet du lien.
     */
    @Column(name = "DATEEFFET", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEffet;

    /**
     * Date de fin du lien.
     */
    @Column(name = "DATEFIN")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    /**
     * 1er élément du lien.
     */
    @OneToOne
    @JoinColumn(name = "ELEMENT1_IDELEMENT", nullable = false)
    private ElementEntite element1;

    /**
     * 2eme élément du lien.
     */
    @OneToOne
    @JoinColumn(name = "ELEMENT2_IDELEMENT", nullable = false)
    private ElementEntite element2;

    /**
     * Les attributs du lien.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "LIEN_IDLIEN", nullable = false, insertable = false, updatable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private List<AttributLienEntite> attributsLiens;

    /**
     * Constructeur.
     */
    public LienEntite() {
    }

    /**
     * Accesseur en lecture de l'attribut idLien.
     *
     * @return idLien .
     */
    public final Long getIdLien() {
        return idLien;
    }

    /**
     * Accesseur en écriture de l'attribut idLien .
     *
     * @param idLien idLien à setter.
     */
    public final void setIdLien(final Long idLien) {
        this.idLien = idLien;
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
     * Accesseur en lecture de l'attribut element1.
     *
     * @return element1 .
     */
    public final ElementEntite getElement1() {
        return element1;
    }

    /**
     * Accesseur en écriture de l'attribut element1 .
     *
     * @param element1 element1 à setter.
     */
    public final void setElement1(final ElementEntite element1) {
        this.element1 = element1;
    }

    /**
     * Accesseur en lecture de l'attribut element2.
     *
     * @return element2 .
     */
    public final ElementEntite getElement2() {
        return element2;
    }

    /**
     * Accesseur en écriture de l'attribut element2 .
     *
     * @param element2 element2 à setter.
     */
    public final void setElement2(final ElementEntite element2) {
        this.element2 = element2;
    }

    /**
     * Accesseur en lecture de l'attribut attributsLiens.
     *
     * @return les attributs du lien.
     */
    public final List<AttributLienEntite> getAttributsLiens() {
        return attributsLiens;
    }

    /**
     * Accesseur en écriture de l'attribut attributsLiens .
     *
     * @param attributsLiens attributsLiens à setter.
     */
    public final void setAttributsLiens(final List<AttributLienEntite> attributsLiens) {
        this.attributsLiens = attributsLiens;
    }
}
