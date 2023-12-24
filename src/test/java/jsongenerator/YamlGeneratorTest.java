package jsongenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class YamlGeneratorTest {

    @Test
    void transactionObjectToYamlFile() throws Exception {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        Transaction transaction = new Transaction(1L, new BigDecimal("10.002"), Status.SUCCESS, LocalDateTime.now(), LocalDateTime.now());

        mapper.writeValue(new File("data/output.yaml"), transaction);

    }

    @Test
    void yamlToTransactionObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        Transaction transaction = mapper.readValue(new File("data/output.yaml"), Transaction.class);

        System.out.println(transaction);
    }


}
