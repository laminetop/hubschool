package sn.projet.hubschool.facade.dto.user;

import sn.projet.hubschool.facade.dto.BaseVO;
import sn.projet.hubschool.model.Language;
import sn.projet.hubschool.transverse.SpringMVCConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel("User")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("User")
@Getter
@Setter
@ToString(callSuper = true)
public final class UserVO extends BaseVO implements UserDetails {

    private static final long serialVersionUID = 8779873117208515155L;
    @NotEmpty
    @Email
    private String username;

    private String password;

    @Size(min = 3, max = 15)
    private String firstname;

    @Size(min = 2, max = 15)
    private String lastname;

    private boolean enabled;

    private Language language;

    private List<Authoritie> authorities;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SpringMVCConstants.DATE_FORMAT)
    @ApiModelProperty(value = "lastLoginTime (pattern = " + SpringMVCConstants.DATE_FORMAT + ")")
    private Date lastLoginTime;

    private String newPassword;

    private String confirmPassword;

    public UserVO() {
        super();
        enabled = true;
    }

    public UserVO(final String username, final String password, final String firstname, final String lastname,
                  final boolean enabled, final String[] roles, final Language language) {
        super();
        this.enabled = enabled;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        newPassword = password;
        confirmPassword = password;
        this.language = language;

        authorities = new ArrayList<Authoritie>();
        final List<String> authoritieKeys = Arrays.asList(roles);
        for (final String authoritieKey : authoritieKeys) {
            authorities.add(new Authoritie(this.username, authoritieKey));
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
        if (authorities != null) {
            for (final Authoritie authoritie : getAuthoritiesAsList()) {
                authoritie.setUsername(username);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = null;
        if (authorities != null) {
            collections = new ArrayList<GrantedAuthority>(authorities);
        } else {
            collections = new ArrayList<GrantedAuthority>();
        }

        return collections;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    private boolean haveRole(final String role) {
        boolean found = false;
        if (authorities != null) {
            for (final Authoritie authoritie : getAuthoritiesAsList()) {
                if (authoritie.getAuthority().equals(role)) {
                    found = true;
                }
            }
        }

        return found;
    }

    private void setRole(final String role, final boolean value) {
        Authoritie found = null;
        if (authorities != null) {
            for (final Authoritie authoritie : getAuthoritiesAsList()) {
                if (authoritie.getAuthority().equals(role)) {
                    found = authoritie;
                }
            }
        }

        if (found == null) {
            if (value) {
                if (authorities == null) {
                    authorities = new ArrayList<Authoritie>();
                }
                authorities.add(new Authoritie(username, role));
            }
        } else {
            if (!value) {
                authorities.remove(found);
            }
        }
    }

    public List<Authoritie> getAuthoritiesAsList() {
        return authorities;
    }

    public boolean isRoleAdmin() {
        return haveRole(SpringMVCConstants.ROLE_ADMIN);
    }

    public void setRoleAdmin(final boolean roleAdmin) {
        setRole(SpringMVCConstants.ROLE_ADMIN, roleAdmin);
    }

    public boolean isRoleManager() {
        return haveRole(SpringMVCConstants.ROLE_MANAGER);
    }

    public void setRoleManager(final boolean roleManager) {
        setRole(SpringMVCConstants.ROLE_MANAGER, roleManager);
    }


    @Override
    public String getPassword() {
        return password;
    }


    public Date getLastLoginTime() {
        return (Date) ObjectUtils.clone(lastLoginTime);
    }

    public void setLastLoginTime(final Date lastLoginTime) {
        this.lastLoginTime = (Date) ObjectUtils.clone(lastLoginTime);
    }

}
