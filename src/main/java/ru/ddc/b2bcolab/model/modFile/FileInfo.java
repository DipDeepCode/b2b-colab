package ru.ddc.b2bcolab.model.modFile;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class FileInfo {

    private Long id;

    private String name;

    private Long size;

    private String key;

//    private LocalDate uploadDate;
}