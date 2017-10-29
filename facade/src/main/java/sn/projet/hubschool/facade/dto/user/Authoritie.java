package sn.projet.hubschool.facade.dto.user;

import sn.projet.hubschool.facade.dto.BaseVO;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Authoritie extends BaseVO implements GrantedAuthority {

    private static final long serialVersionUID = 2411091587368564838L;

    private String username;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}
