package sn.projet.hubschool.model.user;

import sn.projet.hubschool.model.BaseEntity;
import sn.projet.hubschool.model.Language;
import sn.projet.hubschool.transverse.SpringMVCConstants;
import sn.projet.hubschool.validator.FieldMatch;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Utilisateur")
@NamedQueries({
        @NamedQuery(name = "getUserByUserName", query = "select u from User u where u.username = :username and u.enabled = 1"),
        @NamedQuery(name = "getEnabledUsers", query = "select u from User u where u.enabled = 1 order by u.lastname")})
@FieldMatch.List({@FieldMatch(first = "newPassword", second = "confirmPassword", message = "The password fields must match")})
@Getter
@Setter
@ToString(callSuper = true)
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = -6749990853059667647L;

    @NotBlank
    @Email
    @Length(max = 250)
    @Column(unique = true, nullable = false, length = 250)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @NotBlank
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    private String firstname;

    @NotBlank
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    private String lastname;

    @Column
    private boolean enabled;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Authoritie> authorities;

    @Column
    private Date lastLoginTime;

    @Transient
    private String newPassword;

    @Transient
    private String confirmPassword;

    public User() {
        super();
        enabled = true;
    }

    public User(final String username, final String password, final String firstname, final String lastname,
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

    public void setAuthorities(final List<Authoritie> authorities) {
        this.authorities = authorities;
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

    public void addRole(final String role) {
        setRole(role, true);
    }

    public boolean isRoleManager() {
        return haveRole(SpringMVCConstants.ROLE_MANAGER);
    }

    public void setRoleManager(final boolean roleManager) {
        setRole(SpringMVCConstants.ROLE_MANAGER, roleManager);
    }

    public boolean isRolePromoter() {
        return haveRole(SpringMVCConstants.ROLE_PROMOTEUR);
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
