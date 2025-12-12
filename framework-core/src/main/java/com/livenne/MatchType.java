package com.livenne;

public enum MatchType {
    EQUAL("="),LIKE("like");

    final String string;

    MatchType(String string) {
        this.string = string;
    }


    @Override
    public String toString() {
        return string;
    }
}
