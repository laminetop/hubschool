package sn.projet.exception;

import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * Exception de type technique.
 * 
 * @see java.lang.RuntimeException
 */
@XmlType(namespace = "urn://exception.andado.com")
@WebFault(name = "TechniqueException", targetNamespace = "urn://exception.andado.com")
public class TechniqueException extends RuntimeException implements IException, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -8891686784036070875L;

	/** Code de l'exception. */
	private String code = null;

	/**
	 * Constructeur par défaut.
	 */
	public TechniqueException() {
		super("this is a technical exception raised for example purpose");
	}

	/**
	 * Construit une exeption technique avec un message d'erreur.
	 * 
	 * @param pMessage
	 *            le message d'erreur
	 */
	public TechniqueException(final String pMessage) {
		super(pMessage);
	}

	/**
	 * Construit une exeption technique avec un message d'erreur et l'exception d'origine.
	 * 
	 * @param pMessage
	 *            le message d'erreur
	 * @param pException
	 *            l'exception d'origine
	 */
	public TechniqueException(final String pMessage, final Throwable pException) {
		super(pMessage, pException);
	}

	/**
	 * Construit une exeption technique avec l'exception d'origine.
	 * 
	 * @param pException
	 *            l'exception d'origine
	 */
	public TechniqueException(final Throwable pException) {
		super(pException);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getCode() {
		return this.code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Retourne une description de l'exception technique.
	 * 
	 * @return une description de l'exception technique
	 */
	@Override
	public final String toString() {
		return "sn.projet.ct.commun.transverse.exception.TechniqueException - " + this.getCode() + " - " + this.getMessage();
	}

}
