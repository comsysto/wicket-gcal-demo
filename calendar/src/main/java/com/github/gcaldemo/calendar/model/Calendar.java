package com.github.gcaldemo.calendar.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.io.Serializable;

public final class Calendar implements Serializable {
    private final String id;

    private final String name;

    public Calendar(String id, String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "'id' must not be empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "'name' must not be empty");

        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calendar calendar = (Calendar) o;

        if (!id.equals(calendar.id)) return false;
        if (!name.equals(calendar.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
