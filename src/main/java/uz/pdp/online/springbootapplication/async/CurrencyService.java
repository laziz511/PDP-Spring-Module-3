package uz.pdp.online.springbootapplication.async;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getCurrencyById(Long id) {
        return currencyRepository.findById(id);
    }

    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Optional<Currency> updateCurrency(Long id, Currency updatedCurrency) {
        return currencyRepository.findById(id).map(existingCurrency -> {
            existingCurrency.setCode(updatedCurrency.getCode());
            existingCurrency.setName(updatedCurrency.getName());
            existingCurrency.setExchangeRate(updatedCurrency.getExchangeRate());
            return currencyRepository.save(existingCurrency);
        });
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    @Async
    public void updateAllCurrenciesFromCentralBank() {
        List<Currency> newExchangeRates = fetchExchangeRatesFromCentralBank();

        for (Currency updatedCurrency : newExchangeRates) {
            currencyRepository.findByCode(updatedCurrency.getCode())
                    .ifPresent(existingCurrency -> {
                existingCurrency.setExchangeRate(updatedCurrency.getExchangeRate());
                currencyRepository.save(existingCurrency);
            });
        }

    }

    private List<Currency> fetchExchangeRatesFromCentralBank() {
        return List.of(
                new Currency(null, "USD", "US Dollar", 1.1),
                new Currency(null, "EUR", "Euro", 1.2)
        );
    }
}
