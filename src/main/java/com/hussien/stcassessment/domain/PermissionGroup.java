package com.hussien.stcassessment.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // wanted to name it "name". But, I'm following requirements
    String groupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Permission> permissions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permissionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Item> items = new HashSet<>();

    public void addPermission(@NonNull Permission permission) {
        assurePermissionsExist();
        permissions.add(permission);
    }

    public void removePermission(@NonNull Permission permission) {
        assurePermissionsExist();
        permissions.remove(permission);
    }

    private void assurePermissionsExist() {
        if (permissions == null) {
            permissions = new HashSet<>();
        }
    }

    public void addItem(@NonNull Item item) {
        assureItemsExist();
        items.add(item);
    }

    public void removeItem(@NonNull Item item) {
        assureItemsExist();
        items.remove(item);
    }

    private void assureItemsExist() {
        if (items == null) {
            items = new HashSet<>();
        }
    }
}
