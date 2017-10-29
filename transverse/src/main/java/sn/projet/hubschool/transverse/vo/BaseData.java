package sn.projet.hubschool.transverse.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ismailasirimancamara on 2015-08-26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData implements Serializable, Cloneable {

    /**
     * Create by
     */
    private String createdBy;

    /**
     * Creation date
     */
    private Date creationDate;

    /**
     * Modified by
     */
    private String modifiedBy;

    /**
     * Modification date
     */
    private Date modificationDate;
}
