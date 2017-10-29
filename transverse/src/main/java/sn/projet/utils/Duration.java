package sn.projet.utils;

import java.math.BigDecimal;

/**
 * Classe utilitaire pour representer une durée en ans, mois, jour. La classe Calendar ne pouvant poas etre utilisée pour ce
 * besoin car les champs sont renormalisés si l'on accede à l'un deux par in get( nom champ).
 * 
 */
public class Duration implements Comparable < Duration > {

	/** nombre d'années. **/
	private int ans;
	/** nombre de mois. **/
	private int mois;
	/** nombre de jours. **/
	private BigDecimal jour;

	/**
	 * Crée une Duration avec les valeurs indiquées.
	 * 
	 * @param ans
	 *            les années
	 * @param mois
	 *            les mois
	 * @param jour
	 *            les jours
	 */
	public Duration(final int ans, final int mois, final BigDecimal jour) {
		this.ans = ans;
		this.mois = mois;
		this.jour = jour;
	}

	/**
	 * Accesseur en ecriture.
	 * 
	 * @param ans
	 *            les années
	 */
	public final void setAns(final int ans) {
		this.ans = ans;
	}

	/**
	 * Accesseur en lecture.
	 * 
	 * @return ans
	 */
	public final int getAns() {
		return ans;
	}

	/**
	 * Accesseur en ecriture.
	 * 
	 * @param mois
	 *            les mois
	 */
	public final void setMois(final int mois) {
		this.mois = mois;
	}

	/**
	 * Accesseur en lecture.
	 * 
	 * @return mois
	 */
	public final int getMois() {
		return mois;
	}

	/**
	 * Accesseur en ecriture.
	 * 
	 * @param jour
	 *            les jours
	 */
	public final void setJour(final BigDecimal jour) {
		this.jour = jour;
	}

	/**
	 * Accesseur en lecture.
	 * 
	 * @return jour
	 */
	public final BigDecimal getJour() {
		return jour;
	}

	/**
	 * Methode de l'interface Comparable. Compare cet objet avec une autre Duration
	 * 
	 * @param ref
	 *            l'objet avec lequel comparer
	 * @return 0 si égal, 1 ou -1 sinon
	 */
	@Override
	public final int compareTo(final Duration ref) {

		int result = 0;

		if (this.ans > ref.getAns()) {
			return +1;
		} else if (this.ans < ref.getAns()) {
			return -1;
		} else if (this.mois > ref.getMois()) {
			return +1;
		} else if (this.mois < ref.getMois()) {
			return -1;
		} else if (this.jour.compareTo(ref.getJour()) > 0) {
			return +1;
		} else if (this.jour.compareTo(ref.getJour()) < 0) {
			return -1;
		}

		return result;
	}
}
