package annotation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class TransactionTest {

    @Test
    void transactionToJsonFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("data/transaction_reversed.json");
        Transaction transaction = new Transaction(1L, new BigDecimal("10.002"), Status.SUCCESS, LocalDateTime.now(), LocalDateTime.now());

        mapper.writeValue(file, transaction);
    }

    @Test
    void jsonToTransactionObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("data/transaction_reversed.json");

        // Specify the type reference explicitly
        TypeReference<Transaction> typeReference = new TypeReference<Transaction>() {};
        Transaction transaction = mapper.readValue(file, TypeFactory.defaultInstance().constructType(typeReference));

        System.out.println(transaction);
    }
}
