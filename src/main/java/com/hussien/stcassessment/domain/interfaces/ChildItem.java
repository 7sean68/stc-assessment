package com.hussien.stcassessment.domain.interfaces;

import jakarta.persistence.PreRemove;

public interface ChildItem extends WithPermissionGroup {
    ParentItem getParent();

    void setParent(ParentItem parent);

    
    @PreRemove
    default void removeFromGroup() {
        if (getParent() != null) {
            getParent().removeChild(this);
        }
    }
}
