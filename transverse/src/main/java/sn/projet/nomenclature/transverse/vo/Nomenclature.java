package sn.projet.nomenclature.transverse.vo;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.List;

/**
 * Le VO Nomenclature.
 */
@JsonTypeName("Nomenclature")
public class Nomenclature implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6545208996304192665L;

    /**
     * code.
     */
    private String code;

    /**
     * libelle.
     */
    private String libelle;

    /**
     * libelleAttributExterne.
     */
    private String libelleAttributExterne;

    /**
     * Les codes nomenclatures qui sont liés à cette nomenclature.
     */
    private List<String> codeNomenclaturesLies;

    /**
     * Constructeur.
     */
    public Nomenclature() {
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
     * @param code code à setter.
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
     * @param libelle libelle à setter.
     */
    public final void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

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
     * @param libelleAttributExterne libelleAttributExterne à setter.
     */
    public final void setLibelleAttributExterne(final String libelleAttributExterne) {
        this.libelleAttributExterne = libelleAttributExterne;
    }

    /**
     * Retourne les codes nomenclatures qui sont liés à cette nomenclature.
     *
     * @return les codes nomenclatures qui sont liés à cette nomenclature
     */
    public final List<String> getCodeNomenclaturesLies() {
        return codeNomenclaturesLies;
    }

    /**
     * Initialise les codes nomenclatures qui sont liés à cette nomenclature.
     *
     * @param codeNomenclaturesLies les codes nomenclatures qui sont liés à cette nomenclature
     */
    public final void setCodeNomenclaturesLies(final List<String> codeNomenclaturesLies) {
        this.codeNomenclaturesLies = codeNomenclaturesLies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(getCode());
        if (getCodeNomenclaturesLies() != null) {
            sb.append(" => ");
            sb.append(getCodeNomenclaturesLies());
        }
        return sb.toString();
    }
}
