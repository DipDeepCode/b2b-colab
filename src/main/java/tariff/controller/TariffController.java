package tariff.controller;

import lombok.RequiredArgsConstructor;
import tariff.repository.TariffRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TariffController {
    private final TariffRepository tariffRepository;

}
