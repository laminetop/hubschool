package sn.projet.nomenclature.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe utilitaire qui permet de manipuler des graphes.
 * 
 */
public final class Dijkstra {

	/** Le loggeur. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Dijkstra.class);

	/**
	 * Constructeur.
	 */
	private Dijkstra() {
	}

	/**
	 * Retourne les prédécesseurs les plus proches pour chaque sommet.
	 * 
	 * @param graphe
	 *            le graphe
	 * @param sommetDebut
	 *            le sommet source à partir duquel le chemin sera calculé
	 * @return les prédécesseurs les plus proches pour chaque sommet
	 */
	public static int[] djikstra(final IGraphe graphe, final int sommetDebut) {
		if (graphe == null) {
			LOGGER.error("Impossible de calculer les prédécesseurs car le graphe est nul");
			return null;
		}
		if (graphe.getLabel(sommetDebut) == null) {
			LOGGER.error("Impossible de calculer les prédécesseurs car le sommet source " + sommetDebut
				+ " n'appartient pas au graphe");

			return new int[0];
		}

		// La distance la plus proche depuis le sommet de départ
		final int[] distancecc = new int[graphe.getNbSommet()];
		// Les prédecesseurs les plus proches pour chaque sommet
		final int[] predecesseurs = new int[graphe.getNbSommet()];
		// Marque chaque sommet qui a été visité
		final boolean[] sommetsMarques = new boolean[graphe.getNbSommet()];

		// Initialisation
		for (int i = 0; i < distancecc.length; i++) {
			distancecc[i] = Integer.MAX_VALUE;
		}
		distancecc[sommetDebut] = 0;

		for (int i = 0; i < predecesseurs.length; i++) {
			predecesseurs[i] = -1; // Pas de prédecesseurs
		}

		int i = 0;
		boolean existeChemin = true;
		while (i < distancecc.length && existeChemin) {

			final int prochainSommet = extraireMin(distancecc, sommetsMarques);
			if (prochainSommet != -1) {
				LOGGER.trace("Sommet le plus proche [{}] avec une distance de [{}]",
					new Object[] {graphe.getLabel(prochainSommet), distancecc[prochainSommet] });
				sommetsMarques[prochainSommet] = true;

				final int[] sommetsVoisins = graphe.rechercherSommetsVoisins(prochainSommet);
				for (int j = 0; j < sommetsVoisins.length; j++) {
					final int nouveauVoisin = sommetsVoisins[j];
					LOGGER.trace("Voisins de [{}] = [{}], visité?[{}]",
						new Object[] {graphe.getLabel(prochainSommet), graphe.getLabel(nouveauVoisin),
							sommetsMarques[nouveauVoisin] });

					final int d = distancecc[prochainSommet] + graphe.getPoids(prochainSommet, nouveauVoisin);
					if (d < distancecc[nouveauVoisin]) {
						distancecc[nouveauVoisin] = d;
						predecesseurs[nouveauVoisin] = prochainSommet;
					}
				}
			} else {
				LOGGER.trace("Il n'y a plus de chemin");
				existeChemin = false;
			}
			i++;
		}

		return predecesseurs;
	}

	/**
	 * Retourne les index des sommets les plus proches.
	 * 
	 * @param distancecc
	 *            les distances la plus proche depuis le sommet de départ
	 * @param marque
	 *            la listes des sommets marqués et non marqués
	 * @return l'index du sommet le plus proche et non marqué, -1 sinon
	 */
	public static int extraireMin(final int[] distancecc, final boolean[] marque) {
		int min = Integer.MAX_VALUE;
		int indexSommetLePlusProche = 0;
		boolean sommetLePlusProcheTrouve = false;

		for (int i = 0; i < distancecc.length; i++) {
			if (!marque[i] && distancecc[i] < min) {
				indexSommetLePlusProche = i;
				min = distancecc[i];
				sommetLePlusProcheTrouve = true;
			}
		}

		return sommetLePlusProcheTrouve ? indexSommetLePlusProche : -1;
	}

	/**
	 * Retourne le chemin le plus court entre les sommets <code>sommetSource</code> et <code>sommetFin</code>.
	 * 
	 * @param graphe
	 *            le graphe où calculer le chemin le plus court
	 * @param indexSommetSource
	 *            l'index du sommet de départ
	 * @param indexSommetFin
	 *            l'index du sommet d'arrivée
	 * @return la liste des sommets qui représente le chemin le plus court
	 */
	public static List < Object > rechercherLePlusCourtChemin(final IGraphe graphe, final int indexSommetSource,
		final int indexSommetFin) {
		if (graphe == null) {
			LOGGER.error("Impossible de calculer le plus court chemin car le graphe est nul");

			return null;
		}
		if (graphe.getLabel(indexSommetSource) == null || graphe.getLabel(indexSommetFin) == null) {

			LOGGER.error("Impossible de calculer le plus court chemin car le sommet source " + indexSommetSource
				+ " ou le sommet de fin " + indexSommetFin + " n'appartiennent pas au graphe");

			return null;
		}

		LOGGER.trace("Calcul du plus court chemin entre [{}] et [{}]",
			new Object[] {graphe.getLabel(indexSommetSource), graphe.getLabel(indexSommetFin) });

		final int[] pred = djikstra(graphe, indexSommetSource);
		final List < Object > cheminpc = rechercherLePlusCourtChemin(graphe, pred, indexSommetSource, indexSommetFin);

		if (cheminpc != null) {
			LOGGER.trace("Chemin le plus court entre [{}] et [{}] = {}",
				new Object[] {graphe.getLabel(indexSommetSource), graphe.getLabel(indexSommetFin), cheminpc });
		}

		return cheminpc;
	}

	/**
	 * Retourne le chemin le plus court entre les sommets <code>sommetSource</code> et <code>sommetFin</code>.
	 * 
	 * @param graphe
	 *            le graphe où calculer le chemin le plus court
	 * @param labelSommetSource
	 *            le label du sommet de départ
	 * @param labelSommetFin
	 *            le label du sommet d'arrivée
	 * @return la liste des sommets qui représente le chemin le plus court
	 */
	public static List < Object > rechercherLePlusCourtChemin(final IGraphe graphe, final Object labelSommetSource,
		final Object labelSommetFin) {
		if (graphe == null) {
			LOGGER.error("Impossible de calculer le plus court chemin car le graphe est nul");
			return null;
		}
		if (graphe.getIndex(labelSommetSource) == -1 || graphe.getIndex(labelSommetFin) == -1) {

			LOGGER.error("Impossible de calculer le plus court chemin car le sommet source " + labelSommetSource
				+ " ou le sommet de fin " + labelSommetFin + " n'appartiennent pas au graphe");

			return null;
		}

		return rechercherLePlusCourtChemin(graphe, graphe.getIndex(labelSommetSource), graphe.getIndex(labelSommetFin));
	}

	/**
	 * Retourne le chemin le plus court entre les sommets <code>sommetSource</code> et <code>sommetFin</code>.
	 * 
	 * @param graphe
	 *            le graphe où calculer le chemin le plus court
	 * @param pred
	 *            les prédecesseurs
	 * @param indexSommetSource
	 *            l'index du sommet de départ
	 * @param indexSommetFin
	 *            l'index du sommet d'arrivée
	 * @return la liste des sommets qui représente le chemin le plus court
	 */
	private static List < Object > rechercherLePlusCourtChemin(final IGraphe graphe, final int[] pred,
		final int indexSommetSource, final int indexSommetFin) {
		List < Object > cheminpc = new LinkedList < Object >();

		int sommetEnCours = indexSommetFin;
		boolean[] marque = new boolean[pred.length]; // pour éviter les boucles
		boolean boucle = false;
		boolean existeChemin = true;
		while (sommetEnCours != indexSommetSource && !boucle && existeChemin) {
			if (marque[sommetEnCours]) {
				boucle = true;
			} else {
				cheminpc.add(0, graphe.getLabel(sommetEnCours));
				marque[sommetEnCours] = true;
				sommetEnCours = pred[sommetEnCours];
				if (sommetEnCours == -1) {
					// Le sommet en cours n'a pas de prédecesseur
					existeChemin = false;
				}
			}
		}
		cheminpc.add(0, graphe.getLabel(indexSommetSource));

		if (boucle || !existeChemin) {

			LOGGER.error("Pas de chemin entre " + graphe.getLabel(indexSommetSource) + " et " + graphe.getLabel(indexSommetFin));
			return null;
		}

		return cheminpc;
	}
}
