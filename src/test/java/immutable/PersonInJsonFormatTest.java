package immutable;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class PersonInJsonFormatTest {

    @Test
    void personToJson() throws IOException {
        File file = new File("data/person.json");
        Person person = new Person("Laziz", "Djuraev", 20);

        person.writeToJSONFile(file.getPath());
    }

    @Test
    void jsonToPerson() throws IOException {
        File file = new File("data/person.json");
        Person person = Person.readFromJSONFile(file.getPath());

        System.out.println(person);
    }
}
