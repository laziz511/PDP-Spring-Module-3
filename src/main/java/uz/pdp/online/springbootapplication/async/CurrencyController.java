package uz.pdp.online.springbootapplication.async;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        Optional<Currency> currency = currencyService.getCurrencyById(id);
        return currency.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        Currency createdCurrency = currencyService.createCurrency(currency);
        return ResponseEntity.ok(createdCurrency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody Currency updatedCurrency) {
        Optional<Currency> currency = currencyService.updateCurrency(id, updatedCurrency);
        return currency.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-from-central-bank")
    public ResponseEntity<Void> updateAllCurrenciesFromCentralBank() {
        currencyService.updateAllCurrenciesFromCentralBank();
        return ResponseEntity.ok().build();
    }
}

