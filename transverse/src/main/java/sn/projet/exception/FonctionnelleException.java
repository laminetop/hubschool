package sn.projet.exception;

import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * Exception de type fonctionnelle.
 * 
 * @see java.lang.Exception
 */
@XmlType(namespace = "urn://exception.andado.com")
@WebFault(name = "FonctionnelleException", targetNamespace = "urn://exception.andado.com")
public class FonctionnelleException extends Exception implements IException, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1009828101233563103L;

	/** Code de l'exception. */
	private String code = null;

	/**
	 * Constructeur par défaut.
	 */
	public FonctionnelleException() {
		super("this is a functional exception raised for exemple purpose");
	}

	/**
	 * Construit une exeption fonctionnelle avec un message d'erreur.
	 * 
	 * @param pMessage
	 *            le message d'erreur
	 */
	public FonctionnelleException(final String pMessage) {
		super(pMessage);
	}

	/**
	 * Construit une exeption fonctionnelle avec un message d'erreur et l'exception d'origine.
	 * 
	 * @param pMessage
	 *            le message d'erreur
	 * @param pException
	 *            l'exception d'origine
	 */
	public FonctionnelleException(final String pMessage, final Throwable pException) {
		super(pMessage, pException);
	}

	/**
	 * Construit une exeption fonctionnelle avec l'exception d'origine.
	 * 
	 * @param pException
	 *            l'exception d'origine
	 */
	public FonctionnelleException(final Throwable pException) {
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
	 * Retourne une description de l'exception fonctionnelle.
	 * 
	 * @return une description de l'exception fonctionnelle
	 */
	@Override
	public final String toString() {
		return "sn.projet.ct.commun.transverse.exception.FonctionnelleException - " + this.getCode() + " - " + this.getMessage();
	}

}
