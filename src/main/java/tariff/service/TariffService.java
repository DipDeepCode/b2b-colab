package tariff.service;

import lombok.RequiredArgsConstructor;
import tariff.model.Tariff;
import tariff.repository.TariffRepository;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class TariffService {
    private final TariffRepository repository;

    public List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = repository.findAll();
        for (Tariff tariff : tariffs) {
            applyFeatures(tariff);
        }
        return tariffs;
    }

    public void saveTariff(Tariff tariff) {
        applyFeatures(tariff);
        repository.save(tariff);
    }

    public void updateTariff(Tariff tariff) {
        applyFeatures(tariff);
        repository.update(tariff);
    }

    private void applyFeatures(Tariff tariff) {
        switch (tariff.getType()) {
            case LITE_MATCH:
                tariff.setFeatures(new Tariff.LiteMatchFeatures());
                break;
            case COMFORT_MATCH:
                tariff.setFeatures(new Tariff.ComfortMatchFeatures());
                break;
            case BUSINESS_MATCH:
                tariff.setFeatures(new Tariff.BusinessMatchFeatures());
                break;
            default:
                throw new IllegalArgumentException("Unknown tariff type: " + tariff.getType());
        }
    }
}
