package sn.projet.hubschool.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Pour combiner p.ex. un InMemoryUserDetailsService + un UserService
 *
 */
public class CompositeUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeUserDetailsService.class);

    private final List<UserDetailsService> services;

    /**
     * constructeur
     * 
     * @param l
     */
    public CompositeUserDetailsService(List<UserDetailsService> l) {
        this.services = l;
    }

    /**
     * NOTE/PERF : attention à l'ordre des UserDetailsService qui composent
     * cette classe, mettez d'abord les InMemory en premier, puis JDBC et/ou
     * LDAP qui seront plus lent d'accès.
     * 
     * @throws UsernameNotFoundException (RuntimeException)
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails found = null;
        UsernameNotFoundException exception = null;
        for (UserDetailsService service : services) {
            try {
                found = service.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                // try with another manager, or else will throw last exception.
                LOGGER.warn("tried to load user with " + service
                                + " but did not find any data; will try with next UserDetailsService in list : " + e.getMessage());
                LOGGER.debug("full exception stack trace : ", e);
                exception = e; // will erase precedent error.
            }
            if (found != null) {
                break;
            }
        }

        if (found == null && exception != null) {
            throw exception;
        } else {
            return found;
        }
    }
}
