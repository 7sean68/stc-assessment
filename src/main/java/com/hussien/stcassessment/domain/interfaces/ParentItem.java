package com.hussien.stcassessment.domain.interfaces;

import java.util.HashSet;
import java.util.Set;

import lombok.NonNull;

public interface ParentItem extends WithPermissionGroup {
    Set<ChildItem> getChildren();

    void setChildren(@NonNull Set<ChildItem> children);

    public default void addChild(@NonNull ChildItem item) {
        assureChildrenExist();
        getChildren().add(item);
        item.setParent(this);
    }

    public default void removeChild(@NonNull ChildItem item) {
        assureChildrenExist();
        getChildren().remove(item);
    }

    private void assureChildrenExist() {
        if (getChildren() == null) {
            setChildren(new HashSet<>());
        }
    }
}
