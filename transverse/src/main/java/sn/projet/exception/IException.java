package sn.projet.exception;

/**
 * Interface des exceptions.
 */
public interface IException {

	/**
	 * Retourne le code de l'exception.
	 * 
	 * @return le code de l'exception
	 */
	String getCode();

	/**
	 * Définit le code de l'exception.
	 * 
	 * @param code
	 *            e code de l'exception
	 */
	void setCode(String code);

}
