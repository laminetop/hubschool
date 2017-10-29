package sn.projet.hubschool.controller.referentiel;

import sn.projet.exception.FonctionnelleException;
import sn.projet.nomenclature.transverse.vo.Element;
import sn.projet.nomenclature.transverse.vo.ElementAttributs;
import sn.projet.nomenclature.transverse.vo.Nomenclature;
import sn.projet.referentiel.facade.IConsultationNomenclatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Validator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Consultation nomenclature", description = "Services de consultation nomenclature.", position = 1)
@Controller
@RequestMapping(value = "/api/referentiel", produces = {"application/hal+json; charset=UTF-8"})
public class ReferentielController {

    @Autowired
    Validator validator;

    @Autowired
    private IConsultationNomenclatureService consultationNomenclatureService;

    @ApiOperation(value = "Retourne les nomenclatures.", position = 0)
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseBody
    public ResponseEntity<List<Nomenclature>> getNomenclatures() {
        return new ResponseEntity<>(
                consultationNomenclatureService.getNomenclatures(),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "Get elements par nomenclature", position = 1, notes = "Retourne l'ensemble des éléments de la nomenclature <code>codeNomenclature</code> ou les " +
            " éléments qui correspondent au critère <code>typeCritere</code> et la valeur de ce critère " +
            " <code>valeurCritere</code>.\n \n Exemples : \n - http://localhost/myapp/referentiel/PAYS" +
            " : recherche tous les pays valides à la date du jour.\n" +
            " - http://localhost/myapp/referentiel/PAYS?date=1900-01-01 : recherche tous les pays " +
            " valides au 1er Janvier 1900.\n - http://localhost/myapp/referentiel/PAYS?libelleCourt=toto" +
            " : recherche les pays dont le libellé court contient la chaine \"toto\" et valides à la" +
            " date du jour.\n - http://localhost/myapp/referentiel/PAYS?libelleCourt=toto&date=1900-01-01" +
            " : recherche les pays dont le libellé court contient la chaine \"toto\" et valides au 1er" +
            " Janvier 1900.\n")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/{nomenclature}")
    @ResponseBody
    public ResponseEntity<List<Element>> getElementsParNomenclature(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"PAYS\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "nomenclature")
            final String codeNomenclature,

            @ApiParam(value = "la date d'observation. P.ex.: \"2010-01-01\"")
            @RequestParam(value = "date", required = false)
            final String dateQuery,

            @ApiParam(value = "le type de critère de recherche. P.ex.: \"libelleCourt\"")
            @RequestParam(value = "typeCritere", required = false)
            final String typeCritere,

            @ApiParam(value = "la valeur du critère de recherche.")
            @RequestParam(value = "valeurCritere", required = false)
                    String valeurCritere

    ) throws FonctionnelleException {
        return new ResponseEntity<>(
                consultationNomenclatureService.getElementsParNomenclature(codeNomenclature, dateQuery, typeCritere, valeurCritere)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get elements par code", position = 2, notes = "Retourne l'élément de la nomenclature <code>nomenclature</code> qui a le code <code>codeElement</code>." +
            "\n Exemples : \n - http://localhost/myapp/referentiel/PAYS/100" +
            " : retourne l'element pays dont le code est 100 et valide à la date du jour.\n" +
            " - http://localhost/myapp/referentiel/PAYS/100?date=2010-01-01 : retourne l'element pays dont le code est 100 et" +
            " valides au 1er Janvier 2010.\n - http://localhost/myapp/referentiel/PAYS/100,221" +
            " : retourne les pays dont le code correspond à 100 ou 221 et valides à la date du jour")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/{nomenclature}/{elements}")
    @ResponseBody
    public ResponseEntity<List<Element>> getElementsParCode(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"PAYS\"", required = true)
            @PathVariable(value = "nomenclature")
            final String codeNomenclature,

            @ApiParam(value = "les codes des éléments à rechercher (séparés par des virgules). P.ex. \"100,220\"", required = true)
            @PathVariable(value = "elements")
            final String codeElements,

            @ApiParam(value = "la date d'observation. P.ex.: \"2010-01-01\"")
            @RequestParam(value = "date", required = false)
            final String dateQuery

    ) throws FonctionnelleException {
        return new ResponseEntity<>(
                consultationNomenclatureService.getElementsParCode(codeNomenclature, codeElements, dateQuery)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get elements par periode", position = 2, notes = "Retourne les éléments de la nomenclature <code>nomenclature</code> qui correspondent à la période commençant à la date d'effet <code>dateEffet</code> et terminant à la date <code>dateFin</code>" +
            "\n Exemples : \n - http://localhost:8080/myapp/referentiel/periode/PAYS/2009-09-01/2012-08-31 \n" +
            " - http://localhost:8080/myapp/referentiel/periode/PAYS/2009-09-01/null")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/periode/{nomenclature}/{dateEffet}/{dateFin}")
    @ResponseBody
    public ResponseEntity<List<Element>> getElementsParPeriode(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"PAYS\"", required = true)
            @PathVariable(value = "nomenclature")
            final String codeNomenclature,

            @ApiParam(value = "la date délimitant le début de période. P.ex. \"2010-01-01\"", required = true)
            @PathVariable(value = "dateEffet")
            final String dateEffet,

            @ApiParam(value = "la date fermant la période, peut être null. P.ex.: \"2015-01-01\" ou \"null\"")
            @RequestParam(value = "date", required = false)
            final String dateFin

    ) throws FonctionnelleException {
        return new ResponseEntity<>(
                consultationNomenclatureService.getElementsParPeriode(codeNomenclature, dateEffet, dateFin)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get Chemin Entre Liens", position = 3, response = String.class,
            notes = "Retourne le chemin entre la nomenclature <code>premierCodeNomenclature</code> et la nomenclature <code>dernierCodeNomenclature</code>")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/cheminEntreLiens/{premierCodeNomenclature}/{dernierCodeNomenclature}")
    @ResponseBody
    public ResponseEntity<String> getCheminEntreLiens(

            @ApiParam(value = "le code nomenclature de départ. P.ex. \"ACA\"", required = true)
            @PathVariable(value = "premierCodeNomenclature")
            final String premierCodeNomenclature,

            @ApiParam(value = "le code nomenclature d'arrivée. P.ex. \"DPT\"", required = true)
            @PathVariable(value = "dernierCodeNomenclature")
            final String dernierCodeNomenclature

    ) throws FonctionnelleException {
        return new ResponseEntity<>(consultationNomenclatureService.getCheminEntreLiens(premierCodeNomenclature, dernierCodeNomenclature)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get elements par liens", position = 4, notes = "Retourne les éléments de la nomenclature <code>nomenclature</code> qui ont un lien avec les " +
            "éléments de la nomenclature <code>typeLien</code> et valides à la date " +
            "<code>dateQuery</code>.\n Pour rechercher les liens entre éléments d'une même nomenclature, " +
            "<code>typeLien</code> est égal à <code>inf</code> ou <code>sup</code>.\n\n Exemples :\n" +
            " - http://localhost/myapp/referentiel/ACA/001,002/DPT : recherche les départements des " +
            "académies 001 et 002 valides à la date du jour.\n - http://localhost/myapp/referentiel/ACA/001,002/DPT?date=2000-01-01" +
            " : recherche les départements des académies 001 et 002 valides au 1er Janvier 2000.\n" +
            " - http://localhost/myapp/referentiel/ACA/001,002/inf : recherche les académies inférieures aux " +
            "académies 001 et 002 et valides à la date du jour.\n - http://localhost/myapp/referentiel/ACA/001,002/inf?date=2000-01-01" +
            " : recherche les académies inférieures aux académies 001 et 002 et valides au 1er Janvier " +
            "2000.\n")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/{nomenclature}/{elements}/{lien}")
    @ResponseBody
    public ResponseEntity<List<Element>> getElementsParLiens(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"ACA\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "nomenclature")
            final String codeNomenclature,

            @ApiParam(value = "la liste des codes éléments (séparés par des virgules). P.ex. \"001,002\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "elements")
            final String codeElements,

            @ApiParam(value = "le code de la nomenclature (ou le type de lien). P.ex. \"DPT\" ou \"inf\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "lien")
            final String typeLien,

            @ApiParam(value = "la date d'observation. P.ex.: \"2010-01-01\"")
            @RequestParam(value = "date", required = false)
            final String dateQuery

    ) throws FonctionnelleException {

        List<ElementAttributs> result = consultationNomenclatureService.getElementsParLiens(codeNomenclature, codeElements, typeLien, dateQuery);
        return new ResponseEntity<>(
                result.stream()
                        .map(ElementAttributs::getElement)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get code elements par liens", position = 5, response = List.class, responseContainer = "Map",
            notes = "Recherche des codes des liens entre la nomenclature <code>nomenclature</code> et la nomenclature <code>typeLien</code>.")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/codelien/{nomenclature}/{elements}/{lien}")
    @ResponseBody
    public ResponseEntity<Map<String, List<Element>>> getCodeElementsParLiens(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"ACA\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "nomenclature")
            final String codeNomenclature,

            @ApiParam(value = "la liste des codes éléments (séparés par des virgules). P.ex. \"001,002\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "elements")
            final String codeElements,

            @ApiParam(value = "le code de la nomenclature (ou le type de lien). P.ex. \"DPT\" ou \"inf\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "lien")
            final String typeLien,

            @ApiParam(value = "la date d'observation. P.ex.: \"2010-01-01\"")
            @RequestParam(value = "date", required = false)
            final String dateQuery

    ) throws FonctionnelleException {

        Map<String, List<ElementAttributs>> result = consultationNomenclatureService.getCodeElementsParLiens(codeNomenclature, codeElements, typeLien, dateQuery);

        return new ResponseEntity<>
                (result.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> entry.getValue().stream().map(ElementAttributs::getElement).collect(Collectors.toList())
                                )),
                        HttpStatus.OK);
    }

    @ApiOperation(value = "Rechercher Elements Par Liens Par Date", position = 6, response = String.class,
            notes = "Retourne les éléments de la nomenclature <code>nomenclature</code> qui ont un lien avec les éléments de la nomenclature <code>typeLien</code> et valides à la date <code>dateQuery</code>. Pour rechercher les liens entre éléments d'une même nomenclature, <code>typeLien</code> est égal à <code>inf</code> ou <code>sup</code>.")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/elementParLien/{codeNomenclatureUn}/{codesElementsUn}/{codeNomenclatureDeux}/{dateValiditeLien}")
    @ResponseBody
    public ResponseEntity<String> rechercherElementsParLiensParDate(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"ACA\"", required = true)
            @PathVariable(value = "codeNomenclatureUn")
            final String codeNomenclatureUn,

            @ApiParam(value = "la liste des codes éléments (séparés par des virgules). P.ex. \"95,75,92\"", required = true)
            @PathVariable(value = "codesElementsUn")
            final String codeElementsUn,

            @ApiParam(value = "le code de la nomenclature (ou le type de lien). P.ex. \"DPT\" ou \"inf\"", required = true)
            @PathVariable(value = "codeNomenclatureDeux")
            final String codeNomenclatureDeux,

            @ApiParam(value = "la date d'observation. P.ex. \"2010-01-01\"", required = true)
            @PathVariable(value = "dateValiditeLien")
            final String dateValiditeLien

    ) throws FonctionnelleException {
        return new ResponseEntity<>(consultationNomenclatureService.rechercherElementsParLiensParDate(codeNomenclatureUn, codeElementsUn, codeNomenclatureDeux, dateValiditeLien)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Verifier lien", position = 7, response = Boolean.class,
            notes = "Vérifie si l'élément <code>element1</code> est lié à l'élément <code>element2</code> par le lien <code>typeLien</code>")
    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/{nomenclature}/{element1}/{lien}/{element2}")
    @ResponseBody
    public ResponseEntity<Boolean> verifierLien(

            @ApiParam(value = "le code de la nomenclature. P.ex. \"ACA\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "nomenclature")
            final String codeNomenclature,

            @ApiParam(value = "le premier élément. P.ex. \"001\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "element1")
            final String element1,

            @ApiParam(value = "le type de lien. P.ex. \"DPT\" ou \"inf\"",
                    allowMultiple = false, required = true)
            @PathVariable(value = "lien")
            final String typeLien,

            @ApiParam(value = "le deuxième élément.",
                    allowMultiple = false, required = true)
            @PathVariable(value = "element2")
            final String element2,

            @ApiParam(value = "la date d'observation. P.ex.: \"2010-01-01\"")
            @RequestParam(value = "date", required = false)
            final String dateQuery

    ) throws FonctionnelleException {
        return new ResponseEntity<>(consultationNomenclatureService.verifierLien(codeNomenclature, element1, typeLien, element2, dateQuery)
                , HttpStatus.OK);
    }
}
