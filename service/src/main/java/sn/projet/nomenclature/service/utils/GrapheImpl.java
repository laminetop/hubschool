package sn.projet.nomenclature.service.utils;

import sn.projet.nomenclature.transverse.vo.Nomenclature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation d'un graphe à l'aide d'une matrice d'adjacences pour représenter les arcs entre les sommets.
 * 
 */
public class GrapheImpl implements IGraphe {

	/** Matrice d'adjacence pour représenter les arcs. */
	private int[][] arcs;

	/** Map qui fait la correspondance entre les labels des sommets et les index de la matrice d'adjacence. */
	private Map < Object, Integer > labelsSommets;

	/**
	 * Constructeur qui construit le graphe depuis la liste des nomenclatures.
	 * 
	 * @param nomenclatures
	 *            les nomenclatures qui contiennent des liens entre elles
	 */
	public GrapheImpl(final List < Nomenclature > nomenclatures) {
		initGraphe(nomenclatures);
	}

	/**
	 * Initialise le graphe depuis la liste des nomenclatures.
	 * 
	 * @param nomenclatures
	 *            les nomenclatures qui contiennent des liens entre elles
	 */
	private void initGraphe(final List < Nomenclature > nomenclatures) {
		initTailleMatriceAdjacenceEtLabels(nomenclatures);

		initMatriceAdjacence(nomenclatures);
	}

	/**
	 * Initialise la matrice d'adjacence depuis la liste des nomenclatures.
	 * 
	 * @param nomenclatures
	 *            les nomenclatures qui contiennent des liens entre elles
	 */
	private void initMatriceAdjacence(final List < Nomenclature > nomenclatures) {
		if (nomenclatures == null) {
			return;
		}

		for (Nomenclature nomenclature : nomenclatures) {
			if (nomenclature.getCodeNomenclaturesLies() != null) {
				int index1 = labelsSommets.get(nomenclature.getCode());
				for (String codeNomenclaturesLie : nomenclature.getCodeNomenclaturesLies()) {
					int index2 = labelsSommets.get(codeNomenclaturesLie);
					// Ajout d'un arc non orienté
					addArc(index1, index2, 1);
					addArc(index2, index1, 1);
				}
			}
		}
	}

	/**
	 * Initialise la matrice d'adjacence et les labels depuis la liste des nomenclatures.
	 * 
	 * @param nomenclatures
	 *            les nomenclatures qui contiennent des liens entre elles
	 */
	private void initTailleMatriceAdjacenceEtLabels(final List < Nomenclature > nomenclatures) {
		if (nomenclatures == null) {
			return;
		}

		Map < String, String > nomenclatureLiees = new HashMap < String, String >();

		for (Nomenclature nomenclature : nomenclatures) {
			if (nomenclature.getCodeNomenclaturesLies() != null) {
				for (String codeNomenclaturesLie : nomenclature.getCodeNomenclaturesLies()) {
					nomenclatureLiees.put(codeNomenclaturesLie, null);
				}
			}
		}

		this.arcs = new int[nomenclatureLiees.size()][nomenclatureLiees.size()];
		for (int i = 0; i < arcs.length; i++) {
			for (int j = 0; j < arcs.length; j++) {
				arcs[i][j] = -1; // Pas de chemin
			}
		}

		this.labelsSommets = new HashMap < Object, Integer >(nomenclatureLiees.size());

		int index = 0;
		for (String codeNomenclatureLie : nomenclatureLiees.keySet()) {
			if (!this.labelsSommets.containsKey(codeNomenclatureLie)) {
				this.labelsSommets.put(codeNomenclatureLie, index);
				index++;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] rechercherSommetsVoisins(final int indexSommet) {
		int count = 0;

		// On compte le nombre de voisins
		for (int i = 0; i < arcs[indexSommet].length; i++) {
			if (arcs[indexSommet][i] > 0) {
				count++;
			}
		}
		final int[] sommetsAdjacents = new int[count];

		// On récupère les voisins
		count = 0;
		for (int i = 0; i < arcs[indexSommet].length; i++) {
			if (arcs[indexSommet][i] > 0) {
				sommetsAdjacents[count++] = i;
			}
		}
		return sommetsAdjacents;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNbSommet() {
		return this.labelsSommets.size();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object getLabel(final int index) {
		for (Object label : labelsSommets.keySet()) {
			if (labelsSommets.get(label) == index) {
				return label;
			}
		}
		// L'index ne correspond à aucun label
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addArc(final int sommetDep, final int sommetArr, final int dist) {
		this.arcs[sommetDep][sommetArr] = dist;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getPoids(final int sommetDep, final int sommetArr) {
		return this.arcs[sommetDep][sommetArr];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getIndex(final Object labelSommet) {
		if (labelsSommets.containsKey(labelSommet)) {
			return labelsSommets.get(labelSommet);
		}
		// Le label du sommet est inconnu
		return -1;
	}

	/**
	 * Retourne la représentation du graphe sous forme de tableau à 2 dimensions.
	 * 
	 * @return la représentation du graphe
	 */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder("\n");

		Object[] labels = new Object[getNbSommet()];

		for (Object label : labelsSommets.keySet()) {
			labels[labelsSommets.get(label)] = label;
		}

		for (Object label : labels) {
			sb.append(label);
			sb.append("|");
		}

		sb.append("\n");
		for (int i = 0; i < arcs.length; i++) {
			sb.append(labels[i]);
			sb.append("|");
			for (int j = 0; j < arcs.length; j++) {
				sb.append(arcs[i][j]);
				sb.append("|");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
