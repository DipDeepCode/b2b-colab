package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.repository.TariffRepository;

@RestController
@RequiredArgsConstructor
public class TariffController {
    private final TariffRepository tariffRepository;

}
