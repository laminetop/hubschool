package sn.projet.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Factory des exceptions.
 * 
 */

public final class ExceptionFactory implements IExceptionFactory {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionFactory.class);

	/**
	 * Source des messages de l'application.
	 * 
	 */
	@Autowired
	protected MessageSource messageSource;

	/**
	 * Retourne une exception fonctionnelle depuis l'exception <code>e</code>avec le code <code>code</code> et le message
	 * <code>message</code>.
	 * 
	 * @param code
	 *            le code de l'exception
	 * @param message
	 *            le message de l'exception
	 * @param e
	 *            l'exception d'origine (nullable)
	 * @return une exception fonctionnelle depuis l'exception <code>e</code>avec le code <code>code</code> et le message
	 *         <code>message</code>
	 */
	private FonctionnelleException getFonctionnelleException(final String code, final String message, final Throwable e) {
		FonctionnelleException ret = null;
		if (e != null) {
			ret = new FonctionnelleException(message, e);
		} else {
			ret = new FonctionnelleException(message);
		}
		ret.setCode(code);
		return ret;
	}

	/**
	 * Retourne le message qui a le code <code>code</code> en remplaçant les variables du messages par les valeurs du paramètre
	 * <code>args</code>.
	 * 
	 * @param code
	 *            le code du message à retourner
	 * @param args
	 *            les valeurs des arguments du message
	 * @return le message qui a le code <code>code</code> en remplaçant les variables du messages par les valeurs du paramètre
	 *         <code>args</code>
	 */
	private String getMessage(final String code, final Object[] args) {
		return messageSource.getMessage(code, args, new Locale(""));
	}

	/**
	 * Retourne une exception technique depuis l'exception <code>e</code>avec le code <code>code</code> et le message
	 * <code>message</code>.
	 * 
	 * @param code
	 *            le code de l'exception
	 * @param message
	 *            le message de l'exception
	 * @param e
	 *            l'exception d'origine (nullable)
	 * @return une exception technique depuis l'exception <code>e</code>avec le code <code>code</code> et le message
	 *         <code>message</code>
	 */
	private TechniqueException getTechnicalException(final String code, final String message, final Throwable e) {
		TechniqueException ret = null;
		if (e != null) {
			ret = new TechniqueException(message, e);
		} else {
			ret = new TechniqueException(message);
		}
		ret.setCode(code);
		return ret;
	}

	/**
	 * Trace en niveau ERROR le message qui a le code <code>code</code>.
	 * 
	 * @param code
	 *            le code du message à tracer
	 * @param args
	 *            les valeurs des arguments du message
	 * @return le message tracé
	 */
	private String logMessage(final String code, final Object[] args, final String typeErreur) {
		String ret = null;
		ret = getMessage(code, args);
		LOGGER.error("{} [{}] {}", new String[] {typeErreur, code, ret });
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reThrowFonctionnalException(final String code, final FonctionnelleException ex, final String... codes)
		throws FonctionnelleException {
		if (codes != null && codes.length > 0) {
			for (String codeifs : codes) {
				if (codeifs.compareTo(ex.getCode()) == 0) {
					throwFonctionnalException(code, ex);
				}
			}
		} else {
			throwFonctionnalException(code, ex);
		}
	}

	/**
	 * Accesseur en écriture de l'attribut messageSource.
	 * 
	 * @param messageSource
	 *            l'attribut messageSource à setter
	 */
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwFonctionnalException(final String code) throws FonctionnelleException {
		throwFonctionnalException(code, (String[]) null, (Throwable) null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwFonctionnalException(final String code, final String arg) throws FonctionnelleException {
		throwFonctionnalException(code, new String[] {arg }, (Throwable) null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwFonctionnalException(final String code, final String arg, final Throwable cause)
		throws FonctionnelleException {
		throwFonctionnalException(code, new String[] {arg }, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwFonctionnalException(final String code, final String[] arg) throws FonctionnelleException {
		throwFonctionnalException(code, arg, (Throwable) null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwFonctionnalException(final String code, final String[] arg, final Throwable cause)
		throws FonctionnelleException {
		String msg = logMessage(code, arg, "ERREUR FONC");
		FonctionnelleException e = getFonctionnelleException(code, msg, cause);
		throw e;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwFonctionnalException(final String code, final Throwable cause) throws FonctionnelleException {
		throwFonctionnalException(code, (String[]) null, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final String code) {
		throwTechnicalException(code, (String[]) null, (Throwable) null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final String code, final String arg) {
		throwTechnicalException(code, new String[] {arg }, (Throwable) null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final String code, final String arg, final Throwable cause) {
		throwTechnicalException(code, new String[] {arg }, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final String code, final String[] arg) {
		throwTechnicalException(code, arg, (Throwable) null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final String code, final String[] arg, final Throwable cause) {
		String msg = logMessage(code, arg, "ERREUR TECH");
		TechniqueException e = getTechnicalException(code, msg, cause);
		throw e;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final String code, final Throwable cause) {
		throwTechnicalException(code, (String[]) null, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void throwTechnicalException(final Throwable cause) {
		throw new TechniqueException("Erreur Interne : " + cause.getMessage(), cause);
	}
}
