package immutable;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class PersonInXmlFormatTest {
    @Test
    void personToXml() throws IOException {
        File file = new File("data/person.xml");
        Person person = new Person("Laziz", "Djuraev", 20);

        person.writeToXMLFile(file.getPath());
    }

    @Test
    void xmlToPerson() throws IOException {
        File file = new File("data/person.xml");
        Person person = Person.readFromXMLFile(file.getPath());

        System.out.println(person);
    }
}
