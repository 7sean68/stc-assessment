package com.hussien.stcassessment.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hussien.stcassessment.domain.enumerations.ItemType;
import com.hussien.stcassessment.domain.interfaces.ChildItem;
import com.hussien.stcassessment.domain.interfaces.ParentItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = ItemType.Values.FOLDER)
@Data
@EqualsAndHashCode(callSuper = false)
public class FolderItem extends Item implements ParentItem, ChildItem {
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Item.class, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ChildItem> children = new HashSet<>();

    public ParentItem getParent() {
        return parent;
    }

    public void setParent(ParentItem parent) {
        this.parent = parent;
    }

}
