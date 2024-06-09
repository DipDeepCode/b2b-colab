package ru.ddc.b2bcolab.upload.controller.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreFileResponse {
    private String fileName;

    public static StoreFileResponse of(String fileName) {
        return new StoreFileResponse(fileName);
    }
}
