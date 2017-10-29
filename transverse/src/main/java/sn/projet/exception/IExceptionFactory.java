package sn.projet.exception;

/**
 * Factory des exception.
 */
public interface IExceptionFactory {

	/**
	 * Relance une exception fonctionnelle avec un nouveau code si le code de l'exception d'origine appartient à une liste
	 * définie.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @param ex
	 *            l'exception d'origine
	 * @param codes
	 *            la liste des codes de l'exception d'origine permettant de relancer l'exception. Si <code>codes</code> est vide
	 *            ou nulle, l'exception d'origine est automatiquement relancée.
	 * 
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void reThrowFonctionnalException(String code, FonctionnelleException ex, String... codes) throws FonctionnelleException;

	/**
	 * Lance une exception fonctionnelle.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void throwFonctionnalException(String code) throws FonctionnelleException;

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @param arg
	 *            l'argument du message de l'exception technique
	 * 
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void throwFonctionnalException(String code, String arg) throws FonctionnelleException;

	/**
	 * Lance une exception fonctionnelle.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @param arg
	 *            l'argument du message de l'exception technique
	 * @param ex
	 *            l'exception d'origine
	 * 
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void throwFonctionnalException(String code, String arg, Throwable ex) throws FonctionnelleException;

	/**
	 * Lance une exception fonctionnelle.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @param arg
	 *            les arguments du message de l'exception technique
	 * 
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void throwFonctionnalException(String code, String[] arg) throws FonctionnelleException;

	/**
	 * Lance une exception fonctionnelle.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @param arg
	 *            les arguments du message de l'exception technique
	 * @param ex
	 *            l'exception d'origine
	 * 
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void throwFonctionnalException(String code, String[] arg, Throwable ex) throws FonctionnelleException;

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception fonctionnelle
	 * @param ex
	 *            l'exception d'origine
	 * 
	 * @throws FonctionnelleException
	 *             une exception fonctionnelle
	 */
	void throwFonctionnalException(String code, Throwable ex) throws FonctionnelleException;

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 */
	void throwTechnicalException(String code);

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            de l'exception pour le message
	 */
	void throwTechnicalException(String code, String arg);

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            l'argument du message de l'exception technique
	 * @param ex
	 *            l'exception d'origine
	 */
	void throwTechnicalException(String code, String arg, Throwable ex);

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            les arguments du message de l'exception technique
	 */
	void throwTechnicalException(String code, String[] arg);

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param arg
	 *            les arguments du message de l'exception technique
	 * @param ex
	 *            l'exception d'origine
	 */
	void throwTechnicalException(String code, String[] arg, Throwable ex);

	/**
	 * Lance une exception.
	 * 
	 * @param code
	 *            le code de l'exception technique
	 * @param ex
	 *            l'exception d'origine
	 */
	void throwTechnicalException(String code, Throwable ex);

	/**
	 * Lance une exception.
	 * 
	 * @param ex
	 *            l'exception d'origine
	 */
	void throwTechnicalException(Throwable ex);

}
