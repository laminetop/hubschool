package sn.projet.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Factory des exceptions techniques.<br/>
 * Utilisée seulement par les Composants Techniques via l'appel de la méthode {@link #getInstance(Class)}.
 * 
 */
public final class TechniqueExceptionFactory {

	/**
	 * Retourne une exception factory pour la classe passée an paramètre.
	 * 
	 * @param clz
	 *            la classe pour laquelle on souhaite instancier une exception factory.
	 * @return une exception factory pour la classe passée an paramètre
	 */
	public static TechniqueExceptionFactory getInstance(final Class < ? > clz) {
		TechniqueExceptionFactory techniqueExceptionFactory = new TechniqueExceptionFactory();
		techniqueExceptionFactory.init(clz);
		return techniqueExceptionFactory;
	}

	/** Logger. */
	private Logger logger = null;

	/** Le ressource bundle contenant les messages d'erreurs. */
	@Autowired
	protected MessageSource messageSource;

	/**
	 * Constructeur par défaut.
	 */
	private TechniqueExceptionFactory() {
	}

	/**
	 * Retourne le message de l'exception technique dont le code est passé en paramètre.
	 * 
	 * @param code
	 *            le code de l'exception technique dont on souhaite obtenir le message
	 * @param args
	 *            les paramètres du message de l'exception technique
	 * @return le message de l'exception technique dont le code est passé en paramètre
	 */
	private String getMessage(final String code, final Object[] args) {
		return messageSource.getMessage(code, args, "Erreure technique", Locale.FRANCE);
	}

	/**
	 * Retourne une exception technique avec le code, le message et l'exception passés en paramètres.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param message
	 *            le message de l'exception technique
	 * @param ex
	 *            l'exception d'origine
	 * 
	 * @return une exception technique avec le code, le message et l'exception passés en paramètres.
	 */
	private TechniqueException getTechnicalException(final String code, final String message, final Throwable ex) {
		TechniqueException techniqueException = null;
		if (ex != null) {
			techniqueException = new TechniqueException(message, ex);
		} else {
			techniqueException = new TechniqueException(message);
		}
		techniqueException.setCode(code);
		return techniqueException;
	}

	/**
	 * Charge les messages des exceptions techniques du CT auquel est rattaché la classe passé en paramètre.<BR>
	 * Initialise le logger de l'exception factory.
	 * 
	 * @param clz
	 *            la classe qui instancie l'exception factory.
	 */
	private void init(final Class < ? > clz) {
		logger = LoggerFactory.getLogger(clz);
	}

	/**
	 * Trace en niveau ERROR le message qui a le code <code>code</code>.
	 * 
	 * @param code
	 *            le code du message à tracer
	 * @param args
	 *            les valeurs des arguments du message
	 * @param cause
	 *            l'exception d'origine
	 * @return le message tracé
	 */
	public String logMessage(final String code, final Object[] args, final Throwable cause) {
		String ret = null;
		ret = getMessage(code, args);
		if (cause != null) {
			logger.error("ERREUR TECH" + " " + code + " - " + ret, cause);
		} else {
			logger.error("{} {} - {}", new String[] {"ERREUR TECH ", code, ret });
		}
		return ret;
	}

	/**
	 * Génère une exception technique avec le message identifié par le code <code>code</code>.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 */
	public void throwTechnicalException(final String code) {
		throwTechnicalException(code, (String[]) null, (Throwable) null);
	}

	/**
	 * Génère une exception technique avec le message identifié par le code <code>code</code>.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            la valeur de l'argument du message
	 */
	public void throwTechnicalException(final String code, final String arg) {
		throwTechnicalException(code, new String[] {arg }, (Throwable) null);
	}

	/**
	 * Génère une exception technique avec le message identifié par le code <code>code</code> et l'exception d'origine
	 * <code>cause</code>.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            la valeur de l'argument du message
	 * @param cause
	 *            l'exception d'origine
	 */
	public void throwTechnicalException(final String code, final String arg, final Throwable cause) {
		throwTechnicalException(code, new String[] {arg }, cause);
	}

	/**
	 * Génère une exception technique avec le message identifié par le code <code>code</code>.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            les valeurs des arguments du message
	 */
	public void throwTechnicalException(final String code, final String[] arg) {
		throwTechnicalException(code, arg, (Throwable) null);
	}

	/**
	 * Génère une exception technique avec le message identifié par le code <code>code</code> et l'exception d'origine
	 * <code>cause</code>.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            les valeurs des arguments du message
	 * @param cause
	 *            l'exception d'origine
	 */
	public void throwTechnicalException(final String code, final String[] arg, final Throwable cause) {
		String msg = logMessage(code, arg, cause);
		TechniqueException e = getTechnicalException(code, msg, cause);
		throw e;
	}

	/**
	 * Génère une exception technique avec le message identifié par le code <code>code</code> et l'exception d'origine
	 * <code>cause</code>.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param cause
	 *            l'exception d'origine
	 */
	public void throwTechnicalException(final String code, final Throwable cause) {
		throwTechnicalException(code, (String[]) null, cause);
	}

	/**
	 * Génère une exception technique avec l'exception d'origine <code>cause</code>.
	 * 
	 * @param cause
	 *            l'exception d'origine
	 */
	public void throwTechnicalException(final Throwable cause) {
		throw new TechniqueException("Erreur Interne : " + cause.getMessage(), cause);
	}
}
