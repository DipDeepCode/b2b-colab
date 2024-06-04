package ru.ddc.b2bcolab.upload.model;

import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Getter
public enum DirectoryType {

    IPTC("IPTC", "International Press Telecommunications Council"),
    XMP("XMP", "Extensible Metadata Platform"),
    EXIF("EXIF", "Exchangeable Image File Format"),
    FILE("File", "Image File Properties"),
    FILE_INFO("File Info", "Image Format Info"),
    GIF("GIF", "Graphics Interchange Format"),
    WINDOWS("WINDOWS", "Windows-specific EXIF tags"),
    GENERIC("GENERIC", "Generic Image Metadata");

    private final String name;

    private final String description;

    DirectoryType(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public static DirectoryType fromName(final String name) {
        for (DirectoryType directoryType : DirectoryType.values()) {
            if (directoryType.getName().equals(name)) {
                return directoryType;
            }
        }
        return null;
    }

    public static List<DirectoryType> getValuesAsList() {
        return Stream.of(DirectoryType.values()).sorted(Comparator.comparing(DirectoryType::getName)).toList();
    }
}
