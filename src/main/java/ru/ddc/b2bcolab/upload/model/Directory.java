package ru.ddc.b2bcolab.upload.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Directory implements Comparable<Directory>{

    private final DirectoryType directoryType;
    private final String propertyName;
    private final String propertyValue;

    @Override
    public int compareTo(Directory o) {
        return this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Directory directory = (Directory) o;
        return this.directoryType == directory.directoryType && this.propertyName.equals(directory.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.directoryType, this.propertyName);
    }
}
