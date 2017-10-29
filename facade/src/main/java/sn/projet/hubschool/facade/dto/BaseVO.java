package sn.projet.hubschool.facade.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseVO implements Serializable, Cloneable {

    private Long id;

//    private String createdBy;
//
//    private Date creationDate;
//
//    private Date modificationDate;
//
//    private Long version;
}
