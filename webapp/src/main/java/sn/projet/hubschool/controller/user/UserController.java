package sn.projet.hubschool.controller.user;

import com.google.common.collect.Sets;

import sn.projet.hubschool.error.DtoRestRetour;
import sn.projet.hubschool.error.TypeRetour;
import sn.projet.hubschool.facade.dto.user.in.RegisterUserJson;
import sn.projet.hubschool.service.user.UserService;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.groups.Default;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User Controller")
@Controller
@RequestMapping(value = "/api/user", produces = {"application/hal+json; charset=UTF-8"})
public final class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);
    @Autowired
    Validator validator;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;


    /**
     * This method is used to register a new user default category and insurerType are created and
     * send back to the device
     *
     * @return RegisterUserJSONResponse
     */
    @ApiOperation(value = "This method is used to register a new user ", position = 0)
    @PreAuthorize("#oauth2.hasScope('trust')")
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<DtoRestRetour> register(@RequestBody final RegisterUserJson registerUser) {

        if (LOG.isInfoEnabled()) {
            LOG.info(registerUser);
        }

        Set<ConstraintViolation<RegisterUserJson>> constraintViolations = validator.validate(registerUser, Default.class);

        if (!constraintViolations.isEmpty()) {
            final Set<ConstraintViolation<?>> set = Sets.newHashSet();
            set.addAll(constraintViolations);
            throw new ConstraintViolationException(set);

        }

        final Locale locale = LocaleUtils.toLocale(registerUser.getLocale());
        if (LOG.isDebugEnabled()) {
            LOG.debug("locale: " + locale);
        }

        final boolean success = userService.registerNewUser(registerUser.getEmail(), registerUser.getFirstname(),
                registerUser.getLastname(), locale);

        final DtoRestRetour responseJson = new DtoRestRetour();
        responseJson.setTypeRetour(TypeRetour.FONCTIONNEL);
        if (success) {
            responseJson.setCodeRetour("OK");
            return new ResponseEntity<>(responseJson, HttpStatus.OK);
        } else {
            responseJson.setCodeRetour("KO");
            responseJson.setLibelleRetour(messageSource.getMessage("user.message.exists", null, locale));
            return new ResponseEntity<>(responseJson, HttpStatus.BAD_REQUEST);
        }

    }
}
