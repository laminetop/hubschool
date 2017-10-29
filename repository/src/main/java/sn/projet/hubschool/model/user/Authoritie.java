package sn.projet.hubschool.model.user;

import sn.projet.hubschool.model.BaseEntity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Authoritie extends BaseEntity implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 6496914414651672131L;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}
