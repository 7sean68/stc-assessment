package com.hussien.stcassessment.domain.enumerations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public enum ItemType {
    SPACE(Values.SPACE), FOLDER(Values.FOLDER), FILE(Values.FILE);

    String value;

    private ItemType(String value) {
        // force equality between name of enum instance, and value of constant
     if (!this.name().equals(value)) {
        throw new IllegalArgumentException("Name-Value mismatch");
     }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Values {
        public static final String SPACE = "SPACE";
        public static final String FOLDER = "FOLDER";
        public static final String FILE = "FILE";
    }
}
