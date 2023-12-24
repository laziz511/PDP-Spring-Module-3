package jsongenerator;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class JsonGeneratorTest {

    @Test
    void jsonManualGenerateTest() throws IOException {

        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator generator = jsonFactory.createGenerator(new File("data/transaction.json"), JsonEncoding.UTF8)) {

            // Create a sample Transaction object
            Transaction transaction = new Transaction(1L, new BigDecimal("100.50"), Status.SUCCESS, LocalDateTime.now(), LocalDateTime.now());

            // Start writing the JSON object
            generator.writeStartObject();

            // Write transaction properties to JSON
            generator.writeNumberField("id", transaction.getId());
            generator.writeStringField("amount", transaction.getAmount().toString());
            generator.writeStringField("status", transaction.getStatus().name());
            generator.writeStringField("createdAt", transaction.getCreatedAt().toString());
            generator.writeStringField("updatedAt", transaction.getUpdatedAt().toString());

            // End the JSON object
            generator.writeEndObject();

            // Flush and close the generator
            generator.flush();
        }
    }

}
