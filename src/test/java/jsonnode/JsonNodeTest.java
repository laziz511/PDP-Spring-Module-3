package jsonnode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class JsonNodeTest {

    @Test
    void stringToJsonNode() throws Exception {
        String jsonSTRING = """
                {
                "id" : 1,
                "amount" : 7534675.43,
                "status" : "SUCCESS",
                "createdAt" : "2019-03-27T10:15:30",
                "updatedAt" : "2022-03-14T11:05:20"
                }
                """;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonSTRING);

        JsonNode amountJsonNode = node.get("amount");
        JsonNode statusJsonNode = node.get("status");

        double amount = amountJsonNode.asDouble();
        String status = statusJsonNode.asText();

        System.out.println("amount = " + amount);
        System.out.println("status = " + status);

    }
}