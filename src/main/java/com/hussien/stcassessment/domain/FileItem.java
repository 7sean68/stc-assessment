package com.hussien.stcassessment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hussien.stcassessment.domain.enumerations.ItemType;
import com.hussien.stcassessment.domain.interfaces.ChildItem;
import com.hussien.stcassessment.domain.interfaces.ParentItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = ItemType.Values.FILE)
@Data
@EqualsAndHashCode(callSuper = false)
public class FileItem extends Item implements ChildItem {
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item", optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    File file;

    public ParentItem getParent() {
        return parent;
    }

    public void setParent(ParentItem parent) {
        this.parent = parent;
    }
}
