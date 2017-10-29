package sn.projet.nomenclature.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

import static sn.projet.nomenclature.transverse.Constantes.LONGUEUR_NOM_ATTRIBUT_LIEN;
import static sn.projet.nomenclature.transverse.Constantes.LONGUEUR_VALEUR_ATTRIBUT_LIEN;

/**
 * Entité qui mappe la table ATTRIBUTLIEN.
 * 
 */
@Entity
@Table(name = "ATTRIBUTLIEN", uniqueConstraints = {@UniqueConstraint(columnNames = {"LIEN_IDLIEN", "NOMATTRIBUTLIEN" }) })
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AttributLienEntite implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 2621277473560131512L;

	/** Identifiant technique. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDATTRIBUTLIEN", columnDefinition = "serial", nullable = false)
	private Long idAttribut;

	/** Libellé de l'attribut. */
	@Column(name = "NOMATTRIBUTLIEN", nullable = false, length = LONGUEUR_NOM_ATTRIBUT_LIEN)
	private String libelle;

	/** valeur de l'attribut. */
	@Column(name = "VALEURATTRIBUTLIEN", nullable = false, length = LONGUEUR_VALEUR_ATTRIBUT_LIEN)
	private String valeur;

	/** élément parent de l'attribut. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIEN_IDLIEN", nullable = false, insertable = false, updatable = false)
	private LienEntite element;

	/**
	 * Accesseur en lecture de l'attribut idAttribut.
	 * 
	 * @return idAttribut .
	 */
	public final Long getIdAttribut() {
		return idAttribut;
	}

	/**
	 * Accesseur en écriture de l'attribut idAttribut .
	 * 
	 * @param idAttribut
	 *            idAttribut à setter.
	 */
	public final void setIdAttribut(final Long idAttribut) {
		this.idAttribut = idAttribut;
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
	 * Accesseur en lecture de l'attribut valeur.
	 * 
	 * @return valeur .
	 */
	public final String getValeur() {
		return valeur;
	}

	/**
	 * Accesseur en écriture de l'attribut valeur .
	 * 
	 * @param valeur
	 *            valeur à setter.
	 */
	public final void setValeur(final String valeur) {
		this.valeur = valeur;
	}

	/**
	 * Accesseur en lecture de l'attribut element.
	 * 
	 * @return element .
	 */
	public final LienEntite getElement() {
		return element;
	}

	/**
	 * Accesseur en écriture de l'attribut element .
	 * 
	 * @param element
	 *            element à setter.
	 */
	public final void setElement(final LienEntite element) {
		this.element = element;
	}
}
