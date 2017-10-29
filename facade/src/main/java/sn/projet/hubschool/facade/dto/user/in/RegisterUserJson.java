package sn.projet.hubschool.facade.dto.user.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Register new User")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("RegisterUser")
public final class RegisterUserJson implements Serializable {

    private static final long serialVersionUID = 1715302587252390745L;

    private String firstname;

    private String lastname;

    @ApiModelProperty(required = true)
    @NotBlank
    @Email
    @JsonProperty(required = true)
    private String email;

    private String locale;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

}
