package sn.projet.hubschool.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sk.nociar.jpacloner.AbstractJpaExplorer;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public abstract class BaseEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 8128965653406223642L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Create by
     */
    @Column(name = "createdBy", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    /**
     * Creation date
     */
    @Column(name = "creationDate", nullable = false, updatable = false)
    @CreatedDate
    private Date creationDate;

    /**
     * Modified by
     */
    @Column(name = "modifiedBy", nullable = true)
    @LastModifiedBy
    private String modifiedBy;

    /**
     * Modification date
     */
    @Column(name = "modificationDate", nullable = true)
    @LastModifiedDate
    private Date modificationDate;

    @Version
    private Long version;

    @PrePersist
    protected void setCreationFields() {
        creationDate = new Date();
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            createdBy = "system";
        } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            createdBy = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } else {
            createdBy = "anonymous";
        }
    }

    @PreUpdate
    protected void setModificationFields() {
        modificationDate = new Date();
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            modifiedBy = "system";
        } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            modifiedBy = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } else {
            modifiedBy = "anonymous";
        }
    }

    @Override
    public final boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        // get JPA classes (non-proxy)
        final Class<?> thisClass = AbstractJpaExplorer.getJpaClass(this.getClass());
        final Class<?> otherClass = AbstractJpaExplorer.getJpaClass(other.getClass());

        if (thisClass != otherClass) {
            return false;
        }
        final Long thisId = getId();
        final Long otherId = ((BaseEntity) other).getId();

        if (thisId == null || otherId == null) {
            return false;
        }

        return thisId.equals(otherId);
    }

    @Override
    public final int hashCode() {
        final Long thisId = getId();
        if (thisId == null) {
            return super.hashCode();
        }
        return thisId.hashCode();
    }
}
