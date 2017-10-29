package sn.projet.nomenclature.transverse;

/**
 * Constantes du MAN Nomenclature.
 * 
 */
public interface Constantes {

	// Nom des Requêtes
	/** REQUETE_VERIFIER_APPARTENANCE. */
	String REQUETE_VERIFIER_APPARTENANCE = "verifierAppartenance";
	/** REQUETE_RECHERCHER_ELEMENTS. */
	String REQUETE_RECHERCHER_ELEMENTS = "rechercherElements";
	/** REQUETE_RECHERCHER_NOMENCLATURE. */
	String REQUETE_RECHERCHER_NOMENCLATURE = "rechercherNomenclature";
	/** REQUETE_RECHERCHER_NOMENCLATURE_PAR_CODE. */
	String REQUETE_RECHERCHER_NOMENCLATURE_PAR_CODE = "rechercherNomenclatureParCode";
	/** REQUETE_VERIFIER_EXISTANCE_LIEN_INF. */
	String REQUETE_VERIFIER_EXISTANCE_LIEN_INF = "verifierExistanceLienInf";
	/** REQUETE_VERIFIER_EXISTANCE_LIEN_INF_NOMENCLATURE_DIFFERENTES. */
	String REQUETE_VERIFIER_EXISTANCE_LIEN_INF_NOMENCLATURE_DIFFERENTES = "verifierExistanceLienInfNomenclatureDifferentes";
	/** REQUETE_VERIFIER_EXISTANCE_LIEN_SUP_NOMENCLATURE_DIFFERENTES. */
	String REQUETE_VERIFIER_EXISTANCE_LIEN_SUP_NOMENCLATURE_DIFFERENTES = "verifierExistanceLienSupNomenclatureDifferentes";
	/** REQUETE_VERIFIER_EXISTANCE_LIEN_SUP. */
	String REQUETE_VERIFIER_EXISTANCE_LIEN_SUP = "verifierExistanceLienSup";
	/** REQUETE_RECHERCHER_ELEMENT_PAR_CODE. */
	String REQUETE_RECHERCHER_ELEMENT_PAR_CODE = "rechercherParCode";
	/** REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_COURT. */
	String REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_COURT = "rechercherParLibelleCourt";
	/** REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_LONG. */
	String REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_LONG = "rechercherParLibelleLong";
	/** REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_IMPRESSION. */
	String REQUETE_RECHERCHER_ELEMENT_PAR_LIBELLE_IMPRESSION = "rechercherParLibelleImpression";
	/** REQUETE_RECHERCHER_ELEMENT_PAR_ATTRIBUT_EXTERNE. */
	String REQUETE_RECHERCHER_ELEMENT_PAR_ATTRIBUT_EXTERNE = "rechercherParAttributExterne";
	/** Nom de la requête qui recherche les éléments liés à une même nomenclature et qui sont situés à droite dans la table LIEN. */
	String REQUETE_RECHERCHER_LIENS_SUP = "rechercherLiensSup";
	/** Nom de la requête qui recherche les éléments liés à une même nomenclature et qui sont situés à gauche dans la table LIEN. */
	String REQUETE_RECHERCHER_LIENS_INF = "rechercherLiensInf";
	/** Nom de la requête qui recherche les codes éléments des liens supérieurs. */
	String REQUETE_RECHERCHER_CODE_LIENS_SUP = "rechercherCodeLiensSup";
	/** Nom de la requête qui recherche les codes éléments des liens inférieurs. */
	String REQUETE_RECHERCHER_CODE_LIENS_INF = "rechercherCodeLiensInf";
	/** REQUETE_RECHERCHER_LIENS_INF_LEX. */
	String REQUETE_RECHERCHER_LIENS_INF_LEX = "rechercherLiensInfLex";
	/** REQUETE_RECHERCHER_LIENS_SUP_LEX. */
	String REQUETE_RECHERCHER_LIENS_SUP_LEX = "rechercherLiensSupLex";
	/** Nom de la requête qui recherche les codes éléments des liens inférieurs lexicalement. */
	String REQUETE_RECHERCHER_CODE_LIENS_INF_LEX = "rechercherCodeLiensInfLex";
	/** Nom de la requête qui recherche les codes éléments des liens supérieurs lexicalement. */
	String REQUETE_RECHERCHER_CODE_LIENS_SUP_LEX = "rechercherCodeLiensSupLex";
	/** Nom de la requête qui recherche toutes les nomenclatures ainsi que les liens entre ces nomenclatures. */
	String REQUETE_RECHERCHER_ALL_NOMENS_ET_LIEN_ENTRE_NOMENS = "rechercherAllNomenclaturesEtLienEntreNomenclatures";
	/** REQUETE_RECHERCHER_INDICES. */
	String REQUETE_RECHERCHER_INDICES = "rechercherIndices";
	/** REQUETE_RECHERCHER_CORPS_PAR_INDICE. */
	String REQUETE_RECHERCHER_CORPS_PAR_INDICE = "rechercherCorpsParIndice";
	/** REQUETE_RECHERCHER_ELEMENTS_POSSEDES. */
	String REQUETE_RECHERCHER_ELEMENTS_POSSEDES = "rechercherElementsPossedes";
	/** REQUETE_RECHERCHER_ELEMENTS_POSSEDANT. */
	String REQUETE_RECHERCHER_ELEMENTS_POSSEDANT = "rechercherElementsPossedant";
	/** REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE **/
	String REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE = "rechercherElementsParPeriode";
	/** REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE_DATEFIN_NULL **/
	String REQUETE_RECHERCHER_ELEMENTS_PAR_PERIODE_DATEFIN_NULL = "rechercherElementsParPeriodeDatefinNull";

	// Variables utilisées dans les requêtes
	/** Paramètre codeNomenclature utilisé dans les requêtes. */
	String PARAM_CODENOMENCLATURE = "codeNomenclature";
	/** Paramètre codeNomenclature2 utilisé dans les requêtes. */
	String PARAM_CODENOMENCLATURE2 = "codeNomenclature2";

	// Attributs nomenclature
	/** ATTR_CODE_NOMENCLATURE. */
	String ATTR_CODE_NOMENCLATURE = "code";
	/** ATTR_LIBELLE_NOMENCLATURE. */
	String ATTR_LIBELLE_NOMENCLATURE = "libelle";
	/** ATTR_LIBELLE_NOMENCLATURE_INF. */
	String ATTR_LIBELLE_NOMENCLATURE_INF = "libelleInf";
	/** ATTR_LIBELLE_NOMENCLATURE_SUP. */
	String ATTR_LIBELLE_NOMENCLATURE_SUP = "libelleSup";

	// Attributs element
	/** ATTR_CODE_ELEMENT. */
	String ATTR_CODE_ELEMENT = "codeElement";
	/** ATTR_LIBELLE_COURT. */
	String ATTR_LIBELLE_COURT = "libelleCourt";
	/** ATTR_LIBELLE_LONG. */
	String ATTR_LIBELLE_LONG = "libelleLong";
	/** ATTR_LIBELLE_IMPRESSION. */
	String ATTR_LIBELLE_IMPRESSION = "libelleImpression";
	/** ATTR_DATE_EFFET_ELEMENT. */
	String ATTR_DATE_EFFET_ELEMENT = "dateEffet";
	/** ATTR_DATE_FIN_ELEMENT. */
	String ATTR_DATE_FIN_ELEMENT = "dateFin";
	/** ATTR_NOMENCLATURE_ELEMENT. */
	String ATTR_NOMENCLATURE_ELEMENT = "nomenclature";
	/** ATTR_ATTRIBUT_EXTERNE_ELEMENT. */
	String ATTR_ATTRIBUT_EXTERNE_ELEMENT = "attr";
	/** Nom du tag qui contient le chemin pour faire le lien entre 2 nomenclatures. */
	String CHEMIN_ENTRE_LIENS = "cheminEntreLiens";

	// Attributs lien
	/** ATTR_DATE_EFFET_LIEN. */
	String ATTR_DATE_EFFET_LIEN = "dateEffet";
	/** ATTR_DATE_FIN_LIEN. */
	String ATTR_DATE_FIN_LIEN = "dateFin";
	/** ATTR_ELEMENT1_LIEN. */
	String ATTR_ELEMENT1_LIEN = "element1";
	/** ATTR_ELEMENT2_LIEN. */
	String ATTR_ELEMENT2_LIEN = "element2";

	/** ATTR_CODE_ELEMENT1_LIEN. */
	String ATTR_CODE_ELEMENT1_LIEN = "codeElement1";
	/** ATTR_CODE_NOMENCLATURE1_LIEN. */
	String ATTR_CODE_NOMENCLATURE1_LIEN = "codeNomenclature1";
	/** ATTR_CODE_ELEMENT2_LIEN. */
	String ATTR_CODE_ELEMENT2_LIEN = "codeElement2";
	/** ATTR_CODE_NOMENCLATURE2_LIEN. */
	String ATTR_CODE_NOMENCLATURE2_LIEN = "codeNomenclature2";

	// Attributs attributs externes
	/** ATTR_LIBELLE_ATTRIBUT. */
	String ATTR_LIBELLE_ATTRIBUT = "libelle";
	/** ATTR_VALEUR_ATTRIBUT. */
	String ATTR_VALEUR_ATTRIBUT = "valeur";
	/** ATTR_ELEMENT_ATTRIBUT_EXTERNE. */
	String ATTR_ELEMENT_ATTRIBUT_EXTERNE = "element";

	/** ATTR_RECHERCHE_SUP. */
	String ATTR_RECHERCHE_SUP = "sup";
	/** ATTR_RECHERCHE_INF. */
	String ATTR_RECHERCHE_INF = "inf";

	// Codes erreurs

	/** ERR_NOMENCLATURE_NEXIST. */
	String ERR_NOMENCLATURE_NEXIST = "ren.nomenclature.man.404";
	/** ERR_AUCUNE_NOMENCLATURE. */
	String ERR_AUCUNE_NOMENCLATURE = "ren.nomenclature.man.404";
	/** ERR_ELEMENT_NAPARTIENT_PAS_NOMENCLATURE. */
	String ERR_ELEMENT_NAPARTIENT_PAS_NOMENCLATURE = "ren.nomenclature.man.001";
	/** ERR_AUCUN_ELEMENTS. */
	String ERR_AUCUN_ELEMENTS = "ren.nomenclature.man.002";
	/** ERR_ELEMENT_NEXISTE_PAS. */
	String ERR_ELEMENT_NEXISTE_PAS = "ren.nomenclature.man.404";
	/** ERR_BAD_REQUEST. */
	String ERR_BAD_REQUEST = "ren.nomenclature.man.403";
	/** La taille du pool de thread n'est pas initialisée correctement. */
	String ERR_CONFIG_THREADPOOLSIZE_INCORRECTE = "ren.nomenclature.man.transport.001";
	/** ERR_DATE **/
	String ERR_DATE = "ren.nomenclature.man.404";

	/** XHTML. */
	String XHTML = "xhtml";
	/** SLASH. */
	String SLASH = "/";
	/** UTF_8. */
	String UTF_8 = "utf-8";
	/** ATOM_1_0. */
	String ATOM_1_0 = "atom_1.0";
	/** DATE. */
	String DATE = "date";

	/** Longueur d'un libellé. */
	int LONGUEUR_NOM_ATTRIBUT_EXTERNE = 40;
	/** Longueur du champ libelleSuperieur en base de données. */
	int LONGUEUR_LIBELLE_SUPERIEUR = 1000;
	/** Longueur du champ libelleInferieur en base de données. */
	int LONGUEUR_LIBELLE_INFERIEUR = 1000;
	/** Longueur d'un libelle long ou impression. */
	int LONGUEUR_LIBELLE_LONG = 150;
	/** Longueur d'un libelle long ou impression. */
	int LONGUEUR_LIBELLE_ATTREXT = 1500;
	/** Longueur d'un type. */
	int LONGUEUR_VALEUR_ATTRIBUT_EXTERNE = 150;
	/** Longueur d'un code. */
	int LONGUEUR_CODE = 16;
	/** Longueur de la colonne NOMATTRIBUTLIEN dans la table ATTRIBUTLIEN. */
	int LONGUEUR_NOM_ATTRIBUT_LIEN = 40;
	/** Longueur de la colonne VALEURATTRIBUTLIEN dans la table ATTRIBUTLIEN. */
	int LONGUEUR_VALEUR_ATTRIBUT_LIEN = 150;

	/** Namespace global. */
	String GLOBAL_NS = "urn://andado.com";
	/** Namespace des types. */
	String TYPE_NS = GLOBAL_NS + "/referentiel/types";

}
