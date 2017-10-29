package sn.projet.nomenclature.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static sn.projet.nomenclature.transverse.Constantes.*;

/**
 * Entité qui mappe la table NOMENCLATURE.
 * 
 */
@Entity
@Table(name = "NOMENCLATURE")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQueries({
	@NamedQuery(name = REQUETE_RECHERCHER_NOMENCLATURE_PAR_CODE, query = "select nomenclature "
		+ "from NomenclatureEntite as nomenclature where nomenclature.code = :" + PARAM_CODENOMENCLATURE),
	@NamedQuery(name = REQUETE_RECHERCHER_NOMENCLATURE, query = "select nomenclature from NomenclatureEntite as nomenclature"),
	// Recherche les nomenclatures et les nomenclature liées
	@NamedQuery(name = REQUETE_RECHERCHER_ALL_NOMENS_ET_LIEN_ENTRE_NOMENS, query = "select n, ln1 "
		+ "from NomenclatureEntite n left outer join n.nomenclature1 ln1") })
public class NomenclatureEntite implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 5448612791837239259L;

	/** Le nom de la colonne IDNOMENCLATURE. */
	private static final String NOM_COLONNE_IDNOMENCLATURE = "IDNOMENCLATURE";

	/** Identifiant technique. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = NOM_COLONNE_IDNOMENCLATURE, columnDefinition = "serial", nullable = false)
	private Long id;

	/** Code de la nomenclature. */
	@Column(name = "CODENOMENCLATURE", length = LONGUEUR_CODE, nullable = false, unique = true)
	private String code;

	/** libellé de la nomenclature. */
	@Column(name = "LIBELLENOMENCLATURE", length = LONGUEUR_NOM_ATTRIBUT_EXTERNE, nullable = false)
	private String libelle;

	/** libellé de l'attribut externe. */
	@Column(name = "LIBELLEATTRIBUTEXTERNE", length = LONGUEUR_LIBELLE_ATTREXT, nullable = true)
	private String libelleAttributExterne;

	/** Liste d'éléments de la nomenclature. */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOMENCLATURE_IDNOMENCLATURE", nullable = false, insertable = false, updatable = false)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	private List < ElementEntite > elements = new ArrayList < ElementEntite >();

	/**
	 * Les nomenclatures liées à <code>this</code>.
	 */
	@OneToMany
	@JoinTable(name = "LIENNOMENCLATURE", // Jointure sur la table "LIEN_NOMENCLATURE" avec la colonne
	joinColumns = {@JoinColumn(name = "NOMENCLATURE1_IDNOMENCLATURE", referencedColumnName = NOM_COLONNE_IDNOMENCLATURE) }, // NOMENCLATURE1
	inverseJoinColumns = {@JoinColumn(name = "NOMENCLATURE2_IDNOMENCLATURE", referencedColumnName = NOM_COLONNE_IDNOMENCLATURE) })
	private List < NomenclatureEntite > nomenclature1;

	/**
	 * Les nomenclatures liées à <code>this</code>.
	 */
	@OneToMany
	@JoinTable(name = "LIENNOMENCLATURE", // Jointure sur la table "LIEN_NOMENCLATURE" avec la colonne
	joinColumns = {@JoinColumn(name = "NOMENCLATURE2_IDNOMENCLATURE", referencedColumnName = NOM_COLONNE_IDNOMENCLATURE) }, // NOMENCLATURE2
	inverseJoinColumns = {@JoinColumn(name = "NOMENCLATURE1_IDNOMENCLATURE", referencedColumnName = NOM_COLONNE_IDNOMENCLATURE) })
	private List < NomenclatureEntite > nomenclature2;

	/**
	 * Retourne les nomenclatures liées à <code>this</code>.
	 * 
	 * @return les nomenclatures liées à <code>this</code>
	 */
	public final List < NomenclatureEntite > getNomenclature1() {
		return nomenclature1;
	}

	/**
	 * Initialise les nomenclatures liées à <code>this</code>.
	 * 
	 * @param nomenclature1
	 *            les nomenclatures liées à <code>this</code>.
	 */
	public final void setNomenclature1(final List < NomenclatureEntite > nomenclature1) {
		this.nomenclature1 = nomenclature1;
	}

	/**
	 * Retourne les nomenclatures liées à <code>this</code>.
	 * 
	 * @return les nomenclatures liées à <code>this</code>
	 */
	public final List < NomenclatureEntite > getNomenclature2() {
		return nomenclature2;
	}

	/**
	 * Initialise les nomenclatures liées à <code>this</code>.
	 * 
	 * @param nomenclature2
	 *            les nomenclatures liées à <code>this</code>.
	 */
	public final void setNomenclature2(final List < NomenclatureEntite > nomenclature2) {
		this.nomenclature2 = nomenclature2;
	}

	/**
	 * Constructeur.
	 */
	public NomenclatureEntite() {
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
	 * @param id
	 *            id à setter.
	 */

	public final void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Accesseur en lecture de l'attribut code.
	 * 
	 * @return code .
	 */

	public final String getCode() {
		return code;
	}

	/**
	 * Accesseur en écriture de l'attribut code .
	 * 
	 * @param code
	 *            code à setter.
	 */

	public final void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Accesseur en lecture de l'attribut libelle.
	 * 
	 * @return libelle .
	 */

	public final String getLibelle() {
		return libelle;
	}

	/**
	 * Accesseur en écriture de l'attribut libelle .
	 * 
	 * @param libelle
	 *            libelle à setter.
	 */

	public final void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Accesseur en lecture de l'attribut libelleSuperieur.
	 * 
	 * @return libelleSuperieur .
	 */

	// public final String getLibelleSuperieur() {
	// return libelleSuperieur;
	// }

	/**
	 * Accesseur en écriture de l'attribut libelleSuperieur .
	 * 
	 * @param libelleSuperieur
	 *            libelleSuperieur à setter.
	 */

	// public final void setLibelleSuperieur(final String libelleSuperieur) {
	// this.libelleSuperieur = libelleSuperieur;
	// }

	/**
	 * Accesseur en lecture de l'attribut libelleInferieur.
	 * 
	 * @return libelleInferieur .
	 */

	// public final String getLibelleInferieur() {
	// return libelleInferieur;
	// }

	/**
	 * Accesseur en écriture de l'attribut libelleInferieur .
	 * 
	 * @param libelleInferieur
	 *            libelleInferieur à setter.
	 */

	// public final void setLibelleInferieur(final String libelleInferieur) {
	// this.libelleInferieur = libelleInferieur;
	// }

	/**
	 * Accesseur en lecture de l'attribut libelleAttributExterne.
	 * 
	 * @return libelleAttributExterne .
	 */

	public final String getLibelleAttributExterne() {
		return libelleAttributExterne;
	}

	/**
	 * Accesseur en écriture de l'attribut libelleAttributExterne .
	 * 
	 * @param libelleAttributExterne
	 *            libelleAttributExterne à setter.
	 */

	public final void setLibelleAttributExterne(final String libelleAttributExterne) {
		this.libelleAttributExterne = libelleAttributExterne;
	}

	/**
	 * Accesseur en lecture de l'attribut elements.
	 * 
	 * @return elements .
	 */

	public final List < ElementEntite > getElements() {
		return elements;
	}

	/**
	 * Accesseur en écriture de l'attribut elements .
	 * 
	 * @param elements
	 *            elements à setter.
	 */

	public final void setElements(final List < ElementEntite > elements) {
		this.elements = elements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder(getCode());
		sb.append(" | ");
		sb.append(getLibelle());
		return sb.toString();
	}
}
