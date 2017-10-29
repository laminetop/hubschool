package sn.projet.nomenclature.model;

import sn.projet.nomenclature.transverse.Constantes;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entité qui mappe la table ATTRIBUTEXTERNE.
 * 
 */
@Entity
@Table(name = "ATTRIBUTEXTERNE", uniqueConstraints = {@UniqueConstraint(columnNames = {"ELEMENT_IDELEMENT", "NOMATTRIBUTEXTERNE" }) })
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AttributExterneEntite implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 8375327098828373365L;

	/** Identifiant technique. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDATTRIBUTEXTERNE", columnDefinition = "serial", nullable = false)
	private Long idAttribut;

	/** Libellé de l'attribut. */
	@Column(name = "NOMATTRIBUTEXTERNE", nullable = false, length = Constantes.LONGUEUR_NOM_ATTRIBUT_EXTERNE)
	private String libelle;

	/** valeur de l'attribut. */
	@Column(name = "VALEURATTRIBUTEXTERNE", nullable = false, length = Constantes.LONGUEUR_VALEUR_ATTRIBUT_EXTERNE)
	private String valeur;

	/** élément parent de l'attribut. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELEMENT_IDELEMENT", nullable = false, insertable = false, updatable = false)
	private ElementEntite element;

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

	public final ElementEntite getElement() {
		return element;
	}

	/**
	 * Accesseur en écriture de l'attribut element .
	 * 
	 * @param element
	 *            element à setter.
	 */

	public final void setElement(final ElementEntite element) {
		this.element = element;
	}

}
