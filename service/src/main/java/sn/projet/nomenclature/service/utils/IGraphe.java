package sn.projet.nomenclature.service.utils;

/**
 * Définit les services pour un graphe.
 * 
 */
public interface IGraphe {

	/**
	 * Retourne le nombre de sommets du graphe.
	 * 
	 * @return le nombre de sommets du graphe
	 */
	int getNbSommet();

	/**
	 * Ajoute un arc orienté entre les sommets <code>sommetDep</code> et <code>sommetArr</code> avec une distance de
	 * <code>distance</code>.
	 * 
	 * @param sommetDep
	 *            le sommet de départ
	 * @param sommetArr
	 *            le sommet d'arrivée
	 * @param distance
	 *            la distance entre les sommets
	 */
	void addArc(int sommetDep, int sommetArr, int distance);

	/**
	 * Retourne la distance entre les sommets <code>sommetDep</code> et <code>sommetArr</code>.
	 * 
	 * @param sommetDep
	 *            le sommet de départ
	 * @param sommetArr
	 *            le sommet d'arrivée
	 * @return la distance entre les sommets <code>sommetDep</code> et <code>sommetArr</code>
	 */
	int getPoids(int sommetDep, int sommetArr);

	/**
	 * Retourne les sommets adjacents du sommet d'index <code>indexSommet</code>.
	 * 
	 * @param indexSommet
	 *            l'index du sommet
	 * @return les sommets voisins du sommet d'index <code>indexSommet</code>
	 */
	int[] rechercherSommetsVoisins(int indexSommet);

	/**
	 * Retourne le label du sommet d'index <code>indexSommet</code>.
	 * 
	 * @param indexSommet
	 *            l'index du sommet à rechercher
	 * @return le label du sommet d'index <code>indexSommet</code>, <code>null</code> si l'index ne correspond à aucun sommet
	 */
	Object getLabel(int indexSommet);

	/**
	 * Retourne l'index qui correspond au sommet <code>labelSommet</code>.
	 * 
	 * @param labelSommet
	 *            le label du sommet à rechercher
	 * @return l'index qui correspond au sommet <code>labelSommet</code>, -1 si le label du sommet est inconnu du graphe
	 */
	int getIndex(Object labelSommet);
}
