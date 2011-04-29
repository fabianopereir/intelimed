/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IntelimedEntity.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.model.entity;

import java.io.Serializable;

/**
 * <Insert_class_comment>
 *
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 *
 */
@SuppressWarnings("serial")
public abstract class IntelimedEntity implements IIntelimedEntity, Serializable, Comparable<IntelimedEntity> {
	protected Long id;

	/**
	 * Return the value of attribute id.
	 *
	 * @return The value of attribute id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set a value to attribute id.
	 *
	 * @param id The id to be set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        Long idObj;
        if ((getId() != null) && (obj != null)) {
            if (obj.getClass().equals(getClass())) {
                idObj = ((IntelimedEntity) obj).getId();
                if (idObj != null && idObj.equals(getId())) {
                    result = true;
                }
            }
        } else {
            result = (this == obj && obj != null);
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(IntelimedEntity o) {
        Long thisId = null;
        Long paramId = null;
        int comparacao = 0;

        if (this.getId() != null) {
            thisId = this.getId();
        }

        if (o != null && o.getId() != null) {
            paramId = o.getId();
        }

        if (thisId == null && paramId != null) {
            comparacao = -1;
        } else if (thisId != null && paramId == null) {
            comparacao = 1;
        } else if (thisId == null && paramId == null) {
            comparacao = 0;
        } else {
            comparacao = thisId.compareTo(paramId);
        }

        return comparacao;
    }	
	
}
