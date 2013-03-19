package com.github.gcaldemo.calendar.repository;

public enum MinimumAccessRole {
    NONE("freeBusyReader"), READ("reader"), WRITE("writer"), OWNER("owner");

    private final String name;

    private MinimumAccessRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
